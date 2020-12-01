/*

Было желание сделать красивый тест, но не хватило времени ((

Обязательное условие - нужные колонки присутствуют в гриде (проверка на это не реализована!!!),
                     - без символа группировки чисел (проверка на это не реализована!!!).
(проверка на то, что на какой-то колонке уже выбрана сортировка - до конца не реализована!!!)

 */
package academy.tests;

import academy.pages.GridPage;
import academy.pages.LoginPage;
import academy.pages.PageFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class GridSortTest extends Assertions {
    private static LoginPage loginPage;
    private static GridPage grid;
    private List displayedData;
    private List sortData;
    private List reversSortData;
    private List resetData;

    @BeforeAll
    public static void init() {
        loginPage = PageFactory.createPage(LoginPage.class);
        loginPage.connection();
        loginPage.fillCredential();
        grid = PageFactory.createPage(GridPage.class);
        grid.openGridPage();
    }

    @AfterAll
    public static void logOut() {
        loginPage.clickOnLogOut();
    }

    public static String[] columnName() {
        return new String[]{"Type", "Start time", "Num of executions", "Benchmark price"};
    }

    @DisplayName("Grid Column Sort Order ")
    @ParameterizedTest
    @MethodSource("columnName")
    public void testColumnSort(String name) {
        if (name.equals("Type")) {
//            grid.checkColumnToSort(name);   - проверка на то, чтоб не была выбрана сортировка на колонке(не реализовано)
            displayedData = grid.getStringList();
            grid.clickOnColumn(name);
            sortData = grid.getStringList();
            grid.clickOnColumn(name);
            reversSortData = grid.getStringList();
            grid.clickOnColumn(name);
            resetData = grid.getStringList();
        }
        if (name.equals("Start time")) {
            displayedData = grid.getDateList();
            grid.clickOnColumn(name);
            sortData = grid.getDateList();
            grid.clickOnColumn(name);
            reversSortData = grid.getDateList();
            grid.clickOnColumn(name);
            resetData = grid.getDateList();
        }
        if (name.equals("Benchmark price") || name.equals("Num of executions")) {
            if (name.equals("Num of executions")) {
                while (grid.isColumnNumOfExecEnable()) {
                    grid.clickOnScroll();
                }
            }
            displayedData = grid.getDigitsList(name);
            grid.clickOnColumn(name);
            sortData = grid.getDigitsList(name);
            grid.clickOnColumn(name);
            reversSortData = grid.getDigitsList(name);
            grid.clickOnColumn(name);
            resetData = grid.getDigitsList(name);
        }
        assertAll(
                () -> assertEquals(sortData, grid.getSortedList(sortData)),
                () -> assertEquals(reversSortData, grid.getReversSortedList(reversSortData)),
                () -> assertEquals(displayedData, resetData)
        );
    }
}
