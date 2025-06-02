package ru.ok.core.element;

import com.codeborne.selenide.*;
import static com.codeborne.selenide.Condition.visible;

/**
 * Базовый декоратор для любого UI-элемента.
 */
public class UiElement {

    protected final SelenideElement el;

    public UiElement(SelenideElement el) {
        this.el = el;
    }



    public UiElement shouldBeVisible() {
        el.shouldBe(visible);
        return this;
    }

    /** Делегируем любой set из Condition-ов прямо SelenideElement'у */
    public UiElement shouldBe(Condition... conditions) {
        el.shouldBe(conditions);
        return this;
    }

    public UiElement click()    { el.click();             return this; }
    public UiElement setValue(String v) { el.setValue(v); return this; }
    public UiElement clear()    { el.clear();             return this; }
    public String     text()    { return el.text(); }

    /** Получить сырой {@link SelenideElement}. */
    public SelenideElement element() { return el; }
}