package ru.ok.tests.login;

import ru.ok.core.util.CredentialsFactory;
import ru.ok.pages.LoginPage;
import ru.ok.pages.MainPage;
import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TestCheckLoginPositive")
@Tag("UI")
class TestLoginPositive extends BaseTest {

    @Test @Timeout(15)
    @DisplayName("Успешный логин и проверка главной страницы")
    void shouldLoginSuccessfully() {
        MainPage main = new LoginPage()
                .loginAs(CredentialsFactory.valid());

        assertAll("Элементы главной страницы",
                () -> assertTrue(main.isAvatarVisible(), "Аватар не виден"),
                () -> assertTrue(main.isFriendsActive(), "Кнопка 'Друзья' неактивна"),
                () -> assertTrue(main.isFeedVisible(),   "Лента новостей не отображается")
        );
    }
}