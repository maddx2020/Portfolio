package academy.Tests;

import academy.Pages.LoginPage;
import academy.Pages.PageFactory;
import academy.Pages.SummaryPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest extends Assertions {

    private static LoginPage loginPage;
    private static SummaryPage summary;

    @BeforeAll
    public static void init() {
        loginPage = PageFactory.createPage(LoginPage.class);
        loginPage.connection();
        loginPage.fillCredential();
        summary = PageFactory.createPage(SummaryPage.class);
    }

    @AfterAll
    public static void logOut() {
        loginPage.clickOnLogOut();
    }

    @Test
    public void testCorrectLogin() {
        assertFalse(loginPage.isAlertMessagePresent());
    }

    @Test
    public void testSettingsButton() {
        assertTrue(summary.isSettingsPresent());
    }

    @Test
    public void testBenchmarkSelector() {
        assertTrue(summary.isBenchmarkPresent());
    }

    @Test
    public void testToolbarElements() {
        assertAll(
                () -> assertTrue(summary.isToolbarElementPresent("Summary")),
                () -> assertTrue(summary.isToolbarElementPresent("Grid & chart")),
                () -> assertTrue(summary.isToolbarElementPresent("Histogram")),
                () -> assertTrue(summary.isToolbarElementPresent("Scatter-plot")),
                () -> assertTrue(summary.isToolbarElementPresent("Reports"))
        );
    }
}
