package tests;

import api.AuthorizationApi;
import api.Books;
import extentions.WithLogin;
import models.AddBookToListRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import page.ProfilePage;
import static io.qameta.allure.Allure.step;

public class RemovingItemTest  extends TestBase{
    ProfilePage profilePage = new ProfilePage();

    @Tag("All")
    @DisplayName("Удаление книги из списка")
    @WithLogin
    @Test
    void deleteBook () {

        LoginResponseModel responseAuthorization = AuthorizationApi.getAuthorization(TestData.loginRequest);

        step("Удаление всех книг из списка", () ->
            Books.deleteAllBooks(responseAuthorization)
        );

        step("Добавление книги в список", () ->
            Books.addBook(responseAuthorization, new AddBookToListRequestModel(), TestData.isbn)
        );

        step("Проверка добаления книги", () -> {
            profilePage.open()
                    .checkUserName(responseAuthorization.getUserName())
                    .checkVisibleBook(TestData.isbn);
        });

        step("Удаление книги из списка", () ->
            Books.deleteBook(responseAuthorization, TestData.isbn)
        );

        step("Проверка удаления книги", ()->{
            profilePage.open()
                            .checkUserName(responseAuthorization.getUserName())
                            .checkingAbsenceBook(TestData.isbn);
        });
    }

}
