package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OrderConfirmationPage extends PageObject {

    public static final Target CONFIRMATION_MESSAGE = Target.the("confirmation message")
            .locatedBy("//div[@class='confirmation-message']");

    public static final Target ORDER_NUMBER = Target.the("order number")
            .locatedBy("//span[@id='order-number']");

    public static final Target TRACKING_NUMBER = Target.the("tracking number")
            .locatedBy("//span[@id='tracking-number']");

    public static final Target ORDER_DETAILS = Target.the("order details section")
            .locatedBy("//div[@class='order-details']");

    public static final Target CONTINUE_SHOPPING_BUTTON = Target.the("continue shopping button")
            .locatedBy("//a[@id='btn-continue-shopping']");

    public boolean isConfirmationPageLoaded() {
        return find(By.xpath("//div[@class='confirmation-message']")).isDisplayed();
    }

    public String getOrderNumber() {
        return find(By.xpath("//span[@id='order-number']")).getText();
    }

    public String getTrackingNumber() {
        return find(By.xpath("//span[@id='tracking-number']")).getText();
    }

    public boolean isPurchaseConfirmed() {
        return isConfirmationPageLoaded() && !getOrderNumber().isEmpty();
    }
}
