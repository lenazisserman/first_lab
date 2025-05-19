package ru.ok.pages;

import ru.ok.core.element.UiElement;
import ru.ok.core.page.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница пользователя OK.ru
 */
public class MainPage extends BasePage<MainPage> {

    /* ----- локаторы ----- */
    private final UiElement avatar   = new UiElement($x("//div[@id='hook_Block_Avatar']"));
    private final UiElement friends  = new UiElement($x("//a[@data-l='t,userFriend']"));
    private final UiElement feed     = new UiElement($x("//div[@id='hook_Block_MainFeedsWrapper']"));
    private final UiElement suggText = new UiElement($x("//div[contains(@class,'friends-page')]"));

    @Override
    protected SelenideElement root() {
        // страница считается загруженной, когда видна лента
        return feed.element();
    }

    /* ----------- действия ----------- */

    public MainPage openFriends() {
        friends.click();
        return this;
    }

    public MainPage shouldShowSuggests() {
        suggText.shouldBeVisible();
        return this;
    }

    /* ----------- геттеры для assertAll ----------- */

    public boolean isAvatarVisible() {
        return avatar.shouldBeVisible().element().isDisplayed();
    }

    public boolean isFriendsActive() {
        return friends.shouldBeVisible().element().isEnabled();
    }

    public boolean isFeedVisible() {
        return feed.shouldBeVisible().element().isDisplayed();
    }
}