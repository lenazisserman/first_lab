import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final SelenideElement userAvatar = $x("//*[@id='hook_Block_Avatar']").shouldBe(visible);
    private static final SelenideElement friendsButton = $x("//*[@data-l='t,userFriend']").shouldBe(visible);
    private static final SelenideElement newsFeed = $x("//*[@id='hook_Block_MainFeedsWrapper']").shouldBe(visible);
    private static final SelenideElement suggestedFriendsText = $x("//*[@class='friends-page']");

    public void clickFriendsButton() {
        friendsButton.shouldBe(visible).click();
    }

    public void shouldShowSuggestedFriends() {
        suggestedFriendsText.shouldHave(text("Возможно, вы знаете этих людей"));
    }

    public boolean isUserAvatarDisplayed() {
        return userAvatar.shouldBe(visible).isDisplayed();
    }

    public boolean isFriendsButtonEnabled() {
        return friendsButton.shouldBe(visible).isEnabled();
    }

    public boolean isNewsFeedExists() {
        return newsFeed.shouldBe(visible).isDisplayed();
    }
}
