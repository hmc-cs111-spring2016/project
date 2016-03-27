# Project description

## Motivation
Scala Collections provide a wide range of useful functions for operating on 
lists of objects. These functions include _map_, _filter_, _fold_ an many more. 
SQL query languages however, despite being representable by a collection of 
tuples, lack most of these very useful functions. This can result in many over-
complicated SQL queries. The goal of this project is to augment the experience 
of working with and manipulating SQL data by providing the above mentioned 
Collection functions for SQL databases.

## Language domain
The domain for this language can be described as working with SQL databases in 
Scala. The domain is pretty large given the popularity of both SQL and Scala and
there are already exists a few DSLs and libraries for interfacing with SQL 
databases in Scala. The one most similar to this project is [Slick](http://slick.typesafe.com). Slick provides
many of the given Collection functions for SQL databases and will probably have
a big influence on this project. [Here](http://manuel.bernhardt.io/2014/02/04/a-quick-tour-of-relational-database-access-with-scala/) is another article detailing other options
for working with SQL in Scala.

## Language design
In one sentence, the design of this language is a sub-class of a Scala 
Collection that interacts with a SQL database. A program in this language is a
Scala program that uses this sub-class. When a program runs, the Scala program
will run and will most likely access a SQL database somewhere and send queries. 
One thing especially to watch out for in this language is run-time errors. 
Because a database is being accessed and changed, ideally there would be error 
and type checking for the database on the Scala end. Although the current way
of doing things, simply throwing exceptions at runtime, also has this problem. 
For this reason, I will probably stick with just throwing exceptions for a bad
database action. 

## Example computations
A good example for this language is translating an Scala Collections _filter_
operation into a SQL _SELECT_ operation. For example if you wanted to filter 
tuples where the _id > 10_. That would be equivalent to _SELECT * WHERE id > 10_.
 



