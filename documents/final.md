#DOTA-Builder

##Introduction

DOTA 2 is a very hard, complex game. In order to even be competent, one must memorize all 111 heroes (characters) in the game, each of which has at least 4 unique abilities (skills), as well as all of the 100+ items that all do unique things. Then, in order to be good at the game, you must know which of these hero/skill/item (which is commonly referred to as a "build") combinations are effective, and against what other hero/skill/item combinations. Some of this can be figured out through logic and intuition from playing the game for a long time, but for the most part, there is no concrete way to determine how much damage one build does against another; the mental math required is too complex. Even professional players experiment with new builds, and might not be using the optimal ones in every situation.

The purpose of this language is to create a platform for people to experiment with builds in a safe, low-risk environment, to both speed up learning, and help make difficult decisions in a close game. Thus, the essence of the langugae really is a text-based testing environment where users can specify one or more builds, and then ask for all of the relevant stats that they may want to know in order to decide which build is best for that situation. 

The purpose of the language is *not* to simulate all of DOTA 2 and make decisions for the player. The search space of the game is to massive to do this without advanced machine learning. The purpose is to provide the player with the ability to make these decisions in a more informed manner, by providing the stats that are possible to provide analytically. Specifically, this language focuses on carry heroes, as they are the in-game player role that primarily focuses on getting as many items as possible. 

##Language Design Details

In general, the philosophy behind this language is two-fold. First, it must be accessible to non-programmers with minimal learning curve. The language should be targeted towards all DOTA players, not all DOTA players who've taken introductory CS. Second, it must be fast. Having a DOTA simulation that takes 5 minutes to run would be useless to players who want to just type something in and then use the information to make a decision in the game they are currently playing. 

The combination of these things influenced the various elements of language design documented below. Doing this in the form of a simplistic language is a nice intersection between these two goals - the language itself is easy to use, but since it is text-based, it allows for quick copy-pasting and reusing old programs to speed up the user's process.

### Programs:
In order to write programs, the user must write a program in an empty file in the folder of the program. These programs are written in the same style as regular coding languages, but with (obviously) much more limited syntax. The reason for programming this way is to emphasize speed. If someone has a list of standard builds, they can easily copy-paste between files, re-using code with slight modifications to get a working program as quickly as possible.

Once the user has a program, they can run it by running the python file ```dota.py```, which will prompt them to enter the name of their file, and will execute their program.

### Syntax:
The syntax for this language was designed to be as minimal as possible, and as natural-language-esque as possible. This is in line with the primary language goal of accessibilty to non-programmers. With that said, since the goal of the language is to be able to specify multiple builds and test properties of each of them, I adopted some syntax from general-purpose languages, spefically syntax for loops, assignments, and lists. However, since each of these things has exactly one purpose, and there is really no type system other than builds and items, its much easier to grasp than a general-purpose programming language. 

To be more specific, the language lets users define variables to be either entire builds, lists of builds, or sets of items. There's no type enforcement, the language figures out what the type of everything is at runtime. The user does this using assignment syntax, as well as special specification syntax based on the thing that they are specifying. For examples, see the next section.

Once the user has their data specified, they can ask questions about it using a few control structures. First, they can get simple stat information with queries using the ```get``` keyword. There is a finite list of available stat queries for a given build. Next, they can loop across a list builds using the ```foreach``` keyword. In this loop they can then do whatever they want and could otherwise do outside of a loop. Finally, they can ask the program to reccommend an item for a build using the ```optimize``` keyword. Here the user then enters their optimization parameters. Since the program isn't trying to simulate all of DOTA, it won't just give you the perfect item without specification. Instead, the user enters any available stats, followed by an integer weight. The program will then suggest the item that maximizes those stats with those weights.

With each of these syntax elements, the program has pretty simple output. It prints the output to the various stat queries in the standard console output, and when the output can't be expressed simply, it displays a graph instead.

###Error Handling:

One element of the language that's fairly important, but sadly limited in the language's current state is error handling. Currently, the program catches type errors and errors in stat queries, but not syntax errors. Errors in type and stat query result in the program outputting a helpful error message in the form of a regular python exception, so if you try to get the damage of a list instead of a build, it will tell you that the thing you tried to get the damage of isn't a build. This isn't ideal, but time limitations prevented me from adding more features. Some error-handling features that should be included but aren't are smart naming (interpreting user's input for hero names, item names, and stat queries and taking the one closest to their query if they mis-type it), as well as generally better ways to specify parsing errors. Part of the issue here is that the parsing tool I used has terrible error messages, and so this is harder to do. Its still possible though, and may appear in a future version of this project if I continue to work on it.

Additionally, something that should show up in a much, much later version of this project is a Steam app that functions as a friend on your Steam friend's list. With this, you could open up the Steam overlay in a game, and message this "friend" account with your program, and it would reply with the program's output. 

### Other Domain DSLs:
There are a few other DSLs that address this need. However, all of them are in the form of a GUI application that lets you plug in all of the relevant information for a hero build, and then displays all of the stats that it can. The issue with these is simply that you can only test one hero at a time, and much more slowly. Addmittedly, these are more comprehensive in the information that they do show you, but they don't give the player enough information about how that build interacts with other builds to make decisions. Further, entering all of this information by hand in a GUI is very slow, probably too slow to do in the middle of a game.

##Example Programs
Here, you can see a simple program that specifies two builds, and then gets various stats about them individually, and against each other.

```
attacker = level: 15 Dragon Knight with: (Daedalus, Butterfly, Monkey King Bar)
defender = level: 10 Lion with: (Tranquil Boots)
get: damage of: attacker
get: hp of: defender
get: attacks of: attacker vs: defender
```

Here, you can see a program that tests three different builds using a loop. 

```
attacker1 = level: 15 Dragon Knight with: (Power Treads, Shadow Blade, Yasha)
attacker2 = level: 15 Dragon Knight with: (Phase Boots, Desolator, Crystalys)
attacker3 = level: 15 Dragon Knight with: (Power Treads, Sange, Daedalus)
defender = level: 10 Lion with: (Tranquil Boots)
builds = [attacker1, attacker2, attacker3]
for: build in: builds{
	get: attacks of: build vs: defender
}
```

Finally, you can see a more complicated program that specifies a build (presumably the player's current build), and a defending hero, and then asks the program to optimize the first build with various different parameters in mind as the player's priorities.

```
myBuild = level: 20 Phantom Assassin with: (Power Treads, Battle Fury, Desolator, Black King Bar)
defender = level: 10 Lion with: (Tranquil Boots)
optimize: myBuild for:{
	attack speed: 9
	armor: 5
	agi: 2
}

optimize: myBuild for:{
	armor: 3
	damage vs: defender: 2
	attack speed: 5
	agi: 2
	hp: 4
}

optimize: myBuild for:{
	damage: 10
	damage vs: defender: 10
}
```
This last program outputs:
```
Butterfly
Eye of Skadi
Divine Rapier
```
Which are three distinct items that fit the optimization parameters.

##Language Implementation

This language was implemented as an external DSL using Python as the host language. I was originally planning on writing it as an external DSL in Scala, but two things changed my mind. A friend who took this class last year (Matt valentine) mentioned that he did his project in python, and found a working parser-combinator in python. Also, after some discussion with critique partners, I realized one of the things I needed to output with the program is graphs that illustrate probability distributions. Thus, since Python was now usable and provides easy graph support with Matplotlib, it seemed like the obvious choice.Making this an external DSL was a no-brainer. The syntax needed to be as close to natural language and as far from regular programming syntax as possible.

In terms of language architecture, this language follows the standard format very closely. The program input files are parsed into an abstract syntax tree. To represent the tree I used python classes and python inheritance, which admittedly isn't great for this task. However, that's more a function of Python's type syntax than anything else. This abstract syntax is then evaluated using an evaluator class that esentially recursively calls more evaluator methods on the subtrees of the input tree. This evaluation converts the program variables into instances of lists, or custom backend classes for builds and items. These form the internal representation for the language elements. On the very backend, all data is stored in JSON files, and parsed when the program runs.

The two main external packages used are Matplotlib and Parcon. Matplotlib is pretty straightforward, it produces all of the graphs the the program outputs. Parcon is the parsing tool I used. To be honest, its a terrible parser and I wouldn't reccomend it for complicated parsing tasks. However, for simple grammars its very easy to use. I ran into several issues with it, as it's an eager parser, and so doesn't backtrack when parses that succeeded fail later down in the parse. However, with some clever ordering of statements in the grammar it ended up working. 

When a program runs, it stores all of its stat data in a dictionary of variable names to the objects that they refer to. The objects are either the internal representation classes mentioned previously, or lists of those. In this way, the program acts like a normal programming language, but with a limited type system, and can execute any new command using its state information.

##Evaluation

### Language Overview:
Overall, this language is an interesting mix between standard languages and completely unique DSLs. Essentially, it uses the same program model as a regular language, but the actual functionality of the language is totally different. It can only manipulate DOTA-related data types, and can't do anything else, but has programming language elements for doing this manipulation. The reason for this is essentially to optimize for speed of working with these DOTA elements. With respect to this goal, the language does very well. Its incredibly easy to specify a hero build, and its very simple to copy-paste from a list of standard builds, testing multiple things at a time. Execution is also functioning well, it runs quickly, produces easy-to-interpret results, and has concrete use cases.

With that said, there are a number of things that need to be improved before the language is usable by the general population (or at least before I'm comfortable posting this to r/DOTA2). Firstly, the main change on the front-end needs to be, as I mentioned, smarter recognition for stat and hero/item names. Missing a capital letter somewhere will cause the program to tell you that its not a valid hero name, which is not the ideal result. The backend needs more work by far. Most importantly, hero ability data is impossible to find in a standardized format, so I'll have to add it all by had, or crowdsource the task. Next, there is room for many more types of combat queries. I included one that produces a graph, as well as one that doesn't so that the entire user experience is demonstrated by the language, but there's certainly more that could be done here. Finally, since DOTA's engine is quite complex, I didn't simulate it in its entirety. Thus, there will always be more backend development that can go into making the combat simulation more faithful to the game, and thus more accurate to the user. As it is, the program gives a good first-order approximation, which in many cases is good enough, but its not fine-grained enough to trust with very close decisions yet.

Lastly, there are some improvements that need to be made on the usability side. I mentioned turning it into a Steam application already, but apart from this, just having it be a command line program that runs independant of python and the two necessary packages would be nice. Despite this though, the general vision of the language is exactly what I wanted it to be, it just needs continuous development on the backend to make it fully functional.

### Language Evaluation:

Evaluation of this language consisted of three main components. First, I had to make sure that the backend worked, and produced correct results. I did this by both checking some things manually (going into the game, making a custom game, and verifying numbers), but mostly by having a suite of sample programs and testing programs that I ran whenever I made a significant change to make sure the results stayed the same.

This suite of testing programs also served as the testing platform for the second component to my language's evaluation. This was just making sure that the parsing module was working correctly. To this end, I just repeatedly parsed all of my sample programs whenever there was a new parsing feature added to make sure nothing broke. This was a constant worry due to issues with the parser that I mentioned above.

Finally, the most important part of language evaluation for this project was testing it on other DOTA players, both in terms of the output that it produced, and in terms of ease of use. The first was much easier to evaluate. I talked a lot with my critique partners (Andrew plays DOTA and Joshua plays LoL, a similar game) to make sure that the features I was implementing were actually useful to people playing the game, and not just the features I wanted to add for some other reason. The second element was much harder to test. I had to look outside Mudd, since everyone here is familiar with programming at least a little bit. To do this, I messaged some friends on a server that we use to play DOTA, asking them to test the project. However, this was way more difficult that it should have been, because I still don't have a version of this working without python/parcon/matplotlib installed, so they were much more reluctant to test things. Two of them did look at the project, and were able to intuitively pick up the syntax and commands. Thus, it seems at least that the main issue will be error messages, so that people with the right intuition don't get screwed by forgetting a colon. 

### Difficulties
I had two issues with this project's implementation. The first was finding data in a reasonable format. Finding hero dat was quite easy, but items were difficult, and hero abilities were impossible. The reason these last two are harder is that there is much less standardization for what items and abilities do. For items, I was able to find a table of just the passive bonuses that items give, which is fine for this use case, but for abilities its not only impossible to find, but not nearly as useful. However, abilities can't even come close to being approximated by their passive benefits, and they are additionally much more unique than item abilties. Thus, in order to get over this difficulty, I'd have to get some sort of crowdsourcing attempt to collect ability data.

The second issue was with the combination of parsing and my syntax. I initially wanted to have very "natural-language" syntax, with keywords being just english words. However, not only was this an issue with the parser, which ignores white space and eagerly parses keywords as stat queries, but many items and heroes contain these keywords (for example, "and" in the item "Sange and Yasha" or "of" in the hero "Keeper of the Light"). Thus, my grammar was ambiguous, and would be incredibly gross to parse (I'd have to hard-code all of the hero names in). This forced me to adopt commas instead of "and" for item builds and lists, as well as braces around loops and optimize statements, and colons after all keywords. While not idea, at least its self-consistent, so it shouldn't take too long to get used to.
