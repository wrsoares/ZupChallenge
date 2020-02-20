package pageobject;

import com.cucumber.listener.Reporter;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import report.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static driver.Drivers.getDriver;
import static utils.Utils.*;

public class ProductsPO {

    public void selectProduct(String descriptionProduct) {
        new HomePagePO().searchProduct(descriptionProduct);
        List<WebElement> listProducts = getDriver().findElements(By.className("catalog-list-item"));
        for (WebElement product:listProducts
             ) {
            if (product.getText().contains(descriptionProduct)) {
                product.click();
                break;
            }
        }
    }

    public void btnAddProductToCart(){
        click(By.id("buy-button"), 10);
    }

    public void selectSizeProduct(String sizeProduct) {
        sleep(2);
        getElement(By.className("product-variants-button"), 10).
                findElement(By.xpath("//div[contains(text(), '"+sizeProduct+"')]")).click();
    }

    public void addProductToCart(DataTable dataTable) {
        List<List<String>> products = dataTable.asLists(String.class);
        for (List<String> list:products
        ) {
            selectProduct(list.get(0));
            selectSizeProduct(list.get(1));
            btnAddProductToCart();
            try {
                insertEmail(getProperties("user"));
            } catch (Exception e) {
                System.out.println("Usuário logado! E-mail não inserido");
            }
        }

    }

    public void insertEmail(String email) {
        sendKeys(By.id("product-modal-identify-email"), 10, email+Keys.ENTER);
    }

}
