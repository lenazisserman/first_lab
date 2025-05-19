package ru.ok.tests.main;

import ru.ok.core.util.CredentialsFactory;
import ru.ok.pages.LoginPage;
import ru.ok.pages.MainPage;
import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.*;

@DisplayName("TestCheckMainPageFeatures")
class TestMainPage extends BaseTest {

    @Test @Tag("Friends") @Timeout(10)
    @DisplayName("Кнопка 'Друзья' открывает блок рекомендаций")
    void friendsSuggestions() {
        new LoginPage()
                .loginAs(CredentialsFactory.valid())
                .openFriends()
                .shouldShowSuggests();
    }

    @Nested
    @DisplayName("Nested: проверка пустых полей авторизации")
    class EmptyFields {

        @Test @Timeout(5)
        @DisplayName("Ошибка при пустых логине и пароле")
        void emptyLoginAndPassword() {
            String err = new LoginPage()
                    .enterLogin("")
                    .enterPassword("")
                    .submit()
                    .errorText();
            Assertions.assertFalse(err.isBlank(), "Сообщение об ошибке не появилось");
        }
    }
}