package ui.pages.logination;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class AtlassianPage {
    private final SelenideElement passwordInput = $(By.id("password"));
    private final SelenideElement submitButton = $(By.id("login-submit"));
    private final SelenideElement hiddenButton = $(By.xpath("//span[@role='presentation']"));

    @Step("Ввод данных пользователя")
    public AtlassianPage enterTextToLoginString(String text) {
        hiddenButton.hover();
        while (passwordInput.getValue().compareTo(text) != 0) {
                     passwordInput.setValue(text);
        }
        hiddenButton.click();
        return this;
    }

    @Step("Нажатие на подтвеждающую действие кнопку")
    public AtlassianAddPage loginButtonClick() {
        submitButton.click();
        return page(AtlassianAddPage.class);
    }
}
