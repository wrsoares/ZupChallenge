package driver;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class Drivers {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal();
    public static ThreadLocal<String> processDriverName = new ThreadLocal();
    public static ThreadLocal<Scenario> testScenario = new ThreadLocal();
    private static String pathDownload = Utils.getProperties("pathDownload");

    public static String getProcessDriverName() {
        return (String)processDriverName.get();
    }

    public static void setProcessDriverName(String processDriverName) {
        Drivers.processDriverName.set(processDriverName);
    }

    public static WebDriver getDriver() {
        return (WebDriver)driver.get();
    }

    public static void setDriver(WebDriver driver) {
        Drivers.driver.set(driver);
    }

    public static void setConfigurationDownload(DriverManagerType driverManagerType) {
        if (pathDownload.isEmpty()) {
            WebDriverManager.chromedriver().config().setTargetPath(System.getProperty("user.dir") + "\\target\\download\\");
        } else {
            WebDriverManager.chromedriver().config().setTargetPath(pathDownload);
        }

        String webDriverVersion = Utils.getProperties("webdriverversion");
        if (driverManagerType.equals(DriverManagerType.CHROME)) {
            if (!webDriverVersion.isEmpty()) {
                WebDriverManager.chromedriver().config().setChromeDriverVersion(Utils.getProperties("webdriverversion"));
                WebDriverManager.chromedriver().setup();
            }
        }
    }
}