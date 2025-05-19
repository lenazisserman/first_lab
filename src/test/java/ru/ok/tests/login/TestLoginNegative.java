package ru.ok.tests.login;

import ru.ok.core.util.*;
import ru.ok.pages.LoginPage;
import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Негативные проверки авторизации.
 */
class TestLoginNegative extends BaseTest {

    /** Data‑provider: два набора неверных учётных данных. */
    static Stream<Credentials> wrongCredentials() {
        return Stream.of(
                CredentialsFactory.wrongPass(),
                CredentialsFactory.empty()
        );
    }

    @ParameterizedTest(name = "TestCheckInvalidLogin #{index}")
    @MethodSource("wrongCredentials")
    @Timeout(10)
    void shouldShowError(Credentials cred) {
        String err = new LoginPage()
                .enterLogin(cred.login())
                .enterPassword(cred.password())
                .submit()
                .errorText();

        assertTrue(!err.isBlank(), "Ожидалось сообщение об ошибке");
    }
}