import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import selenide.core.SelenideTestBase;
import selenide.pages.google.GoogleSearchPage;
import selenide.pages.google.GoogleSearchResultPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Features("Google search")
@Stories(("WEB-888"))
public class GoogleTest extends SelenideTestBase{
    private String google = "http://google.com/ncr";
    private String searchText = "selenide";



    @Test
    public void searchInGoogleTest(){
        //ChromeDriverManager.getInstance().version("2.29").setup();
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = WebDriverRunner.CHROME;

        open(google);
        $(By.name("q")).val(searchText).pressEnter();
        $$("#ires .g").shouldHave(size(10));
        $("#ires .g").shouldBe(visible).shouldHave(text("Selenide: concise UI tests in Java"), text("selenide.org"));
    }

    @Test
    public void searchInGoogleWithPageObjectTest(){
        open(google);
        GoogleSearchPage googleSearch = new GoogleSearchPage();
        googleSearch.searchFor(searchText);
        GoogleSearchResultPage googleResult = new GoogleSearchResultPage();
        googleResult.getLinksResults()
                .shouldHave(size(10)).first().shouldHave(text("Selenide: concise UI tests in Java"));

    }

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

