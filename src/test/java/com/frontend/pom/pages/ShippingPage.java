package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ShippingPage extends PageObject {

    public static final Target FULLNAME_INPUT = Target.the("full name input")
            .locatedBy("//input[@id='fullname']");

    public static final Target EMAIL_INPUT = Target.the("email input")
            .locatedBy("//input[@id='email']");

    public static final Target ADDRESS_INPUT = Target.the("street address input")
            .locatedBy("//input[@id='address']");

    public static final Target CITY_INPUT = Target.the("city input")
            .locatedBy("//input[@id='city']");

    public static final Target ZIP_CODE_INPUT = Target.the("zip code input")
            .locatedBy("//input[@id='zipcode']");

    public static final Target SHIPPING_METHOD_SELECT = Target.the("shipping method selector")
            .locatedBy("//select[@id='shipping-method']");

    public static final Target CONTINUE_TO_PAYMENT_BUTTON = Target.the("continue to payment button")
            .locatedBy("//button[@id='btn-continue-payment']");

    public void enterFullName(String fullName) {
        find(By.xpath("//input[@id='fullname']")).typeAndTab(fullName);
    }

    public void enterEmail(String email) {
        find(By.xpath("//input[@id='email']")).typeAndTab(email);
    }

    public void enterAddress(String address) {
        find(By.xpath("//input[@id='address']")).typeAndTab(address);
    }

    public void enterCity(String city) {
        find(By.xpath("//input[@id='city']")).typeAndTab(city);
    }

    public void enterZipCode(String zipCode) {
        find(By.xpath("//input[@id='zipcode']")).typeAndTab(zipCode);
    }

    public void selectShippingMethod() {
        find(By.xpath("//select[@id='shipping-method']")).click();
        find(By.xpath("//option[@class='shipping-option'][1]")).click();
    }

    public void continueToPayment() {
        find(By.xpath("//button[@id='btn-continue-payment']")).click();
    }

    public boolean isShippingPageLoaded() {
        return find(By.xpath("//h2[@class='shipping-title']")).isDisplayed();
    }
}
