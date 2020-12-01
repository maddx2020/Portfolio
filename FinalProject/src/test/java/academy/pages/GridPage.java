package academy.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GridPage extends BasePage {
    private int indexForChart;

    private final By LABEL_ID_COLUMN = By.xpath("//span[@ref='eText' and contains(text(),'Id')]/.."); // колонка Id
    //  FiltersButton
    private final By FILTERS_BUTTON = By.xpath("//button[@title='Filters']");  //кнопка Фильтр
    private final By FILTERS_BUTTON_CONTAINER = By.xpath("//app-fast-filter-panel"); // контейнер кнопки Фильтр
    //    private final By FILTERS_CHEСKBOX_SELECTED = By.xpath("//app-fast-filter-panel//span[contains(@class,'icon-checkbox-checked')]"); //Фильтр чекбокс выбран
    private final By FILTERS_CHEСKBOX_SELECTED = By.xpath("//app-fast-filter-panel//span[contains(text(),'Id')]/..//span[contains(@class,'icon-checkbox-checked')]");
    //    private final By FILTERS_CHEСKBOX_EMPTY = By.xpath("//app-fast-filter-panel//span[contains(@class,'icon-checkbox-unchecked')]"); //Фильтр чекбокс пустой
    private final By FILTERS_CHEСKBOX_EMPTY = By.xpath("//app-fast-filter-panel//span[contains(text(),'Id')]/..//span[contains(@class,'icon-checkbox-unchecked')]");
    //  ColumnsButton
    private final By COLUMNS_BUTTON = By.xpath("//span[text()='Columns']"); // панель Columns справа
    private final By COLUMNS_BUTTON_CONTAINER = By.xpath("//div[@class='ag-column-panel']"); // контейнер Column
    private final By COLUMNS_CHEСKBOX_SELECTED = By.xpath("//div[contains(@class,'ag-primary-cols-list')]//span[contains(@class,'icon-checkbox-checked')]"); //Column чекбокс выбран
    private final By COLUMNS_CHEСKBOX_EMPTY = By.xpath("//div[contains(@class,'ag-primary-cols-list')]//span[contains(@class,'icon-checkbox-unchecked')]"); //Column чекбокс пустой
    //  ColumnFilterButton
    private final By COLUMN_FILTER_BUTTON = By.xpath("//div[@col-id='orderSide']//span[contains(@class,'ag-icon-menu')]");  //Кнопка Фильтра-колонки
    private final By COLUMN_FILTER_BUTTON_MENU = By.xpath("//div[@class='ag-menu']"); // меню кнопки Фильтра-колонки
    private final By _3rd_BUTTON_MENU = By.xpath("//div[@class='ag-menu']//span[contains(@class,'icon-columns')]"); //третья кнопка меню кнопки Фильтра-колонки
    private final By COLUMN_FILTER_BUTTON_CONTAINER = By.xpath("//div[@class='ag-menu']//div[@class='ag-column-select-panel']"); //контейнер Фильтра-колонки
    private final By COLUMN_FILTER_CHEСKBOX_SELECTED =
            By.xpath("//div[@class='ag-theme-balham']//div[contains(@class,'list')]//span[contains(@class,'icon-checkbox-checked')]");//чек бок Фильтр-колонки выбран
    private final By COLUMN_FILTER_CHEСKBOX_EMPTY =
            By.xpath("//div[@class='ag-theme-balham']//div[contains(@class,'list')]//span[contains(@class,'icon-checkbox-unchecked')]");//чек бок Фильтр-колонки пустой
    //  Sorting Branch
    private final String COLUMN_TYPE_ELEMENT_PATTERN = "//div[@row-index='%d']//div[contains(@col-id,'Type')]"; // паттерн элементов в колонке Type(String)
    private final String COLUMN_NUM_OF_ELEMENT_PATTERN = "//div[@row-index='%d']//div[contains(@col-id,'numberOfExecutions')]"; // паттерн элементов в колонке NumOf(Int)
    private final String COLUMN_BENCHMARK_ELEMENT_PATTERN = "//div[@row-index='%d']//div[contains(@col-id,'benchmarkPrice')]";// паттерн элементов в колонке Bench(Float)
    private final String COLUMN_START_TIME_ELEMENT_PATTERN = "//div[@row-index='%d']//div[contains(@col-id,'startTime')]";//паттерн элементов в колонке StartTime(Date)
    private final By COLUMN_NUM_OF = By.xpath("//span[@ref='eText' and contains(text(),'Num of executions')]"); //колонка NumOf
    private final By SCROLL = By.xpath("//div[@class='ag-body-horizontal-scroll-viewport']"); //нижний скролл
    //  Chart Branch
    private final By CHART_PANEL = By.xpath("//div[@class=\"flex-1 post-trade__chart\"]"); // панель Чарта
    private final By LINES_BUTTON = By.xpath("//span[contains(text(),'+ Lines')]"); //кнопка Lines+
    private final By LINES_BUTTON_MENU = By.xpath("//app-chart-lines-panel-content"); // меню кнопки Lines+
    private final By AVG_FILL_PRICE_IN_LINES_MENU = By.xpath("//app-chart-lines-panel-content//span[contains(text(),'Avg')]"); //переключатель AvgFillPrice
    private final By MID_PRICE_IN_LINES_MENU = By.xpath("//app-chart-lines-panel-content//span[contains(text(),'Mid')]"); //переключатель MidPrice
    private final By SELECTED_CHECKBOX_AVG_FILL_PRICE = By.xpath("//app-chart-lines-panel-content//span[contains(text(),'Avg')]/..//label//input"); //чекбокс выбран Avg
    private final By SELECTED_CHECKBOX_MARKER_MID_PRICE = By.xpath("//app-chart-lines-panel-content//span[contains(text(),'Mid')]/..//label//input"); //чекбокс выбран Mid
    private final By EXEC_AVR_FILL_PRICE = By.xpath("//*[name()='g' and contains(@class,'AVERAGE_EXECUTION_PRICE')]"); // Exec tooltip Avg
    private final By INTERACTIVE_L_C_AVR_F_P = By.xpath("//div[@class='legend-desktop']//span[contains(text(),'Avg fill price')]/following-sibling::div"); //Interactive Avg
    private final String COLUMN_AVG_ELEMENT_PATTERN = "//div[@row-index='%d']//div[contains(@col-id,'averageFillPrice')]"; // паттерн элементов в колонке AvgFillPrice
    private final By LINE_AVG_FILL_PRICE = By.xpath("//div[@class='d-inline']"); //данные со строки под Чартом
    private final By INTERACTIVE_L_C_MID_P = By.xpath("//div[@class='legend-desktop']//span[contains(text(),'Mid price')]/following-sibling::div"); //Interactive Avg
    private final By EXEC_MID_PRICE = By.xpath("//*[name()='g' and contains(@class,'MID_PRICE')]"); // Exec tooltip MidPrice
    private final By COLOR_EXEC_AVG = By.xpath("//*[name()='g' and contains(@class,'AVERAGE_EXECUTION_PRICE')]//*"); //цвет Exec Avg
    private final By COLOR_INTERACTIVE_AVG = By.xpath("//div[@class='legend-desktop']//span[contains(text(),'Avg fill price')]/preceding-sibling::div"); //цвет Interactive AVG


    public void openGridPage() {
        toolbar.clickOnItem("Grid & chart");
    }

    public boolean isColumnVisible() {
        return isExist(LABEL_ID_COLUMN);
    }

    //Filters Button Actions
    public void clickOnFiltersButton() {
        $(FILTERS_BUTTON).should(Condition.visible).click();
    }

    public boolean isFiltersContainerExist() {
        return !isExist(FILTERS_BUTTON_CONTAINER);
    }

    public void clickOnFiltersSelectedCheckBox() {
        $(FILTERS_CHEСKBOX_SELECTED).should(Condition.visible).click();
    }

    public void clickOnFiltersEmptyCheckBox() {
        $(FILTERS_CHEСKBOX_EMPTY).should(Condition.visible).click();
    }

    public boolean isFilterCheckBoxSelected() {
        return isVisible(FILTERS_CHEСKBOX_SELECTED);
    }

    //Columns Button Actions
    public void clickOnColumnsButton() {
        $(COLUMNS_BUTTON).should(Condition.visible).click();
    }

    public boolean isColumnsContainerVisible() {
        return !isVisible(COLUMNS_BUTTON_CONTAINER);
    }

    public void clickOnColumnsSelectedCheckBox() {
        $(COLUMNS_CHEСKBOX_SELECTED).should(Condition.visible).click();
    }

    public void clickOnColumnsEmptyCheckBox() {
        $(COLUMNS_CHEСKBOX_EMPTY).should(Condition.visible).click();
    }

    public boolean isColumnsCheckBoxSelected() {
        return isVisible(COLUMNS_CHEСKBOX_SELECTED);
    }

    //ColumnFilter Actions
    public void clickOnColumnFilterButton() {
        $(COLUMN_FILTER_BUTTON).hover().click();
    }

    public boolean isColumnFilterMenuExist() {
        return !isExist(COLUMN_FILTER_BUTTON_MENU);
    }

    public void clickOn_3rdMenuButton() {
        $(_3rd_BUTTON_MENU).should(Condition.visible).click();
    }

    public boolean isColumnFilterContainerExist() {
        return !isExist(COLUMN_FILTER_BUTTON_CONTAINER);
    }

    public boolean isColumnFilterCheckBoxSelected() {
        return isVisible(COLUMN_FILTER_CHEСKBOX_SELECTED);
    }

    public void clickOnColumnFilterSelectedCheckBox() {
        $(COLUMN_FILTER_CHEСKBOX_SELECTED).should(Condition.visible).click();
    }

    public void clickOnColumnFilterEmptyCheckBox() {
        $(COLUMN_FILTER_CHEСKBOX_EMPTY).should(Condition.visible).click();
    }

// - проверка на то, чтоб не была выбрана сортировка на колонке(не реализовано)

//    public boolean isIncreaseArrowDisplayed(String name) {
//        return isVisible(gridElements.getIncArrow(name));
//    }
//
//    public boolean isDecreaseArrowDisplayed(String name) {
//        return isVisible(gridElements.getDecArrow(name));
//    }
// - проверка на то, чтоб не была выбрана сортировка на колонке(не реализовано)

//    public void checkColumnToSort(String name) {
//        while (isIncreaseArrowDisplayed(name) || isDecreaseArrowDisplayed(name)) {
//            clickOnColumn(name);
//        }
//    }

    //Sorting
    public void clickOnColumn(String name) {
        gridElements.clickOnColumnName(name);
    }

    public boolean isColumnNumOfExecEnable() {
        return !isExist(COLUMN_NUM_OF);
    }

    public void clickOnScroll() {
        $(SCROLL).click();
    }

    private SelenideElement getTypeElement(int i) {
        return $x(String.format(COLUMN_TYPE_ELEMENT_PATTERN, i)).should(Condition.visible);
    }

    private SelenideElement getBenchmarkElement(int i) {
        return $x(String.format(COLUMN_BENCHMARK_ELEMENT_PATTERN, i)).should(Condition.visible);
    }

    private SelenideElement getNumOfExecElement(int i) {
        return $x(String.format(COLUMN_NUM_OF_ELEMENT_PATTERN, i)).should(Condition.visible);
    }

    private SelenideElement getStartTimeElement(int i) {
        return $x(String.format(COLUMN_START_TIME_ELEMENT_PATTERN, i)).should(Condition.visible);
    }

    public List<String> getStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            list.add(getTypeElement(i).getText());
        }
        return list;
    }

    public List<Date> getDateList() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            try {
                list.add(sdf.parse(getStartTimeElement(i).getText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Double> getDigitsList(String name) {
        List<Double> list = new ArrayList<>();
        if (name.equals("Benchmark price")) {
            for (int i = 0; i < 22; i++) {
                list.add(Double.parseDouble(getBenchmarkElement(i).getText()));
            }
        } else {
            for (int i = 0; i < 22; i++) {
                list.add(Double.parseDouble(getNumOfExecElement(i).getText()));
            }
        }
        return list;
    }

    public List getSortedList(List list) {
        List sortedList = new ArrayList(list);
        sortedList.sort(Comparator.naturalOrder());
        return sortedList;
    }

    public List getReversSortedList(List list) {
        List reversSortedList = new ArrayList(list);
        reversSortedList.sort(Comparator.reverseOrder());
        return reversSortedList;
    }

    //Chart

    private boolean isChartPanelExist() {
        return isExist(CHART_PANEL);
    }

    public void selectOrderExecution() {
        for (int i = 0; i < 22; i++) {
            SelenideElement element = getNumOfExecElement(i);
            double elementValue = Double.parseDouble(getNumOfExecElement(i).getText());
            if (elementValue > 0) {
                if (isChartPanelExist()) {
                    element.click();
                }
                element.click();
                indexForChart = i;
                break;
            }
        }
    }

    public void selectTwoPriceField() {
        if (!isExist(LINES_BUTTON_MENU)) {
            $(LINES_BUTTON).click();
        }
        if (!$(SELECTED_CHECKBOX_AVG_FILL_PRICE).isSelected()) {
            $(AVG_FILL_PRICE_IN_LINES_MENU).click();
        }
        Selenide.sleep(3000);
        if (!$(SELECTED_CHECKBOX_MARKER_MID_PRICE).isSelected()) {
            $(MID_PRICE_IN_LINES_MENU).click();
        }
        $(LINES_BUTTON).click();
    }

    public Double getExecValueAvg() {
        return Double.parseDouble($(EXEC_AVR_FILL_PRICE).getText());
    }

    public Double getInteractiveValueAvg() {
        return Double.parseDouble($(INTERACTIVE_L_C_AVR_F_P).getText());
    }

    public Double getOrderLineValueAvg() {
        String temp = $(LINE_AVG_FILL_PRICE).getText();
        temp = temp.substring(temp.indexOf("Exec price") + "Exec price".length() + 3);
        temp = temp.substring(0, temp.indexOf(","));
        double result = Double.parseDouble(temp);
        DecimalFormat dF = new DecimalFormat( "#.#####" );
        return Double.parseDouble(dF.format(result).replace(',','.'));
    }

    private SelenideElement getAvgElement(int i) {
        return $x(String.format(COLUMN_AVG_ELEMENT_PATTERN, i)).should(Condition.visible);
    }

    public Double getGridValueAvg() {
        SelenideElement element = getAvgElement(indexForChart);
        return Double.parseDouble(element.getText());
    }

    public String getExecAvgColor() {
        String hexColor =  $(COLOR_EXEC_AVG).getAttribute("fill");
        Color colorToRGB = Color.fromString(hexColor);
        return colorToRGB.asRgb();
    }

    public String getInteractiveAvgColor() {
        String color = $(COLOR_INTERACTIVE_AVG).getAttribute("style");
        color = color.substring(color.indexOf(":") + 2);
        color = color.substring(0, color.indexOf(";"));
        return color;
    }

    public Double getInteractiveValueMidPrice() {
        return Double.parseDouble($(INTERACTIVE_L_C_MID_P).getText());
    }

    public Double getExecValueMidPrice() {
        return Double.parseDouble($(EXEC_MID_PRICE).getText());
    }


}
