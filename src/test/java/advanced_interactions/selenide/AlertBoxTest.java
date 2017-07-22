package advanced_interactions.selenide;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import selenide.core.SelenideTestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class AlertBoxTest extends SelenideTestBase{
    String webPageForW3 = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm";
    private String cancelled = "You pressed Cancel!";

    @Test
    public void alertTest(){
        open(webPageForW3);
        switchTo().frame("iframeResult");
        $(By.xpath("html/body/button")).click();
        switchTo().alert().dismiss();
        $(By.id("demo")).shouldHave(text(cancelled));

    }

}
