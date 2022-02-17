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
public class MenuBoardSectionTest extends BaseTest {

    private final MainPage mainPage = new MainPage();

    static Stream<Arguments> dataForCheckOfLeftMenu() {
        return Stream.of(
                Arguments.of("boardsMenu",
                        Arrays.asList("Доски", "Таблица рабочего пространства", "Участники", "Настройки"),
                        "List of boards does not match"),
                Arguments.of("boardsHeader",
                        Arrays.asList("Рабочее пространство Trello"),
                        "Header of boards does not match"),
                Arguments.of("boardsMainHeader",
                        Arrays.asList("ВАШИ РАБОЧИЕ ПРОСТРАНСТВА"),
                        "Header of boards does not match"),
                Arguments.of("boardsButton",
                        Arrays.asList("Повысить", "Посмотреть закрытые доски"),
                        "List of buttons of Top menu does not match"),
                Arguments.of("boards",
                        Arrays.asList("Создать доску"),
                        "List of buttons of Top menu does not match")
        );
    }

    @Epic(value = "Внешний вид")
    @Feature(value = "Секция досок")
    @Story(value = "Наличие досок и возмоности создания новой доски")
    @Description("Проверка внешнего вида секции, в которой располагаются все доски пользователя")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @MethodSource("dataForCheckOfLeftMenu")
    public void testTopMenu(String typeOfSearch,
                            List<String> listForCheck,
                            String errorMessage){

        //top menu block: checking existing and list of elements
        MenuCheckObject topMenuList = mainPage.getExistsItem(typeOfSearch);
        System.out.println (topMenuList.getFoundList().toString());

        Assertions.assertTrue(topMenuList.isExist(), "The item was not found");
        Assertions.assertTrue(LogUtils.compareItemsCheck(topMenuList.getFoundList(), listForCheck), errorMessage);
    }
}
