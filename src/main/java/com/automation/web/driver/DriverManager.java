package com.automation.web.driver;

import com.automation.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Thread-safe WebDriver manager.
 * Selenium 4.15+ has built-in driver management — no WebDriverManager needed.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private DriverManager() {}

    public static void initDriver() {
        ChromeOptions options = new ChromeOptions();

        // Support headless via env variable (for CI) or config file
        String envHeadless = System.getenv("WEB_HEADLESS");
        boolean headless = envHeadless != null
                ? Boolean.parseBoolean(envHeadless)
                : Boolean.parseBoolean(ConfigManager.getInstance().get("web.headless", "false"));

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        int implicitWait = Integer.parseInt(
                ConfigManager.getInstance().get("web.implicit.wait", "10"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();

        driverThread.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }
}