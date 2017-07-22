package selenium.pages.stylus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.core.WebDriverTestBase;

public class MainPage extends WebDriverTestBase{
    private String searchFieldXPath = ".//*[@id='head-search']/form/input[@name=\"q\"]";
    private By searchField = By.name("q");
    private String searchBtnXPath = ".//*[@id='head-search']/form/input[@type=\"submit\"]";

// To debug: method returns NullPointer.
    public MainPage searchFor(String searchWord){
        WebElement element = webDriver.findElement(searchField);
        element.sendKeys(searchWord);
        WebElement element2 = webDriver.findElement(By.xpath(searchBtnXPath));
        element2.click();
        return this;
    }



}
