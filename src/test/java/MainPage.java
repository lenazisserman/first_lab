import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    // Элементы
    public SelenideElement userAvatar = $(".ucard-mini_img");
    public SelenideElement friendsButton = $x("//*[@id='hook_Block_Navigation']/div/div/div[4]/a/div");
    public SelenideElement newsFeed = $(".feed-w");

    // Методы действий
    public void clickFriendsButton() {
        friendsButton.click();
    }
}