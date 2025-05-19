package ru.ok.core.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

/** Родитель всех страниц. Проверяем, что реально на нужной странице. */
public abstract class BasePage<T extends BasePage<T>> {

    /** Главный локатор страницы — должен быть видимым. */
    protected abstract SelenideElement root();

    /** Дополнительные проверки (можно переопределить). */
    protected void extraChecks() {}

    @SuppressWarnings("unchecked")
    public T checkPage() {
        root().shouldBe(visible);
        extraChecks();
        return (T) this;
    }
}