package academy.Tests;

import academy.Helpers.CredConnectionApi;
import academy.Pages.LoginPage;
import academy.Pages.PageFactory;
import academy.Pages.SummaryPage;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class SummaryApiTest extends Assertions {

    private static Header authorization;
    private static LoginPage loginPage;
    private static SummaryPage summary;

    @BeforeAll
    static void init() throws IOException {
        CredConnectionApi cc = new CredConnectionApi();
        HttpResponse response = cc.makeConnection();
        String token = cc.getToken(response);
        authorization = cc.getAuthorization(token);

        loginPage = PageFactory.createPage(LoginPage.class);
        loginPage.connection();
        loginPage.fillCredential();
        summary = PageFactory.createPage(SummaryPage.class);
        summary.openSummaryPage();
    }

    @AfterAll
    public static void logOut() {
        loginPage.clickOnLogOut();
    }

    @Test
    public void testApiDataEqualsWebData() throws IOException {

        List<String> expected = summary.getTableDataFromWeb();
        List<String> actual = summary.getTableDataFromApi(authorization);

        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

}
