package com.automation.web.pages;

import com.automation.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    // Locators
    private final By navbarBrand = By.cssSelector(".navbar-brand");
    private final By loginButton = By.id("login2");
    private final By signupButton = By.id("signin2");
    private final By cartButton = By.id("cartur");
    private final By productCards = By.cssSelector(".card");
    private final By productNames = By.cssSelector(".hrefch");
    private final By categoryLinks = By.cssSelector("#itemc");
    private final By nextButton = By.id("next2");
    private final By prevButton = By.id("prev2");

    public HomePage() {
        super();
    }

    public void open() {
        String baseUrl = ConfigManager.getInstance().get("web.base.url");
        driver.get(baseUrl);
        waitForPageLoad();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(navbarBrand);
    }

    public void clickLogin() {
        waitForElementClickable(loginButton).click();
    }

    public void clickSignup() {
        waitForElementClickable(signupButton).click();
    }

    public void clickCart() {
        waitForElementClickable(cartButton).click();
    }

    public List<WebElement> getProductCards() {
        return driver.findElements(productCards);
    }

    public int getProductCount() {
        return driver.findElements(productNames).size();
    }

    public void clickProduct(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void filterByCategory(String category) {
        List<WebElement> categories = driver.findElements(categoryLinks);
        for (WebElement cat : categories) {
            if (cat.getText().equalsIgnoreCase(category)) {
                cat.click();
                return;
            }
        }
    }

    public void clickNextPage() {
        waitForElementClickable(nextButton).click();
    }

    public boolean isNextButtonDisplayed() {
        return isElementDisplayed(nextButton);
    }
}