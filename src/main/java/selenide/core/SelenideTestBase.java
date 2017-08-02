package selenide.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import static com.codeborne.selenide.WebDriverRunner.CHROME;

@Listeners({selenide.core.TestListener.class})
public class SelenideTestBase {
    private String browser = System.getProperty("browser", CHROME);
    public DesiredCapabilities setDesiredCapabilities(String platform, String remoteBrowser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (platform.equalsIgnoreCase("VISTA")) {
            caps.setPlatform(Platform.VISTA);
            caps.setBrowserName(remoteBrowser);
        }
        return caps;
    }

    @Parameters({"platform", "remoteBrowser"})
    @BeforeClass
    public void setUp(@Optional String platform, @Optional String remoteBrowser) throws MalformedURLException {
        switch (browser) {
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                Configuration.browser = browser;
                break;
            case WebDriverRunner.FIREFOX:
                FirefoxDriverManager.getInstance().setup();
                Configuration.browser = browser;
                break;
            case "remote":
                DesiredCapabilities caps = setDesiredCapabilities(platform, remoteBrowser);
                WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                WebDriverRunner.setWebDriver(webDriver);
                break;
        }
    }
}
