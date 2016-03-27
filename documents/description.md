# Project description

## Motivation

###### Why is this project useful or interesting, what problem are you trying to address, and why is a DSL an appropriate solution?
This project is useful since it will attempt to be a tool for teaching novices programming while also challenging experienced 
programmers in a fun game enviornment. I originally became interested in programming in 4th grade by learning how to script using
_SCM Draft 2_ and _StarForge_, two 3rd party map editors made for creating custom scenarios for the popular computer game _Starcraft_.
My goal is to create a similar more defined experience to help inspire new or novice programmers to learn programming while also 
challenging experienced programmers to tackle some interesting AI and navigation problems.

A DSL is an appropriate solution for this project since I want to embed the language inside of a game. Although I definetly want this
language to feel C-like, I want to include very detailed feedback/errorchecking to the user, especially useful for new programmers,
and an API-like interface to interact with a given spaceship. When you make a syntax error, I want a character in the game to complain
to you about it!



## Language domain

###### What is the domain that this language addresses, and why is the domain useful?
The domain of this language is that of a game. That being said, a game is not a classification in itself. I want this game to have
levels that are educational to programming novices while simultaniously being able to have levels that challenge even veteran
programmers. This means the game's DSL language needs to present its syntax in an interesting way that helps build up domain knowledge
to novices while also supporting a plethora of features neccessary for more interesting challenges. This domain is useful since it can
be both entertaining and educational!

###### Who will benefit from this language?
As mentioned above, although they will get different things out of it, this language should ideally benefit both novice and
experienced programmers. That is, a novice programmer should get an interactive educational and fun experience while a experienced 
programmer should get an interactive challenge with regards to AI and naviagation.

###### Are there any other DSLs for this domain? If so, what are they, and how might they influence your language design and implementation?
There is nothing I have discovered that is exactly like this idea. That being said, the idea for this project was inspired by
_SCM Draft 2_, _StarForge_, the _Warcraft III Map Editor_, and _Game Maker_. In the case of the first three inspirations, these
are all map editors to create custom scenarios in real time strategy (RTS) games. While coding is not part of the actual game,
here these map editors allow you to define a game within either _Starcraft_ or _Warcraft III_'s engine. Game Maker on the other hand
is a drag and drop and scripting DSL made for enabling easy development of 2D and simple 3D games. Although this also does not make
coding "part of the game", the simplified language design is an inspiration for the API-like interface that will be used within this 
game.

This idea is unique since the game itself is to code. It is not just making a game with code.



## Language design

###### If you had to capture your DSL's design in one sentence, what would it be?
Providing coding feedback and error checking within a game enviornment.

###### What constitutes a program in your language?
A program will look very much like a simple C-like program. There will be a "main" function that the game hooks into and uses to 
define the behavior of various spaceships within the game. The user may also define helper functions and global variables to aid in
defining behavior.

###### What happens when a program runs?
If the code works, it will define the behavior of a spaceship. Examples of this behavior may be to fly at various speeds, shoot at 
enemies, navigate obstacles, etc.

If a program has either a syntax or runtime error, however, the game should _not_ crash. Rather the game should provide feedback
to the user. For instance, if a user forgets a semicolon, a character in the game should tell the player "Hey! I think you forgot a
semicolon here!"

###### What kinds of input might a program take, and what kinds of output might it produce?
The input of a program would be calls to the spaceship's API. This will allow the code to access the spaceships environment and make
decisions based on its surroundings. Examples of these might be range finders, hull status, and receiving signals from a flagship.

The output of a program will be behavior within the game such as flying at various speeds, shooting at enemies, navigating obstacles,
etc.

###### Are there data or control structures that you know will be useful?
I am essentially building a full language and interpretor for my DSL. Thus I will be making extensive use of parsers. Additionally,
since the game will run in a server with clients uploading there code, there will be some socket programming.

I will probably want to either write my own parser or use something more sophisticated than the PackRat parser used in class since I
will want to be able to run extensive tests on my code to provide the most interesting error messages and feedback to the user.
Although this maybe possible with the PackRat parser, I believe there are easier ways to do it -- I will need to investigate this
however.

###### What kinds of things might go wrong in a program in this domain (e.g., syntax errors, compile-time errors, run-time errors)?
Since programs are essentially "compiling" and being interpreted by the game, all of these problems are possible and need to be 
detected. Of these errors, however, the most interesting is the runtime error. This is because the server will need to run at 
something like 30fps regardless of how much code the user provides. Thus, a runtime error that needs to be accounted for is how long
a snippet of code takes to run. If a user provides code that will take "too long to run," even if it would eventually terminate, the
game needs a way to detect this and report it as some type of runtime error.

###### How might you design your language to prevent such errors or to clearly communicate the results of errors to the user?
Although I could certainly design the language to not resemble C, that is in many ways the point of the game (I do hope to eventually 
add other languages like a psudo-LISP later). I want the game to help users feel comfortable with common programming language idioms 
(i.e. ending lines with semicolons, using parenthesis to call functions, returning values, etc). Thus, since the syntax may not
be as clear as it _could_ be, the idea is to provide in game voiced feedback about the user's code. Thus, an error in the users 
program will be reported to the user by a character saying something like, "I think you forgot a semicolon here!"



## Example computations

###### Describe some example computations in your DSL. These computations should describe what happens when a specific program in your DSL executes. Note that you shouldn't describe the syntax of the program. Rather you should describe some canonical examples that help someone else understand the kinds of things that your DSL will eventually be able to do.
Although the point of this game is to be extensible and sandbox-like, here are some scenarios I have already imagined:
   * Program a ship to have a space "dogfight" with other spaceships
   * Program a fleet to have a coordinated space battle with enemy spaceships
   * Program a ship to naviagate an astroid belt without colliding with rocks or debris
   * Program a ship to naviagate a supply route to provide fringe space colonies with supplies
   * Program a ship to locate and gather minerals deposits from asteroids
   * Program a flagship to construct additional ships from a selection of parts

