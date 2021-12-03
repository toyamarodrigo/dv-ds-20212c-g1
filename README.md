# Trabajo Práctico - Diseño de Sistemas

## Integrantes ✒️

* **Natalia Oliveto** - *Desarrolladora*
* **Marcos Rearte** - *Desarrollador*
* **Toyama Rodrigo** - *Desarrollador*

## Tienda Ropita - Enunciado

Se require:

* Identificar los requerimientos
* Presentar una solución usando el paradigma de objetos (pseudocódigo, diagrama de clases).
* Explicar todo lo que considere necesario en prosa.
* Si descarta alguna alternativa durante el desarrollo de la solución, o tiene otra solución explicarla brevemente

La conocida empresa de ropa formal para caballeros, **Tienda Ropita**, es capaz de darle
soporte a la venta de prendas. Un fragmento de la grabación del analista con el cliente:

> “Queremos saber el precio de venta de una prenda y sus tipos, los tipos de prenda son: sacos, pantalones, camisas.”

El cálculo del precio de una prenda es, el precio propio de la prenda modificado según el
estado de la prenda, que pueden ser:

* Nueva: en este caso no modifican el precio base.

* Promoción: Le resta un valor fijo decidido por el usuario.

* Liquidación: Es un 50% del valor del producto.

Ah, un requerimiento más: **Tienda Ropita** registra las ventas de estas prendas y necesita
saber las ganancias de un determinado día.

> “Cada venta tiene asociada las prendas que se vendieron, su cantidad y la fecha de venta. Las ventas pueden ser en efectivo o con tarjeta. En el caso que sea con tarjeta, tienen el mismo comportamiento que en efectivo (el cual no modifica el precio), solo que se le aplica un recargo según la cantidad de cuotas seleccionadas (cantidad de cuotas * un coeficiente fijo + 0.01 del valor de cada prenda).”

## Cómo empezar

Debemos tener

* **Maven**
* **Java 8**
* **[Lombok](https://projectlombok.org/)**
* **[Eclipse](https://www.eclipse.org/)**
* **MySQL** ejecutando
* Conexión a **Base de Datos**
  * default: `dv-ds-20212c-g1` (si se quiere cambiar nombre, chequear `application.properties`)

```shell
# Clonar repositorio
git clone https://github.com/toyamarodrigo/dv-ds-20212c-g1.git
```

### Importar proyecto en Eclipse

* `File > Import... > Maven > Existing Maven Projects`
* Seleccionar carpeta donde se encuentra el repositorio

Una vez importado

`Click derecho sobre el proyecto > Run As.. > Maven clean`

`Click derecho sobre el proyecto > Run As.. > Maven install`

o en consola sobre el proyecto a la altura del `pom.xml`

```shell
dv-ds-20212c-g1$ mvn clean install
```

### Instalación de Lombok

* Descarga de Lombok [https://projectlombok.org/download](https://projectlombok.org/download)
* Abrir lombok.jar
* Seleccionar Eclipse.app
* Install / Update
* Reiniciar Eclipse

## Spring Tools 4

* Abrir Eclipse
* Help > Eclipse Marketplace...
* Buscar Spring Tools 4 e instalar
* Una vez instalada, agregar vista `"Boot Dashboard"` (Ctrl + 3, Boot Dashboard)

### Ejecutar proyecto

`Click derecho sobre el proyecto > Run As.. > Spring Boot App`

o en consola sobre el proyecto a la altura del `pom.xml`

```shell
dv-ds-20212c-g1$ mvn spring-boot:run
```

> estará deployado en <http://localhost:8090>

**En caso de no encontrar la aplicación, verificar puerto/bbdd en `application.properties` o servicio mysql ejecutando**

## Endpoints

`Swagger UI`

**http://localhost:8090/swagger-ui.html**


### Diagrama de Clases

![Diagrama de clases](https://i.imgur.com/eZLzCHq.png)

### Mindmap

![MindMap](https://i.imgur.com/EwJUo7V.png)
