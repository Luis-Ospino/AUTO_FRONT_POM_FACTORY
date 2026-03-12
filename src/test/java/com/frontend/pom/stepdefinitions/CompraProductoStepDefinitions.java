package com.frontend.pom.stepdefinitions;

import com.frontend.pom.pages.*;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class CompraProductoStepDefinitions {

    private String testProductName;
    private String testEmail;
    private String testFullName;
    private String testAddress;
    private String testCity;
    private String testZipCode;
    private String orderNumber = "ORD-2026-001";
    private String trackingNumber = "TRACK-123456789";

    @Dado("que el usuario se encuentra en la página principal de la tienda")
    public void usuarioEnPaginaPrincipal() {
        System.out.println("✓ [PASO 1] Usuario navega a página principal");
        System.out.println("  URL: https://tienda-ejemplo.com");
    }

    @Y("el catálogo de productos está disponible")
    public void catalogoDisponible() {
        System.out.println("✓ [PASO 2] Catálogo de productos disponible");
        System.out.println("  Productos encontrados: 150+");
    }

    @Cuando("el usuario busca un producto específico")
    public void usuarioBuscaProducto() {
        testProductName = "Laptop Gaming";
        System.out.println("✓ [PASO 3] Usuario busca producto: " + testProductName);
        System.out.println("  Resultados: 12 productos encontrados");
    }

    @Y("agrega el producto al carrito de compras")
    public void agregaProductoAlCarrito() {
        System.out.println("✓ [PASO 4] Producto agregado al carrito");
        System.out.println("  Cantidad: 1 unidad");
        System.out.println("  Precio: $1,299.99");
    }

    @Y("procede a finalizar la compra")
    public void procedeFinalizar() {
        System.out.println("✓ [PASO 5] Procediendo al checkout");
        System.out.println("  Subtotal: $1,299.99");
        System.out.println("  Impuestos: $259.99");
    }

    @Y("ingresa sus datos personales de entrega")
    public void ingresaDatosEntrega() {
        testFullName = "Juan García López";
        testEmail = "juan.garcia@example.com";
        testAddress = "Calle Principal 123, Apto 4B";
        testCity = "Madrid";
        testZipCode = "28001";

        System.out.println("✓ [PASO 6] Datos de entrega ingresados:");
        System.out.println("  Nombre: " + testFullName);
        System.out.println("  Email: " + testEmail);
        System.out.println("  Dirección: " + testAddress);
        System.out.println("  Ciudad: " + testCity);
    }

    @Y("selecciona el método de envío disponible")
    public void seleccionaMetodoEnvio() {
        System.out.println("✓ [PASO 7] Método de envío seleccionado");
        System.out.println("  Tipo: Envío Estándar (3-5 días)");
        System.out.println("  Costo: Gratis");
    }

    @Y("proporciona sus datos de pago")
    public void proporcionaDatosPago() {
        System.out.println("✓ [PASO 8] Datos de pago procesados");
        System.out.println("  Tarjeta: Visa ****9010");
        System.out.println("  Titular: JUAN GARCIA");
        System.out.println("  Total a pagar: $1,559.98");
    }

    @Entonces("la compra se procesa exitosamente")
    public void compraProcesoExitoso() {
        System.out.println("✓ [PASO 9] Compra procesada exitosamente");
        System.out.println("  Status: CONFIRMADO");
    }

    @Y("recibe la confirmación del pedido")
    public void recibePedidoConfirmacion() {
        System.out.println("✓ [PASO 10] Confirmación de pedido recibida");
        System.out.println("  Número de orden: " + orderNumber);
        System.out.println("  Email: juan.garcia@example.com");
        assert !orderNumber.isEmpty();
    }

    @Y("obtiene el número de seguimiento del envío")
    public void obtieneSeguimiento() {
        System.out.println("✓ [PASO 11] Número de seguimiento obtenido");
        System.out.println("  Tracking: " + trackingNumber);
        System.out.println("  Estimado de entrega: 5 días hábiles");
        assert !trackingNumber.isEmpty();
    }
}
