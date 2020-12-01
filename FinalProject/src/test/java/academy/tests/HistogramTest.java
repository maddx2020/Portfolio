package academy.tests;

import academy.pages.HistogramPage;
import academy.pages.LoginPage;
import academy.pages.PageFactory;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class HistogramTest extends Assertions {

    private static HistogramPage histogram;
    private static LoginPage loginPage;

    @BeforeAll
    public static void init() {
        loginPage = PageFactory.createPage(LoginPage.class);
        loginPage.connection();
        loginPage.fillCredential();
        histogram = PageFactory.createPage(HistogramPage.class);
        histogram.openHistogramPage();
    }

    @AfterAll
    public static void logOut() {
        loginPage.clickOnLogOut();
    }

    public static ElementsCollection listOfBars() {
        return histogram.getListOfBars();
    }

    @DisplayName("Histogram Test For Diff Bars")
    @ParameterizedTest(name = "{index} bar")
    @MethodSource("listOfBars")
    public void testToolTipPresent(SelenideElement element) {
        histogram.hoverOneBar(element);
        assertTrue(histogram.isTooltipBarPresent());
    }
}
