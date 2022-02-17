package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.enums.PageValues;
import ui.pages.logination.AtlassianAddPage;
import ui.pages.userProfile.ProfilePage;
import ui.pages.userProfile.SettingsPage;
import ui.utils.LogUtils;
import api.pojo.MenuCheckObject;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class MainPage {
    private final SelenideElement mainPageCheckH3 = $(By.xpath("//h3[@class='boards-page-section-header-name']"));

    //Authorization
    private final SelenideElement userInfoButton = $(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    private final SelenideElement userInfoMenu = $(By.xpath("//section[@data-test-id='header-member-menu-popover']"));
    private final SelenideElement userLogout = $(By.xpath("//button[@data-test-id='header-member-menu-logout']"));
    private final SelenideElement userLogoutSubmit = $(By.id("logout-submit"));
    private final SelenideElement userLogoutExit = $(By.xpath("//button[@data-test-id='popover-close']"));

    //Change profile
    private final SelenideElement changeUserMenuProfile = $(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
    private final SelenideElement changeUserMenuSettings = $(By.xpath("//a[@data-test-id='header-member-menu-settings']"));

    //Header
    private final SelenideElement topNavigatorOptionLink = $(By.id("header"));
    private final SelenideElement topNavigatorMenuLink = $(By.xpath("//div[@id='header']//button[@type='button']"));
    private final ElementsCollection topNavigatorMenu = $$(By.xpath("//div[@id='header']//button[@type='button']"));
    private final ElementsCollection topNavigatorDropdownMenu = $$(By.xpath("//div[@id='header']//button[@type='button']//span"));

    private final ElementsCollection headerInfoSectionButton = $$(By.xpath("//button[contains(@data-test-id,'header')]//span"));
    private final SelenideElement headerInfoSectionLink = $(By.xpath("//button[contains(@data-test-id,'header')]"));
    private final SelenideElement searchLink = $(By.xpath("//*[@type='search']"));
    private final ElementsCollection search = $$(By.xpath("//*[@type='search']"));

    //Left sidebar container
    private final SelenideElement leftSidebarContainerLink = $(By.xpath("//*[@class='home-left-sidebar-container']"));
    private final ElementsCollection leftSidebarContainerMenu = $$(By.xpath("//*[@class='home-left-sidebar-container']//a"));

    private final SelenideElement leftSidebarButtonLink = $(By.xpath("//nav[@class='home-left-sidebar-container']//button"));
    private final ElementsCollection leftSidebarButton = $$(By.xpath("//nav[@class='home-left-sidebar-container']//button"));

    private final SelenideElement leftSidebarButtonCloseLink = $(By.xpath("//nav[@class='home-left-sidebar-container']//button//span"));
    private final ElementsCollection leftSidebarButtonClose = $$(By.xpath("//nav[@class='home-left-sidebar-container']//button//span"));

    //Board Section
    private final SelenideElement boardHeaderLink = $(By.xpath("//h3[@class='boards-page-section-header-name']"));
    private final ElementsCollection boardHeader = $$(By.xpath("//h3[@class='boards-page-section-header-name']"));

    private final SelenideElement boardSectionHeaderLink = $(By.xpath("//h3[@class='boards-page-board-section-header-name']"));
    private final ElementsCollection boardSectionHeader = $$(By.xpath("//h3[@class='boards-page-board-section-header-name']"));

    private final SelenideElement boardSectionHeaderOptionsItemLink = $(By.xpath("//*[@class='boards-page-board-section-header-options-item-name']"));
    private final ElementsCollection boardSectionHeaderOptionsItem = $$(By.xpath("//*[@class='boards-page-board-section-header-options-item-name']"));

    private final ElementsCollection boardSelectionListItems = $$(By.xpath("//li[@class='boards-page-board-section-list-item']"));
    private String boardItem = "//div[contains(text(),'";
    private final SelenideElement boardSectionButtonLink = $(By.xpath("//div[@class='all-boards']//button"));
    private final ElementsCollection boardSectionButton = $$(By.xpath("//div[@class='all-boards']//button"));

    private final SelenideElement boardCreateLink = $(By.xpath("//li[@data-test-id='create-board-tile']"));
    private final ElementsCollection boardCreate = $$(By.xpath("//li[@data-test-id='create-board-tile']"));

    // choose of parameters for new board
    private final SelenideElement newBoardTitleInput = $(By.xpath("//input[@data-test-id='create-board-title-input']"));
    private ElementsCollection newBoardView;
    private String newBoardViewLocator = "//div[@id='background-picker']//ul";
    private final SelenideElement newBoardButton = $(By.xpath("//*[@data-test-id='create-board-submit-button']"));

    // new board template
    private final SelenideElement newTemplateBoardButton = $(By.xpath("//*[@data-test-id='create-from-template-button']"));
    private final SelenideElement newTemplateSelectionTitle = $(By.xpath("//div[contains(@title,'Создать по шаблону')]"));

    @Step("Проверка нахождения на нужной странице")
    public boolean ifMainPage() {
        try {
            System.out.println("url: " + url());
            return mainPageCheckH3.text().equals(PageValues.MAIN_PAIGE_TITLE);
        } catch (Exception e) {
            System.out.println("Main page was not found. " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка данных пользователя")
    public boolean authorizationCheck(String user, String email) {
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);

            boolean result = userInfoMenu.findElement(By.xpath("//div[contains(text()," + user + ")]"))
                    .isEnabled()
                    || userInfoMenu.findElement(By.xpath("//span[contains(text()," + email + ")]"))
                    .isEnabled();
            userLogoutExit.click();
            return result;
        } catch (Exception e) {
            System.out.println("Authorization was failed. " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка данных пользователя")
    public ProfilePage changeUserProfile() {
        //Профиль и доступ
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);
            changeUserMenuProfile.click();
        } catch (Exception e) {
            System.out.println("Changing user profile was failed. " + e.getMessage());
        }
        return page(ProfilePage.class);
    }

    @Step("Изменение данных пользователя")
    public SettingsPage changeUserSettings() {
        //Профиль и доступ
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);
            changeUserMenuSettings.click();
        } catch (Exception e) {
            System.out.println("Changing user settings was failed. " + e.getMessage());
        }
        return page(SettingsPage.class);
    }

    @Step("Выход из профиля")
    public AtlassianAddPage logOut() {
        try {
            userInfoButton.hover().click();
            userLogout.click();
            userLogoutSubmit.click();
        } catch (Exception e) {
            System.out.println("Authorization was failed. " + e.getMessage());
        }
        return page(AtlassianAddPage.class);
    }

    @Step("Провевка левого меню")
    public MainPage leftMenuPointClick(String pointOfMenu) {
        leftSidebarContainerMenu.find(text(pointOfMenu)).click();
        return this;
    }

    @Step("Получить количество досок")
    public int getBoardCount() {
        return boardSelectionListItems.size();
    }

    @Step("Проверка наименования доски")
    public boolean ifBoardPageExist(String forCheck) {
        try {
            return ($(By.xpath(boardItem + forCheck + "')]")).exists());
        } catch (Exception e) {
            System.out.println("Main page was not found. " + e.getMessage());
            return false;
        }
    }

    /*
     * Checking the items of navigation menu. Getting names of sections by condition:
     * allLeft - all left option items;
     * drop-down - only drop-down items;
     * button - only buttons
     * search - search section
     */
    @Step("Провевка навигационного меню")
    public MenuCheckObject getExistsItem(String forSearch) {
        MenuCheckObject resultFromPage = null;
        topNavigatorOptionLink.hover();
        try {
            switch (forSearch) {
                case "top":
                    if (topNavigatorMenuLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(topNavigatorMenu, "text"));
                    }
                    break;
                case "drop-down":
                    if (topNavigatorMenuLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(topNavigatorDropdownMenu, "text"));
                    }
                    break;
                case "TopButton":
                    if (headerInfoSectionLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(headerInfoSectionButton, "aria-label"));
                    }
                    break;
                case "search":
                    if (searchLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(search, "type"));
                    }
                    break;
                case "left":
                    if (leftSidebarContainerLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(leftSidebarContainerMenu, "text"));
                    }
                    break;
                case "leftButtonStart":
                    if (leftSidebarButtonLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(leftSidebarButton, "text"));
                    }
                    break;
                case "leftButtonWorkSpace":
                    if (leftSidebarButtonLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(leftSidebarButton, "aria-label"));
                    }
                    break;
                case "leftButtonClose":
                    if (leftSidebarButtonCloseLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(leftSidebarButtonClose, "aria-label"));
                    }
                    break;
                case "boardsMainHeader":
                    if (boardHeaderLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(boardHeader, "text"));
                    }
                    break;
                case "boardsHeader":
                    if (boardSectionHeaderLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(boardSectionHeader, "text"));
                    }
                    break;
                case "boardsMenu":
                    if (boardSectionHeaderOptionsItemLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(boardSectionHeaderOptionsItem, "text"));
                    }
                    break;
                case "boardsButton":
                    if (boardSectionButtonLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(boardSectionButton, "text"));
                    }
                    break;
                case "boards":
                    if (boardCreateLink.exists()) {
                        resultFromPage = new MenuCheckObject(true,
                                getListOfItemsOfCheckedSection(boardCreate, "text"));
                    }
                    break;
                default:
                    resultFromPage = new MenuCheckObject(false, null);
            }
            return resultFromPage;
        } catch (Exception e) {
            System.out.println("Element " + forSearch + " was not found. " + e.getMessage());
            return new MenuCheckObject(false, null);
        }
    }

    @Step("Провевка навигационного меню, получениие списка пунктов")
    public List<String> getListOfItemsOfCheckedSection(ElementsCollection elementForSearch, String kindOfSearch) {
        switch (kindOfSearch) {
            case "text":
                return elementForSearch.filter(not(empty)).texts();
            default:
                return LogUtils.getAttribute(elementForSearch, kindOfSearch);
        }
    }

    @Step("Создание доски")
    public void boardCreateClick() {
        try {
            boardCreateLink.click();
        } catch (Exception e) {
            System.out.println("Button of Board creation was not found. " + e.getMessage());
        }
    }

    @Step("Проверка создание доски")
    public BoardPage createNewBoard(String boardName, String boardViewType, String color) {
        try {
            boardCreateLink.click();

            //board view selection block
            switch (boardViewType) {
                case "pictures":
                    newBoardViewLocator = newBoardViewLocator + "[1]//li";
                    break;
                case "color":
                    if (!color.equals("0")) {
                        newBoardViewLocator = newBoardViewLocator + "//button[contains(@title,'" + color + "')]";
                    } else {
                        newBoardView = $$(By.xpath(newBoardViewLocator));
                        newBoardViewLocator = newBoardViewLocator
                                + "[" + LogUtils.chooseView(newBoardView.size()) + "]";
                    }
                    break;
            }
            setBoardName(boardName);
            $(By.xpath(newBoardViewLocator)).click();

            //workspace selection block

            //finish
            buttonClick(newBoardButton);
        } catch (Exception e) {
            System.out.println("Board " + boardName + " was not created. " + e.getMessage());
        }
        return Selenide.page(BoardPage.class);
    }

    @Step("Создание доски из шаблона")
    public BoardPage createNewTemplateBoard(String boardName, String boardTemplate) {
        try {
            boardCreateLink.click();

            //workspace selection block

            //template selection block
            newTemplateBoardButton.hover().click();

            newTemplateSelectionTitle.hover();
            $(By.xpath("//div[contains(text(),'" + boardTemplate + "')]")).click();

            //set name of board and finish
            setBoardName(boardName);
            buttonClick(newBoardButton);
        } catch (Exception e) {
            System.out.println("Board " + boardName + " was not created. " + e.getMessage());
        }
        return Selenide.page(BoardPage.class);
    }

    @Step("Открытие страницы доски")
    public BoardPage openBoardPage(String boardName) {
        try {
            $(By.xpath("//div[contains(text(),'" + boardName + "')]")).click();
            return Selenide.page(BoardPage.class);
        } catch (Exception e) {
            System.out.println("Can`t open " + boardName + ". " + e.getMessage());
        }
        return null;
    }

    @Step("Задать имя доски")
    public void setBoardName(String boardName) {
        newBoardTitleInput.setValue(boardName.trim());
    }

    @Step("Нажатие на подтвеждающую действие кнопку")
    public void buttonClick(SelenideElement button) {
        if (button.isDisplayed()) button.click();
        else System.out.println("The template button does not displayed");
    }
}
