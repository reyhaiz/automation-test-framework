package com.automation.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginModal extends BasePage {

    private final By loginModal = By.id("logInModal");
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By loginSubmitButton = By.xpath("//button[text()='Log in']");
    private final By closeButton = By.xpath("//div[@id='logInModal']//button[@class='close']");
    private final By logoutButton = By.id("logout2");
    private final By welcomeText = By.id("nameofuser");

    public LoginModal() {
        super();
    }

    public boolean isModalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginModal));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        typeText(passwordField, password);
    }

    public void clickLoginButton() {
        waitForElementClickable(loginSubmitButton).click();
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));
            return driver.findElement(welcomeText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeText));
            return driver.findElement(welcomeText).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void handleAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            // No alert present
        }
    }

    public void logout() {
        waitForElementClickable(logoutButton).click();
    }
}