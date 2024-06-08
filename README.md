# Guitar Website

The Guitar Webstore is an online platform where people can visit to buy a guitar or get their guitar repaired. If they want to buy a guitar, they can visit the website and browse through the products. This means they can click on a specific product to read more about it, and if they decide to make a purchase, they need to log in. On the site, they can also select the type of guitar they are interested in viewing. If they want to get their guitar repaired, there will be an option to input their name, guitar details (such as type), and phone number for contact purposes.

Additionally, there will be an admin who manages and moderates the site. The admin can add, delete, or update products (guitars). They also need to log in, and as an admin, they have the option to edit user data. As an admin, they can also delete users who have not logged in for a long time or whose data is inaccurate.

Furthermore, I plan to add a search bar to the site to help users find guitars more quickly, but this will be a future development.

For this project, I used Java 17 with IntelliJ IDEA as my development environment. I created a Spring Boot project and added several dependencies such as JPA, MySQL, Lombok (for generating getters and setters), and Spring Web. In addition, I established the MySQL database connection where I created the necessary database and tables. I created four tables: Order_info, User, Guitar, and GuitarService.

The User table stores essential information about users, such as their name, phone number (for contact purposes), address (for shipping orders), and password (for login authentication).
The Guitar table contains data about guitars, including their brand, model, available stock quantity, and pricing.
The GuitarService table helps to store information related to guitar servicing needs, such as string replacement, fixing broken parts, or customization requests.
Finally, the Order table stores all order details, including the User ID to track orders, quantities, item details, and total amounts for financial reporting at month-end.

![DATABASE](https://github.com/gergoszekely20/Proiect_Proiectare_Software/assets/126805310/9213508f-aabc-4183-85e1-373089da9084)

The project is structured into packages: the Model package contains classes like User, Guitar, GuitarService, and Orders, each with their respective attributes. The Repository package contains interfaces for JPA connections, simplifying database operations. In the GuitarService package,I implemented GET, POST, PUT, and DELETE commands which are used in the Controller package, where I have a separate controller for each table. These controllers handle operations such as deletion, updating, or insertion for their respective tables.

I have introduced a new controller responsible for handling user authentication requests. This controller comprises two endpoints: 'login' and 'registration'. For the 'login' endpoint, users are required to provide only their email address and password, whereas for 'registration', the same data set as required for creating a new user is necessary (any user can register, but new users can only be manually created by an admin).

Furthermore, I implemented the observer pattern to facilitate two distinct listeners. One listener is tasked with sending a welcome email to users upon registration, while the other keeps track of new orders. To support this functionality, events were created and subsequently invoked within the relevant service classes and for better code organization and readability, I have created two additional packages, 'event' and 'listener'. These packages contain classes that will be utilized in the future development process.

As for additional ideas, you might consider implementing user roles and permissions, such as admin, regular user, etc., to enhance security and access control within the application. Also, incorporating password hashing and encryption techniques for improved security of user data would be beneficial. Additionally, you could explore implementing user profile management functionalities, allowing users to update their information or reset their passwords.

#### Endpoints

##### Authentication
- /auth/login - This endpoint will be used when a user wants to log in. Here, it will verify the provided email address and password, and based on that, it will bring up either the admin or user interface.
- /auth/register - This endpoint will invoke the saveUser endpoint to help register the user when they want to create an account.
##### User
- /addUser - This endpoint assists us when a registration process is completed, thus when a person registers, their data will be saved in the database.
- /addUsers - The admin can add employees or customers to the database using this endpoint.
- /showUsers - The admin can view the list of users.
- /userById/{id} - The admin can search for individuals based on specific information.
- /userByName/{name} - If, for example, the customer's name is remembered, this endpoint helps easily locate the person who needs to be contacted.
- /userUpdate - Users will be able to update their information when necessary, such as address or phone number changes.
- /userDelete/{id} - If the admin notices that a customer has been inactive for a long time or detects a fake account, they can delete it.
- /userByEmail/{email} - If the admin or a service provider needs to contact a user, they can easily find them based on their email address.
##### Guitar
- /addGuitars - With this endpoint, the admin can add guitars to the database.
- /showGuitars - This endpoint helps gather the required guitars to display on our website.
- /guitarById/{id} - This endpoint allows viewing information about a specific guitar, helping the admin find the right one.
- /guitarByType/{type} - As our website offers various types of guitars (CLASSICAL, ELECTRICAL, BASS), this endpoint assists customers in finding the desired type.
- /guitarUpdate - The admin can update information about guitars if necessary.
- /guitarDelete/{id} - The admin can remove guitars that are out of stock or no longer needed.
##### Guitar Service
- /addGuitarService - With this endpoint, users can book and record guitar services.
- /addGuitarServices - If the admin or an authorized employee wants to add multiple services at once, they can save them simultaneously.
- /showGuitarServices - Service providers and admins can view the history of services performed, associated guitars, and costs.
- /guitarServiceById/{id} - The admin can search for specific service records by ID, useful for investigating customer dissatisfaction.
- /guitarServiceByType/{type} - This endpoint helps service providers easily select guitars of a certain type to work with.
- /guitarServiceUpdate - The admin or service provider can add comments about guitar repairs or make changes to service records.
- /guitarServiceDelete/{id} - Once guitar servicing is complete and information becomes irrelevant, the service provider can delete the record.
##### Order
- /addOrder - Customers can place orders on the website, and this endpoint assists in handling them.
- /addOrders - When customers add multiple items to their cart, we can easily save their order with the same order ID.
- /showOrders - The admin can view orders, prepare them for shipping, or analyze sales data.
- /orderById/{id} - The admin can view specific orders for analysis or review.
- /orderUpdate - With this endpoint, the admin can update order details.
- /orderDelete/{id} - If a customer cancels an order, the admin can delete it from the database.

#### Class Diagrams
![DiagramClass1](https://github.com/gergoszekely20/Proiect_Proiectare_Software/assets/126805310/2dfcf2c0-7f88-479a-8018-ab6fbdb480ab)
![classDiagramm2](https://github.com/gergoszekely20/Proiect_Proiectare_Software/assets/126805310/82a91531-c929-433e-b829-20bb331a4d74)


I embarked on the next stage of a project to develop and design the frontend. It was challenging to conceive how it should look and what it should consist of. Ultimately, I chose Angular as the programming language. I found it interesting and decided to give it a try. First, I had to start the project, and then I gradually familiarized myself with the language. It was easy to use because I could easily generate components and services from the Visual Studio command prompt.

So, how does an online store build up? First, there's a homepage where products, in our case guitars, are displayed. Visitors can view the products but can't purchase them. To make a purchase, they need to log in. After logging in, they can buy products or even register a reservation through the product service. Additionally, they can view their past orders.

#### The Activity Diagram
![diagrama activitate](https://github.com/gergoszekely20/Proiect_Proiectare_Software/assets/126805310/efc777bb-8551-4711-bff3-501709e8e9ef)


If someone logs in as an admin, they have greater authority. They can add, update, or delete products, and also monitor the status of orders, deciding whether they're shipped or not. Moreover, they can view services and mark them as completed or not.

In the frontend, I invoked my APIs in both the admin and customer services to send messages to the backend, indicating what actions to take. I created a separate invocation for each API, and then called them in their respective places. Then, using HTML and CSS, I designed and added the necessary functionalities to the pages.

In the backend, I had to make a couple of changes because I wasn't satisfied with the order table; it wasn't detailed enough. So, I added a new table for cart items, allowing customers to add multiple items to their carts. I also created DTOs for the entities to easily establish a connection between the frontend and backend.

Working on the project allowed me to deepen my knowledge of Java and also become acquainted with JPA. Additionally, I wrote successful tests to test my code. Furthermore, I learned about Angular and its peculiarities. I learned that starting from the database, a contract is established through JPA, and then the connection is made through the service to the controller, where the APIs are located. I receive and send information using DTOs. In the frontend, I receive the request and pass it to the service, which then calls the HTTP.

Future developments for the website include allowing customers to leave reviews, rate products, introduce discounts, and implement a search feature.
