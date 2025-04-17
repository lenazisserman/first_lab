import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты авторизации и главной страницы OK.RU")
@Tag("UI")
public class OkRuLoginTest extends BaseTest {

    @Test
    @DisplayName("Невалидный логин: неправильный пароль")
    @Timeout(value = 10)
    public void failedLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage()
                .enterCredentials(TestConfig.getUsername(), "неверный_пароль")
                .clickSubmit();

        String errorText = loginPage.getErrorMessage();
        assertTrue(errorText.contains("Неправильно указан логин и/или пароль"));
    }

    @Test
    @DisplayName("Проверка главной страницы после логина")
    @Timeout(value = 15)
    public void testMainPageFeatures() {
        new LoginPage()
                .enterCredentials(TestConfig.getUsername(), TestConfig.getPassword())
                .clickSubmit();

        MainPage mainPage = new MainPage();

        assertAll("Проверка элементов главной страницы",
                () -> assertTrue(mainPage.isUserAvatarDisplayed(), "Аватар не отображается"),
                () -> assertTrue(mainPage.isFriendsButtonEnabled(), "Кнопка 'Друзья' недоступна"),
                () -> assertTrue(mainPage.isNewsFeedExists(), "Лента новостей не найдена")
        );
    }

    @Test
    @DisplayName("Кнопка 'Друзья' должна быть кликабельной")
    @Tag("Friends")
    @Timeout(value = 10)
    public void friendsButtonShouldBeClickable() {
        new LoginPage()
                .enterCredentials(TestConfig.getUsername(), TestConfig.getPassword())
                .clickSubmit();

        MainPage mainPage = new MainPage();
        mainPage.clickFriendsButton();
        mainPage.shouldShowSuggestedFriends();
    }

    @Nested
    @DisplayName("Нестед: Проверка поведения с пустыми полями ввода")
    class EmptyFieldTests {

        @Test
        @DisplayName("Ошибка при попытке логина с пустым логином и паролем")
        @Timeout(value = 5)
        void shouldShowErrorOnEmptyFields() {
            LoginPage loginPage = new LoginPage()
                    .enterCredentials("", "")
                    .clickSubmit();

            String errorText = loginPage.getErrorMessage();
            assertTrue(errorText.length() > 0, "Ожидалась ошибка при пустых полях");
        }
    }
}
