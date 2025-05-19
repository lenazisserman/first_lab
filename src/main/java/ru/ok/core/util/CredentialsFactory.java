package ru.ok.core.util;

import ru.ok.config.TestConfig;

/** Создаёт наборы учётных данных для различных сценариев. */
public final class CredentialsFactory {
    private CredentialsFactory() {}

    public static Credentials valid() {
        return new Credentials(TestConfig.username(), TestConfig.password());
    }
    public static Credentials wrongPass() {
        return new Credentials(TestConfig.username(), "wrong-" + System.currentTimeMillis());
    }
    public static Credentials empty() {
        return new Credentials("", "");
    }
}