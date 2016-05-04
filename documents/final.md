# Project description

## Introduction
Scala Collections provide a wide range of useful functions for operating on lists of objects. These functions include `map`, `filter`, `fold` an many more. SQL query languages however, despite being representable by a collection of tuples, lack most of these very useful functions. This can result in many over-complicated SQL queries. The goal of this project is to augment the experience of working with and manipulating SQL data by providing the above mentioned Collection functions for SQL databases. The domain for this language can be described as working with SQL databases in Scala. The domain is pretty large given the popularity of both SQL and Scala and so an improvement in this domain would provide large benefits.

## Language Design
This language is an internal DSL with Scala as its host language. With that in mind, a typical program in this language would be a valid Scala program that uses the scala-sql library. The most common way to use the library would be to create and manipulate a data structure called a `SQLSet`. A `SQLSet` is a Scala collection that represents the set of tuples in a single relational database table. The `SQLSet` is initialized with information about the database such as its endpoint, credentials, table name and table schema. 
Because this is an internal DSL, the error checking for this language is the Scala error checker. Additional Scala Exceptions and/or error messages have yet to be defined in this version. There already exists a few DSLs and libraries for interfacing with SQL databases in Scala. The one most similar to this project is [Slick](http://slick.typesafe.com). Slick provides many of the given Collection functions for SQL databases and will probably have a big influence on this project. [Here](http://manuel.bernhardt.io/2014/02/04/a-quick-tour-of-relational-database-access-with-scala/) is another article detailing other options
for working with SQL in Scala.

## Example Program
Example programs can be found in the [scala-sql-test](https://github.com/danield9tqh/scala-sql-test/) repository. Programs that use the scala-sql library should be separate from the library's source code because scala-sql uses Scala macros and macro functions must be called in a differnt project from which they are defined. The [scala-sql](https://github.com/danield9tqh/scala-sql) library must also be added to the sample project so that is can be imported and used. 

## Language Implementation
Scala-sql is an internal DSL written in Scala. I chose Scala for this project because of its flexibility in creating syntax for DSLs. The project has three main components: front-end, intermediate representation and back-end translation. The `SQLSet` data structure provides the front end and allows the user to interface with a SQL database through conventional Scala collection syntax such as `table.filter(_.id > 10)`. The functions performed on these `SQLSet` objects are then converted to the intermediate representation of a SQL query. This IR is based off of relational algebra and contains selections and projections over relations. Different selections and projections slowly build up an abstract syntax tree for the SQL query. Once the information in the `SQLSet` is ready to be consumed, this AST is translated to SQL syntax (in particular MySQL) and the query is executed on the database. The results of this query are then returned to the user as if they were the elements of the original Scala collection or `SQLSet`.

## Evaluation
My language is about as domain specific as the SQL language is. This is because it aims to provide exactly as much functionality as directly writing SQL queries. Commands in my language, although they may be easier to say, cannot go beyond the functionality of SQL. So far, the language works well with defining database schema and executing filter commands on that schema. 
There are many improvements that could be made to the language and it is still very far off from my larger vision for its features. These additional features include implementing more Scala collection functions such as `map`, `fold` etc. as well as allowing users to insert information into SQL tables instead of simply querying them. Although I did not reach my end goal for the language as a whole, I feel like I reached a good level for this project. 
The main issue I ran into with this project was creating a good user syntax for my `SQLSet` object. There was sometimes a disconnect between whether I wanted the set to be a collection of actual items like `Integer`s or `String`s or if I wanted it to be represented as a collection of SQL fields such as `age` or `name`. This led to problems with deciding the type of elements in the `SQLSet`. 








