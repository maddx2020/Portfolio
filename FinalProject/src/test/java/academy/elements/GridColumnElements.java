package academy.elements;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class GridColumnElements {

    private final String COLUMN_NAME_PATTERN = "//span[@ref='eText' and contains(text(),'%s')]"; // название колонки

// - проверка на то, чтоб не была выбрана сортировка на колонке(не реализовано)

//    private final String INCREASE_ARROW = "//span[contains(text(),'%s')]/..//span[@ref='eSortAsc']"; //стрелка сортировки на возрастание
//    private final String DECREASE_ARROW = "//span[contains(text(),'%s')]/..//span[@ref='eSortDesc']"; //стрелка сортировки на убывание
//
//    public By getIncArrow(String name) {
//        return By.xpath(String.format(INCREASE_ARROW, name));
//    }
//
//    public By getDecArrow(String name) {
//        return By.xpath(String.format(DECREASE_ARROW, name));
//    }

    public void clickOnColumnName(String name) {
        $x(String.format(COLUMN_NAME_PATTERN, name)).should(Condition.visible).click();
    }

}
