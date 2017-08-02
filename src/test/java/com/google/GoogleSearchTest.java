package com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import selenium.core.WebDriverTestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

@Features("Google search")
@Stories(("WEB-887"))
public class GoogleSearchTest extends WebDriverTestBase{

    private String googleSearch = "https://www.google.com.ua";
    private String searchText = "Selenium";

    @Title("Test to search Selenium word")
    @Test
    public void searchTest(){
        System.setProperty("webdriver.chrome.driver", "D:\\IT\\Javacore\\chromedriver.exe");
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        webDriver.manage().window().maximize();
        webDriver.get(googleSearch);
        By searchLocator = By.id("lst-ib");
        WebElement searchField = webDriver.findElement(searchLocator);
        searchField.sendKeys(searchText);
        searchField.submit();
        By linkLokator = By.xpath(".//*[@id='rso']//h3[@class='r']/a[text()='Selenium - Web Browser Automation']");
        WebElement link = webDriver.findElement(linkLokator);
        assertTrue(link.getText().contains(searchText));
        //webDriver.quit();
    }

    @Test
    public void searchSeleniumWordsTest(){
        System.setProperty("webdriver.chrome.driver", "D:\\IT\\Javacore\\chromedriver.exe");
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //webDriver.manage().window().maximize();
        webDriver.get(googleSearch);
        By searchLocator = By.id("lst-ib");
        WebElement searchField = webDriver.findElement(searchLocator);
        searchField.sendKeys(searchText);
        searchField.submit();

        By linkLokators = By.xpath("//h3[@class='r']");
        List <WebElement> links = webDriver.findElements(linkLokators);
        for(WebElement link: links){
            boolean result = link.getText().contains(searchText);
            if (result == true){
                System.out.println(link.getText());
            }
        }
        //webDriver.quit();
    }

}
