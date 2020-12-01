package academy.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HistogramPage extends BasePage {

    private final By HISTOGRAM_AREA = By.xpath("//div[@class='histogram-container']"); //локатор для Гистограммы
    private final By LIST_OF_BARS = By.xpath("//*[name()='g' and @class='bars']//*[name()='rect' and @class='bar']"); //список столбиков
    private final By BAR_WINDOW = By.xpath("//div[@class='tooltip-container']//div"); // всплывающее окно

    public void openHistogramPage() {
        toolbar.clickOnItem("Histogram");
    }

    public boolean isTooltipBarPresent() {
        return isVisible(BAR_WINDOW);
    }

    public ElementsCollection getListOfBars() {
        $(HISTOGRAM_AREA).should(Condition.visible);
        return $$(LIST_OF_BARS);
    }

    public void hoverOneBar(SelenideElement element) {
        element.should(Condition.visible).hover();
    }
}
