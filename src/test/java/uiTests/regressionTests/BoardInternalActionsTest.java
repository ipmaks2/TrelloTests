package uiTests.regressionTests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;
import uiTests.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.BoardPage;
import ui.pages.MainPage;

@ExtendWith(ScreenShooterExtension.class)
public class BoardInternalActionsTest extends BaseTest {

    @Epic(value = "Действия пользователя")
    @Feature(value = "Действия с досками")
    @Story(value = "Создание новых колонок и задач")
    @Description("Создание новой колонки, задачи, добавление описания")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @CsvSource({"MyTestBoard, To do, task first, Первая задача для выполнения, Комментарий"})
    public void createNewListTest(String boardName, String listName, String cardName, String descriptionText, String commentText){
        MainPage mainPage = new MainPage();
        BoardPage boardPage = mainPage.openBoardPage(boardName);
        boardPage.ifBoardPage(boardName);
        boardPage.addNewList(listName);
        boardPage.addNewCard(cardName);
        boardPage.addCardDescription(descriptionText);
        boardPage.addCardComment(commentText);
    }

//    @ParameterizedTest
//    @CsvSource({"MyTestBoard, To do, task first, Первая задача для выполнения, Комментарий"})
//    public void addNewListTest(String boardName, String listName, String cardName, String descriptionText, String commentText){
//        MainPage mainPage = new MainPage();
//        BoardPage boardPage = mainPage.openBoardPage(boardName);
//        boardPage.ifBoardPage(boardName);
//        boardPage.addNewList(listName);
//        boardPage.addNewCard(cardName);
//        boardPage.addCardDescription(descriptionText);
//        boardPage.addCardComment(commentText);
//    }
}
