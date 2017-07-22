package advanced_interactions.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;
import static org.testng.Assert.assertTrue;
public class AlertBoxTest extends WebDriverTestBase {
    String webPageForW3 = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm";
    @Test
    public void testToAlert() {
        webDriver.get(webPageForW3);
        webDriver.switchTo().frame("iframeResult");
        WebElement tryItButton = webDriver.findElement(By.xpath("html/body/button88"));
        tryItButton.click();
        Alert alert1 = webDriver.switchTo().alert();
        // alert1.accept()
        // alert1.getText()
        alert1.dismiss();
        WebElement fildResult = webDriver.findElement(By.id("demo"));
        String text = fildResult.getText();
        assertTrue(text.equals("You pressed Cancel!"));
    }
}
