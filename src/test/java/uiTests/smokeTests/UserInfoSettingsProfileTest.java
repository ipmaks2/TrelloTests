package uiTests.smokeTests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uiTests.BaseTest;
import org.junit.jupiter.api.*;
import ui.pages.MainPage;
import ui.pages.userProfile.ProfilePage;

@ExtendWith(ScreenShooterExtension.class)
public class UserInfoSettingsProfileTest extends BaseTest {

    private MainPage mainPage = new MainPage();

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия настройками пользователя")
    @Story(value = "Изменение в профиле пользователя")
    @Description("Проверка возможности изменения информации 'О себе'")
    @Severity(SeverityLevel.MINOR)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @CsvSource({"Interested in becoming an automator"})
    public void changeUserInfoProfile(String informationString) {
        ProfilePage profilePage = mainPage.changeUserProfile();
        Assertions.assertEquals("Interested in becoming an automator", profilePage.setUserDescription(informationString));
        profilePage.returnToMain();
    }
}

