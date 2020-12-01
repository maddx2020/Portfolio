package academy.tests;

import academy.pages.LoginPage;
import academy.pages.PageFactory;
import academy.pages.ScatterPlotPage;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ScatterPlotTest extends Assertions {

    private static LoginPage loginPage;
    private static ScatterPlotPage scatterPlot;
    private static String previousValue;
    private static String presentValue;

    @BeforeAll
    public static void init() {
        loginPage = PageFactory.createPage(LoginPage.class);
        loginPage.connection();
        loginPage.fillCredential();
        scatterPlot = PageFactory.createPage(ScatterPlotPage.class);
        scatterPlot.openScatterPlotPage();
    }

    @AfterAll
    public static void logOut() {
        loginPage.clickOnLogOut();
    }

    public static List<String> attributeList() {
        List<String> list = new ArrayList<>();
        if (scatterPlot.isAttributeContainerVisible()) {
            scatterPlot.clickOn_X_AttributeDropDownButton();
        }
        for (SelenideElement e : scatterPlot.getAttributeCollection()) {
            list.add(e.getText());
        }
        scatterPlot.clickOn_X_AttributeDropDownButton();
        return list;
    }

    @DisplayName("Check X Axis Name")
    @ParameterizedTest
    @MethodSource("attributeList")
    public void test_X_AxisNameEqualAttName(String attribute) {
        if (scatterPlot.isAttributeContainerVisible()) {
            scatterPlot.clickOn_X_AttributeDropDownButton();
        }
        scatterPlot.clickOnAttributeName(attribute);
        assertEquals(attribute, scatterPlot.get_X_AxisName());
    }

    @DisplayName("Check Y Axis Name And No Grid Area")
    @ParameterizedTest
    @MethodSource("attributeList")
    public void test_Y_AxisNameEqualAttNameAndNoGrid(String attribute) {
        if (scatterPlot.isAttributeContainerVisible()) {
            scatterPlot.clickOn_Y_AttributeDropDownButton();
        }
        scatterPlot.clickOnAttributeName(attribute);
        assertAll(
                () -> assertEquals(attribute, scatterPlot.get_Y_AxisName()),
                () -> assertFalse(scatterPlot.isSPGridVisible())
        );
    }

    @DisplayName("Check X Axis Digits Reloaded")
    @ParameterizedTest
    @MethodSource("attributeList")
    public void test_X_AxisReload(String attribute) {
        if (scatterPlot.isAttributeContainerVisible()) {
            scatterPlot.clickOn_X_AttributeDropDownButton();
        }
        scatterPlot.clickOnAttributeName(attribute);
        if (attribute.equals("Avg fill price")) {
            previousValue = scatterPlot.get_X_LastDigit();
        } else {
            try {
                presentValue = scatterPlot.get_X_LastDigit();
                assertNotEquals(previousValue, presentValue);
            } finally {
                previousValue = presentValue;
            }
        }
    }

    @DisplayName("Check Y Axis Digits Reloaded")
    @ParameterizedTest
    @MethodSource("attributeList")
    public void test_Y_AxisReload(String attribute) {
        if (scatterPlot.isAttributeContainerVisible()) {
            scatterPlot.clickOn_Y_AttributeDropDownButton();
        }
        scatterPlot.clickOnAttributeName(attribute);
        if (attribute.equals("Avg fill price")) {
            previousValue = scatterPlot.get_Y_LastDigit();
        } else {
            try {
                presentValue = scatterPlot.get_Y_LastDigit();
                assertNotEquals(previousValue, presentValue);
            } finally {
                previousValue = presentValue;
            }
        }
    }
}
