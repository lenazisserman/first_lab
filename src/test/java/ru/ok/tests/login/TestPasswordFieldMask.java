package ru.ok.tests.login;

import ru.ok.tests.base.BaseTest;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверяем, что input#field_password имеет type="password",
 * а значит символы не отображаются в открытом виде.
 */
@DisplayName("TestCheckPasswordFieldMask")
class TestPasswordFieldMask extends BaseTest {

    @Test
    @Tag("UI")
    @Timeout(5)
    @DisplayName("Поле пароля должно быть masked (type=password)")
    void passwordInputIsMasked() {
        String typeAttr = $x("//input[@id='field_password']")
                .getAttribute("type");

        assertEquals("password", typeAttr,
                "Ожидалось, что атрибут type у поля пароля равен 'password'");
    }
}