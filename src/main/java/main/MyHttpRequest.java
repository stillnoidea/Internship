package main;

import File.Pass;
import org.javatuples.Pair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class MyHttpRequest {
    private static final String DEFAULT_HTTP = "https://eurail-inspection.ticketingsuite.com/api/eiw/ticket/";
    private static final String TENAT = "TENANT";
    private static final String EURAIL = "eurail";
    private static final String VALIDITY_STATE = "validityState";
    private static final String ACTIVATION_DATE = "activationDate";
    private static final String ERROR = "error: ";
    public static final String VALIDITY_STATUS_NOT_STARTED = "NOT_STARTED";

    private static HttpClient client;
    private String http = "";
    private Pass pass;


    MyHttpRequest() {
        pass = new Pass("", "");
        client = HttpClient.newBuilder().build();
    }

    MyHttpRequest(Pass pas) {
        pass = pas;
        updateHttp();
        client = HttpClient.newBuilder().build();
    }

    private HttpRequest makeRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(http))
                .header(TENAT, EURAIL)
                .build();
    }

    public Pair<String, Integer> getAsync() {
        Pair<String, Integer> request_response = null;
        HttpRequest request = makeRequest();

        CompletableFuture<Pair<String, Integer>> resp = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> new Pair<>(response.body(), response.statusCode()));

        try {
            request_response = new Pair<>(resp.get().getValue0(), resp.get().getValue1());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return request_response;
    }

    public Pair<String, Integer> getSynch() {
        String response_body = "";
        int response_code = 0;
        HttpRequest request = makeRequest();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            response_body = response.body();
            response_code = response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Pair<>(response_body, response_code);
    }


    public void assignValidityState(Pair<String, Integer> pair) {
        String result = "";

        if (pair.getValue1() > 399) {
            result = ERROR + pair.getValue1();
        } else {
            JSONParser parser = new JSONParser();

            try {
                JSONObject json = (JSONObject) parser.parse(pair.getValue0());
                result = json.get(VALIDITY_STATE).toString();

                if (result.equals(VALIDITY_STATUS_NOT_STARTED)) {
                    String date = json.get(ACTIVATION_DATE).toString();
                    pass.setActivationDate(date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        pass.setValidityStatus(result);
    }

    public String getHttp() {
        return http;
    }

    private void setHttpDefault() {
        http = DEFAULT_HTTP;
    }

    public void changePass(Pass p) {
        pass = p;
        updateHttp();
    }

    private void updateHttp() {
        if (http.length() != DEFAULT_HTTP.length()) {
            setHttpDefault();
        }
        http += pass.getLogin() + "/" + pass.getPasswordLastChars();

    }

}
