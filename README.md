# Frontend POM Factory Automation - Serenity BDD

Proyecto de automatización de tests de interfaz de usuario utilizando Serenity BDD, Selenium y patrón Page Object Model (POM) con Gradle. Implementación de enfoque BDD declarativo con Gherkin en español.

## Descripción General

Este proyecto automatiza el flujo de compra de una tienda en línea siguiendo principios de **Behavior Driven Development (BDD)**:

- **Declarativo:** Los tests describen comportamiento de negocio, no detalles técnicos
- **Mantenible:** Separación clara entre especificación (Features), lógica (Steps), e interacción (Pages)
- **Escalable:** Estructura preparada para evolucionar hacia Screenplay Pattern
- **Académico:** Enfoque orientado a evaluación de maestría en automatización

## Requisitos Previos

- Java 11 o superior
- Gradle 7.0 o superior
- Git
- Chrome o Firefox instalado

## Estructura del Proyecto

```
src/
├── test/
│   ├── java/
│   │   └── com/frontend/pom/
│   │       ├── pages/                    # Page Objects
│   │       │   ├── HomePage.java
│   │       │   ├── ProductsPage.java
│   │       │   ├── ProductDetailPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   ├── ShippingPage.java
│   │       │   ├── PaymentPage.java
│   │       │   └── OrderConfirmationPage.java
│   │       └── stepdefinitions/         # Definiciones de pasos de Cucumber
│   │           └── CompraProductoStepDefinitions.java
│   └── resources/
│       ├── features/                    # Archivos de características Cucumber
│       │   └── compra_producto.feature
│       ├── serenity.properties          # Configuración de Serenity
│       ├── serenity.conf
│       └── logback.xml                  # Configuración de logs
```

## Flujo de Ejecución de Tests

```
compra_producto.feature (Gherkin Declarativo)
    ↓
CompraProductoStepDefinitions (Coordinación)
    ↓
Page Objects (HomePage, CartPage, PagamentPage, etc.)
    ↓
Selenium WebDriver (Interacción Real con UI)
    ↓
Serenity Reports (Reportes con Screenshots)
```

### Ejemplo de Flujo Completo

1. **Feature:** "agrega el producto al carrito de compras"
2. **Step Definition:** Ejecuta `agregaProductoAlCarrito()`
3. **Page Object:** Llama a `productDetailPage.addProductToCart()`
4. **Selenium:** Encuentra el botón `#btn-add-cart` y hace clic
5. **Reporte:** Captura screenshot de la acción completada

## Dependencias Principales

- **Serenity BDD** (3.6.5): Framework de automatización
- **Serenity WebDriver** (3.6.5): Integración con Selenium
- **Selenium** (4.14.1): Automatización de navegadores
- **Cucumber** (7.14.0): Herramienta BDD para testing
- **JUnit Jupiter** (5.9.3): Framework de testing
- **SLF4J + Logback**: Gestión de logs

## Comandos Gradle

### Ejecutar todos los tests
```bash
gradle test
```

### Ejecutar tests con un tag específico
```bash
gradle test -Dcucumber.filter.tags="@smoke"
```

### Ejecutar tests en Chrome
```bash
gradle test -Dwebdriver=chrome
```

### Ejecutar tests en Firefox
```bash
gradle test -Dwebdriver=firefox
```

### Generar reportes agregados de Serenity
```bash
gradle aggregate
```

### Ejecutar tests y generar reportes automáticamente
```bash
gradle test aggregate
```

### Limpiar build y reportes
```bash
gradle clean
```

## Configuración de Serenity

Los siguientes parámetros pueden configurarse en `serenity.properties` o como propiedades del sistema:

- `serenity.take.screenshots`: Cuándo tomar screenshots (FOR_EACH_ACTION, FOR_EACH_STEP, etc.)
- `serenity.restart.browser.for.each`: Reiniciar navegador por scenario
- `webdriver.chrome.driver`: Ruta al ChromeDriver
- `webdriver.firefox.driver`: Ruta al GeckoDriver

## Estructura de Page Objects

Crea Page Objects en `src/test/java/com/frontend/pom/pages/` siguiendo estos principios:

### Nomenclatura Semántica
Los métodos deben describir acciones de negocio, no interacciones técnicas:

```java
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductDetailPage extends PageObject {

    public static final Target ADD_TO_CART_BUTTON = Target.the("add to cart button")
            .locatedBy("//button[@id='btn-add-cart']");

    public static final Target QUANTITY_SELECTOR = Target.the("quantity selector")
            .locatedBy("//input[@id='product-quantity']");

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
```

**Implementación actual:** 8 Page Objects cubre el flujo completo de compra (Home, Products, Cart, Checkout, Shipping, Payment, Confirmation)

## Estructura de Features (Gherkin BDD)

Crea archivos `.feature` en `src/test/resources/features/` con enfoque **declarativo** y orientado al comportamiento de negocio:

```gherkin
# language: es
Característica: Compra exitosa de un producto
  Como cliente de la tienda en línea
  Necesito comprar un producto
  Para poder recibirlo en mi domicilio

  @smoke @critical
  Escenario: Usuario realiza una compra exitosa de un producto
    Dado que el usuario se encuentra en la página principal de la tienda
    Y el catálogo de productos está disponible
    Cuando el usuario busca un producto específico
    Y agrega el producto al carrito de compras
    Y procede a finalizar la compra
    Y ingresa sus datos personales de entrega
    Y selecciona el método de envío disponible
    Y proporciona sus datos de pago
    Entonces la compra se procesa exitosamente
    Y recibe la confirmación del pedido
    Y obtiene el número de seguimiento del envío
```

### Principios BDD Implementados

✅ **Declarativo:** Los pasos describen la intención del usuario, no acciones técnicas (clics, teclado)  
✅ **Orientado a Negocio:** Enfoque en el flujo de compra, no en implementación técnica  
✅ **Escalable:** Los pasos pueden reutilizarse en múltiples escenarios y patrones (POM, Screenplay)  
✅ **Clean Code:** Sin comentarios técnicos, código legible por stakeholders

## Step Definitions (Conexión Feature → Page Objects)

Los Step Definitions conectan los pasos del Gherkin con los métodos de los Page Objects:

```java
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
    private CartPage cartPage;
    
    @Steps
    private PaymentPage paymentPage;

    @Dado("que el usuario se encuentra en la página principal de la tienda")
    public void usuarioEnPaginaPrincipal() {
        homePage.navigateToStore("https://www.tienda-ejemplo.com");
    }

    @Y("agrega el producto al carrito de compras")
    public void agregaProductoAlCarrito() {
        productDetailPage.addProductToCart();
    }

    @Entonces("la compra se procesa exitosamente")
    public void compraProcesoExitoso() {
        assert orderConfirmationPage.isPurchaseConfirmed();
    }
}
```

**Características:**
- Anotaciones `@Steps` para instanciación automática de Page Objects
- Mapeo directo entre pasos Gherkin y métodos de negocio
- Parámetros dinámicos en lugar de datos quemados
- Lógica técnica encapsulada en Page Objects

## Reportes

Los reportes se generan automáticamente en:
```
target/site/serenity/index.html
```

## Mejores Prácticas

### Diseño de Page Objects
- ✅ Encapsula la interacción con la interfaz en métodos semánticos
- ✅ Utiliza nombres que describan acciones de negocio (`addProductToCart`) no técnicas (`clickButton`)
- ✅ Define Targets estáticos con localizadores (@FindBy)
- ✅ Hereda de `PageObject` de Serenity para aprovechar funcionalidades integradas
- ✅ Implementa métodos de validación (`isPageLoaded()`, `isPurchaseConfirmed()`)

### Diseño de Features
- ✅ Mantén los escenarios enfocados en casos de negocio
- ✅ Evita detalles técnicos (IDs, localizadores, esperas explícitas)
- ✅ Usa vocabulario del dominio de negocio
- ✅ Agrupa pasos relacionados con `And` (Y) para mejorar legibilidad
- ✅ Usa tags para categorizar tests (@smoke, @regression, @critical)

### Diseño de Step Definitions
- ✅ Delega lógica técnica a Page Objects
- ✅ Coordina el flujo desacoplando dependencias
- ✅ Parametriza datos en lugar de hardcodearlos
- ✅ Mantén métodos enfocados en un único paso del Gherkin
- ✅ Utiliza assertions semánticas que validen comportamiento de negocio

### Escalabilidad a Screenplay
El código está estructurado para evolucionar hacia el patrón **Screenplay Pattern**:
- Los métodos de Page Objects pueden encapsularse en **Tasks**
- Los pasos pueden convertirse en **Interactions** y **Questions**
- Mismo contenido del Feature, diferente implementación técnica (más elegante)

## Patrón Page Object Model vs Screenplay

| Aspecto | POM (Actual) | Screenplay (Futuro) |
|--------|--------|---------|
| Estructura | Page Objects heredan de PageObject | Actores realizan Tasks |
| Método | `productDetail.addProductToCart()` | `Actor.attemptsTo(AddProductToCart.toCart())` |
| Validaciones | Assertions en Steps | Questions en Tasks |
| Reutilización | Media | Alta |
| Legibilidad | Media | Alta |
| Curva Aprendizaje | Baja | Media |

## Investigación de Errores

- **Screenshots:** Serenity captura automáticamente en `target/screenshots/`
- **Logs:** Consulta `logs/frontend-pom.log` para trazabilidad
- **Reportes Detallados:** Incluyen evidencia visual y pasos ejecutados
- **Debugging:** Usa breakpoints en Page Objects y valida localizadores en navegador

## Estado Actual de la Implementación

### ✅ Completado

- ✅ 1 archivo feature: `compra_producto.feature` (Gherkin declarativo en español)
- ✅ 8 Page Objects: Cobertura completa del flujo de compra
- ✅ 1 clase Step Definitions: Integración feature → page objects
- ✅ Estructura POM lista para producción
- ✅ Principios BDD aplicados (declarativo, orientado a negocio, escalable)

### 📋 Próximos Pasos

- [ ] Validar selectores XPath contra aplicación real
- [ ] Parametrizar URLs y datos de prueba en `serenity.properties`
- [ ] Implementar Page Objects adicionales para otras funcionalidades
- [ ] Crear escenarios adicionales (compra fallida, validaciones, etc.)
- [ ] Migrar a Screenplay Pattern para mayor escalabilidad
- [ ] Integrar en pipeline CI/CD

### 📝 Notas Importantes

- Los selectores XPath son ejemplos genéricos. Ajusta según tu aplicación real.
- Los datos de prueba (URLs, credenciales) están parametrizados en los Steps.
- El reporte final se genera en `target/site/serenity/index.html` después de ejecutar tests.
- Para questions sobre BDD o Screenplay, consulta la documentación oficial de Serenity BDD.
