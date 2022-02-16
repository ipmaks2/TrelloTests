package uiTests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.params.provider.ValueSource;
import ui.pages.BoardPage;
import uiTests.BaseTest;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.MainPage;

@ExtendWith(ScreenShooterExtension.class)
public class BoardDeleteTest extends BaseTest {

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия с досками")
    @Story(value = "Закрытие доски")
    @Description("Закрытие доски с заданным именем")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @Flaky
    @ValueSource(strings = {"MyTemplateBoard Agile Board", "MyGreenBoard"}) //MyTemplateBoard Agile Board,
    public void testNewBoardCreation(String boardName) {
        String boardMenuItem = "Доски";
        MainPage mainPage = new MainPage();
        int startBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
        BoardPage boardPage = mainPage.openBoardPage(boardName);
        System.out.println("boardPage:" + boardPage);

        if (boardPage != null) {
            boardPage.closeBoardPage().closeBoard();

            // new count of boards on the main paige
            int endBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
            Assertions.assertEquals(1, (startBoardCount - endBoardCount), "Count of boards on the main page does not match");
        } else {
            System.out.println("The board " + boardName + " was not found");
        }

    }
}
