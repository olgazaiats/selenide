package advanced_interactions.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selenide.core.SelenideTestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class DropDownAndIFrameTest extends SelenideTestBase{
    private String dropdownPage = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select";

    @Test
    public void testDropDownAndIframe(){
        open(dropdownPage);

        String volvo = "Volvo";
        switchTo().frame("iframeResult");
        SelenideElement cars = $(By.xpath("html/body/select"));
        cars.selectOption(volvo);
        cars.getSelectedOption().shouldHave(text(volvo));


    }
}
