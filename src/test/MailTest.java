package test;

import main.MyWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

class MailTest {
    private static final String HTTP_GMAIL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    private static final String LOGIN_GMAIL = "stillnoidea00@gmail.com";
    private static final String PASSWORD_GMAIL = "AniaMaKota123";
    private static final String RECEIVER_GMAIL = "daria.krupinska@globallogic.com";
    private static final String MAIL_SUBJECT = "Trolololo";
    //TODO change the attachment file path, so the program work properly
    private static final String MAIL_ATTACHMENT = "YOUR FILE PATH e.g. C:\\file.csv";
    private static final String PAGE_LOGIN_INPUT = "identifierId";
    private static final String PAGE_NEXT_BUTTON = "identifierNext";
    private static final String PAGE_PASSWORD_INPUT = "password";
    private static final String PAGE_LOG_IN_BUTTON = "//*[@id=\"passwordNext\"]";
    private static final String PAGE_COMPOSE_BUTTON = "//*[@role='button' and text()='Compose']";
    private static final String PAGE_SEND_BUTTON = "//*[@role='button' and text()='Send']";
    private static final String PAGE_MAIL_RECEIVER_INPUT = "//*[@name='to']";
    private static final String PAGE_MAIL_SUBJECT_INPUT = "//*[@name='subjectbox' and @class='aoT']";
    private static final String PAGE_ATTACHMENT_INPUT = "//*[@name='Filedata']";
    private static final String HTTP_TROLOLOLO = "https://www.google.com/search?q=trolololo";
    private static final String PAGE_RESULTS = "//div[@class='r']/a [not(@class)]";
    private static final String ARGUMENTS_NAME = "href";
    private static final String DRIVER = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "C:\\Program Files\\ChromeDriver\\chromedriver.exe";
    private List<String> links;


    static Stream<WebDriver> setUp() {

        System.setProperty(DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        return Stream.of(driver);
    }


    @ParameterizedTest
    @MethodSource("setUp")
    void logToGmail(WebDriver driver) {
        driver.get(HTTP_GMAIL);

        driver.findElement(By.id(PAGE_LOGIN_INPUT)).sendKeys(LOGIN_GMAIL);
        driver.findElement(By.id(PAGE_NEXT_BUTTON)).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(PAGE_LOG_IN_BUTTON)));

        driver.findElement(By.name(PAGE_PASSWORD_INPUT)).sendKeys(PASSWORD_GMAIL);
        driver.findElement(By.xpath(PAGE_LOG_IN_BUTTON)).click();
    }

    @ParameterizedTest
    @MethodSource("setUp")
    void composeMail(WebDriver driver) {
        logToGmail(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(PAGE_COMPOSE_BUTTON)).click();

        driver.findElement(By.xpath(PAGE_MAIL_RECEIVER_INPUT)).sendKeys(RECEIVER_GMAIL);
        driver.findElement(By.xpath(PAGE_MAIL_SUBJECT_INPUT)).sendKeys(MAIL_SUBJECT);

        driver.findElement(By.xpath(PAGE_ATTACHMENT_INPUT)).sendKeys(MAIL_ATTACHMENT);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @ParameterizedTest
    @MethodSource("setUp")
    void getLinksFromGoogleSearch(WebDriver driver) {
        links = new ArrayList<>();
        driver.get(HTTP_TROLOLOLO);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> list = driver.findElements(By.xpath(PAGE_RESULTS));

        for (WebElement item : list) {
            links.add(item.getAttribute(ARGUMENTS_NAME));
        }
        Assertions.assertTrue(links.size() > 0);

    }

    @ParameterizedTest
    @MethodSource("setUp")
    void sendMailLinks(WebDriver driver) {
        getLinksFromGoogleSearch(driver);
        MyWriter.writeCSV(links);
        composeMail(driver);
        driver.findElement(By.xpath(PAGE_SEND_BUTTON)).click();
    }

    @ParameterizedTest
    @MethodSource("setUp")
    void sendMailTickets(WebDriver driver) {
        composeMail(driver);
        driver.findElement(By.xpath(PAGE_SEND_BUTTON)).click();
        driver.quit();
    }

}