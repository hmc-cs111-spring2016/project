# Project description

## Motivation

Every fall and every spring, students across the country struggling with the same feelings of panic, uncertainty and indecision as they are asked to choose the courses that will define their college experience in the subsequent semester. Which core requirements do they need to get out of the way? Which electives should they take now? Which professors should they try to get? During what times of the day and week do they want to be in class? All these questions and more, must be carefully weighed as they try to make the best decision. 

Students at the Claremont Colleges struggle with these same questions. We have some tools to help us visualize the possibilities for the subsequent semester, such as the [Scheduler App](http://scheduler.5capps.com/#) and the [ASPC-Scheduler App](https://aspc.pomona.edu/courses/schedule/), but neither seems to give the users the full flexibility they may want and need to specify exactly what they are looking for. A DSL such as CourseScheduler, which I am designing will allow students to express their preferences for courses with the fluidity and expressiveness of a full language. Rather than looking through all the possible schedules with different combinations of the classes you are interested in, students should be able to input a list of all the courses they might be interested in, and then write a program describing the kind of schedule they want, run it, and get a list of the possibly schedules that most closely align with their preferences.

## Language domain

The domain of the language is classes and class schedules, which is a useful domain in so far as it is applicable to all students trying to plan their schedule based on a set of classes they have varying degrees of interest in. My language will be designed specifically based on classes at the 5Cs and may contain some things specific to classes offered here, such as an interface with the 5C portal, but it should be possible to generalize it to meet the needs of students at other schools as well. The [Scheduler App](http://scheduler.5capps.com/#) and the [ASPC-Scheduler App](https://aspc.pomona.edu/courses/schedule/), are softwares that have already been developed in this domain to assist students, and I intend to look at the way the Scheduler App gets information about classes from portal and borrow from that, with the permission of the student who developed it. However, neither app progrives the full flexibility and expressiveness of a language that would allow a student to fully express their preferences and I want to develope a language that allows that. 

## Language design

In one sentence, my DSL design would be: Constraint satisfaction generating course schedules. A program in my language will be a set of classes input to the system which may need to be stored in some type of data structure and then a list of constraints the user wants to impose on the output list of schedules along with a specification of the number of possible schedules the user would like to have generated. The output should be a list of several possible schedules that align as closely as possible with the users stated preferences. In the future, the output might be transformed into a graphical output like the Scheduler App, so that the user can better visualize a particular schedule.

Some errors that might occur would be if a student tried to give their preference for a class that wasn't one of the input classes, if a student put conflicting preference, or if no possible schedules could be generated for some reason. I would like to write clear error messages to handle these cases, with sugggestions for things they might have forgotten.

## Example computations

A student using my DSL should be able to write a program where they input a list of classes such as these:

CSCI081 HM-01 (Keller): MW 1:15 - 2:30 PM; HM Campus, Shanahan Center 2460
CSCI081 HM-02 (Keller): MW 2:45 - 4:00 PM; HM Campus, Shanahan Center 2460
LGCS010 PO-04 (Landman): MW 2:45 - 4:00 PM; PO Campus, Edmunds 101
LGCS010 PO-01 (Landman): MW 1:15 - 2:30 PM; PO Campus, Edmunds 101
LGCS010 PO-02 (Samuels): TR 9:35 - 10:50 AM; PO Campus, Millikan Laboratory 2393
LGCS010 PO-03 (Samuels): TR 2:45 - 4:00 PM; PO Campus, Edmunds 101
LIT 179E HM-01 (Plascencia): W 2:45 - 5:30 PM; HM Campus, Shanahan Center 2425
POLI119 SC-01 (Sharma): MW 09:30PM-10:50 AM; SC Campus, Humanities Building 105

They should then be able to write facts with the names of professors, classes, dates and times and give them positive or negative weights corresponding to how badly they want something in their schedules. When the program runs it will search its database of classes and facts to find the set of classes with the highest positive weight corresponding to a students highest preference and output it. If the student asked for multiple possible schedules it will give the next highest weighted schedule and so on until their are no more possible schedules or it has printed out the desired number of schedules. 

The language will be an internal DSL in Prolog so when the program runs it will do a unification algorithm which should ensure that no classes in a generated schedule overlap, are the same course just at different times, and might also check that a maximum hours per day is satisfied.


