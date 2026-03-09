package com.automation.web.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginModal extends BasePage {

    private final By modal         = By.id("logInModal");
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By submitBtn     = By.xpath("//button[normalize-space()='Log in']");
    private final By welcomeLabel  = By.id("nameofuser");
    private final By logoutBtn     = By.id("logout2");

    public boolean isModalVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void submit() {
        click(submitBtn);
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        enterUsername(username);
        enterPassword(password);
        submit();
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeLabel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeLabel));
            return driver.findElement(welcomeLabel).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String acceptAlert() {
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

    public void logout() {
        click(logoutBtn);
    }
}