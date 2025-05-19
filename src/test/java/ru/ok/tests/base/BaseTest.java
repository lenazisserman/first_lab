package ru.ok.tests.base;

import com.codeborne.selenide.Configuration;
import ru.ok.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeEach
    void openBaseUrl() {
        /* Задаём базовый url для относительных путей */
        Configuration.baseUrl = TestConfig.BASE_URL;
        Configuration.timeout = TestConfig.DEFAULT_TIMEOUT.toMillis();

        /* Открываем главную страницу */
        open("/");
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        try {
            clearBrowserLocalStorage();      // может выбросить "access denied"
        } catch (RuntimeException ignored) {} // молча игнорируем
        closeWebDriver();
    }
}