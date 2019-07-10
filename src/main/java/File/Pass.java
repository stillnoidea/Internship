package File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static main.MyHttpRequest.VALIDITY_STATUS_NOT_STARTED;

public class Pass implements Comparable<Pass> {
    private static final String DATE_SPLITTER = "T";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String VALIDITY_STATUS_NOT_VALID = "NOT_VALID";
    private String login;
    private String password;
    private String validityStatus;
    private Date activationDate;
    private SimpleDateFormat simple_date = new SimpleDateFormat(DATE_FORMAT);

    public Pass(String log, String pas) {
        login = log;
        password = pas;
        validityStatus = null;
        activationDate = null;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordLastChars() {
        int size = password.length();
        if (size > 2) {
            return String.valueOf(password.charAt(size - 3)) +
                    password.charAt(size - 2) +
                    password.charAt(size - 1);
        } else return "";
    }

    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
    }

    public Date getActivationDate() {
        return activationDate;
    }
    public String getActivationDateString(){
        return simple_date.format(activationDate);
    }

    public void setActivationDate(String activationDate) {
        if (activationDate != null) {

            String[] list = activationDate.split(DATE_SPLITTER);
            Date result = null;
            String date = list[0] + " " + list[1];

            try {
                result = simple_date.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.activationDate = result;
        }
    }

    @Override
    public int compareTo(Pass o) {
        int result = 0;
        if (this.validityStatus.equals(o.validityStatus)) {
            if (this.validityStatus.equals(VALIDITY_STATUS_NOT_STARTED)) {
                result = activationDate.compareTo(o.activationDate);
            }
        } else {
            if (validityStatus.equals(VALIDITY_STATUS_NOT_STARTED) || (validityStatus.equals(VALIDITY_STATUS_NOT_VALID) && !o.validityStatus.equals(VALIDITY_STATUS_NOT_STARTED))) {
                result = -1;
            } else result = 1;
        }
        return result;
    }
}
