I really like the project idea. I think that it could be very helpful for 
students to be able to express class preference in a fluid manner rather than
simply seeing if a specific schedule instance is conflicting.

One possible pitfall could be that you are planning to implement it in Prolog.
Users of the language would have to write their programs in Prolog which might
not be the easiest option for most people.

One thing you could ask the Mudd student who created the scheduling app is if
they would allow you to use their front end website to connect to your Prolog
backend. In this case it would be much easier for non-Prolog experienced people
to use. 

Here is one of the constraint satisfaction algorithms I was talking about. It's
called linear programming and is for maximizing a set of equations which could
work with your type of problem. You would be maximizing something different like
the score for a particular class based on a user's preferences.

https://en.wikipedia.org/wiki/Linear_programming

and here is a Prolog library for these types of problems

http://www.swi-prolog.org/pldoc/man?section=simplex


