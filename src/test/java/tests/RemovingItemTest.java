package tests;

import api.AuthorizationApi;
import api.Books;
import extentions.LoginExtension;
import extentions.WithLogin;
import models.AddBookToListRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

/*
1. Удаление всех книг
2. Добавление книги в список
3. Открытие страницы в браузере
4. Удаление книги из списка
*/

public class RemovingItemTest  extends TestBase{

    @Test
    @WithLogin
    @DisplayName("Удаление книги из списка")
    void deleteBook () {

        LoginResponseModel responseAuthorization = AuthorizationApi.getAuthorization(TestData.loginRequest);

        step("Удаление всех книг из списка", () -> {
            Books.deleteAllBooks(responseAuthorization);
        });

        step("Добавление книги в список", () -> {
            Books.addBook(responseAuthorization, new AddBookToListRequestModel(), TestData.isbn);
        });

        step("Проверка добаления книги", () -> {
           open("/profile");
            $("#userName-value").shouldBe(text("123456"));
            $(".mr-2").shouldBe(text("Learning JavaScript Design Patterns"));
        });

        step("Удаление книги из списка", () -> {
            Books.deleteBook(responseAuthorization, TestData.isbn);
        });

        step("Проверка удаления книги", ()->{
            open("/profile");
            $("#userName-value").shouldBe(text("123456"));
            $("[href='/profile?book=9781449325862']").shouldBe(hidden);
        });
    }

}
