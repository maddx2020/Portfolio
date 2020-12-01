package academy.pages;

import academy.helpers.JsonHelper;
import com.codeborne.selenide.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SummaryPage extends BasePage {

    private final By SETTINGS_BUTTON = By.xpath("//a[@title='Settings']"); //кнопка Настройки
    private final By BENCHMARK_BUTTON = By.xpath("//app-benchmark-selection"); //кнопка Бенчмарк
    private final By ALGO_TABLE = By.xpath("//div[contains(text(),'Algo Performance')]"); //табличка АлгоПерфоманс
    private final By DATA_VALUES_ALGO = By.xpath("//div[@class='simple-table__content']"); //данные всей таблички АлгоПерфоманс
    private final String URL_REQUEST = "https://app.qa.axa.epm-rtc.projects.epam.com/api/v1/post-trade/orders/query?benchmarkType=VWAP";
    private final String JSON_REQUEST = "{\"expressions\":" +
            "[\"orderType\"," +
            "\"count() as all\"," +
            "\"countIf(priceImprovementToBenchmarkAmount < 0) as negative\"," +
            "\"countIf(priceImprovementToBenchmarkAmount > 0) as positive\"]," +
            "\"groupByExpressions\":[\"orderType\"]}";

    public boolean isSettingsPresent() {
        return isVisible(SETTINGS_BUTTON);
    }

    public boolean isBenchmarkPresent() {
        return isVisible(BENCHMARK_BUTTON);
    }

    public boolean isToolbarElementPresent(String element) {
        return toolbar.getToolbarItem(element).isEnabled();
    }

    public void openSummaryPage() {
        toolbar.clickOnItem("Summary");
    }

    public List<String> getTableDataFromWeb() {
        $(ALGO_TABLE).should(Condition.visible);
        return Arrays.asList($(DATA_VALUES_ALGO).getText().split("\n"));
    }

    public List<String> getTableDataFromApi(Header authorization) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL_REQUEST);
        post.setHeader(authorization);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(JSON_REQUEST));
        HttpResponse response = client.execute(post);

        String jsonString = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        JsonHelper jsonHelper = mapper.readValue(jsonString, JsonHelper.class);
        StringBuilder sb = new StringBuilder();

        for (JsonHelper.ItemInJsonList j : jsonHelper.items) {
            sb.append(j.orderType).append(" ").append(j.all).append(" ").append(j.negative).append(" ").append(j.positive).append(" ");
        }
        return Arrays.asList(sb.toString().split(" "));
    }
}
