package selenide.pages.google;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class AbstractPage {

    public void jsClick(String locator, String type) {
        switch (type) {
            case "id":
                executeJavaScript("document.getElementById(\"" + locator + "\").click()");
                break;
            case "name":
                executeJavaScript("document.getElementsByName(\"" + locator + "\")[0].click()");
                break;
        }
    }
}
