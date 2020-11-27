package academy.Pages;

public class PageFactory {

    public static <T extends BasePage> T createPage(Class<T> pageClass) {
        if (pageClass.equals(LoginPage.class)) {
            return (T) new LoginPage();
        }
        if (pageClass.equals(SummaryPage.class)) {
            return (T) new SummaryPage();
        }
        if (pageClass.equals(GridPage.class)) {
            return (T) new GridPage();
        }
        if (pageClass.equals(HistogramPage.class)) {
            return (T) new HistogramPage();
        }
        if (pageClass.equals(ScatterPlotPage.class)) {
            return (T) new ScatterPlotPage();
        }
        throw new RuntimeException("No such pageClass");
    }
}
