package stepdefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import Utility.BaseClass;
import saucelab.Test;

public class sauce {

    @Given("user opens the browser")
    public void user_opens_the_browser() {
        BaseClass.launchBrowser("chrome");
    }

    @And("navigates to Sauce-demo login page")
    public void navigates_to_sauce_demo_login_page() {
        BaseClass.login(BaseClass.credentials(1));
    }
    @When("User enters the username and password of user one and click on login button")
    public void user_enters_the_username_and_password_of_user_one_and_click_on_login_button() {

    }
    @And("Verify the sorting")
    public void verify_the_sorting() throws InterruptedException {
        Test.verifySorting();
    }
    @And("user add item to cart and do checkout")
    public void user_add_item_to_cart_and_do_checkout() {
        Test.addToCart();
        Test.checkout();
    }
    @Then("user should do the final checkout")
    public void user_should_do_the_final_checkout() {
        Test.finalFinish();
        BaseClass.tearDown();
    }
}
