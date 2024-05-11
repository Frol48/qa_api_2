package extentions;

import api.AuthorizationApi;
import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import tests.TestData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {
    @Override
    @Step("Авторизация на UI")
    public void beforeEach(ExtensionContext context) {
        LoginRequestModel requestBody = new LoginRequestModel(TestData.userName, TestData.password);


        LoginResponseModel response = AuthorizationApi.getAuthorization(requestBody);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
    }

}
