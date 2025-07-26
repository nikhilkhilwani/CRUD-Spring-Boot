|**Object**|**Context**|**Important Information**|
|---|---|---|
|`Entity (Product)`|Represents data model for CRUD operations|Fields: `id`, `name`, `price`; Annotated with `@Entity` and `@Table`|
|`Repository`|Data access layer|Extends `JpaRepository<Product, Long>` for DB operations|
|`Service`|Business logic layer|Handles CRUD logic between controller and repository|
|`Controller`|API endpoint for clients|Mapped using `@RestController` and `@RequestMapping`|
|`Application`|Entry point of Spring Boot app|Annotated with `@SpringBootApplication`|
|`application.properties`|Configuration context|DB configs, port setup, Hibernate dialect|
|`ExceptionHandler`|Handles application exceptions|Annotated with `@ControllerAdvice` and `@ExceptionHandler`|