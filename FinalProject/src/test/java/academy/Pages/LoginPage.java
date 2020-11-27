package academy.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final Properties properties = new Properties();

    private final By LOGIN_FIELD = By.xpath("//input[@type='text']");           //поля для кредов и кнопка
    private final By PASSWORD_FIELD = By.xpath("//input[@type='password']");
    private final By SUBMIT = By.tagName("button");

    private final By ALERT_MESSAGE = By.xpath("//span[contains(text(),'Bad credentials')]");//скрытый элемент, появляется при некорректных логине или пароле
    private final By LOG_OUT_BUTTON = By.xpath("//a[@title='Sign Out']"); //кнопка Выхода со страницы Log Out

    public void connection() {
        try {
            properties.load(new FileInputStream("src/test/resources/url.properties"));
        } catch (IOException e) {
            System.out.println("No such file in folder");
        }
        Configuration.browser = properties.getProperty("Browser");
        Configuration.startMaximized = true;
        Selenide.open(properties.getProperty("LoginPage"));
    }

    public void fillCredential() {
        $(LOGIN_FIELD).should(Condition.visible).setValue("_");
        $(PASSWORD_FIELD).should(Condition.visible).setValue("_");
        $(SUBMIT).click();
    }

    public boolean isAlertMessagePresent() {
        return isExist(ALERT_MESSAGE);
    }

    public void clickOnLogOut() {
        $(LOG_OUT_BUTTON).should(Condition.visible).click();
    }
}
