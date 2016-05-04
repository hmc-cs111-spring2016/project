# Project plan

## Language evaluation
Because I am basing my syntax off of the syntax for Scala Collections, I will 
evaluate my implementation by how closely it resembles an actual Scala 
Collection. I think the main factor will be how many Collection functions I can
implement in my language. I plan to use one of the Scala testing frameworks we
discussed in class to make sure that my function implementations actually return
the correct results. Because my project fits very well with unit testing (each
function has a test), I will probably practice test-driven development. 

## Implementation plan
Because I am using Scala as the host language the initial setup for this project
is greatly reduced. My first goal will be to become acquainted with both the 
Scala Collections interface and methods of accessing SQL databases in Scala. 
Once I am acquainted with the back-end, I want to implement an initial function 
as a proof of concept. I think the easiest function to implement would be 
_filter_. Each of these tasks would take about a week. The next two weeks I
plan on successively implementing more Scala Collection functions that translate
to SQL SELECT statements. Because reading from a database would be easier to 
implement than writing to a database, I will focus my project more on SELECT
queries than UPDATE queries. The final week however, I will try to implement
some Collections functions that would translate to a SQL UPDATE query. 

Week 1 - research Collections and SQL in Scala
Week 2 - implement _filter_ operation
Week 3 - implement some SQL SELECT functions
Week 4 - implement some SQL SELECT functions
Week 5 - implement some SQL UPDATE functions

## Contingency plan
A large obstacle that I expect is lack of a clear mapping between a Collection
function and a SQL statement. To overcome some of these issues I will most
likely look to Slick to see how they have implemented the function. Another 
obstacle I expect to encounter is concurrency issues for implementing SQL UPDATE
functions. I will probably also look towards Slick for this issue. 


