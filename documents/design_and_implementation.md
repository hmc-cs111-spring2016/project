# Language design and implementation overview

## Language design

### How does a user write programs in the language?
The main interface for my language will be in the form of textual input - in a file with with extension ".dota". This is ideal for most use cases of the language, as it will let users keep hero and item builds in text form for easy copy-pasting between files and queries. Its also the main pro of this language being a language rather than a GUI - one can store multiple builds and easily replicate them, without having to tediously go through the options for every item, stat, level, etc, that all of the existing dota 2 calculators make you do. However, there is one additional way that I would like (in the hypothetical future) the user to be able to use this language. I would like to make an app that functions as a Steam user (For any not familiar, Steam is a program that manages your games, and you have a friends list of other Steam users that you can easily interact with while in a game), so you can add the user to your friends list, and message it while in-game. It would then respond as if you were typing to it in a .dota file. This is a massive stretch goal though, inspired in part by someone who did a basic version of this that just lets you query individual hero stats.

### Computation Model
I have stored data in 3 ways for my DSL. First, I have the raw, unaltered game data stored in JSON format. I chose this because its universal, easy to get, and easy to parse. Second, I have python classes that compute some additional hero stats based on this json data and store the relevant information for easy querying. Next, I have simulation methods that handle the computation. These essentially deal with simulating combat and doing efficiency calculations, which are two of the main features that I want users to be able to find out about.

### Data structures
The main data structures in my DSL are Hero Builds, Item Builds, and Lists of one of the above types of things. Syntax for creating these is very simple, its just "variable_name = variable_definition" or "[variable_definition, variable_definition]", etc. This is designed to be as similar to normal languages as possible - specifying builds isn't the interesting part of the language, its the querying and simulation that people care about.

### Control Structures
The main control structures of the DSL are queries and loops, where loops are just queries across lists of builds. I want the user to be able to do every sort of natural comparison between builds, as well as iterating across several builds. Thus, I will have the commands like "max", and "min", as well as "optimize(parameter_to_optimize)", and then loops across lists, specified by "foreach var in list_of_vars". One of the main challenges for this language is determining the right syntax for these queries, as the majority of dota players aren't programmers, so having "build.damage"-style notation would be bad. My plan here is to have things like the "of" keyword, so you can query for a stat by the stat name (e.g. damage) like: "damage of build1". This is enough like natural language that people should be comfortable with it, but restricted enough in the syntax that implementation is feasible.

### Input and Output
One of the main tricky things about this language will be input handling. Since there are items in the game that are very frequently known by their abbreviations or nicknames, forcing the user to list a hero called "Queen of Pain" by writing exactly that name, including capitalization, would be terrible. Thus, I'll need several aliases for hero and item names (in this case, I'd accept "QOP", "qop", and "queen of pain"). Then, for heroes that have the same abbreviation (e.g. "Earth Spirit" and "Ember Spirit") I'll throw an error and ideally give the user an interactive option to specify which they mean (as a stretch goal). Thus, all input will be these hero and item names on top of the control flow and data specification listed about.

In terms of output, this will depend entirely on the query. In most cases, simple queries will result in text output formatted nicely. In some cases though (like if you wanted to know how many hits it will take for build1 to kill build2) I'll produce a graph of the relative frequencies of numbers of hits. Thus, the main forms of output will be text and visuals that explain the user's query the best. This is a somewhat large change from the original design, as I hadn't intended to do much repeated simulation. However, after talking to my critique partners, it was clear that this would be really useful information to convey visually.

###Error handling
The main errors that can occur (and, in fact, the only ones I've thought of) are errors with the user specifying invalid data. For example, Heroes in the game cannot level up their skills until they reach certain levels themselves, so if the user specifies that a hero has their ultimate ability before level 6, I'll throw an error. Since I'm doing this in python, I raise an exception and print an error message. This will be handled on the user's end by printing the error message and terminating.

###Tool support
As of now, I don't plan to add any special tool support, other than the super stretch goal of the Steam app. Dev environments would be cool, but probably uneccessary, as the language itself is fairly simple, just with lots of cool interactions and query possibilities.

###Other DSLs
There is technically one other DSL in this domain. Its the inspiration for my Steam app idea. All that it does is let you message a steam friend for hero stats, where these are limited entirely to the data that I have in my json files. Thus, my DSL provides a lot more features, probably at the expense of the context of being a really cool app. 

## Language implementation

### Internal vs External
After the piconot assignment, I don't want to touch writing an internal language with a 10-foot pole, so I chose external. Silly reasons aside, I need to make this language as friendly to non-coders as possible, so I need to make the syntax very different from regular programming, as I've described above.

### Host Language
This is a significant difference from the plan - I'm going with Python instead of Scala. The reasoning for this is essentially two main issues. Primarily, after the conversation with Andrew in which I decided that producing graphs and doing repeated simulations of combat would be really useful, it was clear that all of the python libraries that exist for this kind of thing (e.g. matplotlib) would be really helpful. Second, I am muuuuch more familiar with python than with scala, so this was an advantage in itself. Lastly, I just had to make sure that nice parser-combinator packages exist, and I checked a few (pyparsing and parcon), and they both look very useable for this task.

### Syntax design decisions

The only major decisions I've made are to not have object-oriented syntax (ie object.data), for reasons I've explained above. Other than that, most of the syntax elements are self-explanatory (lists, foreach loops, variable assignment). The other issues are just figuring out what to name each query, which will be an iterative process. Ideally, this syntax design should allow users to compare queries as easily as possible without programming knowledge.

### Architecture

My DSL's architecture is pretty straightforward - I'll have a parser, an abstract syntax tree, an internal representation, and an evaluator, that takes the AST and converts it into the IR, and evaluates any commands that it represents.
