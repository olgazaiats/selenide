package selenide.pages.google;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class GoogleSearchResultPage {
    private ElementsCollection linksResults = $$("#ires .g");

    public ElementsCollection getLinksResults() {
        return linksResults;
    }
}
