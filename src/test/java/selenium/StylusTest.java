package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;
import selenium.pages.stylus.MainPage;

public class StylusTest extends WebDriverTestBase {

    private String mainPage = "https://stylus.ua/";
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

    @Test
    public void verifySearchPhoneAndOpenLinkWithPageObject(){
        webDriver.get(mainPage);
        MainPage mainPage = PageFactory.initElements(webDriver, MainPage.class);
        mainPage.searchFor(searchPhone);

        WebElement link = webDriver.findElement(By.xpath(resultLinkXPath));
        Assert.assertTrue(link.getText().contains("Sony"));
        Assert.assertTrue(link.getText().contains("Z2"));

        link.click();
        WebElement title = webDriver.findElement(By.xpath(openedLinkTitle));
        Assert.assertTrue(title.getAttribute("content").contains("Sony"));
        Assert.assertTrue(title.getAttribute("content").contains("Z2"));
    }

}
