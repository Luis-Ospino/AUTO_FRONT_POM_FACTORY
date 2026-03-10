package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductDetailPage extends PageObject {

    public static final Target PRODUCT_NAME = Target.the("product name")
            .locatedBy("//h1[@class='product-name']");

    public static final Target PRODUCT_DESCRIPTION = Target.the("product description")
            .locatedBy("//div[@class='product-description']");

    public static final Target PRODUCT_FINAL_PRICE = Target.the("final product price")
            .locatedBy("//span[@class='final-price']");

    public static final Target QUANTITY_SELECTOR = Target.the("quantity selector")
            .locatedBy("//input[@id='product-quantity']");

    public static final Target ADD_TO_CART_BUTTON = Target.the("add to cart button")
            .locatedBy("//button[@id='btn-add-cart']");

    public static final Target CONTINUE_SHOPPING_BUTTON = Target.the("continue shopping button")
            .locatedBy("//a[@class='continue-shopping']");

    public void addProductToCart() {
        find(By.xpath("//button[@id='btn-add-cart']")).click();
    }

    public void setQuantity(int quantity) {
        find(By.xpath("//input[@id='product-quantity']")).clear();
        find(By.xpath("//input[@id='product-quantity']")).type(String.valueOf(quantity));
    }

    public boolean isProductDetailsLoaded() {
        return find(By.xpath("//h1[@class='product-name']")).isDisplayed();
    }
}
