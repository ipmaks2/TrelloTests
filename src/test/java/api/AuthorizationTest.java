package api;

import api.pojo.UserRootData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.utils.ApiValue;
import api.utils.ConfProperties;

import static io.restassured.RestAssured.given;

public class AuthorizationTest {

    private static String getUserInfoRequest = "members/me/?fields=id,fullName,url,username,status,email&";

    @Test
    public void checkUserInfo() {
        UserRootData userInfo = given()
                .when()
                .header("Authorization", "Bearer " + ApiValue.TOKEN)
                .contentType(ContentType.JSON)
                .get(ApiValue.API_URL + getUserInfoRequest + ApiValue.APIKEY_TOKEN_STRING)
                .as(UserRootData.class);

        System.out.println("userInfo:" + userInfo.toString());
        Assertions.assertAll("User profile",
                () -> Assertions.assertEquals(ConfProperties.getProperty("user"), userInfo.getFullName()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("username"), userInfo.getUsername()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("email"), userInfo.getEmail()),
                () -> Assertions.assertEquals(ConfProperties.getProperty("url"), userInfo.getUrl())
        );
    }
}
