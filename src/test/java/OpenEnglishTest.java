import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import selenide.core.SelenideTestBase;
import selenide.pages.openenglish.LoginPage;
import selenide.pages.openenglish.RecoverPasswordPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Features("OpenEnglish")
@Stories(("WEB-777"))
public class OpenEnglishTest extends SelenideTestBase{
    private String loginURL = "https://learningplatform.stg.openenglish.com";
    private String recoverEmailURL = "https://learningplatform.stg.openenglish.com/recovery.html";
    private String unRegisteredEmail = "olga@gmail.com";
    private String loginEmail = "shiosaky@gmail.com";
    private String loginPass = "Test123";
    private String tooltipXPath = "//div[@class=\"tooltipster-content\"]";


    @BeforeTest
    public void loginLogout(){
        open(loginURL);
        LoginPage loginPage = new LoginPage();
        loginPage.clickContinue();
        loginPage.enterEmail(loginEmail).enterPassword(loginPass).logIn().logOut();
    }


    @Title("Test for Email field validation on Recover Password page")
    @Test
    public void recoverEmailValidationTest() {
        open(recoverEmailURL);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage();
        //Unregistered email validation
        recoverPasswordPage.enterEmail(unRegisteredEmail).submitEmail();
        $(By.xpath(tooltipXPath)).shouldHave(text("El email que usted ingresó no ha sido reconocido. Por favor, inténtelo de nuevo."));
        recoverPasswordPage.removeEmail();

        //Invalid email (no @) validation
        recoverPasswordPage.enterEmail("olga.com").submitEmail();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("Por favor, ingresa el correo electrónico vinculado a tu cuenta. Si necesitas ayuda, diríjete a las opciones de ayuda más abajo."));
        recoverPasswordPage.removeEmail();

        //Invalid email (no.com) validation
        recoverPasswordPage.enterEmail("olga@gmail").submitEmail();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("Por favor, ingresa el correo electrónico vinculado a tu cuenta. Si necesitas ayuda, diríjete a las opciones de ayuda más abajo."));
        recoverPasswordPage.removeEmail();
    }

    @Test
    public void signInEmailFieldValidationTest(){
        open(loginURL);
        //Unregistered email validation
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(unRegisteredEmail).enterPassword(loginPass).logIn();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("El email que usted ingresó no ha sido reconocido. Por favor, inténtelo de nuevo."));

        //Invalid email (no @) validation
        loginPage.enterEmail("olga.com").enterPassword(loginPass).logIn();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("El email que usted ingresó no ha sido reconocido. Por favor, inténtelo de nuevo."));

        //Invalid email (no.com) validation
        loginPage.enterEmail("olga@gmail").enterPassword(loginPass).logIn();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("El email que usted ingresó no ha sido reconocido. Por favor, inténtelo de nuevo."));
    }

    @Test
    public void verifyContrasenaFieldTest(){
        open(loginURL);
        LoginPage loginPage = new LoginPage();
        //Invalid password, the first attempt
        loginPage.enterEmail(loginEmail).enterPassword("invalidPassword").logIn();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("Parece que hay un error en la contraseña. Por favor, inténtelo de nuevo."));

        //Invalid password, the second attempt
        loginPage.enterPassword("invalidPassword").logIn();
        $(By.xpath(tooltipXPath))
                .shouldHave(text("Parece que hay un error en la contraseña. Por favor, inténtelo de nuevo."));
        $("#captcharesponse").shouldHave(attribute("placeholder", "Ingrese texto de seguridad"));
    }

    @Test
    public void verifyRecordarMisDatosCheck(){
        open(loginURL);
        LoginPage loginPage = new LoginPage();
        //How to verify checkbox is checked???
        loginPage.enterEmail(loginEmail).enterPassword(loginPass).checkRemeberCheckbox().logIn();
        $("#nav-home").shouldHave(text("inicio"));
        loginPage.logOut();
        $("#username").shouldHave(exactValue("shiosaky@gmail.com"));
    }


    //Not ready yet
    @Test
    public void verifyIngreseTextoDeSeguridad(){
        open(loginURL);
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(loginEmail).enterPassword("invalidPassword").logIn();
        //Invalid password, the second attempt
        loginPage.enterPassword("invalidPassword").logIn();
        loginPage.enterEmail(loginEmail).enterPassword(loginPass).enterCapcha("test");
    }
}
