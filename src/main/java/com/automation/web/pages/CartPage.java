package com.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.cssSelector("tr.success");
    private final By totalPrice = By.id("totalp");
    private final By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private final By deleteButtons = By.xpath("//a[text()='Delete']");
    private final By emptyCartMessage = By.xpath("//td[contains(text(),'')]");

    public CartPage() {
        super();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public String getTotalPrice() {
        try {
            return driver.findElement(totalPrice).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }

    public void clickPlaceOrder() {
        waitForElementClickable(placeOrderButton).click();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = driver.findElements(
                By.xpath("//td[contains(text(),'" + productName + "')]")
        );
        return !items.isEmpty();
    }

    public void deleteFirstItem() {
        List<WebElement> delBtns = driver.findElements(deleteButtons);
        if (!delBtns.isEmpty()) {
            delBtns.get(0).click();
        }
    }
}