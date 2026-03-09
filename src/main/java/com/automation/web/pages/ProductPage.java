package com.automation.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private final By productName  = By.cssSelector("h2.name");
    private final By productPrice = By.cssSelector("h3.price-container");
    private final By productDesc  = By.cssSelector("div#more-information p");
    private final By addToCartBtn = By.xpath("//a[normalize-space()='Add to cart']");
    private final By homeBreadcrumb = By.xpath("//a[normalize-space()='Home ']");

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getProductDescription() {
        try { return getText(productDesc); } catch (Exception e) { return ""; }
    }

    public boolean isAddToCartButtonVisible() {
        return isDisplayed(addToCartBtn);
    }

    public void clickAddToCart() {
        click(addToCartBtn);
    }

    public String addToCartAndConfirm() {
        clickAddToCart();
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String msg = alert.getText();
            alert.accept();
            return msg;
        } catch (Exception e) {
            return "";
        }
    }

    public void goHome() {
        click(homeBreadcrumb);
        waitForPageLoad();
    }
}