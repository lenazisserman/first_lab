package ru.ok.pages;

import ru.ok.core.element.Input;
import ru.ok.core.element.UiElement;
import ru.ok.core.page.BasePage;
import ru.ok.core.util.Credentials;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница авторизации OK.ru
 */
public class LoginPage extends BasePage<LoginPage> {

    /* ----- локаторы ----- */
    private final Input emailInput    = new Input($x("//input[@id='field_email']"));
    private final Input passwordInput = new Input($x("//input[@id='field_password']"));

    private final UiElement submitBtn = new UiElement($x(
            "//form[contains(@class,'login-form')]//" +
                    "*[(self::button or self::input)" +
                    "   and (@type='submit' or @data-l='t,sign_in')]"));

    private final UiElement errorBox  = new UiElement($x("//div[contains(@class,'login_error')]"));

    @Override protected SelenideElement root() {
        return emailInput.element();
    }

    /* ----------- действия ----------- */

    public LoginPage enterLogin(String login)       { emailInput.clearAndType(login); return this; }
    public LoginPage enterPassword(String password) { passwordInput.clearAndType(password); return this; }
    public LoginPage submit()                       { submitBtn.click();              return this; }


    public MainPage loginAs(Credentials cred) {
        return enterLogin(cred.login())
                .enterPassword(cred.password())
                .submit()
                .waitForMain();
    }

    /* ---------- проверки ---------- */

    public String errorText() {
        return errorBox.shouldBeVisible().text();
    }

    private MainPage waitForMain() {
        return new MainPage().checkPage();
    }
}