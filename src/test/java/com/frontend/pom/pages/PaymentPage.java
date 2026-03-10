package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaymentPage extends PageObject {

    public static final Target CARD_NUMBER_INPUT = Target.the("card number input")
            .locatedBy("//input[@id='card-number']");

    public static final Target CARDHOLDER_NAME_INPUT = Target.the("cardholder name input")
            .locatedBy("//input[@id='cardholder-name']");

    public static final Target EXPIRY_DATE_INPUT = Target.the("expiry date input")
            .locatedBy("//input[@id='expiry-date']");

    public static final Target CVV_INPUT = Target.the("cvv input")
            .locatedBy("//input[@id='cvv']");

    public static final Target BILLING_ADDRESS_SAME_CHECKBOX = Target.the("billing address same checkbox")
            .locatedBy("//input[@id='billing-same']");

    public static final Target COMPLETE_PURCHASE_BUTTON = Target.the("complete purchase button")
            .locatedBy("//button[@id='btn-complete-purchase']");

    public void enterCardNumber(String cardNumber) {
        find(By.xpath("//input[@id='card-number']")).typeAndTab(cardNumber);
    }

    public void enterCardholderName(String cardholderName) {
        find(By.xpath("//input[@id='cardholder-name']")).typeAndTab(cardholderName);
    }

    public void enterExpiryDate(String expiryDate) {
        find(By.xpath("//input[@id='expiry-date']")).typeAndTab(expiryDate);
    }

    public void enterCVV(String cvv) {
        find(By.xpath("//input[@id='cvv']")).typeAndTab(cvv);
    }

    public void completePurchase() {
        find(By.xpath("//button[@id='btn-complete-purchase']")).click();
    }

    public boolean isPaymentPageLoaded() {
        return find(By.xpath("//h2[@class='payment-title']")).isDisplayed();
    }
}
