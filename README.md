# Book Store Project


## 📚 Overview


The "Book Store" project is a comprehensive online platform for book lovers. It allows users to explore various books across various genres, read detailed descriptions, learn about authors, and check prices.


### Key Features


- **Extensive Book Catalog**: Browse a wide selection of books in multiple categories.
- **User Accounts**: Create personal accounts to track purchase history and manage preferences.
- **Secure Shopping Cart**: Easily add books to your cart and confidently complete orders.
- **Efficient Search**: Quickly find books using category-based search functionality.


## Technologies Used


- **Java**: The backbone of the project, providing robust and versatile programming capabilities.
- **Spring Boot**: Facilitates the creation of production-ready, standalone Java applications.
- **Spring Security**: Ensures secure authentication and authorisation mechanisms.
- **JWT (JSON Web Token)**: Enables secure information transfer between parties as a JSON object.
- **Spring Data JPA**: Simplifies database interactions by reducing boilerplate code.
- **MapStruct**: Automatically maps between Data Transfer Objects (DTOs) and entities.
- **Lombok**: Minimizes boilerplate code, improving readability and maintainability.
- **Maven**: Manages project dependencies and streamlines the build process.
- **Liquibase**: Controls database changes, ensuring consistency across environments.
- **Jackson**: Handles efficient JSON parsing and generation.
- **Swagger**: Generates interactive API documentation.
- **MySQL**: Manages and stores application data as a relational database system.
- **Docker**: Develops, ships, and runs applications within containers for consistent environments.


## Layered Architecture


- **Controller Layer**: Manages incoming HTTP requests and client communications.
- **Service Layer**: Contains business logic, processing data, and interacting with repositories.
- **Repository Layer**: Handles direct database interactions for querying and managing data.
- **Security Layer**: Manages authentication and authorisation processes.
- **DTO Layer**: Transfers data between application layers, including only necessary information.
- **Mapper Layer**: Converts between object models to ensure data consistency.
- **Test Layer**: Validates functionality through unit and integration tests.


## Database Entities


- **Book**: Represents a book with metadata such as title, author, and publication year.
- **Category**: Classifies books into genres or subjects.
- **Shopping Cart**: Temporarily holds selected products for potential purchase.
- **Cart Item**: Specifies a book and its quantity in the shopping cart.
- **User**: Registered individual with personal details and assigned permissions.
- **Role**: Defines user permissions, such as USER or ADMIN.
- **Order**: Records completed transactions, capturing purchase details.
- **Order Item**: Details specific books purchased in an order.


## Access to Endpoints


### User
- 🟩 **POST** `/auth/registration`: User registration.
- 🟩 **POST** `/auth/login`: User login.


### Book
- 🟨 **GET** `/books`: Retrieve book catalogue.
- 🟨 **GET** `/books/{id}`: View book details.
- 🟨 **GET** `/books/search`: Search books.
- 🟥 **POST** `/books`: Add a new book.
- 🟥 **PUT** `/books/{id}`: Update book details.
- 🟥 **DELETE** `/books/{id}`: Delete book.


### Category
- 🟨 **GET** `/categories`: Retrieve categories.
- 🟨 **GET** `/categories/{id}`: View category.
- 🟨 **GET** `/categories/{id}/books`: Get books by category.
- 🟥 **POST** `/categories`: Create a category.
- 🟥 **PUT** `/categories/{id}`: Update category.
- 🟥 **DELETE** `/categories/{id}`: Delete category.


### Shopping Cart
- 🟨 **GET** `/cart`: View cart.
- 🟨 **POST** `/cart`: Add to cart.
- 🟨 **PUT** `/cart/cart-items/{id}`: Update cart.
- 🟨 **DELETE** `/cart/cart-items/{id}`: Remove from cart.


### Order
- 🟨 **GET** `/orders`: View orders history.
- 🟨 **GET** `/orders/{id}/items`: View order items.
- 🟨 **GET** `/orders/{orderId}/items/{itemId}`: View specific order item.
- 🟨 **POST** `/orders`: Place order.
- 🟥 **PATCH** `/orders/{id}`: Update order status.


## SQL Database Diagram


![Database Diagram]


## How to Run the Book Store API


1. **Install Prerequisites**: Ensure Docker, Maven, and JDK Development Kit are installed.
2. **Clone Repository**: Clone the project repository.
3. **Configure Environment**: Update the `.env` file with necessary DB and Docker variables:
  ```plaintext
  MYSQLDB_USER=root 
   MYSQLDB_ROOT_PASSWORD=root 
   MYSQLDB_DATABASE=test_db 
   MYSQLDB_LOCAL_PORT=3307 
   MYSQLDB_DOCKER_PORT=3306 
   SPRING_LOCAL_PORT=8088 
   SPRING_DOCKER_PORT=8080 
   DEBUG_PORT=5005
   ```
4. **Build Project**: Run `mvn clean package`.
5. **Build Docker Container**: Use `docker-compose build`.
6. **Run Docker Container**: Use `docker-compose up`.
7. **Access Application**: Visit `http://localhost:8088/api` and test using Postman/Swagger.
**The client can authenticate in the program using either the user's basic auth or the jwt bearer token.**
- For admin features, use credentials:
   ```json
   {
     "email": "harrypotter.admin@gmail.com",
     "password": "12345678"
   }
   ```
- For user features, use credentials:
   ```json
   {
     "email": "harrypotter@wp.pl",
     "password": "12345678"
   }
   ```
8. **Stop and Remove Containers**: Use `docker-compose down`.


## Possible Improvement


- **Payment Integration**: Implement a payment system using the Stripe API to facilitate secure online payments and enhance user convenience.
- **Telegram Notification Service**: Implement a Telegram notification service for customers or create a shopping interface.


## Final Thoughts
This project marked my first significant Java development effort, and it presented numerous challenges. At first, combining multiple layers into a cohesive architecture took more work than I expected. Also, maintaining a consistent coding style and sticking to SOLID principles was quite challenging. Overcoming these difficulties improved my problem-solving skills and enhanced my ability to use documentation effectively. I am now better equipped to tackle future projects with confidence and efficiency.
