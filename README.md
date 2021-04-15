# Inventory

This document describe how it has been implemented the assignment:

# Code Assignment

## Intro
This assignment will be used as a discussion during a technical interview.
Time constraints are part of software development and even though we don't expect a perfect solution, imagine your code to be on its way to production.
If you have to make compromises, provide a README to briefly explain pros and cons of your approach, what considerations you would make for another iteration and in general what your 
future colleagues might be interested in if they had to pick up your code.

The primary values for the code we look for are: simplicity, readability, maintainability, testability. It should be easy to scan the code, and rather quickly understand what it’s doing. 
Pay attention to naming. 
 
You may choose any coding language, and we look forward to discussing your choice.

## The Task
The assignment is to implement a warehouse software. This software should hold articles, and the articles should contain an identification number, a name and available stock. 
It should be possible to load articles into the software from a file, see the attached inventory.json.
The warehouse software should also have products, products are made of different articles. Products should have a name, price and a list of articles of which they are made from with a quantity. 
The products should also be loaded from a file, see the attached products.json. 
 
The warehouse should have at least the following functionality;
* Get all products and quantity of each that is an available with the current inventory
* Remove(Sell) a product and update the inventory accordingly


## Tecnologies:
  MySQL 5.6, Spring Boot (Java 1.8), Angular 11 + Bootstrap 5
  
Backend repository: Inventory
Angular repository: inventory-web
  
Initial import script is on: inventory\src\main\resources\import.sql  

## Functionality

It requires initial load of the inventory json data file.
Then you should load products json datafile. 
Once is done you can start to manage your stock.

![Captura](https://user-images.githubusercontent.com/12112571/114868884-b2126e00-9df6-11eb-9aa9-a6152d3bbf1a.PNG)


## TODOS: 
  Capture error loading files
  Manage errors on the backend side

