import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class LoginPage {
    private static final SelenideElement emailField = $x("//*[@id='field_email']").shouldBe(visible);
    private static final SelenideElement passwordField = $x("//*[@id='field_password']").shouldBe(visible);
    private static final SelenideElement signInButton = $x("//*[@value='Войти в Одноклассники']").shouldBe(visible);
    private static final SelenideElement loginError = $x("//*[@class='input-e login_error']");

    public LoginPage enterCredentials(String login, String password) {
        emailField.setValue(login);
        passwordField.setValue(password);
        return this;
    }

    public LoginPage clickSubmit() {
        signInButton.click();
        return this;
    }

    public String getErrorMessage() {
        return loginError.shouldBe(visible).getText();
    }
}
