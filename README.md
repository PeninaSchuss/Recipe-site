
## Author
* Name: Penina Schuss  


## Description

A Recipe site

### General information
The recipes are in the database in a one-to-many way, which means there is a table of recipes and there is a table of ingredients and they are connected in a one-to-many relationship.
As for the tables of the categories and subcategories of the bar.

### Functionality
A website of recipes. There are uses of user, admin and unregistered users.
1. Permit All - selecting a list of recipes from the bar and the option to search for recipes in the search button according to various parameters: ingredients, recipe name, preparation time, etc.
2. Admin - ability to add a recipe, and a button for deleting an existing recipe. It is possible to add a category to the bar and a subcategory to the bar, and delete a category from the bar.
3. User - can access the contact page from the bar.
   For each action with permissions, the button that performs it will only appear for those who are authorized to do it.

## Installation
Before running the program you need to create a database called ex5.
(in this project there is directory of sql call ex5.sql)

## Useful information
for user - (name:user, password:password)
for admin - (name:admin, password:password)
