package manager;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseApi;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi {
    public static Response requestRegLogin(User user, String url) {
    return given()
            .baseUri(BASE_URL)
            .contentType(ContentType.JSON)
            .body(user)
            .when()
            .post(url)
            .thenReturn();
    }
}
