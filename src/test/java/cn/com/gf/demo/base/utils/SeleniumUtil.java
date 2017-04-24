package cn.com.gf.demo.base.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import cn.com.gf.demo.base.beans.BrowserTypeEnum;


/**
 * Created by Administrator on 2017/4/22.
 */
public class SeleniumUtil {
    private static String classPath="src\\test\\resources\\testData\\";
    public static WebDriver createDriver(String browserType) {

        WebDriver driver=null;
        switch (BrowserTypeEnum.valueOf(browserType.toUpperCase())) {
            case IE:
                System.setProperty("webdriver.ie.driver", classPath+"IEDriverServer.exe");
                InternetExplorerDriverService service = InternetExplorerDriverService.createDefaultService();
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

                driver = new InternetExplorerDriver(service, capabilities);
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", classPath+"chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case SAFARI:
              break;
            case FIREFOX:
                break;
            case EDGE:
                break;
        }

        return driver;
    }
}
