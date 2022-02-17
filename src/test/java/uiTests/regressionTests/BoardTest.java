package uiTests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;
import uiTests.BaseTest;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.pages.BoardPage;
import ui.pages.MainPage;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ScreenShooterExtension.class)
public class BoardTest extends BaseTest {

    private final String mainPageCheck = "ВАШИ РАБОЧИЕ ПРОСТРАНСТВА";
    private MainPage mainPage = new MainPage();

    static Stream<Arguments> dataForCheckOfNewBoardCreation() {
        return Stream.of(
                //Синий, Оранжевый, Зелёный, Красный, Фиолетовый, color = 0 -> random selection
                Arguments.of("MyBoardWithPictures", "pictures", "0"),
                Arguments.of("MyVioletBoard", "color", "Фиолетовый")
        );
    }

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия с досками")
    @Story(value = "Создание новых досок с заданным именем и фоном")
    @Description("Создание новых досок с цветным фоном или фоном в виде шаблонной картинки из списка")
    @Severity(SeverityLevel.NORMAL)
    @Order(1)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @MethodSource("dataForCheckOfNewBoardCreation")
    public void testNewBoardCreation(String boardName, String boardViewType, String color) {
//        String boardMenuItem = "Доски";
//     //   MainPage mainPage = new MainPage();
//        int startBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();

        // create new board
        BoardPage boardPage = mainPage
                .createNewBoard(boardName, boardViewType, color);
        Assertions.assertTrue(boardPage.ifBoardPage(boardName), "The board was not created");

        //if return to main page check
        boardPage.returnToMain();
        Assertions.assertTrue(mainPage.ifMainPage(), "The main page was not found");

        // new count of boards on the main paige
   //     int endBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
        Assertions.assertTrue(mainPage.ifBoardPageExist(boardName), "New board name was not found");
    }

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия с досками")
    @Story(value = "Создание новых досок из шаблона")
    @Description("Создание новых досок по заданному шаблону с готовыми колонками")
    @Severity(SeverityLevel.NORMAL)
    @Order(2)
    @Test
    @Owner(value = "Violetta Basina")
    public void testNewTemplateBoardCreation() {
        String boardName = "MyTemplateBoard";
        String boardTemplate = "Design Huddle";
   ///     MainPage mainPage = new MainPage();
        BoardPage boardPage = mainPage
                .createNewTemplateBoard(boardName, boardTemplate);

        Assertions.assertTrue(boardPage.ifBoardPage(boardName), "The board was not created");

        mainPage = boardPage.returnToMain();
        Assertions.assertTrue(mainPage.ifMainPage(), "The main page was not found");

        // new count of boards on the main paige
        Assertions.assertTrue(mainPage.ifBoardPageExist(boardName), "New board name was not found");
    }

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия с досками")
    @Story(value = "Закрытие доски")
    @Description("Закрытие доски с заданным именем")
    @Severity(SeverityLevel.NORMAL)
    @Order(3)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @Flaky
    @ValueSource(strings = {"MyTemplateBoard", "MyVioletBoard", "MyBoardWithPictures"})
    public void testDeleteBoard(String boardName) {
        String boardMenuItem = "Доски";
        MainPage mainPage = new MainPage();
        int startBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
        BoardPage boardPage = mainPage.openBoardPage(boardName);

        if (boardPage != null) {
            boardPage.closeBoardPage().closeBoard();

            // new count of boards on the main paige
            int endBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
            Assertions.assertTrue(!mainPage.ifBoardPageExist(boardName), "New board name was found");
        //    Assertions.assertEquals(1, (startBoardCount - endBoardCount), "Count of boards on the main page does not match");
        } else {
            System.out.println("The board " + boardName + " was not found");
        }
    }
}
