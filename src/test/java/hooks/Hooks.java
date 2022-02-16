package hooks;

//import com.codeborne.selenide.Selenide;
//import eyes.Eyes;
//import io.cucumber.core.backend.TestCaseState;
//import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ui.pages.logination.StartPage;
//import io.cucumber.plugin.event.Result;
//import io.cucumber.plugin.event.Status;
//import io.qameta.allure.Allure;
//import utils.Logger;

import static com.codeborne.selenide.Selenide.open;

public class Hooks {
    private static final String BASE_URL = "https://trello.com";

    @Before
    public static void openBrowser(Scenario scenario){
        StartPage startPage = open(BASE_URL, StartPage.class);
    }
}
