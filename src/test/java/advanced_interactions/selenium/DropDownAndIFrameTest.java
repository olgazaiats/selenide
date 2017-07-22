package advanced_interactions.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class DropDownAndIFrameTest extends WebDriverTestBase{

    @Test
    public void testDropdown() {

        webDriver.get("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");

        WebElement iFrame = webDriver.findElement(By.xpath(".//iframe[@title='3rd party ad content']"));
        webDriver.switchTo().frame(iFrame);

        webDriver.switchTo().frame(0);

        webDriver.switchTo().frame("iframeResult");

        //Get the Dropdown as a Select using its name attribute
        Select make = new Select(webDriver.findElement(By.xpath("html/body/select88")));

        System.out.printf("%s ", make.getOptions().size());

        for (WebElement m : make.getOptions()) {
            System.out.print(m.getText());
        }

        //Verify Dropdown does not support multiple selection
        assertFalse(make.isMultiple());

        Assert.assertEquals(4, make.getOptions().size());

        make.selectByVisibleText("Volvo");

        Assert.assertEquals("Volvo", make.getFirstSelectedOption().getText());

        make.selectByValue("opel");

        Assert.assertEquals("Opel", make.getFirstSelectedOption().getText());

        make.selectByIndex(2);
        Assert.assertEquals("Opel", make.getFirstSelectedOption().getText());

        webDriver.switchTo().defaultContent();

        assertTrue(webDriver.getTitle().equals("Frameset Example Title (Replace this section with your own title)"));

    }
}