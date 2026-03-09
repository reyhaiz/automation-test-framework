package com.automation.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartRows      = By.cssSelector("tr.success");
    private final By totalPriceEl  = By.id("totalp");
    private final By placeOrderBtn = By.xpath("//button[normalize-space()='Place Order']");
    private final By deleteLinks   = By.xpath("//a[normalize-space()='Delete']");

    public int getItemCount() {
        pause(1500);
        return findAll(cartRows).size();
    }

    public boolean isCartEmpty() {
        return getItemCount() == 0;
    }

    public String getTotalPrice() {
        try { return driver.findElement(totalPriceEl).getText().trim(); }
        catch (Exception e) { return "0"; }
    }

    public boolean containsProduct(String productName) {
        for (WebElement row : findAll(cartRows)) {
            if (row.getText().contains(productName)) return true;
        }
        return false;
    }

    public void clickPlaceOrder() {
        click(placeOrderBtn);
    }

    public void deleteFirstItem() {
        List<WebElement> delBtns = findAll(deleteLinks);
        if (!delBtns.isEmpty()) {
            delBtns.get(0).click();
            pause(1000);
        }
    }
}