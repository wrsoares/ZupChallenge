package pageobject;

import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import report.Report;

import java.util.List;

import static utils.Utils.getElement;
import static utils.Utils.verifyElementPresent;

public class CartPO {

    public void validateProductsInCart(DataTable products) {
        List<List<String>> listProducts = products.asLists(String.class);
        for (List<String> list:listProducts
             ) {
            Assert.assertTrue("Não foi possível encontrar o produto: ".concat(list.get(0).concat(" - ").concat(list.get(1))),
                    verifyElementPresent(By.xpath("//*[contains(text(), '"+list.get(0)+"')]")));
        }
        Report.appendToReport();
    }

}
