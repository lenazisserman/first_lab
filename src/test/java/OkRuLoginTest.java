import com.codeborne.selenide.Configuration;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.junit.jupiter.api.AfterEach;
import com.codeborne.selenide.Selenide;

public class OkRuLoginTest {

    @Test
    public void failedLoginWithWrongPassword() {
        open("https://ok.ru");

        LoginPage loginPage = new LoginPage();
        loginPage.enterCredentials("логин", "неверный_пароль");
        loginPage.clickSubmit();

        String errorText = $(".input-e.login_error").getText();
        assertTrue(errorText.contains("Неправильно указан логин и/или пароль"));
    }

    @Test
    public void testMainPageFeatures() {
        open("https://ok.ru");
        LoginPage loginPage = new LoginPage();
        loginPage.enterCredentials("логин", "правльный_пароль");
        loginPage.clickSubmit();

        MainPage mainPage = new MainPage();

        assertTrue(mainPage.userAvatar.isDisplayed(), "Аватар не отображается");
        assertTrue(mainPage.friendsButton.isEnabled(), "Кнопка 'Друзья' недоступна");
        assertTrue(mainPage.newsFeed.exists(), "Лента новостей не найдена");

    }

    @Test
    public void friendsButtonShouldBeClickable() {
        open("https://ok.ru");
        LoginPage loginPage = new LoginPage();
        loginPage.enterCredentials("логин", "правильный_пароль");
        loginPage.clickSubmit();

        MainPage mainPage = new MainPage();
        mainPage.clickFriendsButton();

        sleep(3000);

        String pageText = $("body").getText();
        assertTrue(pageText.contains("Возможно, вы знаете этих людей"),
                "Текст не найден на странице");
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}



