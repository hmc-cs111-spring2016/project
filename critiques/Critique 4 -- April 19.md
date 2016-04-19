## Response to Project Notebook
After last week's discussion, I remember you were conflicted that your project was turning out to be somewhat more of a UI for wine tasting than a DSL. You also reflected those concerns in your project notebook. I think your decision to go with writing a parser instead of creating a UI for users to input their notes is the correct decision at least as far as this class is concerned. Possibly it could've been more user friendly to have a UI but then your project would've lacked relevance to what we've learned about DSLs in this class. 

As for your jsonParser.py, from skimming it I like how you've modularized the functions for parsing different sections of the wine tasting notes. My only suggestion is that you account for incorrect syntax in a user's wine notes. Your current implementation does not throw any errors if the user does not format their note correctly. My suggestion is that you use some sort of regex to do this. This way the regex can handle more of the parsing load for you and show syntax errors where needed.

## Response to Project Notebook Questions
_Could I organize the project structure in a different way? Should the files be stored somewhere else? Is it all right to call the parser in this way, and is it fine to have all of that parsing logic in the same file? Should I create a better AST?_

I think your current way of calling the parser should be fine but I think you should find a way to pass the JSON created by your parser to the d3 code without saving it in another file. This could mean calling the d3 code with a command line argument of the JSON or some other way. I'm not really sure how it's done.

As for your AST, I don't think you need to spend much time on it. In fact, as I see it, JSON is effectively your intermediate representation. So creating another intermediate representation from your AST doesn't seem necessary. 

_What visualizations should I make?_

I personally like the idea of displaying flavors based on the time that they were tasted; something that both you and Rebekah mentioned. Other than that, I could see displaying flavors by intensity, category (e.g. fruity) or even displaying wines that were similar to the one that you are reviewing. 