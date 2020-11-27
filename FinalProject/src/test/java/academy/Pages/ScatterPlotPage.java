package academy.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ScatterPlotPage extends BasePage {

    private final By SCATTER_PLOT_AREA = By.xpath("//div[@class='scatter-plot-container']"); //локатор для точечной диаграммы
    private final By X_DROPDOWN_BUTTON = By.xpath("//div[text()='X Attribute']/..//button");// кнопка выпадающего меню атрибутов X
    private final By Y_DROPDOWN_BUTTON = By.xpath("//div[text()='Y Attribute']/..//button");// кнопка выпадающего меню атрибутов Y
    private final By ATTRIBUTE_DROPDOWN_CONTAINER = By.xpath("//ul");// контейнер выпадающего меню
    private final By LIST_OF_ATTRIBUTE = By.xpath("//li[contains(@class,'autocomplete-dropdown-item')]");//список атрибутов
    private final By X_AXIS_NAME = By.xpath("//*[name()='text' and contains(@class,'x-label')]");//название оси X
    private final By Y_AXIS_NAME = By.xpath("//*[name()='text' and contains(@class,'y-label')]");//название оси Y
    private final By X_AXIS_DIGITS = By.xpath("//*[name()='g' and @class='x axis']/child::*[name()='g']"); //список цифр оси Х
    private final By Y_AXIS_DIGITS = By.xpath("//*[name()='g' and @class='y axis']/child::*[name()='g']"); //список цифр оси Y
    private final By SCATTER_PLOT_GRID_AREA = By.xpath("//div[contains(@class,'visualization__grid')]//div[@ref='gridPanel']"); // панель грида внизу
    private final By SCATTER_PLOT_DOT = By.xpath("//*[name()='g' and @class='scatter_element']"); //точка на графике


    public void openScatterPlotPage() {
        toolbar.clickOnItem("Scatter-plot");
    }

    public boolean isAttributeContainerVisible() {
        return !isExist(ATTRIBUTE_DROPDOWN_CONTAINER);
    }

    public void clickOn_X_AttributeDropDownButton() {
        $(X_DROPDOWN_BUTTON).click();
    }

    public void clickOn_Y_AttributeDropDownButton() {
        $(Y_DROPDOWN_BUTTON).click();
    }

    public ElementsCollection getAttributeCollection() {
        return $$(LIST_OF_ATTRIBUTE);
    }

    public void clickOnAttributeName(String name) {
        dropDown.clickOn(name);
    }

    public String get_X_AxisName() {
        $(SCATTER_PLOT_AREA).should(Condition.visible);
        return $(X_AXIS_NAME).getText();
    }

    public String get_Y_AxisName() {
        $(SCATTER_PLOT_AREA).should(Condition.visible);
        return $(Y_AXIS_NAME).getText();
    }

    public boolean isSPGridVisible() {
        return isExist(SCATTER_PLOT_GRID_AREA);
    }

    public String get_X_LastDigit() {
        $(SCATTER_PLOT_DOT).shouldBe(Condition.visible, Condition.enabled);
        $(X_AXIS_DIGITS).should(Condition.visible);
        ElementsCollection digitList = $$(X_AXIS_DIGITS);
        return digitList.get(digitList.size() - 1).getText();
    }

    public String get_Y_LastDigit() {
        $(SCATTER_PLOT_DOT).shouldBe(Condition.visible, Condition.enabled);
        $(Y_AXIS_DIGITS).should(Condition.visible);
        ElementsCollection digitList1 = $$(Y_AXIS_DIGITS);
        return digitList1.get(digitList1.size() - 1).getText();
    }
}


