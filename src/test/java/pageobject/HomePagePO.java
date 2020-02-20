package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;

import static report.Report.appendToReport;
import static report.Report.appendToReportElementHighlight;
import static utils.Utils.*;

public class HomePagePO {

    public void validateHomePage() {
        Assert.assertTrue("Página não localizada",
                getElement(By.className("logo"), 10).findElement(By.tagName("img")).getAttribute("alt").contains("Petlove – Petshop Online"));
        appendToReport();
    }

    public void setProduct(String product) {
        sendKeys(By.id("autocomplete-input"), 10, product);
    }

    public void btnSearch() {
        click(By.className("icon-search"), 10);
    }

    public void searchProduct(String product) {
        setProduct(product);
        btnSearch();
    }

}