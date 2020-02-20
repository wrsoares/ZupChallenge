package driver;


import io.github.bonigarcia.wdm.DriverManagerType;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;

import static driver.Drivers.*;
import static io.github.bonigarcia.wdm.DriverManagerType.*;
import static utils.Utils.*;

public class AppWeb {

    String sistemaOperacional;
    private String url;
    public static final String HEADLESS = "headless";
    public static final String BROWSER = "browser";

    public AppWeb() {
        this.sistemaOperacional = System.getProperty("os.name").toLowerCase();
        this.url = getProperties("url");
    }

    public void setUpDriver(DriverManagerType driverManagerType, String headless) {
        if (getDriver() == null || getDriver().toString().contains("null")) {
            switch (driverManagerType) {
                case CHROME:
                    setConfigurationDownload(CHROME);
                    switch (headless) {
                        case HEADLESS:
                            initChromeHeadless();
                            break;
                        case BROWSER:
                            initChromeDriver();
                            break;
                    }
                    break;
            }
        }
    }

    public void initChromeDriver() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--ignore-ssl-errors","–-no-sandbox","ignore-certicate-errors");
        options.addArguments("--disable-extensions");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", chromePrefs);
        setDriver(setUrl(new ChromeDriver(options)));
        setProcessDriverName(getProcessName(CHROME));
    }

    public void initChromeHeadless() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1366,768","--disable-dev-shm-usage","--no-sandbox", "disable-extensions", "--ignore-ssl-errors", "disable-gpu", "headless");
        setDriver(setUrl(new ChromeDriver(options)));
        setProcessDriverName(getProcessName(CHROME));
    }

    private WebDriver setUrl(WebDriver driver){
        try {
            if (!url.isEmpty() && url != null) {
                driver.get(url);
            }
        }catch (Exception e){
            Assert.fail("Não foi possível carregar a url do arquivo de configuração");
        }
        return driver;
    }

    public static void quitDriver() {
        getDriver().quit();
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            killProcess(getProcessDriverName());
        }
    }

}
