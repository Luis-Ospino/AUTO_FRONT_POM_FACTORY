package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage extends PageObject {

    public static final Target SEARCH_INPUT = Target.the("search input field")
            .locatedBy("//input[@id='search-products']");

    public static final Target SEARCH_BUTTON = Target.the("search button")
            .locatedBy("//button[@class='btn-search']");

    public static final Target PRODUCT_ITEM = Target.the("product item in catalog")
            .locatedBy("//div[@class='product-card']");

    public static final Target PRODUCT_TITLE = Target.the("product title")
            .locatedBy("//h2[@class='product-title']");

    public static final Target PRODUCT_PRICE = Target.the("product price")
            .locatedBy("//span[@class='product-price']");

    public void searchForProduct(String productName) {
        find(By.xpath("//input[@id='search-products']")).typeAndEnter(productName);
    }

    public void selectFirstAvailableProduct() {
        find(By.xpath("//div[@class='product-card']")).click();
    }

    public boolean isProductsDisplayed() {
        return findAll(By.xpath("//div[@class='product-card']")).size() > 0;
    }
}
