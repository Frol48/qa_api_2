package api;

import models.AddBookToListRequestModel;
import models.DeleteBookModel;
import models.LoginResponseModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.TestSpecs.requestSpec;
import static specs.TestSpecs.responseSpec;

public class Books {

    public static void deleteAllBooks (LoginResponseModel responseAuthorization) {

        given(requestSpec)
                .header("Authorization", "Bearer " + responseAuthorization.getToken())
                .queryParam("UserId", responseAuthorization.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }

    public static void addBook(LoginResponseModel responseAuthorization, String isbn1) {
        AddBookToListRequestModel.CollectionOfIsbns isbn = new AddBookToListRequestModel.CollectionOfIsbns(isbn1);
        List<AddBookToListRequestModel.CollectionOfIsbns> isbnList = new ArrayList<>();
        isbnList.add(isbn);

        AddBookToListRequestModel request = new AddBookToListRequestModel();
        request.setUserId(responseAuthorization.getUserId());
        request.setCollectionOfIsbns(isbnList);

        given(requestSpec)
                .header("Authorization", "Bearer " + responseAuthorization.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec)
                .statusCode(201);
    }

    public static void deleteBook(LoginResponseModel responseAuthorization, String isbn) {
        DeleteBookModel request = new DeleteBookModel();
        request.setIsbn(isbn);
        request.setUserId(responseAuthorization.getUserId());

        given(requestSpec)
                .header("Authorization", "Bearer " + responseAuthorization.getToken())
                .body(request)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec)
                .statusCode(204);
    }
}
