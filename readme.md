## Arquitectura
Este proyecto es una API para la gestión de precios de productos, desarrollada siguiendo los principios de la arquitectura hexagonal. La arquitectura está inspirada en el proyecto [java-ddd-example](https://github.com/CodelyTV/java-ddd-example) de CodelyTV, pero ha sido simplificada para centrarse en la funcionalidad esencial.

### Capas principales
- **Dominio**: Núcleo de la aplicación, contiene:
    - **Entidades**: (marcas) y (precios) definidas como objetos inmutables `Brand``Price`
    - **Excepciones**: para gestionar errores específicos del dominio `PriceNotFoundException`
    - **Repositorios**: Interfaces como que definen contratos para acceder a datos sin acoplarse a tecnologías específicas `PriceRepository`

- **Aplicación**: Orquesta los casos de uso mediante:
    - Implementación de CQRS (Command Query Responsibility Segregation)
    - Queries: para solicitar información `PriceQuery`
    - Handlers: para procesar las consultas `PriceQueryHandler`
    - Servicios: para implementar la lógica de negocio `PriceSearch`

- **Infraestructura**: Provee implementaciones concretas para las interfaces del dominio:
    - **Controladores**: expone endpoints REST `PriceController`
    - **Persistencia**: Adaptadores para H2 mediante `PriceDatabaseRepository`
    - **Mappers**: Para convertir entre entidades de dominio y entidades JPA

- **Shared**: Componentes transversales compartidos:
    - Bus de comandos y consultas
    - Utilidades comunes
    - Gestión de errores

## Implementación de CQRS
El proyecto implementa el patrón CQRS (Command Query Responsibility Segregation) para separar las operaciones de lectura y escritura,
Utiliza un bus de queries para enrutarlas a sus handlers correspondientes.
Esta separación permite:
- Mayor claridad en las responsabilidades
- Escalabilidad independiente para operaciones de lectura y escritura
- Testing más sencillo y enfocado

## Persistencia desacoplada
La aplicación implementa el principio de inversión de dependencias para desacoplar el dominio de las tecnologías de persistencia:
- **Interfaces en el dominio**: define lo que necesita el dominio sin depender de ninguna tecnología `PriceRepository`
- **Implementación específica para H2**: adapta la interfaz del dominio a la tecnología concreta (H2) `PriceDatabaseRepository`
- **JPA**: Se utiliza y exclusivamente en la capa de infraestructura `PriceH2Repository``PriceJpaEntity`

## Cobertura de pruebas
El proyecto cuenta con:
- 100% de cobertura unitaria en las capas de aplicación, dominio y infraestructura
- Pruebas de integración para el controlador REST con rest assured.


## Tecnologías Utilizadas
- Java 22
- Spring Boot 3.4.5
- Maven para gestión de dependencias
- H2 como base de datos en memoria para desarrollo

## Cómo ejecutar
1. Clona este repositorio
2. Ejecuta `mvn clean install` para construir el proyecto
3. Ejecuta `mvn spring-boot:run` para iniciar la aplicación
4. La API estará disponible en `http://localhost:8080`
5. Se puede llamar al endpoint siguiendo esta llamada de ejemplo: `http://localhost:8080/api/prices?brandId=1&productId=35455&date=2025-03-03T11:00`
