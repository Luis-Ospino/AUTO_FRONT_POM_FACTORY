package com.frontend.pom.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage extends PageObject {

    public static final Target STORE_LOGO = Target.the("store logo")
            .locatedBy("//img[@class='store-logo']");

    public static final Target SEARCH_BAR = Target.the("search bar")
            .locatedBy("//input[@id='search-products']");

    public static final Target PRODUCT_CATALOG = Target.the("product catalog")
            .locatedBy("//div[@class='products-grid']");

    public void navigateToStore(String url) {
        getDriver().navigate().to(url);
    }

    public boolean isStoreLoaded() {
        return find(By.xpath("//img[@class='store-logo']")).isDisplayed();
    }
}
