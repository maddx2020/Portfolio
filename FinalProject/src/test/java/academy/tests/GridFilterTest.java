package academy.tests;

import academy.pages.GridPage;
import academy.pages.LoginPage;
import academy.pages.PageFactory;
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
        try {
            if (grid.isFiltersContainerExist()) {
                grid.clickOnFiltersButton();
            }
            if (grid.isFilterCheckBoxSelected()) {
                grid.clickOnFiltersSelectedCheckBox();
            }
            assertFalse(grid.isColumnVisible());
        } finally {
            grid.clickOnFiltersEmptyCheckBox();
            grid.clickOnFiltersButton();
        }
    }

    @Test
    public void testColumnAppearFiltersButton() {
        try {
            if (grid.isFiltersContainerExist()) {
                grid.clickOnFiltersButton();
            }
            if (grid.isFilterCheckBoxSelected()) {
                grid.clickOnFiltersSelectedCheckBox();
            }
            grid.clickOnFiltersEmptyCheckBox();
        } finally {
            assertTrue(grid.isColumnVisible());
            grid.clickOnFiltersButton();
        }
    }

    @Test
    public void testColumnDisappearColumnsButton() {
        try {
            if (grid.isColumnsContainerVisible()) {
                grid.clickOnColumnsButton();
            }
            if (grid.isColumnsCheckBoxSelected()) {
                grid.clickOnColumnsSelectedCheckBox();
            }
            assertFalse(grid.isColumnVisible());
        } finally {
            grid.clickOnColumnsEmptyCheckBox();
            grid.clickOnColumnsButton();
        }
    }

    @Test
    public void testColumnAppearColumnsButton() {
        try {
            if (grid.isColumnsContainerVisible()) {
                grid.clickOnColumnsButton();
            }
            if (grid.isColumnsCheckBoxSelected()) {
                grid.clickOnColumnsSelectedCheckBox();
            }
            grid.clickOnColumnsEmptyCheckBox();
            assertTrue(grid.isColumnVisible());
        } finally {
            grid.clickOnColumnsButton();
        }
    }

    @Test
    public void testColumnDisappearColumnFilter() {
        try {
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
        } finally {
            grid.clickOnColumnFilterEmptyCheckBox();
            grid.clickOn_3rdMenuButton();
        }
    }

    @Test
    public void testColumnAppearColumnFilter() {
        try {
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
        } finally {
            grid.clickOn_3rdMenuButton();
        }
    }
}
