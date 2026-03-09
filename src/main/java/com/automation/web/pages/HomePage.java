package com.automation.web.pages;

import com.automation.utils.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    private final By navBrand      = By.cssSelector("a.navbar-brand");
    private final By loginBtn      = By.id("login2");
    private final By signupBtn     = By.id("signin2");
    private final By cartBtn       = By.id("cartur");
    private final By productLinks  = By.cssSelector("a.hrefch");
    private final By categoryItems = By.cssSelector("#itemc");
    private final By nextPageBtn   = By.id("next2");

    public void open() {
        driver.get(ConfigManager.getInstance().get("web.base.url"));
        waitForPageLoad();
        pause(1500);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isNavLogoDisplayed() {
        return isDisplayed(navBrand);
    }

    public int getProductCount() {
        return findAll(productLinks).size();
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void clickSignup() {
        click(signupBtn);
    }

    public void clickCart() {
        click(cartBtn);
        waitForPageLoad();
        pause(1000);
    }

    public void clickFirstProduct() {
        List<WebElement> links = findAll(productLinks);
        if (links.isEmpty()) {
            pause(2000);
            links = findAll(productLinks);
        }
        links.get(0).click();
        waitForPageLoad();
        pause(1000);
    }

    public void clickProductByName(String name) {
        List<WebElement> links = findAll(productLinks);
        for (WebElement link : links) {
            if (link.getText().trim().equalsIgnoreCase(name)) {
                link.click();
                waitForPageLoad();
                return;
            }
        }
        throw new RuntimeException("Product not found: " + name);
    }

    public void filterByCategory(String category) {
        List<WebElement> cats = findAll(categoryItems);
        for (WebElement cat : cats) {
            if (cat.getText().trim().equalsIgnoreCase(category)) {
                cat.click();
                pause(2000);
                return;
            }
        }
        throw new RuntimeException("Category not found: " + category);
    }

    public boolean isNextButtonDisplayed() {
        return isDisplayed(nextPageBtn);
    }

    public void clickNextPage() {
        click(nextPageBtn);
        pause(2000);
    }
}