package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import report.Report;

import static utils.Utils.*;

public class LoginPO {

    public void btnLogin() {
        click(By.id("header-user-login"), 10);
    }

    public void setUser() {
        sendKeys(By.name("email"), 10, getProperties("user"));
    }

    public void setPassword() {
        sendKeys(By.name("password"), 10, getProperties("password") + Keys.ENTER);
    }

    public void login() {
        btnLogin();
        setUser();
        setPassword();
        Report.appendToReport();
        sleep(5);
    }

}
