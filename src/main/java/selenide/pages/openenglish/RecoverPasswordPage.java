package selenide.pages.openenglish;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class RecoverPasswordPage {
    private SelenideElement emailField = $(By.id("email"));
    private SelenideElement submitButton = $(By.id("recovery-submit"));

    public RecoverPasswordPage enterEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public RecoverPasswordPage removeEmail(){
        emailField.clear();
        return this;
    }

    public RecoverPasswordPage submitEmail(){
        submitButton.click();
        return this;
    }

}
