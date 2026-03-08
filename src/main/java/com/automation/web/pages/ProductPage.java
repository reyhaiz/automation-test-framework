package com.automation.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private final By productName = By.cssSelector(".name");
    private final By productPrice = By.cssSelector(".price-container");
    private final By productDescription = By.cssSelector("#more-information p");
    private final By addToCartButton = By.xpath("//a[text()='Add to cart']");
    private final By homeLink = By.xpath("//a[text()='Home ']");

    public ProductPage() {
        super();
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public void clickAddToCart() {
        waitForElementClickable(addToCartButton).click();
    }

    public String getAddToCartAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (Exception e) {
            return "";
        }
    }

    public void goToHome() {
        waitForElementClickable(homeLink).click();
        waitForPageLoad();
    }
}