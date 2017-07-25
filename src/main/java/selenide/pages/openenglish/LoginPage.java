package selenide.pages.openenglish;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import selenide.core.SelenideTestBase;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends SelenideTestBase{
    private SelenideElement usernameField = $(By.id("username"));
    private SelenideElement passwordField = $(By.id("password"));
    private SelenideElement loginButton = $(By.id("login-btn"));
    private SelenideElement logOutOption = $(By.xpath(".//*[@id='account-menu']//a[@href=\"/j_spring_security_logout\"]"));
    private SelenideElement accountMenu = $(By.xpath(".//*[@id='account-menu-nav']/strong"));
    private SelenideElement continuePopUpButton = $(By.xpath(".//*[@id='login_content']//a[@class=\"black-btn migration-continue-btn\"]"));

    public LoginPage clickContinue(){
        continuePopUpButton.click();
        return this;
    }

    public LoginPage enterEmail(String email){
        usernameField.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage logIn(){
        loginButton.click();
        return this;
    }

    public LoginPage logOut(){
        accountMenu.hover();
        logOutOption.click();
        return this;
    }


}
