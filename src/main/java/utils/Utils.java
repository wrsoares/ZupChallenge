package utils;

import driver.Drivers;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static driver.Drivers.getDriver;

public class Utils {

    public static String getProperties(String propertie) {
        try {
            InputStream inputStream = new FileInputStream("setup.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(propertie);
        } catch (IOException var3) {
            var3.printStackTrace();
            Assert.fail("Erro ao carregar propriedade");
            return "";
        }
    }

    public static WebElement getElement(By by, int timeout) {
        WebElement element = (new WebDriverWait(Drivers.getDriver(), timeout)).until(ExpectedConditions.elementToBeClickable(by));
        if (element == null) {
            Assert.fail("Elemento não encontrado na página: " + element);
        }
        return element;
    }

    public static void click(By by, int timeout) {
        getElement(by, timeout).click();
    }

    public static void sendKeys(By by, int timeout, String text) {
        WebElement element = getElement(by, timeout);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public static boolean verifyElementPresent(By by) {
        try {
            getElement(by, 10);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static void killProcess(String process) {
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
                    if (line.substring(1, line.indexOf("\"", 1)).equalsIgnoreCase(process)) {
                        Runtime.getRuntime().exec("taskkill /F /IM " + line.substring(1, line.indexOf("\"", 1)));
                    }
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getProcessName(DriverManagerType type) {
        List<String> list = new ArrayList<>();
        switch (type) {
            case CHROME:
                list = Arrays.asList(WebDriverManager.chromedriver().getBinaryPath().split("\\\\"));
                break;
            case FIREFOX:
                list = Arrays.asList(WebDriverManager.firefoxdriver().getBinaryPath().split("\\\\"));
                break;
        }
        return list.get(list.size()-1);
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}