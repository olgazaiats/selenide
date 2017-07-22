package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;

public class StylusTest extends WebDriverTestBase {

    private String mainPage = "http://stylus.com.ua/";
    private By searchField = By.name("q");
    private String searchBtnXPath = ".//*[@id='head-search']/form/input[@type=\"submit\"]";
    private String searchPhone = "Sony Z2";
    private String resultLinkXPath = ".//*[@id='search-list']/ul//a";
    private String openedLinkTitle = "//html//meta[@name=\"title\"]";

    @Test
    public void verifySearchPhoneAndOpenLink(){
        webDriver.get(mainPage);
        WebElement searchFieldElement = webDriver.findElement(searchField);
        searchFieldElement.sendKeys(searchPhone);
        //Commented - for PageObject pattern - doesn't work
        /*MainPage mainPage = new MainPage();
        mainPage.searchFor(searchPhone);*/

        WebElement searchBtnElement = webDriver.findElement(By.xpath(searchBtnXPath));
        searchBtnElement.click();
        WebElement link = webDriver.findElement(By.xpath(resultLinkXPath));
        Assert.assertTrue(link.getText().contains("Sony"));
        Assert.assertTrue(link.getText().contains("Z2"));

        link.click();
        WebElement title = webDriver.findElement(By.xpath(openedLinkTitle));
        Assert.assertTrue(title.getAttribute("content").contains("Sony"));
        Assert.assertTrue(title.getAttribute("content").contains("Z2"));


    }

}
