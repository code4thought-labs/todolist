# Todolist

Todolist is a simple todo list project in order to practice integrations. 

## Description

### Requirements
- There are the concepts of **Todo List** and **List Item**.
- A **Todo List** can have many **List Items**.
- A user can:
  - Create a new **Todo List** with a `name`. A new list has no **List Items**
  - Remove a **Todo List** based on its `name`. All of all its **List Items** are also removed.
  - Edit a **Todo List** by:
    - *Add* a new **List Item** by adding its `desciption`,
    - *Remove* an existing **List Item**,
    - *Edit* an existing **List Item** updating its `desciption`,
    - *Move* an existing **List Item** from one list to another.
  - A **List Item** also keeps (and shows) its `last updated` date tot he user.

### Technologies 
Server:
- Java >= 8
- Maven
- Spring framework (SpringBoot, Spring MVC, feel free to use whatever library is needed)
- Junit for unit tests in Java [Test]
- Mockito for mocks tests in Java [Test]

Client:
- Angular NG, Typescript (feel free to use whatever library is needed)
- npm package manager
- Bootstrap for UI
- Protactor and Karma for Angular [Test]

Database:
- MySQL

Infrastructure:
- TBA (*Will involve docker*)

Continuous integration:
- TBA (*We may use GitHub's CI here*)

## Architecture

### Domain model
TODO ...
*Use simple UML (class) diagrams to describe the domain*

### Logical architecture
TODO ...
*Use C4Model to descibe the architecture*
