Store management API is designed to illustrate OOP principles

Java version: 21
Spring Boot Version: 3.2
Database: H2
Security: Spring Security with In Memory User


At start-up, the database structure will be automatically created and the initial set of data will be inserted through the script from src/main/resources/data.sql
Database configuration can be found in src/main/resources/application.properties
Content can be viewed at http://localhost:8080/h2-console

Managed entities: ClothingApparel, Accessory, Material, Supplier

Java OOP principles:
Encapsulation: use of access modifiers, entities have private fields that can be accessed through getters/setters
Inheritance: ClothingApparel and Accessory extend Product
Polymorphism: ProductMapper class :  ProductDTO mapToProductDto(Product product) and ProductDTO mapProductDTO(ModelMapper mapper, ClothingApparel clothingApparel)
Abstraction: repository interfaces

Available resources:

PRODUCT CONTROLLER 


1. Get products by type

Request type: GET
URL: http://localhost:8080/products
Required request parameters: ProductType type
Optional request parameters: Integer pageNumber, Integer pageSize
ResponseType: Page<ProductDTO>

example: http://localhost:8080/products?type=RING



2. Save product

Request type: POST
URL: http://localhost:8080/products
Request body type: ClothingApparelDTO



3. Update product
Request type: PUT
URL: http://localhost:8080/products/{id}
Request body type: ClothingApparelDTO


DiscountController

1. Apply discount by product type

URL: http://localhost:8080/discounts
