package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.TestSpecs.requestSpec;
import static specs.TestSpecs.responseSpec;

public class AuthorizationApi {

    @Step("Authorization")
    public static LoginResponseModel getAuthorization (LoginRequestModel requestBody) {
        return given(requestSpec)
                .body(requestBody)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}
