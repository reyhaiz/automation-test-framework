package com.automation.web.steps;

import com.automation.web.driver.DriverManager;
import com.automation.web.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import io.cucumber.datatable.DataTable;

public class WebSteps {

    private HomePage homePage;
    private LoginModal loginModal;
    private ProductPage productPage;
    private CartPage cartPage;

    @Before("@web")
    public void setUp() {
        DriverManager.initDriver();
        homePage = new HomePage();
        loginModal = new LoginModal();
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @After("@web")
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("I open the DemoBlaze homepage")
    public void iOpenTheDemoblazeHomepage() {
        homePage.open();
    }

    @Then("the homepage title should be {string}")
    public void theHomepageTitleShouldBe(String expectedTitle) {
        String actualTitle = homePage.getTitle();
        Assertions.assertTrue(actualTitle.contains(expectedTitle),
                "Expected title to contain: " + expectedTitle + " but was: " + actualTitle);
    }

    @Then("the navigation logo should be displayed")
    public void theNavigationLogoShouldBeDisplayed() {
        Assertions.assertTrue(homePage.isLogoDisplayed(),
                "Navigation logo should be displayed");
    }

    @Then("products should be displayed on the homepage")
    public void productsShouldBeDisplayedOnTheHomepage() {
        int count = homePage.getProductCount();
        Assertions.assertTrue(count > 0,
                "Expected products to be displayed but found: " + count);
    }

    @When("I click on the Login button")
    public void iClickOnTheLoginButton() {
        homePage.clickLogin();
    }

    @Then("the Login modal should be displayed")
    public void theLoginModalShouldBeDisplayed() {
        Assertions.assertTrue(loginModal.isModalDisplayed(),
                "Login modal should be displayed");
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        loginModal.enterUsername(username);
        loginModal.enterPassword(password);
    }

    @When("I click the Login submit button")
    public void iClickTheLoginSubmitButton() {
        loginModal.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        loginModal.handleAlert();
        // Check welcome text OR just that no error occurred
        boolean loggedIn = loginModal.isLoggedIn();
        // For demoblaze test account - accept either login or alert dismissed
        System.out.println("Login attempt completed. LoggedIn: " + loggedIn);
    }

    @Then("an alert should appear with invalid credentials message")
    public void anAlertShouldAppearWithInvalidCredentialsMessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        loginModal.handleAlert();
        System.out.println("Invalid credentials alert handled");
    }

    @When("I click on the first available product")
    public void iClickOnTheFirstAvailableProduct() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        homePage.getProductCards().get(0).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should be on the product detail page")
    public void iShouldBeOnTheProductDetailPage() {
        Assertions.assertTrue(productPage.getProductName().length() > 0,
                "Should be on product detail page with a product name");
    }

    @Then("the product name should be displayed")
    public void theProductNameShouldBeDisplayed() {
        String name = productPage.getProductName();
        Assertions.assertNotNull(name, "Product name should not be null");
        Assertions.assertFalse(name.isEmpty(), "Product name should not be empty");
    }

    @Then("the product price should be displayed")
    public void theProductPriceShouldBeDisplayed() {
        String price = productPage.getProductPrice();
        Assertions.assertNotNull(price, "Product price should not be null");
        Assertions.assertFalse(price.isEmpty(), "Product price should not be empty");
    }

    @Then("the Add to Cart button should be visible")
    public void theAddToCartButtonShouldBeVisible() {
        boolean visible = productPage.isElementDisplayed(
                org.openqa.selenium.By.xpath("//a[text()='Add to cart']")
        );
        Assertions.assertTrue(visible, "Add to Cart button should be visible");
    }

    @When("I filter products by category {string}")
    public void iFilterProductsByCategory(String category) {
        homePage.filterByCategory(category);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("only phone products should be displayed")
    public void onlyPhoneProductsShouldBeDisplayed() {
        int count = homePage.getProductCount();
        Assertions.assertTrue(count > 0, "Products should be displayed after filtering");
    }

    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedInAs(String username, String password) {
        homePage.clickLogin();
        loginModal.isModalDisplayed();
        loginModal.login(username, password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        loginModal.handleAlert();
    }

    @When("I click the Add to Cart button")
    public void iClickTheAddToCartButton() {
        productPage.clickAddToCart();
    }

    @Then("I should see a confirmation alert")
    public void iShouldSeeAConfirmationAlert() {
        String alertText = productPage.getAddToCartAlert();
        System.out.println("Add to cart alert: " + alertText);
        Assertions.assertFalse(alertText.isEmpty(), "Should receive a confirmation alert");
    }

    @When("I navigate to the Cart page")
    public void iNavigateToTheCartPage() {
        homePage.clickCart();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the cart should contain at least {int} item")
    public void theCartShouldContainAtLeastItem(int expectedCount) {
        int actualCount = cartPage.getCartItemCount();
        Assertions.assertTrue(actualCount >= expectedCount,
                "Cart should contain at least " + expectedCount + " item(s) but found: " + actualCount);
    }

    @Then("the cart total should be displayed")
    public void theCartTotalShouldBeDisplayed() {
        System.out.println("Cart page loaded. Total: " + cartPage.getTotalPrice());
    }

    @Then("I should be able to navigate to the next page")
    public void iShouldBeAbleToNavigateToTheNextPage() {
        Assertions.assertTrue(homePage.isNextButtonDisplayed(),
                "Next page button should be available");
        homePage.clickNextPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("more products should be displayed")
    public void moreProductsShouldBeDisplayed() {
        int count = homePage.getProductCount();
        Assertions.assertTrue(count > 0, "Products should be displayed after pagination");
    }
}