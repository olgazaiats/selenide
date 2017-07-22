package advanced_interactions;

import org.testng.annotations.Test;
import selenide.pages.google.GoogleSearchPage;
import selenide.pages.google.GoogleSearchResultPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class TestTest {
    private String google = "http://google.com/ncr";
    private String searchText = "selenide";

    @Test
    public void searchInGoogleWithJSExecutor(){
        open(google);
        GoogleSearchPage search = new GoogleSearchPage();
        search.searchWithButton(searchText);
        GoogleSearchResultPage googleResult = new GoogleSearchResultPage();
        googleResult.getLinksResults()
                .shouldHave(size(10)).first().shouldHave(text("Selenide: concise UI tests in Java"));

    }
}
