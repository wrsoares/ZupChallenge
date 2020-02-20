package gherkin.stepdefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobject.CartPO;
import pageobject.HomePagePO;
import pageobject.LoginPO;
import pageobject.ProductsPO;

public class ZupChallengeStep {

    DataTable products;

    @Given("^que acessei o site do Petlove$")
    public void que_acessei_o_site_do_Petlove() {
        new HomePagePO().validateHomePage();
    }

    @Given("^estou logado com usuario e senha$")
    public void estou_logado_com_usuario_e_senha() {
        new LoginPO().login();
    }

    @When("^seleciono os produtos para inserir no carrinho de compras$")
    public void seleciono_os_produtos_para_inserir_no_carrinho_de_compras(DataTable products) {
        this.products = products;
        new ProductsPO().addProductToCart(products);
    }

    @Then("^valido que os produtos estao no carrinho de compras$")
    public void valido_que_os_produtos_estao_no_carrinho_de_compras() {
        new CartPO().validateProductsInCart(this.products);
    }

}
