package com.frontend.pom.hooks;

import com.frontend.pom.drivers.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class WebDriverHook {

    private static WebDriver driver;

    @Before
    public void setUpDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        WebDriverManager.chromedriver().setup();
        driver = DriverFactory.getInstance(browser);
        System.out.println("[HOOK] ✓ WebDriver inicializado: " + browser);
    }

    @After
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("[HOOK] ✓ WebDriver cerrado");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
