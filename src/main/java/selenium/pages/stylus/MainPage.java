package selenium.pages.stylus;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.core.WebDriverTestBase;

public class MainPage extends WebDriverTestBase{
    @FindBy(how = How.NAME, using = "q")
    private WebElement searchField;

    @FindBy(how = How.XPATH, using = ".//*[@id='head-search']/form/input[@type=\"submit\"]")
    private WebElement searchBtn;

    public MainPage searchFor(String searchWord){
        searchField.sendKeys(searchWord);
        searchBtn.click();
        return this;
    }

}
