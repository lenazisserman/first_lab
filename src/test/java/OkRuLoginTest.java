import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OkRuLoginTest extends BaseTest {

    @Test
    public void failedLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage()
                .enterCredentials(TestConfig.getUsername(), "неверный_пароль")
                .clickSubmit();

        String errorText = loginPage.getErrorMessage();
        assertTrue(errorText.contains("Неправильно указан логин и/или пароль"));
    }

    @Test
    public void testMainPageFeatures() {
        new LoginPage()
                .enterCredentials(TestConfig.getUsername(), TestConfig.getPassword())
                .clickSubmit();

        MainPage mainPage = new MainPage();

        assertTrue(mainPage.isUserAvatarDisplayed(), "Аватар не отображается");
        assertTrue(mainPage.isFriendsButtonEnabled(), "Кнопка 'Друзья' недоступна");
        assertTrue(mainPage.isNewsFeedExists(), "Лента новостей не найдена");
    }

    @Test
    public void friendsButtonShouldBeClickable() {
        new LoginPage()
                .enterCredentials(TestConfig.getUsername(), TestConfig.getPassword())
                .clickSubmit();

        MainPage mainPage = new MainPage();
        mainPage.clickFriendsButton();
        mainPage.shouldShowSuggestedFriends();
    }
}
