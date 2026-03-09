# Frontend POM Factory Automation - Serenity BDD

Proyecto de automatización de tests de interfaz de usuario utilizando Serenity BDD, Selenium y patrón Page Object Model (POM) con Gradle.

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
│   │       ├── pages/          # Page Objects
│   │       ├── steps/          # Definiciones de pasos de Cucumber
│   │       └── utils/          # Utilidades y helpers
│   └── resources/
│       ├── features/           # Archivos de características Cucumber
│       ├── serenity.properties # Configuración de Serenity
│       └── logback.xml         # Configuración de logs
```

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

Crea Page Objects en `src/test/java/com/frontend/pom/pages/`:

```java
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");

    public void enterUsername(String username) {
        find(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        find(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        find(loginButton).click();
    }
}
```

## Estructura de Features

Crea archivos `.feature` en `src/test/resources/features/`:

```gherkin
Feature: Autenticación de usuarios

  @smoke
  Scenario: Usuario puede iniciar sesión con credenciales válidas
    Given el usuario está en la página de login
    When ingresa credenciales válidas
    And hace clic en el botón de loguar
    Then debe ver el dashboard principal
```

## Reportes

Los reportes se generan automáticamente en:
```
target/site/serenity/index.html
```

## Mejores Prácticas

- Utiliza Page Objects para encapsular la interacción con la interfaz
- Mantén los features enfocados en casos de negocio
- Agrupa los pasos relacionados en la misma clase
- Usa tags para categorizar tests (@smoke, @regression, @ui-critical)
- Aprovecha los screenshots automáticos para debugging
- Revisa los logs en `logs/frontend-pom.log` para investigar fallos
