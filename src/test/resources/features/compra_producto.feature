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
