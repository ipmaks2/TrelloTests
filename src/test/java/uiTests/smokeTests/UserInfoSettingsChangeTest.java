package uiTests.smokeTests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;
import uiTests.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.MainPage;
import ui.pages.userProfile.SettingsPage;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(ScreenShooterExtension.class)
public class UserInfoSettingsChangeTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final SettingsPage settingsPage = mainPage.changeUserSettings();

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия настройками пользователя")
    @Story(value = "Изменение согласия получения маркетинговых сообщений")
    @Description("Проверка возможности изменения согласия получения маркетинговых сообщений (согласен или не согласен)")
    @Severity(SeverityLevel.MINOR)
    @RepeatedTest(2)
    @Owner(value = "Violetta Basina")
    public void changeUserInfoNotification() {
        Assertions.assertAll("User profile",
                () -> Assertions.assertTrue(settingsPage.changeMarketingNotification()),
                () -> Assertions.assertTrue(settingsPage.changeRecommendation())
        );
    }

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия настройками пользователя")
    @Story(value = "Изменение частоты получения информационных e-mail сообщений")
    @Description("Проверка возможности изменения частоты получения информационных  e-mail сообщений (Никогда, Мгновенно, Один раз в час)")
    @Severity(SeverityLevel.MINOR)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @CsvSource({"Никогда"})
    public void changeEmailNotificationType(String emailNotificationInput) {
        Map<String, String> emailNotificationType = new HashMap<>();
        {
            emailNotificationType.put("Никогда", "-1");
            emailNotificationType.put("Периодически", "60");
            emailNotificationType.put("Мгновенно", "1");
        }

        Assertions.assertEquals(emailNotificationType.get(emailNotificationInput),
                settingsPage.changeEmailNotificationType(emailNotificationType.get(emailNotificationInput)));
    }
}

