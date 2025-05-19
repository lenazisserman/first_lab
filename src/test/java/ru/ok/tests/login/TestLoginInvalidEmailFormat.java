package ru.ok.tests.login;

import ru.ok.core.util.CredentialsFactory;
import ru.ok.pages.LoginPage;
import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Проверяем, что при вводе логина в недопустимом формате
 * (пропущен, лишние пробелы, кириллица) сайт отдаёт ошибку.
 */
@DisplayName("TestCheckInvalidEmailFormats")
class TestLoginInvalidEmailFormat extends BaseTest {

    @ParameterizedTest(name = "Неверный логин \"{0}\"")
    @CsvSource(value = {
            // login,               password
            "' user@example.com',   'pass123'",   // пробел в начале
            "'userexample.com',     'pass123'",   // нет @
            "'user@ example .com',  'pass123'",   // пробелы внутри
            "'юзер@почта.рф',       'pass123'"    // кириллица в адресе
    })
    @Tag("UI")
    @Timeout(10)
    void shouldRejectInvalidEmail(String badLogin, String anyPwd) {

        String err = new LoginPage()
                .enterLogin(badLogin)
                .enterPassword(anyPwd)
                .submit()
                .errorText();

        assertTrue(!err.isBlank(),
                "Ожидалось сообщение об ошибке для логина: " + badLogin);
    }
}