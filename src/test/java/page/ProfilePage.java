package page;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    public ProfilePage open() {
        Selenide.open("/profile");
        return this;
    }

    public ProfilePage checkVisibleBook(String ibsn){
        $("[href='/profile?book=" + ibsn + "']").shouldBe(visible);
        return this;
    }

    public ProfilePage checkUserName(String userName){
        $("#userName-value").shouldBe(text(userName));
        return this;
    }

    public ProfilePage checkingAbsenceBook(String ibsn){
        $("[href='/profile?book=" + ibsn + "']").shouldBe(hidden);
        return this;
    }

}
