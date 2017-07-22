package selenide.pages.google;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage extends AbstractPage{
    private SelenideElement search = $(By.name("q"));
    private String searchButton = "btnK";

    public GoogleSearchPage searchFor(String searchText){
        search.val(searchText).pressEnter();
        return this;
    }

    public GoogleSearchPage searchWithButton(String searchText){
        search.val(searchText);
        jsClick(searchButton, "name");
        return this;
    }



}
