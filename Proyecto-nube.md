# Desarrollo de Aplicaciones en la Nube 
## Documento de Arquitectura del Software
### Nombre: MarketApp

## Autores:
- **Miguel Chay Nah**
- **Efrain Aké Ojeda**
- **Jessica Sarai González Bautista**
- **Karina Carmona Vargas**

# Introducción
# **Propósito:**
Actualmente, existen muchos negocios locales que no tienen la facilidad de promocionar sus productos o no cuentan con un poder adquisitivo suficiente para añadirse a      plataformas en los cuales les ayuden a distribuir su producto. El propósito de esta aplicación es ayudar a los negocios locales a promocionar y expandir su negocio, en su pueblo o ciudad, de forma que su clientela aumente, también que brinden un servicio eficiente para satisfacer las necesidades de los clientes. 

# **Alcance:**
En este proyecto se centra en que los vendedores de comercios locales, puedan expandir su negocio, generando ingresos y clientes. Por otra parte, por la pandemia, se ha visto afectado muchos locales debido a que no se debe de salir ni estar en lugares concurridos, por lo que muchos negocios han cerrado al no tener clientes ni ingresos. A las personas se le es fácil y práctico revisar los productos en un dispositivo y solicitarlos con un simple clic, para que estos sean llevados a su dirección sin tener que salir de casa. Solo recibir el producto, desinfectalo y listo. 
Para poder lograr esto nos debemos de enfocar en:

- _El lado de los usuarios_: permitir que creen una cuenta (vendedor, cliente o repartidor) usando una dirección de correo electrónico. Cliente: capacidad de elegir el comercio y pedidos. Vendedor: capacidad de recibir ordenes de los clientes y de mostrar en la app los productos disponibles. Repartidor: capacidad de elegir la orden y de entregarlo al cliente. 
- _El lado del comercio:_ la aplicación para poder registrar usuarios, capturar información pertinente sobre el comercio como su nombre, ubicación, información de vendedores, etc.Publicar sus productos y dar seguimiento a los pedidos entrantes y actualizar sobre el estado de sus ordenes. 
Todo esto se puede lograr mediante API para responder consultas sobre lugares a traves de solicitudes HTTP, incluida ubicaciones geográficas y establecimientos comerciales. 

- **Documentos de referencia**

[API](https://fulcrum.rocks/blog/food-delivery-app/)

# Arquitectura
- **Descripción de la arquitectura usada (capas)(Describir responsabilidades de las capas):**

**_Capa de presentación:_** se encarga de presentar la información de forma agradable al usuario, pero no le interesa de donde viene la información ni la lógica de negocio que hay detrás, en su lugar, solo sabe que existe una capa de negocio que le proporcionará la información.

**_Capa de negocio:_** se encarga de aplicar todas las reglas de negocio y validaciones, pero no le interesa como recuperar los datos, guardarlos o borrarlos, ya que para eso tiene una capa de persistencia que se encarga de eso.

**_Capa de persistencia:_** es la encargada de comunicarse a la base de datos, crear las instrucciones SQL para consultar, insertar, actualizar o borrar registros y retornarlos en un formato independiente a la base de datos.

**_Capa de datos:_** Almacena los datos. 

<p align="center">
  <img width="500" height="500" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/capas.png">
</p>

<p align="center">
  <img width="500" height="500" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/capa.png">
</p>


- **Diagrama de arquitectura con descripción (Arquitectura del proyecto completo):**
- 
- **Diagrama de secuencia para los procesos más importante de la App (CRUD)**

**Creando un usuario:**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/create-user.png)

**Buscando un producto por el cliente:**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/read-products.png)

**Comprando productos**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/buy-product.png)

- **Diagrama de base de la base de datos**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/base-datos.png)

- **Descripción de las entidades**

**_User:_** esta entidad es la identificación de los usuarios que entren a la aplicación para darse de alta, como: cliente, vendedor y comercio. Esta entidad tiene los siguientes atributos: ID, nombre de usuario, correo, contraseña, token, el tipo de usuario y la fecha de registro. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/User.png">
</p>

**_Customer:_** esta entidad “Cliente”, como su nombre dice es para la persona que comprará los productos disponibles, obtiene dos llaves foráneas, el user_id de la entidad User, address_id de la entidad Address y payment_type_id de la entidad payment_type. Tambien se tienen los atributos como: nombre, apellido, teléfono y género. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/customer.png">
</p>

**_salesman:_** esta entidad “Vendedor”, como su nombre dice, es el responsable en vender sus productos a los clientes. Obtiene dos llaves foráneas, el user_id de la entidad User y city_id de la entidad City. Sus otros atributos son el nombre, apellido, teléfono, género, fecha de nacimiento y dirección. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/salesman.png">
</p>

**_commerce:_** esta entidad “Comercio”, se refiere al negocio donde saldrán los productos solicitados por los clientes. Obtiene dos llaves fóraneas, salesman_id de la entidad Salesman y city_id de la entidad City. Sus otros atributos son el nombre, rfc, descripción del lugar, teléfono, dirección y logo. 

<p align="center">
  <img width="300" height="350" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/commerce.png">
</p>

**_delivery_man:_** esta entidad “Repartidor”, como su nombre dice, es la persona responsable en recoger el producto solicitado por el cliente y llevarlo a su dirección. Obtiene tres llaves foráneas, user_id de la entidad User, city_id de la entidad City y vehicle_id de la entidad Vehicle.  Tambien obtiene los atributos como nombre, apellido, teléfono, género, fecha de nacimiento y dirección. 

<p align="center">
  <img width="300" height="350" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/delivery_man.png">
</p>

**_City_id:_** Esta entidad “Ciudad” se refiere a la ciudad donde se encuentran los clientes, los repartidores y los negocios. Tiene los atributos de ID y el nombre. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/city.png">
</p>

**_vehicle:_** esta entidad “Vehiculo” se refiere al tipo de vehiculo que el repartidor utiliza para llevar los productos solicitados por los clientes. Tiene los atributos de ID y el nombre (tipo). 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/vehicle.png">
</p>

**_order:_** esta entidad “Orden” se refiere a los productos solicitados por el cliente. Obtiene cuatro llaves foráneas, delivery_man_id de la entidad Delivery_man, customer_id de la entidad Customer y status_id de la entidad order_status. Tambien tiene los atributos de fecha de orden (fecha y hora) y la fecha de entrega (fecha y hora) y el precio total. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/order.png">
</p>

**_order_detail:_** esta entidad “detalle del orden”, se refiere a los detalles de la orden y del producto, tomando como llave foránea el order_id de la entidad Order y commerce_product_id de la entidad commerce_product. Tambien tiene los atributos como la cantidad de la orden, el subtotal y un valor booleano para saber si la orden ha sido entregada o aún no. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/order_detail.png">
</p>

**_order_status:_** esta entidad “estatus del orden” se refiere si el pedido ha sido aceptado, rechazado, si se está preparando, en camino o ya ha sido entregado. Tiene como atributos el ID y su nombre(los cuales son las acciones especificadas). 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/order_status.png">
</p>

**_payment_type:_** esta entidad “tipo de pago”, como su nombre dice, se refiere al tipo de pago que el cliente podrá realizar como efectivo o en tarjeta. Tiene los atributos id y el nombre (tipo).

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/payment_type.png">
</p>

**_address:_** esta entidad “Dirección” como su nombre dice, se refiere a la o las direcciones que el cliente puede guardar, ya que en ocasiones no siempre se está en el mismo lugar (trabajo, escuela, casa, etc) entonces esta bien añadir más direcciones de entrega, en caso de requerirlo. Obtiene dos llaves foraneas, city_id de la entidad City y customer_id de la entidad Customer. Tiene otros atributos como el ID, calle, cruzamiento y colonia. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/address.png">
</p>

**_product:_** esta entidad “Producto” como su nombre dice, se refiere a los productos que el negocio vende. Tiene los atributos ID y nombre. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/product.png">
</p>

**_commerce_product:_** esta entidad “productos del comercio” como su nombre dice, se refiere a los productos que se venden en el comercio. Se obtiene dos llaves foraneas, commerce_id de Commerce y product_id de Product. Tiene atributos como descripcion, precio y cantidad disponible. 

<p align="center">
  <img width="300" height="300" src="https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/commerce_product.png">
</p>

- **Diagrama entidad relacion**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/entidad-relacion.PNG)

# Documentación de la API

## Documentación individual de cada Endpoint por cada entidad

## **URL(URI)**

### User:
- GET/users -> Devuelve una lista de usuarios
- GET/users/id -> Devuelve un usuario con id especifica
- POST/users -> Crea un nuevo usuario
- PUT/users/id -> Actualiza el usuario onc id especifica
- DELETE/users/id -> Elimina el usuario con id especifica

### Campos requeridos
**POST:** username, password, email, type, registration_date

### Tipo de dato y validaciones:
- **String username** [varchar(128)] -> NOT NULL
- **String password** [varchar(128)] -> NOT NULL, caracteres >= 8, contenga dígitos, minúsculas y mayúsculas.
- **String email** [varchar(175)] -> NOT NULL, perteneciente a RegEx email según w3.org
- **String type** [enum(‘delivery_man’, ‘salesman’ , ‘customer’)]-> NOT NULL
- **String registration_date** [date]-> no vacio, dd-mm-yyyy, yyyy>año en curso - 18

### Ejemplo del Request y Response
- Crear un usuario (POST)
   - Request
      - UserData = 
      
            {“username”: “Efrain”, “password”: “1234AaBb”, “email”:  “name@ejemplo.com”, “type”: “customer”}
      - https://localhost:8080/api/users
   - Response:
   
         {“id”: “1”,
           “username”: “Efrain”, 
            “password”: “1234AaBb”, 
            “email”: “name@ejemplo.com”, 
            “token”: “3434534$$%$%$%d”
            “type”: “customer”
            “registration_date”: “11-05-2021 12:01:44 }

### Delivery_man:
- GET/delivery_man -> Devuelve una lista de repartidores
- GET/delivery_man/id -> Devuelve un repartidor específico
- POST/delivery_man -> Crea un nuevo repartidor
- PUT/delivery_man/id -> Actualiza el repartidor con el id específico
- DELETE/delivery_man/id -> Elimina el repartidor con el id correspondiente

### Campos requeridos: 
**POST:** user_id, firstname, lastname, cellphone, sex, birth_date, address, city_id, vehicle_id

### Tipo de dato y validaciones:
- **Integer user_id** [int(10)] -> exista un user con el mismo id
- **String firstname** [[varchar(128)]-> NOT NULL
- **String lastname** [varchar(128)]->  NOT NULL
- **String cellphone** [varchar(40)]->  not null
- **Strig sex** [enum('man','woman')] -> NOT NULL
- **String birth_date** [date] -> NOT NULL, dd-mm-yyyy, yyyy>año en curso - 18
- **String address** [varchar(175)] -> NOT NULL
- **Integer city_id** [int(10)] -> NOT NULL
- **Integer vehicle_id** [int(10)] -> NOT NULL

### Ejemplo del Request y Response
- Crear un delivery_man(POST)
  - Request 
    - delivery_man_Data=
    
    
                           {
                          “ firstname”: “Efrain”, 
                          “lastname”: “ake”, 
                          “cellphone”: “9997234556”, 
                          “sex”: “hombre”, 
                          “birth_date”: “12-12-2021”, 
                          “address”: “calle 29 merida yucatan”, 
                          “city_id”: “1”, 
                           “vehicle_id”:1
                          }
     - https://localhost:8080/delivery_man
   - Response
      
               {
              “user_id”: 1,
              “ firstname”: “Efrain”, 
              “lastname”: “ake”, 
              “cellphone”: “9997234556”, 
              “sex”: “hombre”, 
              “birth_date”: “12-12-2021”, 
              “address”: “calle 29 merida yucatan”, 
              “city_id”: “1”, 
               “vehicle_id”:1
              }
### Salesman
- GET/salesman -> Devuelve una lista de vendedores
- GET/salesman/id -> Devuelve un vendedor especifico
- POST/salesman -> Crea un nuevo vendedor
- PUT/salesman/id -> Actualiza el vendedor con el id correspondiente
- DELETE/salesman/id -> Elimina el vendedor con el ud correspondiente

### Campos requeridos
**POST:** user_id, firstname, lastname, cellphone, sex, birth_date, addressm city_id

### Tipo de dato y validaciones
- **Integer user_id** [int(10)] -> exista un user con el mismo id
- **String firstname** [[varchar(128)]-> NOT NULL
- **String lastname** [varchar(128)]->  NOT NULL
- **String cellphone** [varchar(40)]->  not null
- **Strig sex** [enum('man','woman')] -> NOT NULL
- **String birth_date** [date] -> NOT NULL, dd-mm-yyyy, yyyy>año en curso - 18
- **String address** [varchar(175)] -> NOT NULL
- **Integer city_id** [int(10)] -> NOT NULL

### Ejemplo del Request y Response: 
- Crear un salesman (POST)
  - Request
    - salesman_Data=

          {
          “user_id”: 1,
          “ firstname”: “Efrain”, 
          “lastname”: “ake”, 
          “cellphone”: “9997234556”, 
          “sex”: “hombre”, 
          “birth_date”: “12-12-2021”, 
          “address”: “calle 29 merida yucatan”, 
          “city_id”: “1”, 
          }
     - https://localhost:8080/api/salesman
  - Response:

            {
            “user_id”: 2,
            “ firstname”: “Juan”, 
            “lastname”: “Alvarez”, 
            “cellphone”: “9997234556”, 
            “sex”: “hombre”, 
            “birth_date”: “12-12-2021”, 
            “address”: “calle 29 merida yucatan”, 
            “city_id”: “1”, 
            }

### Customer
- GET/customers -> Devuelve una lista de clientes
- GET/customers/id -> Devuelve un cliente especifico
- POST/customers -> Crea un nuevo cliente
- PUT/customers/id -> Actualiza el cliente con el id correspondiente
- DELETE/customers/id -> Elimina el cliente con el ud correspondiente

### Campos requeridos
**POST:** user_id, firstname, lastname, cellphone, sex, birth_date, address, payment_type_id

### Tipo de dato y validaciones 
- **Integer user_id** [int(10)] -> exista un user con el mismo id
- **String firstname** [[varchar(128)]-> NOT NULL
- **String lastname** [varchar(128)]->  NOT NULL
- **String cellphone** [varchar(40)]->  not null
- **Strig sex** [enum('man','woman')] -> NOT NULL
- **String birth_date** [date] -> NOT NULL, dd-mm-yyyy, yyyy>año en curso - 18
- **Integer address_id** [int(10)] -> existe un address con el mismo id
- **Integer payment_type_id** [int(10)] -> existe un payment con el mismo id

### Ejemplo del Request y Response
- Crear un customer(POST)
  - Request
    - customer_Data= 

          {
          “user_id”: 1,
          “ firstname”: “Efrain”, 
          “lastname”: “ake”, 
          “cellphone”: “9997234556”, 
          “sex”: “hombre”, 
          “birth_date”: “12-12-2021”, 
          “address”: “calle 29 merida yucatan”, 
          “payment_type_id”: 1, 
          }
       
     - https://localhost:8080/api/customer
     
   - Response: 

            {
            “user_id”: 1,
            “ firstname”: “Efrain”, 
            “lastname”: “ake”, 
            “cellphone”: “9997234556”, 
            “sex”: “hombre”, 
            “birth_date”: “12-12-2021”, 
            “address”: “calle 29 merida yucatan”, 
            “payment_type_id”: 1, 
            }

### Commerce
- GET/commerce -> Devuelve una lista de comercios
- GET/commerce/id -> Devuelve un comercio especifico
- POST/commerce -> Crea un nuevo comercio
- PUT/commerce/id -> Actualiza el comercio con el id correspondiente
- DELETE/commerce/id -> Elimina el comercio con el id correspondiente

### Campos requeridos
**POST:** username, password, email, type

### Tipo de dato y validaciones 
- **Integer salesman_id** [int(10)] -> exista un salesman con el mismo id
- **String commercial_name** [[varchar(128)]-> NOT NULL
- **String rfc** [varchar(12)]->  NOT NULL
- **String description** [varchar(100)]->  not null
- **String phone** [varchar(175)] -> NOT NULL
- **String address** [varchar(175)] -> NOT NULL
- **Integer city_id** [int(10)] -> NOT NULL
- **String logo** [varchar(100)] -> NOT NULL

### Ejemplo del Request y Response
- Crear un comercio(POST)
  - Request
    - customer_Data= 

          {
          “salesman_id”: 1,
          commercial_name”: “coca cola”, 
          “rfc”: “12144324”, 
          “description”: “refrescos”, 
          “phone”: “99765656”, 
          “address”: “calle 29 merida yucatan”, 
          “city_id”: 1,
          “logo”: “https://i.blogs.es/6091fa/java/450_1000.jpg” 
          }
     - https://localhost:8080/api/commerce

   - Response: 

            {
            “user_id”: 1,
            “ firstname”: “Efrain”, 
            “lastname”: “ake”, 
            “cellphone”: “9997234556”, 
            “sex”: “hombre”, 
            “birth_date”: “12-12-2021”, 
            “address”: “calle 29 merida yucatan”, 
            “payment_type_id”: 1, 
            }
          
### Product 
- GET/product -> Devuelve una lista de productos
- GET/product/id -> Devuelve un producto especifico
- POST/product -> Crea un nuevo producto
- PUT/product/id -> Actualiza el producto con el id correspondiente
- DELETE/product/id -> Elimina el producto con el id correspondiente

### Campos requeridos
**POST:** name

### Tipo de dato y validaciones 
- **String name** [[varchar(128)]-> NOT NULL

### Ejemplo del Request y Response
- Crear un producto(POST)
  - Request
    - producto_Data= 

          {
          “name”: “galletas”,
          }

     - https://localhost:8080/api/producto

   - Response: 

             {
            “id”: 1,
            “name”: “galletas”,
            }
           
### Commerce_product
- GET/commerce-product -> Devuelve una lista de productos de un comercio
- GET/commerce-product/id -> Devuelve un producto de un comercio especifico
- POST/commerce-product -> Crea un nuevo producto de un comercio
- PUT/commerce-product/id -> Actualiza el producto de un comercio con el id correspondiente
- DELETE/commerce-product/id -> Elimina el producto de un comercio con el ud correspondiente

### Campos requeridos
**POST:** commerce_id, producto_id, description, price, stock

### Tipo de dato y validaciones 
- **Integer commerce_id** [int(10)] -> exista un comercio con el mismo id
- **String description** [[varchar(100)]-> NOT NULL
- **Double price** [Decimal(10,2)] -> NOT NULL
- **Integer product_id** [int(10)] -> exista un producto con el mismo id
- **Integer stock** [int(10)] -> NOT NULL, No mayor a cero 

### Ejemplo del Request y Response
- Crear un producto de un comercio(POST)
  - Request
    - RequestBody= 

          {
          “commerce_id”: 1, 
          “product_id”: “5”, 
          “desciption”: “coca-cola de medio litro”, 
          “price”: 10.50, 
          “stock”: 4}

     - https://localhost:8080/api/commerce-product

   - Response: 

          {
          “commerce_id”: 1, 
          “product_id”: “5”, 
          “desciption”: “coca-cola de medio litro”, 
          “price”: 10.50, 
          “stock”: 4
          }

### Vehicle
- GET/vehicle -> Devuelve una lista de vehiculos
- GET/vehicle/id -> Devuelve un vehiculo especifico
- POST/vehicle -> Crea un nuevo vehiculo
- PUT/vehicle/id -> Actualiza el vehiculo con el id correspondiente
- DELETE/vehicle/id -> Elimina el vehiculo con el id correspondiente

### Campos requeridos
**POST:** name

### Tipo de dato y validaciones 
- **String name** [[varchar(128)]-> NOT NULL

### Ejemplo del Request y Response
- Crear un vehiculo(POST)
  - Request
    - vehicle_Data= 

          {
          “name”: “bicicleta”,
          }

     - https://localhost:8080/api/vehicle

   - Response: 

             {
            “id”: 1,
            “name”: “bicileta”,
            }
           
### City
- GET/city -> Devuelve una lista de ciudades
- GET/city/id -> Devuelve una ciudad especifica
- POST/city -> Crea una nuevo ciudad
- PUT/city/id -> Actualiza la ciudad con el id correspondiente
- DELETE/city/id -> Elimina la ciudad con el id correspondiente

### Campos requeridos
**POST:** name

### Tipo de dato y validaciones 
- **String name** [[varchar(128)]-> NOT NULL

### Ejemplo del Request y Response
- Crear una ciudad(POST)
  - Request
    - RequestBody= 

          {
          “name”: “Merida”,
          }

     - https://localhost:8080/api/city

   - Response: 

             {
            “id”: 1,
            “name”: “bicileta”,
            }

### Address
- GET/address -> Devuelve una lista de direcciones
- GET/address/id -> Devuelve una direccion especifica
- POST/address -> Crea una nueva direccion
- PUT/address/id -> Actualiza la direccion con el id correspondiente
- DELETE/address/id -> Elimina la direccion con el id correspondiente

### Campos requeridos
**POST:** customer_id, street, crossing, suburb, city_id

### Tipo de dato y validaciones 
- **Integer customer_id** [int(10)] -> exista un customer con el mismo id
- **String street** [[varchar(175)]-> NOT NULL
- **String crossing** [varchar(128)]->  NOT NULL
- **String suburb** [varchar(128)]->  not null
- **Integer city_id** [int(10)] -> NOT NULL

### Ejemplo del Request y Response
- Crear una direccion(POST)
  - Request
    - address_Data= 

          {
            “customer_id”: 1, 
           “street”: “calle 12 x 55 y 55”, 
          “city_id”: 5
 
          }
     - https://localhost:8080/api/address

   - Response: 

          {
          “customer_id”: 1, 
          “street”: “calle 12 x 55 y 55”, 
          “city_id”: 5, 
          “crossing”: null, 
          “suburb”: null
          }

### Order_detail
- GET/order-detail -> Devuelve una lista de detalle de pedidos
- GET/order-detail/id -> Devuelve un detalle de pedidos especifico
- POST/order-detail -> Crea un detalle de pedido
- PUT/order-detail/id -> Actualiza el detalle de pedido con el id correspondiente
- DELETE/order-detail/id -> Elimina el detalle de pedido con el id correspondiente

### Campos requeridos
**POST:** order_id, commerce_product_id, amount

### Tipo de dato y validaciones 
- **Integer order_id** [int(10)] -> exista una orden con el mismo id
- **Integer commerce_product_id** [int(10)] -> exista una commerce_product con el mismo id
- **Integer amount** [int(10)] > NOT NULL
- **Double subtotal** [Decimal(10,2)] -> NOT NULL
- **Bool finished** [bool] -> por defecto 0

### Ejemplo del Request y Response
- Crear un detalle de orden(POST)
  - Request
    - order_detail_Data= 

          {
          “order_id”: 1, 
          “commerce_product_id”: 4, 
          “amount”: 2

          }
     - https://localhost:8080/api/order_detail

   - Response: 

            {
            “id": "1",
            name:"efectivo"
            }
            
### Order
- GET/order -> Devuelve una lista de pedidos
- GET/order/id -> Devuelve un pedido especifico
- POST/order -> Crea un nuevo pedido
- PUT/order/id -> Actualiza el pedido con el id correspondiente
- DELETE/order/id -> Elimina el pedido con el id correspondiente

### Campos requeridos
**POST:** customer_id

### Tipo de dato y validaciones 
- **Integer customer_id** [int(10)] -> exista una customer con el mismo id
- **Integer delivery_man_id** [int(10)] -> exista una repartidor con el mismo id
- **Integer status_id** [int(10)] -> exista un status con el mismo id
- **Integer payment_type_id** [int(10)] -> exista un tipo de pago con el mismo id
- **Double total** [Decimal(10,2)] -> NOT NULL
- **String order_date** [datetime] -> NOT NULL, dd-mm-yyyy hh:mm:ss, yyyy< año en curso 18
- **String delivered_date** [datetime] -> NOT NULL, dd-mm-yyyy hh:mm:ss, yyyy< año en curso 18

### Ejemplo del Request y Response
- Crear una orden(POST)
  - Request
    - order_Data= 

          {
          “customer_id": "1"

          }
     - https://localhost:8080/api/order

   - Response: 

            {
           “id”: “1”,
          “delivery_man_id”: null,
          “customer_id”: “1”,
          “status_id”: “1”,
          “order_date”: “11-05-2021 12:00:32”,
          “delivered_Date”: null,
          “total”: “209”,
          “payment_type”: “1”,
          }

### Payment_type
- GET/payment_type -> Devuelve una lista de tipos de pago
- GET/payment_type/id -> Devuelve un tipo de pago especifico
- POST/payment_type -> Crea una nuevo tipo de pago
- PUT/payment_type/id -> Actualiza el tipo de pago con el id correspondiente
- DELETE/payment_type/id -> Elimina el tipo de pago con el id correspondiente

### Campos requeridos
**POST:** name

### Tipo de dato y validaciones 
- **String name** [[varchar(128)]-> NOT NULL

### Ejemplo del Request y Response
- Crear un tipo de pago(POST)
  - Request
    - payment_type_Data= 

          {
          “name”: “efectivo”,
          }

     - https://localhost:8080/api/payment_type

   - Response: 

             {
            “id”: 1,
            “name”: “efectivo”,
            }

### Order_status
- GET/order_status -> Devuelve una lista de estados de orden
- GET/order_status/id -> Devuelve unb estado de orden especifica
- POST/order_status -> Crea un nuevo estado de orden
- PUT/order_status/id -> Actualiza el estado de orden con el id correspondiente
- DELETE/order_status/id -> Elimina el estado de orden con el id correspondiente

### Campos requeridos
**POST:** name

### Tipo de dato y validaciones 
- **String name** [[varchar(128)]-> NOT NULL

### Ejemplo del Request y Response
- Crear una ciudad(POST)
  - Request
    - order_status_Data= 

          {
          “name”: “aceptado”,
          }

     - https://localhost:8080/api/order-status

   - Response: 

             {
            “id”: 1,
            “name”: “aceptado”,
            }
            
### Ejemplos del Request y Response
- Crear un usuario(POST)
  - Request
    - User_Data =
    
          {“username”: “Efrain”, “password”: “1234AaBb”, “email”: “name@ejemplo.com”,             “type”: “customer”}
    - https://localhost:8080/api/user
    
  - Response

        {“id”: “1”,
        “username”: “Efrain”, 
        “password”: “1234AaBb”, 
        “email”: “name@ejemplo.com”, 
        “token”: “3434534$$%$%$%d”
        “type”: “customer”
        “registration_date”: “11-05-2021 12:01:44”
         }

            
- Consultar un usuario(GET)
  - Request       
    - https://localhost:8080/api/user/10
    
  - Response
  
           {“id”: “1”, “username”: “Efrain”, “password”: “1234AaBb”, “email”:    “name@ejemplo.com”, “token”: “3434534$$%$%$%d”, “type”: “customer”, “registration_date”: “11-05-2021 12:01:44” }
           
   
- Consultar un lista de usuarios(GET)
  - Request       
    - https://localhost:8080/api/users
    
  - Response
  
          [ {“id”: “1”, “username”: “Efrain”, “password”: “1234AaBb”, “email”: “12345”, “token”: “3434534$$%$%$%d”, “type”: “customer”, “registration_date”: “11-05-2021 12:01:44” }, {“id”: “2”, “username”: “Efrain2”, “password”: “1234AaBb”, “email”: “name2@ejemplo.com”, “token”: “34345s34$s$%$%$%d”, “type”: “customer”, “registration_date”: “11-05-2021 12:01:44” } ]}

    


