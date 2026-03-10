package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage extends PageObject {

    public static final Target CHECKOUT_HEADER = Target.the("checkout header")
            .locatedBy("//h1[@class='checkout-title']");

    public static final Target ORDER_SUMMARY = Target.the("order summary section")
            .locatedBy("//div[@class='order-summary']");

    public static final Target NEXT_STEP_BUTTON = Target.the("next step button")
            .locatedBy("//button[@id='btn-next-step']");

    public static final Target BACK_BUTTON = Target.the("back button")
            .locatedBy("//button[@id='btn-back']");

    public void continueToShippingInfo() {
        find(By.xpath("//button[@id='btn-next-step']")).click();
    }

    public boolean isCheckoutPageLoaded() {
        return find(By.xpath("//h1[@class='checkout-title']")).isDisplayed();
    }
}
