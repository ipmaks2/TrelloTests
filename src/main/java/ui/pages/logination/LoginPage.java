package ui.pages.logination;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement language = $(By.id("language-picker"));
    private final SelenideElement insertLoginPageCheck = $(By.xpath("//h1[contains(text(),'Вход в Trello')]"));
    private final SelenideElement emailInput = $(By.id("user"));
    private final SelenideElement passwordInput = $(By.id("password"));
    private final SelenideElement loginButton = $(By.id("login"));
    private final SelenideElement ssoButton = $(By.id("use-sso-button"));
    private final SelenideElement googleButton = $(By.id("googleButton"));
    private final SelenideElement msButton = $(By.id("msftButton"));
    private final SelenideElement appleButton = $(By.id("signInWithAppleButton"));
    private final SelenideElement errorMessage = $(By.xpath("//p[@class='error-message']"));

    @Step("Проверка нахождения на нужной странице")
    public LoginPage ifLoginPage() {
        try {
            insertLoginPageCheck.exists();
        }
        catch (Exception e) {
            System.out.println("Login page does not found. " + e.getMessage());
        }
        return this;
    }

    @Step("Выбор типа авторизаци")
    public AtlassianPage loginButtonClick(String loginAction) {
        switch (loginAction){
            case "Google":
                googleButton.click();
                break;
            case "Microsoft":
                msButton.click();
                break;
            case "Apple":
                appleButton.click();
                break;
            default: loginButton.click();
        }
        return Selenide.page(AtlassianPage.class);
    }

    @Step("Ошибка")
    public boolean isErrorMessage() {
        return errorMessage.exists();
    }

    @Step("Нажатие на подтвеждающую действие кнопку")
    public LoginPage ssoClick() {
        if (loginButton.getValue().equals("Войти")) {
            ssoButton.click();
        }
        return this;
    }

    @Step("Ввод данных пользователя")
    public LoginPage enterTextToLoginString(String inputElement, String text) {
        switch (inputElement) {
            case "email":
                emailInput.setValue(text);
                break;
            case "password":
                passwordInput.setValue(text);
                break;
        }
        return this;
    }

    @Step("Выбор языка интерфейа")
    public LoginPage setRusLanguage() {
        if (!language.getValue().equals("ru")){
            language.selectOptionByValue("ru");
        }
        return this;
    }
}
