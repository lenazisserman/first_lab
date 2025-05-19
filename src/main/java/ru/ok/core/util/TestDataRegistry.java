package ru.ok.core.util;

import java.util.Map;

/** Хранилище любых реиспользуемых данных для тестов. */
public final class TestDataRegistry {
    private TestDataRegistry() {}

    private static final Map<String, String> RU_ERROR_TEXTS = Map.of(
            "login",  "Неправильно указан логин и/или пароль",
            "empty",  "Заполните все поля"
    );

    public static String errorText(String key) {
        return RU_ERROR_TEXTS.getOrDefault(key, "Неизвестная ошибка");
    }
}