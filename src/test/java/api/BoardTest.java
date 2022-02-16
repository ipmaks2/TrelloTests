package api;

import api.pojo.BoardData;
import org.junit.jupiter.api.Assertions;
import api.utils.ApiValue;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BoardTest {

    private static String getBoardNamesRequest = ApiValue.getBoardInfoRequest;

    @Test
    public void checkBoardList() {

        List<BoardData> boardList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(ApiValue.API_URL + getBoardNamesRequest + ApiValue.APIKEY_TOKEN_STRING)
                .then().statusCode(200)
                .extract().jsonPath().getList(".", BoardData.class);

        boardList
                .forEach(
                        i -> Assertions.assertTrue(i.getUrl().contains(i.getName().toLowerCase()),
                        "Error: " + i.getName())
                );
    }
}
