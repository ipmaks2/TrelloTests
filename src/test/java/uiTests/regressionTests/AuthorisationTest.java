package uiTests.regressionTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.pages.MainPage;
import ui.pages.logination.AtlassianPage;
import ui.pages.logination.LoginPage;
import ui.pages.logination.StartPage;
import api.utils.ConfProperties;

import static com.codeborne.selenide.Selenide.open;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ScreenShooterExtension.class)
public class AuthorisationTest {


    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize="1920x1080";
        System.setProperty("webdriver.chrome.driver", "C:/Users/Violeta_B/bin/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ru");    //en-US
//        WebDriver driver = new ChromeDriver(chromeOptions);
//        driver.manage().window().maximize();
//        driver.get("https://google.com");
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Проверка корректного процесса авторизации на сайте")
    @Story(value = "Установка языка, выбор типа входа и ввод данных пользователя")
    @Description("Проверка процесса авторизации. Ввод корректных имени пользователя и пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Order(1)
    @Test
    public void Authorisation() {
        StartPage startPage = open("https://trello.com", StartPage.class);
        LoginPage loginPage = startPage.loginButtonClick().setRusLanguage();
        AtlassianPage atlassianPage = loginPage.ifLoginPage()
                .enterTextToLoginString("email", ConfProperties.getProperty("email"))
                .enterTextToLoginString("password", ConfProperties.getProperty("password"))
                .loginButtonClick("login");
        atlassianPage.enterTextToLoginString(ConfProperties.getProperty("password"));

        MainPage mainPage = atlassianPage.loginButtonClick()
                       .getMainPage();
        Assertions.assertTrue(mainPage.ifMainPage(), "The main page was not found");
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Проверка корректного процесса авторизации на сайте")
    @Story(value = "Проверка данных пользователя после авторизации")
    @Description("Проверка данных пользователя после авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    @Test
    @Owner(value = "Violetta Basina")
    public void AuthorizationCheck() {
        MainPage mainPage = new MainPage();
        Assertions.assertTrue(mainPage.authorizationCheck(ConfProperties.getProperty("user"), ConfProperties.getProperty("email")),
                "The username or e-mail does not match.");
    }

    @Epic(value = "Авторизация")
    @Feature(value = "Проверка выхода из учетной записи")
    @Story(value = "Проверка выхода из учетной записи")
    @Description("Проверка выхода из учетной записи")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @CsvSource({"https://trello.com/home"})
    public void AuthorizationOut(String homePage) {
        MainPage mainPage = new MainPage();
        StartPage startPage = mainPage.logOut().getStartPage();

        Assertions.assertTrue(startPage.ifStartPage(homePage),
                "Logout failed.");
    }
}
