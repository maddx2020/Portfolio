package academy.tests;

import academy.pages.GridPage;
import academy.pages.LoginPage;
import academy.pages.PageFactory;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

public class GridChartTest extends Assertions {
    private static LoginPage loginPage;
    private static GridPage grid;

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

    @BeforeEach
    public void prepare() {
        while (grid.isColumnNumOfExecEnable()) {
            grid.clickOnScroll();
        }
        grid.selectOrderExecution();
        grid.selectTwoPriceField();
    }

    @Test
    public void chekAvgFillPriceValue() {
        Double expected = grid.getExecValueAvg();
        String e = grid.getExecAvgColor();
        String a = grid.getInteractiveAvgColor();
        assertAll(
                () -> assertEquals(expected, grid.getInteractiveValueAvg()),
                () -> assertEquals(expected, grid.getGridValueAvg()),
                () -> assertEquals(expected, grid.getOrderLineValueAvg())
        );
    }

    @Test
    public void checkMidPriceValue() {
        Double expected = grid.getExecValueMidPrice();
        assertEquals(expected, grid.getInteractiveValueMidPrice());
    }
// некорректно работает если выбрано значение VWAP для отображения
    @Test
    public void checkColor() {
        List<String> e = Arrays.asList(grid.getExecAvgColor());
        List<String> a = Arrays.asList(grid.getInteractiveAvgColor());
        assertEquals(e,a);
    }

}

