package selenide.pages.google;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage extends AbstractPage{
    private SelenideElement search = $(By.name("q"));
    private String searchButton = "btnK";

    @Step("Searches for {0} text")
    public GoogleSearchPage searchFor(String searchText){
        search.val(searchText).pressEnter();
        return this;
    }

    @Step("Searches for {0} text with button")
    public GoogleSearchPage searchWithButton(String searchText){
        search.val(searchText);
        jsClick(searchButton, "name");
        return this;
    }



}
