package academy.Elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ApplicationToolbar {
    private String ToolbarItemPattern = "//div[contains(text(),'%s')]";

    public void clickOnItem(String name){
        $x(String.format(ToolbarItemPattern, name)).should(Condition.visible).click();
    }

    public SelenideElement getToolbarItem(String name){
        return $x(String.format(ToolbarItemPattern, name));
    }
}
