import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

class LoginPage {
    public void enterCredentials(String login, String password) {
        $("#field_email").setValue(login);
        $("#field_password").setValue(password);
    }

    public void clickSubmit() {
        $("input[data-l='t,sign_in']").click();
    }
}