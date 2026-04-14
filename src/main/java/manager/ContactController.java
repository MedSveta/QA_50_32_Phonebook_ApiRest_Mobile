package manager;

import dto.Contact;
import dto.TokenDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseApi;
import static io.restassured.RestAssured.given;
public class ContactController implements BaseApi {

    public static Response requestGetAllUserContacts(String tokenDto){
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", tokenDto)
                .contentType(ContentType.JSON)
                .when()
                .get(GET_ALL_CONTACTS_URL)
                .thenReturn();
    }

    public static Response requestAddNewContact(Contact contact, String tokenDto){
        return given()
                .body(contact)
                .baseUri(BASE_URL)
                .header("Authorization", tokenDto)
                .contentType(ContentType.JSON)
                .when()
                .post(ADD_NEW_CONTACT_URL)
                .thenReturn();
    }
}
