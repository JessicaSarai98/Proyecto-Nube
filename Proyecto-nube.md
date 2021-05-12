# Desarrollo de Aplicaciones en la Nube 
### Documento de Arquitectura del Software
#### -Nombre del proyecto-
## Autores:
- **Miguel Chay Nah**
- **Efrain Aké Ojeda**
- **Jessica Sarai González Bautista**
- **Karina Carmona Vargas**

# Introducción
- **Propósito:**
   Actualmente, existen muchos negocios locales que no tienen la facilidad de promocionar sus productos o no cuentan con un poder adquisitivo suficiente para añadirse a plataformas en los cuales les ayuden a distribuir su producto. El propósito de esta aplicación es ayudar a los negocios locales a promocionar y expandir su negocio, en su pueblo o ciudad, de forma que su clientela aumente, también que brinden un servicio eficiente para satisfacer las necesidades de los clientes. 
- **Alcance**
- **Documentos de referencia**
# Arquitectura
- **Descripción de la arquitectura usada (capas)(Describir responsabilidades de las capas):**
**_Capa de presentación:_** se encarga de presentar la información de forma agradable al usuario, pero no le interesa de donde viene la información ni la lógica de negocio que hay detrás, en su lugar, solo sabe que existe una capa de negocio que le proporcionará la información.
**_Capa de negocio:_** se encarga de aplicar todas las reglas de negocio y validaciones, pero no le interesa como recuperar los datos, guardarlos o borrarlos, ya que para eso tiene una capa de persistencia que se encarga de eso.
**_Capa de persistencia:__** es la encargada de comunicarse a la base de datos, crear las instrucciones SQL para consultar, insertar, actualizar o borrar registros y retornarlos en un formato independiente a la base de datos.
**_Capa de datos:_** Almacena los datos. 

- **Diagrama de arquitectura con descripción (Arquitectura del proyecto completo):**

- **Diagrama de secuencia para los procesos más importante de la App (CRUD)**

- **Diagrama de base de la base de datos**

- **Descripción de las entidades**

_User:_

_Customer:_

_salesman:_

_commerce:_

_delivery_man:_

_City_id:_

_vehicle:_

_order:_

_order_detail:_

_order_statusr:_

_payment_type:_

_address:_

_product:_

_commerce_product:_


- **Diagrama entidad relacion**

![image](https://github.com/JessicaSarai98/Proyecto-Nube/blob/main/imagenes/entidad-relacion.PNG)

# Documentación de la API
**Documentación individual de cada Endpoint por cada entidad**
- **URL(URI)**
- **Descripción**
- **Campos requeridos**
- **Validaciones**
- **Tipo de datos de cada campo**
- **Respuesta (Response)**
- **Ejemplos del Request**

# Criterios de calidad





