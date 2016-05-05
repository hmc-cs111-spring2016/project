Unfortunately, it looks like a lot of your questions have to do with programming languages stuff that I probably can't help you with much. However, I will make some suggestions that you probably haven't thought of that are at least worth considering.

###### Statements, expressions, and function calls
While I don't really get the important of keeping statements and expressions separate, I have a few solutions for your function call problem. The first approach is to just assume all functions have side effects as that would probably get you in less trouble than assuming the opposite. However, another solution is analyzing function definitions to see if the function has side effects or not. You could then keep track of which functions defined thus far have side effects and then use one of two Call case class depending on whether there could be side effects (or you could just have one case class with a has_side_effect parameter or a clobbers list to keep track of whether it has side effects and what could / would be affected). Finally, perhaps you could implement some kind of lazy evaluation so that you don't touch / evaluate parts of the AST until you know you need their result (or want their side effects to take place).

###### Function collisions
In my optimion, I think the `ship.` syntax is overkill, so I'll focus on solutions to the function collision problem. The obvious solution is to just say that the function is already defined / provided. However, it would be interesting if you took some hints from OOP and allowed users to redefine the function and call the original within it. So, someone could override the `fire()` function to first check if they can fire so they don't get errors. However, this is likely too complicated for both you and the users. Alternatively, you could just let them clobber the provided functions just so they can realize how bad of an idea it is.

###### Error checking vs. interpreter features
I'm not really sure what interpreter features your referring to. Perhaps I missed them in your writeup. I do know that good error checking will be important, so that's definitely a safe choice.

###### AST components for ship functions
Just do whatever is easiest for you. I figure you'll want to write the ship functions in a language other than your DSL, so you wouldn't be parsing them. I guess you could just call them like any other function, but I feel like it would be easier and cleaner to catch predefined function calls and use custom AST components for them so that you can use pattern matching to identify them. Otherwise interpreting function calls could get complicated because you have to branch on whether the function is predefined or not.

###### Casting
This is something I'm strugging with right now as well. One solution is to require all variables to be of the same type and cast by assigning one variable type to another (e.g. `double d = 1.1; int i = d;`). However, this doesn't allow for casing in the middle of an expression, which would be handy. Unfortunately, having methods like `double2int` could be kind of verbose and end up making casting in the middle of an expression not worthwhile anyway.

###### Big types
While BigInteger and BigDecimal types would be nice, it might be good to expose floating-point madness to users. If you still want to limit the precisions exposed to users, you could store doubles as floats but convert them to doubles for computation. That way computations will be accurate, but precision will be limited. Also, Big types could potentially slow your program down, especailly if your user does something very bad in their code.

###### Double / float / decimal /etc.
More options to consider are "real" (for real number) or just "num" / "number" since that's what normal people call them. It's natural for people to work with numbers with decimal points in them, so why emphasize that this is anything other than a "regular" number. We don't see a sign that says "[City] in 20.3 miles" and think about the fact that the number has a decimal point in it. If anything, it's integers that are weird to normal poeple because they aren't what we're used to. For instance, it's intuitive for 3/2 to be represented as 1.5. If you said 3/2 is 1, people would look at you weird. So, I actually like something like "num" or "number" for doubles.  Also, while "Decimal" isn't too bad, it may discourage people from using it unless they know there will be important stuff after the decimal point (i.e. they may not realize it's just as good at representing really large number as it is really small ones).

###### Arrays
Like I suggested before, you can hide your arrays behind getters and setters if you need to. That way people can still use provided functions like before, but ask how many of something there is and to get a particular instance of one (e.g. how many ships are in radar range and to get the distance and direction to ship i).
Also, if arrays wasn't complex enough, having to learn about strings as arrays sounds like a mess, especially if you aren't going to be modifying them anyway.

###### Order of operations
Can you just parse higher-order operations first? That way function calls will end up near the bottom of the AST and you will only eveluate them if you actually get to them. For instance, if you have a short-circuiting and operator, you could first evaluate the left branch and only evaluate the right one if the evaluation of the left resulted in true.

###### Assert
I can't think of any good use cases for a traditional assert (that kills your program if the assertion fails), but it shouldn't be hard to implement.

###### Bonus extra critique stuff
So, I noticed that some of your function names / actions aren't as intuitive / helpful as I think they could be. Here are some potential changes you could make:
* Instead of `void fire()` and `bool can_fire()`, how about `bool try_fire()`? Users will often just want to fire if they can, and `try_fire()` lets them do that easily. They can even check if it succeeded or not. Although, you may want to retain `can_fire()` in case they don't want any firing to actually happen.
* Instead of vel / velocity, use speed. Velocity implies direction and magnitude, which is inaccurate as you only get and set the speed with your velocity methods. Rotation / rot is ok, but direction might also be clearer.
* `get_hull()` doesn't make a lot of sense. A novice might ask "Why do I want a hull? What dod I even do with that? Why is it an int?". Why not use "health" instead? After all, that's the same word you use to describe what the hull methods actually return.
* `radar()` is ok, but I would still like the ability to just see how many ships are nearby and what their distances / angles are relative to you. Otherwise everyone is going to write their own wrapper method for `radar()` and it's going to get annoying. Also, bounded queries would be nice as well (e.g. `radar(-15,15)` could be used to detect how many ships lie within the range of angles from -15 to 15, `radar_dist(-15,15,i)` could be used to get the distance to the `i`th object in the range, and `radar_angle(-15,15,i)` could get the relative angle of the `i`th ship to you).
* It would be really cool it `alert` could take an arbitrary number of strings, ints, and doubles, and turn them all into one string. For example, `alert("Velocity set to ", get_vel())` would printt "Velocity set to 15" in game (15 is obviously just some filler number here).
