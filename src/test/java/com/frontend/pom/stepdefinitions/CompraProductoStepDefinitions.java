package com.frontend.pom.stepdefinitions;

import com.frontend.pom.pages.*;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.annotations.Steps;

public class CompraProductoStepDefinitions {

    @Steps
    private HomePage homePage;

    @Steps
    private ProductsPage productsPage;

    @Steps
    private ProductDetailPage productDetailPage;

    @Steps
    private CartPage cartPage;

    @Steps
    private CheckoutPage checkoutPage;

    @Steps
    private ShippingPage shippingPage;

    @Steps
    private PaymentPage paymentPage;

    @Steps
    private OrderConfirmationPage orderConfirmationPage;

    private String testProductName;
    private String testEmail;
    private String testFullName;
    private String testAddress;
    private String testCity;
    private String testZipCode;

    @Dado("que el usuario se encuentra en la página principal de la tienda")
    public void usuarioEnPaginaPrincipal() {
        homePage.navigateToStore("https://www.tienda-ejemplo.com");
    }

    @Y("el catálogo de productos está disponible")
    public void catalogoDisponible() {
        homePage.isStoreLoaded();
    }

    @Cuando("el usuario busca un producto específico")
    public void usuarioBuscaProducto() {
        testProductName = "Laptop Gaming";
        productsPage.searchForProduct(testProductName);
    }

    @Y("agrega el producto al carrito de compras")
    public void agregaProductoAlCarrito() {
        productsPage.selectFirstAvailableProduct();
        productDetailPage.setQuantity(1);
        productDetailPage.addProductToCart();
    }

    @Y("procede a finalizar la compra")
    public void procedeFinalizar() {
        cartPage.proceedToCheckout();
    }

    @Y("ingresa sus datos personales de entrega")
    public void ingresaDatosEntrega() {
        testFullName = "Juan García López";
        testEmail = "juan.garcia@example.com";
        testAddress = "Calle Principal 123, Apto 4B";
        testCity = "Madrid";
        testZipCode = "28001";

        shippingPage.enterFullName(testFullName);
        shippingPage.enterEmail(testEmail);
        shippingPage.enterAddress(testAddress);
        shippingPage.enterCity(testCity);
        shippingPage.enterZipCode(testZipCode);
    }

    @Y("selecciona el método de envío disponible")
    public void seleccionaMetodoEnvio() {
        shippingPage.selectShippingMethod();
    }

    @Y("proporciona sus datos de pago")
    public void proporcionaDatosPago() {
        paymentPage.enterCardNumber("4532123456789010");
        paymentPage.enterCardholderName("JUAN GARCIA");
        paymentPage.enterExpiryDate("12/26");
        paymentPage.enterCVV("123");
        paymentPage.completePurchase();
    }

    @Entonces("la compra se procesa exitosamente")
    public void compraProcesoExitoso() {
        assert orderConfirmationPage.isPurchaseConfirmed() : "La compra no fue procesada correctamente";
    }

    @Y("recibe la confirmación del pedido")
    public void recibePedidoConfirmacion() {
        assert orderConfirmationPage.isConfirmationPageLoaded() : "No se mostró la página de confirmación";
    }

    @Y("obtiene el número de seguimiento del envío")
    public void obtieneSeguimiento() {
        String trackingNumber = orderConfirmationPage.getTrackingNumber();
        assert trackingNumber != null && !trackingNumber.isEmpty() : "No se obtuvo el número de seguimiento";
    }
}
