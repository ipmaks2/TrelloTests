package stepdefs.pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import ui.pages.MainPage;
import ui.pages.logination.AtlassianAddPage;
import ui.pages.logination.AtlassianPage;
import ui.pages.logination.LoginPage;
import ui.pages.logination.StartPage;
import api.utils.ConfProperties;

public class AuthorizationStepdefs {
    private final StartPage startPage = new StartPage();
    private final LoginPage loginPage = new LoginPage();
    private final AtlassianPage atlassianPage = new AtlassianPage();
    private final AtlassianAddPage atlassianAddPage = new AtlassianAddPage();
    private final MainPage mainPage = new MainPage();
    private final String finishURL = "https://trello.com/home";

    @Given("start Trello page is open")
    public void startTrelloPageIsOpen() {
        startPage.setRusLanguage().loginButtonClick();
    }

    @When("user insert the RUS language and clicks the logIn button")
    public void userInsertTheRUSLanguageAndClicksTheLogInButton() {
        startPage.loginButtonClick();//.setRusLanguage();
    }

    @Then("user inserts his or her e-mail and password and clicks enter button")
    public void userInsertsHisHerEMailAndPasswordAndClicksEnterButton() {
        loginPage.ifLoginPage()
                .enterTextToLoginString("email", ConfProperties.getProperty("email"))
                .enterTextToLoginString("password", ConfProperties.getProperty("password"))
                .loginButtonClick("login");
    }

    @Then("user confirms his password and clicks enter button")
    public void userConfirmsHisPasswordAndClicksEnterButton() {
        atlassianPage.enterTextToLoginString(ConfProperties.getProperty("password"));
        atlassianPage.loginButtonClick();
        atlassianAddPage.getMainPage();
    }

    @Then("user checks his or her user information")
    public void userChecksHisOrHerUserInformation() {
        Assertions.assertTrue(mainPage.authorizationCheck(ConfProperties.getProperty("user"), ConfProperties.getProperty("email")),
                "The username or e-mail does not match.");
    }

    @And("user logs out of authorization")
    public void userLogsOutOfAuthorization() {
        mainPage.logOut().getStartPage();
        Assertions.assertTrue(startPage.ifStartPage(finishURL),
                "Logout failed.");
    }
}
