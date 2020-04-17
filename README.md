# Todolist

Todolist is a simple todo list project in order to practice integrations. 

## Description

### Requirements
- There are the concepts of **Todo List** and **List Item**.
- A **Todo List** can have many **List Items**.
- A **List Item** has a status: `completed` that represents if that item has been completed or not.
- A user can:
  - Create a new **Todo List** with a `name`. A new list has no **List Items**
  - Remove a **Todo List** based on its `name`. All of all its **List Items** are also removed.
  - Edit a **Todo List** by:
    - *Add* a new **List Item** by adding its `description`,
    - *Remove* an existing **List Item**,
    - *Edit* an existing **List Item** updating its `description`,
    - *Move* an existing **List Item** from one list to another.
  - A **List Item** also keeps (and shows) its `last_updated` date to the user.

Rules:
 - A **Todo List** can have from 0 to N **List Items**
 - A **List Item** can only exist if it is in a **Todo List**
 - Every **Todo List** is unique based on its `name`
 - Every **List Item** is unique based on its `name` in the context of a **Todo List** (no duplicated **List Items** in the same list)
 
 UI: 
 - As soon as the user visits the web app, he/she sees the created todo list, and a button to create a new one.
 - When a todolist is opened, all the non-completed items are shows. The user can also select to be shown the completed `items` also (filter).

### Technologies 
Server:
- Java >= 8
- Maven
- Spring framework (SpringBoot, Spring MVC, feel free to use whatever library is needed)
- Junit for unit tests in Java [Test]
- Mockito for mocks tests in Java [Test]

Client:
- Angular NG, Typescript (feel free to use whatever library is needed)
- NPM package manager
- Material for UI
- Protactor and Karma for Angular [Test]

Database:
- MySQL

Testing:
- A testing coverage tool should be used to report coverage
- Unit tests should cover at least 50% of the code
- Integration tests should be in place for the core functionality
- End2End tests should be in place for the core functionality

Infrastructure:
- All components should run in a separate docker container (Client, Server, Database)
- A docker-compose file discribes the infrastructure and manages those containers.

Continuous integration:
- TBA (*We may use GitHub's CI here*)

## Architecture

### Domain model
TODO ...
*Use simple UML (class) diagrams to describe the domain*

### Logical architecture
TODO ...
*Use C4Model to descibe the architecture*
