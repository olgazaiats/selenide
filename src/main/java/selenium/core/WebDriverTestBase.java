package selenium.core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static util.PropertiesCache.getProperty;

@Listeners({selenium.core.TestListener.class})
public class WebDriverTestBase {
    protected WebDriver webDriver;
    private String browser = System.getProperty("browser", CHROME);
    private long implicitWait = Long.parseLong(getProperty("wait.implicit"));
    private long pageWait = Long.parseLong(getProperty("wait.page"));
    private long scriptWait = Long.parseLong(getProperty("wait.script"));

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
            case "remote":
                DesiredCapabilities caps = setDesiredCapabilities(platform, remoteBrowser);
                webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                break;
            case BrowserType.CHROME:
                ChromeDriverManager.getInstance().setup();
                webDriver = new ChromeDriver();
                break;
        }
        //webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(scriptWait, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}