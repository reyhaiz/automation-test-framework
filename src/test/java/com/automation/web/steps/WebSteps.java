package com.automation.web.steps;

import com.automation.web.driver.DriverManager;
import com.automation.web.pages.CartPage;
import com.automation.web.pages.HomePage;
import com.automation.web.pages.LoginModal;
import com.automation.web.pages.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class WebSteps {

    private HomePage    homePage;
    private LoginModal  loginModal;
    private ProductPage productPage;
    private CartPage    cartPage;

    @Before("@web")
    public void setUp() {
        DriverManager.initDriver();
        homePage    = new HomePage();
        loginModal  = new LoginModal();
        productPage = new ProductPage();
        cartPage    = new CartPage();
    }

    @After("@web")
    public void tearDown() {
        DriverManager.quitDriver();
    }

    // ── Homepage ──────────────────────────────────────────

    @Given("I open the DemoBlaze homepage")
    public void openHomepage() {
        homePage.open();
    }

    @Then("the page title should contain {string}")
    public void pageTitleShouldContain(String expected) {
        String actual = homePage.getPageTitle();
        Assertions.assertTrue(actual.contains(expected),
                "Page title expected to contain '" + expected + "' but was: " + actual);
    }

    @Then("the navigation logo should be visible")
    public void navLogoVisible() {
        Assertions.assertTrue(homePage.isNavLogoDisplayed(),
                "Navigation logo should be visible");
    }

    @Then("at least {int} product should be displayed")
    public void atLeastNProductsDisplayed(int min) {
        int count = homePage.getProductCount();
        Assertions.assertTrue(count >= min,
                "Expected at least " + min + " product(s) but found: " + count);
    }

    @When("I click the Next button")
    public void clickNextButton() {
        Assertions.assertTrue(homePage.isNextButtonDisplayed(), "Next button should be visible");
        homePage.clickNextPage();
    }

    @When("I select the {string} category")
    public void selectCategory(String category) {
        homePage.filterByCategory(category);
    }

    // ── Login ─────────────────────────────────────────────

    @When("I click the Login button")
    public void clickLoginButton() {
        homePage.clickLogin();
    }

    @Then("the Login modal should appear")
    public void loginModalShouldAppear() {
        Assertions.assertTrue(loginModal.isModalVisible(),
                "Login modal should be visible after clicking Login");
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        loginModal.enterUsername(username);
        loginModal.enterPassword(password);
    }

    @When("I submit the login form")
    public void submitLoginForm() {
        loginModal.submit();
    }

    @Then("an alert dialog should appear")
    public void alertShouldAppear() {
        String alertText = loginModal.acceptAlert();
        System.out.println("Alert text: " + alertText);
    }

    @Then("I dismiss the alert")
    public void dismissAlert() {
        loginModal.acceptAlert();
    }

    // ── Product ───────────────────────────────────────────

    @When("I click the first product")
    public void clickFirstProduct() {
        homePage.clickFirstProduct();
    }

    @Then("the product name should not be empty")
    public void productNameNotEmpty() {
        String name = productPage.getProductName();
        Assertions.assertFalse(name.isEmpty(), "Product name should not be empty");
    }

    @Then("the product price should not be empty")
    public void productPriceNotEmpty() {
        String price = productPage.getProductPrice();
        Assertions.assertFalse(price.isEmpty(), "Product price should not be empty");
    }

    @Then("the Add to Cart button should be present")
    public void addToCartButtonPresent() {
        // Use the public method on ProductPage instead of calling protected BasePage method directly
        Assertions.assertTrue(productPage.isAddToCartButtonVisible(),
                "Add to Cart button should be visible on the product page");
    }

    @When("I click Add to Cart")
    public void clickAddToCart() {
        productPage.clickAddToCart();
    }

    @Then("a confirmation alert should appear")
    public void confirmationAlertAppears() {
        String msg = productPage.addToCartAndConfirm();
        System.out.println("Add-to-cart alert: " + msg);
        Assertions.assertFalse(msg.isEmpty(), "Expected a confirmation alert from Add to Cart");
    }

    // ── Cart ──────────────────────────────────────────────

    @When("I go to the Cart page")
    public void goToCartPage() {
        homePage.clickCart();
    }

    @Then("the cart should have at least {int} item")
    public void cartShouldHaveAtLeastItems(int min) {
        int count = cartPage.getItemCount();
        Assertions.assertTrue(count >= min,
                "Cart should have at least " + min + " item(s) but found: " + count);
    }

    @Then("the cart total element should be present")
    public void cartTotalPresent() {
        String total = cartPage.getTotalPrice();
        System.out.println("Cart total: " + total);
        Assertions.assertNotNull(total, "Cart total element should be accessible");
    }
}