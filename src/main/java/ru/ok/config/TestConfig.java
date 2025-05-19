package ru.ok.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/** Централизованные константы + чтение логина/пароля из resources. */
public final class TestConfig {

    public static final String BASE_URL = "https://ok.ru";
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(6);

    private static final Properties props = new Properties();

    static {
        try (InputStream in = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Не найден config.properties", e);
        }
    }

    public static String username() { return props.getProperty("username", ""); }
    public static String password() { return props.getProperty("password", ""); }

    private TestConfig() {}
}