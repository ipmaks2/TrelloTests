package uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.List;
import java.util.logging.Level;
import ui.pages.logination.AtlassianPage;
import api.utils.ConfProperties;

import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith(ScreenShooterExtension.class)
public class BaseTest {

    public static String baseUrl = "https://trello.com";
    public static String baseAuthorizedUrl = "https://id.atlassian.com/login?application=trello&continue=https%3A%2F%2Ftrello.com%2Fauth%2Fatlassian%2Fcallback%3FreturnUrl%3D%252F%26display%3D%26aaOnboarding%3D%26updateEmail%3D%26traceId%3D620a9100aa98c20bc528c10f2e3ca8f3%26migrateGoogle%3D%26ssoVerified%3D&email=mishaZninja%40gmail.com&errorCode&login_hint=mishaZninja%40gmail.com&restrict=true";

    @BeforeAll
    public static void setUp() {


        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        LoggingPreferences loggingPrefs = new LoggingPreferences();
        loggingPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", loggingPrefs);
        System.setProperty("webdriver.chrome.driver", "../downloads/chromedriver");

        AtlassianPage atlassianPage = open(baseAuthorizedUrl, AtlassianPage.class);
        atlassianPage.enterTextToLoginString(ConfProperties.getProperty("password"));

        atlassianPage.loginButtonClick().getMainPage();
    }

    public static List<String> getAvailableLogEntries(){
        return getWebDriverLogs(LogType.PERFORMANCE, Level.ALL);
    }

    @AfterAll
    public static void TeardownLogs(){
        for (String line: getAvailableLogEntries()){
            if (line.contains("Network.requestWillBeSent") && !line.contains("ExtraInfo") && line.contains("api")) {
                System.out.println(line);
            }
        }

    }
}
