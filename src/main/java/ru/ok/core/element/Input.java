package ru.ok.core.element;

/** Специализированный элемент — текстовое поле. */
public class Input extends UiElement {
    public Input(com.codeborne.selenide.SelenideElement el) { super(el); }

    public Input clearAndType(String text) {
        return (Input) clear().setValue(text);
    }
}