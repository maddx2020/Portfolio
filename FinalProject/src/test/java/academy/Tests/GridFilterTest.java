package academy.Tests;

import academy.Pages.GridPage;
import academy.Pages.LoginPage;
import academy.Pages.PageFactory;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GridFilterTest extends Assertions {

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

    @Test
    public void testColumnDisappearFiltersButton() {
        if (grid.isFiltersContainerExist()) {
            grid.clickOnFiltersButton();
        }
        if (grid.isFilterCheckBoxSelected()) {
            grid.clickOnFiltersSelectedCheckBox();
        }
        assertFalse(grid.isColumnVisible());
        grid.clickOnFiltersEmptyCheckBox();
        grid.clickOnFiltersButton();
    }

    @Test
    public void testColumnAppearFiltersButton() {
        if (grid.isFiltersContainerExist()) {
            grid.clickOnFiltersButton();
        }
        if (grid.isFilterCheckBoxSelected()) {
            grid.clickOnFiltersSelectedCheckBox();
        }
        grid.clickOnFiltersEmptyCheckBox();
        assertTrue(grid.isColumnVisible());
        grid.clickOnFiltersButton();
    }

    @Test
    public void testColumnDisappearColumnsButton() {
        if (grid.isColumnsContainerVisible()) {
            grid.clickOnColumnsButton();
        }
        if (grid.isColumnsCheckBoxSelected()) {
            grid.clickOnColumnsSelectedCheckBox();
        }
        assertFalse(grid.isColumnVisible());
        grid.clickOnColumnsEmptyCheckBox();
        grid.clickOnColumnsButton();
    }

    @Test
    public void testColumnAppearColumnsButton() {
        if (grid.isColumnsContainerVisible()) {
            grid.clickOnColumnsButton();
        }
        if (grid.isColumnsCheckBoxSelected()) {
            grid.clickOnColumnsSelectedCheckBox();
        }
        grid.clickOnColumnsEmptyCheckBox();
        assertTrue(grid.isColumnVisible());
        grid.clickOnColumnsButton();
    }

    @Test
    public void testColumnDisappearColumnFilter() {
        if (grid.isColumnFilterMenuExist()) {
            grid.clickOnColumnFilterButton();
        }
        Selenide.sleep(3000);
        if (grid.isColumnFilterContainerExist()) {
            grid.clickOn_3rdMenuButton();
        }
        Selenide.sleep(3000);
        if (grid.isColumnFilterCheckBoxSelected()) {
            grid.clickOnColumnFilterSelectedCheckBox();
        }
        assertFalse(grid.isColumnVisible());
        grid.clickOnColumnFilterEmptyCheckBox();
        grid.clickOn_3rdMenuButton();
    }

    @Test
    public void testColumnAppearColumnFilter() {
        if (grid.isColumnFilterMenuExist()) {
            grid.clickOnColumnFilterButton();
        }
        if (grid.isColumnFilterContainerExist()) {
            grid.clickOn_3rdMenuButton();
        }
        if (grid.isColumnFilterCheckBoxSelected()) {
            grid.clickOnColumnFilterSelectedCheckBox();
        }
        grid.clickOnColumnFilterEmptyCheckBox();
        assertTrue(grid.isColumnVisible());
        grid.clickOn_3rdMenuButton();
    }
}
