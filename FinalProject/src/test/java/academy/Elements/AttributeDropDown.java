package academy.Elements;

import static com.codeborne.selenide.Selenide.$x;

public class AttributeDropDown {

    private final String ATTRIBUTE_NAME_PATTERN = "//a[contains(text(),'%s')]"; // паттерн имени атрибутов

    public void clickOn(String name) {
        $x(String.format(ATTRIBUTE_NAME_PATTERN, name)).click();
    }

}
