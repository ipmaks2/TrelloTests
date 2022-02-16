package api;

import api.utils.ApiValue;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

    public class BoardCreateNewTest {

        private static String getBoardNamesRequest = ApiValue.getBoardInfoRequest;
        private static String getBoardInfoFilterRequest = ApiValue.getBoardInfoFilterRequest;

        @ParameterizedTest
        @CsvSource("NewApiBoard")
        public void checkBoardList(String boardName) {
            List<String> boardNameList = getBoardNameList();
            int startBoardsCount = boardNameList.size();
            Assertions.assertTrue(startBoardsCount > 0);

            addNewBoard(boardName);
            boardNameList = getBoardNameList();
            int endBoardsCount = boardNameList.size();

            List<String> finalBoardNameList = boardNameList;
            Assertions.assertAll("add new board",
            () -> Assertions.assertEquals(1, endBoardsCount - startBoardsCount),
                    () -> Assertions.assertTrue(
                            finalBoardNameList.stream()
                                    .filter(i -> i.equals(boardName))
                                    .collect(Collectors.toList()).size() >0)
            );
        }

        public List<String> getBoardNameList() {
            return given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get(ApiValue.API_URL + getBoardNamesRequest + ApiValue.APIKEY_TOKEN_STRING)
                    .then().statusCode(200)
                    .extract().jsonPath().getList("name");
        }

        public void addNewBoard(String boardName) {
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .post(ApiValue.API_URL + getBoardInfoFilterRequest + boardName + "&" + ApiValue.APIKEY_TOKEN_STRING)
                    .then()
                    .statusCode(200);
        }
    }
