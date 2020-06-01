# Spring Boot Demo

The reason for this project is to show my abilities with spring boot, implementing some of the most basic spring functionalities and other technologies. This project was asked of me in my current job as a presentation or examination of abilities and the request was as follows:

* CRUD for the customer entity
* Rest service for the custtrans entity that must send the data to a JMS queue for further processing
* Make a JMS queue listener from the previous point that performs the following validations:
    1. validate the existence of the customer id
    2. the date must be less than or equal to the date
    3. the amount can only be positive or negative
* If the validation result is satisfactory, update the customer balance and persist custtrans in the database
* If the result of the validation is unsatisfactory, send an email to admin@acme.com
* implement spring security (HTTP Basic Authentication, in-memory users and role management)
* realize unit test

## Points to consider

* Due to development nature (one exercise), i ended up implementing all the functionalities in a single development, but in another circumstance i would have opted for an architecture more similar to microservices, where at least the sending of emails and both the sender as the receiver of the JMS queue
* At the beginning of the development I had thought to implement sending the entities to JMS queue as XML, but due to the little documentation I found I opted to change it to JSON, in addition to the fact that the request did not specify this
* For this development I thought about starting to use a tool that I had read before, [gitmoji](https://gitmoji.carloscuesta.me), which consists of adding an emoji to the beginning of each commit to typify the change that is being made, and I have seen that it is implemented in two ways adding the emoji code (:code:) or directly adding the emoji character, the first one caught my attention but reviewing it to bitbucket I see that it does not recognize all emojis, so I would think better to opt for the second option
* There is a commit called "change entities for best struture", this is because the balance of the customer entity needs to be updated and the logic of this was added to that entity, this decision is due to having it within the entity, if it changes the way it saves the balance, say the BigDecimal balance, the logic of the update does not need to change in other classes, encapsulating the functions of the Customer class
* For this development, it was taken into account that it would work for 2 days, but due to other issues, it started late and the time ended if everything had been implemented, so take into account that the time is up for the commit d08df04

## How to run it?

```bash
# run activemq
docker run --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq

# run mysql
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

# run the proyect
./gradlew -q bootRun

# add users to data base
mysql -u root -p
# INSERT INTO users (id,user_name,password,role) VALUES (1,'admin','$2a$10$gEWR9U9c3rodEtPf5B.q1umna9T8RRkZJjGFw6K1P/s2lVRMw5yNa','ADMIN'); -- pass: contraseña
# INSERT INTO users (id,user_name,password,role) VALUES (2,'user','$2a$10$gEWR9U9c3rodEtPf5B.q1umna9T8RRkZJjGFw6K1P/s2lVRMw5yNa','USER'); -- pass: contraseña
```

