package ui.pages.logination;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleLoginPage {

    private final SelenideElement emailInput = $(By.name("identifier"));
    private final SelenideElement nextButton = $(By.id("identifierNext"));

    @Step("Нажатие на подтвеждающую действие кнопку")
    public MainPage enterTextToInputString(String text) {
                emailInput.setValue(text);
        return page(MainPage.class);
    }
}
