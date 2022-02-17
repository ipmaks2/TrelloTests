package uiTests.smokeTests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.pages.MainPage;
import ui.utils.LogUtils;
import api.pojo.MenuCheckObject;
import uiTests.BaseTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(ScreenShooterExtension.class)
public class MenuLeftSectionTest extends BaseTest {

    private final MainPage mainPage = new MainPage();

    static Stream<Arguments> dataForCheckOfLeftMenu() {
        return Stream.of(
                Arguments.of("left",
                        Arrays.asList("Доски", "Шаблоны", "Главная страница",
                        "Рабочее пространство Trello", "Доски", "Важные события", "Таблица рабочего пространства", "Участники", "Настройки"),
                        "List of left menu buttons does not match"),
                Arguments.of("leftButtonStart",
                        Arrays.asList("Начать пробный бесплатный период"),
                        "The start button does not found"),
                Arguments.of("leftButtonWorkSpace",
                        Arrays.asList("Создайте рабочее пространство"),
                        "The workspace creation button was not found"),
                Arguments.of("leftButtonClose",
                        Arrays.asList("CloseIcon"),
                        "The close button does not found")
        );
    }

    @Epic(value = "Внешний вид")
    @Feature(value = "Секция левого меню")
    @Story(value = "Наличие кнопок и пунктов меню")
    @Description("Проверка наличия всех кнопок и пунктов в секции левого меню")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @MethodSource("dataForCheckOfLeftMenu")
    public void testTopMenu(String typeOfSearch,
                            List<String> listForCheck,
                            String errorMessage) {

        //top menu block: checking existing and list of elements
        MenuCheckObject topMenuList = mainPage.getExistsItem(typeOfSearch);
        System.out.println (topMenuList.getFoundList().toString());

        Assertions.assertTrue(topMenuList.isExist(), "The item was not found");
        Assertions.assertTrue(LogUtils.compareItemsCheck(topMenuList.getFoundList(), listForCheck), errorMessage);
    }
}
