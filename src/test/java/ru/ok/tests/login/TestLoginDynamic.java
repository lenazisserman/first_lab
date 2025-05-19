package ru.ok.tests.login;

import ru.ok.core.util.CredentialsFactory;
import ru.ok.core.util.Credentials;
import ru.ok.pages.LoginPage;
import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

/**
 * Динамические тесты: две ошибки пароля.
 */
class TestLoginDynamic extends BaseTest {

    @TestFactory
    Stream<DynamicTest> dynamicWrongPasswords() {

        List<String> pwds = List.of("123", "qwerty");

        return pwds.stream().map(pwd ->
                DynamicTest.dynamicTest("TestCheckLoginWithPwd_" + pwd, () -> {

                    CompletableFuture<String> future =
                            CompletableFuture.supplyAsync(() -> {

                                refresh();

                                Credentials c = new Credentials(
                                        CredentialsFactory.valid().login(), pwd);

                                return new LoginPage()
                                        .enterLogin(c.login())
                                        .enterPassword(c.password())
                                        .submit()
                                        .errorText();
                            }, Runnable::run);   // выполняем в текущем потоке

                    String err = future.join();
                    Assertions.assertFalse(err.isBlank(),
                            "Ожидалось сообщение об ошибке");

                    clearBrowserCookies();      // обнуляем счётчик ошибок
                }));
    }
}