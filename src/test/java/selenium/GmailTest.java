package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.core.WebDriverTestBase;

public class GmailTest extends WebDriverTestBase{
    private String gmailURL = "https://gmail.com";
    private String email = "olga.qatest@gmail.com";
    private String password = "qwertyqwerty";

    By emailFieldId = By.id("identifierId");
    By passField = By.name("password");
    By nextBtn = By.xpath("//span[@class=\"RveJvd snByac\"]");
    By nextBtn2 = By.xpath(".//*[@id='passwordNext']");

    @Test
    public void verifySignInToGmail() {
        webDriver.get(gmailURL);
        Assert.assertTrue(webDriver.getTitle().contains("Gmail"));

        WebElement emailField = webDriver.findElement(emailFieldId);
        emailField.isDisplayed();
        emailField.sendKeys(email);

        WebElement nextButton = webDriver.findElement(nextBtn);
        nextButton.click();

        WebElement passwordField = webDriver.findElement(passField);
        passwordField.sendKeys(password);

        WebElement nextButton2 = webDriver.findElement(nextBtn2);
        nextButton2.isEnabled();
        nextButton2.click();

        webDriver.findElement(By.xpath("//div[contains(text(), \"COMPOSE\")]")).isDisplayed();
    }
}
