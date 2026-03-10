package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    public static final Target CART_ITEMS = Target.the("cart items")
            .locatedBy("//div[@class='cart-item']");

    public static final Target ITEM_PRICE = Target.the("item price in cart")
            .locatedBy("//span[@class='item-price']");

    public static final Target SUBTOTAL = Target.the("subtotal amount")
            .locatedBy("//span[@class='subtotal']");

    public static final Target TAX_AMOUNT = Target.the("tax amount")
            .locatedBy("//span[@class='tax']");

    public static final Target TOTAL_AMOUNT = Target.the("total amount")
            .locatedBy("//span[@class='total-amount']");

    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .locatedBy("//button[@id='btn-checkout']");

    public static final Target CONTINUE_SHOPPING_LINK = Target.the("continue shopping link")
            .locatedBy("//a[@class='continue-shop']");

    public void proceedToCheckout() {
        find(By.xpath("//button[@id='btn-checkout']")).click();
    }

    public boolean isCartLoaded() {
        return find(By.xpath("//div[@class='cart-container']")).isDisplayed();
    }

    public int getCartItemsCount() {
        return findAll(By.xpath("//div[@class='cart-item']")).size();
    }

    public String getTotalAmount() {
        return find(By.xpath("//span[@class='total-amount']")).getText();
    }
}
