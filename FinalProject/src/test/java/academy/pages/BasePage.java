package academy.pages;

import academy.elements.ApplicationToolbar;
import academy.elements.AttributeDropDown;
import academy.elements.GridColumnElements;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected ApplicationToolbar toolbar;
    protected AttributeDropDown dropDown;
    protected GridColumnElements gridElements;

    public BasePage() {
        toolbar = new ApplicationToolbar();
        dropDown = new AttributeDropDown();
        gridElements = new GridColumnElements();
    }

    public boolean isExist(By xPath) {
        try {
            $(xPath).should(Condition.visible);
            return true;
        } catch (ElementNotFound alertMessagePresent) {
            return false;
        }
    }

    public boolean isVisible(By xPath) {
        try {
            return $(xPath).should(Condition.visible).isDisplayed();
        } catch (ElementShould elementShould) {
            return false;
        }
    }
}
