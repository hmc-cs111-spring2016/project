# Project plan

## Language evaluation

###### How will you know that your language has accomplished its goals?
Since this game is overall much bigger than what can be completed in the coming weeks, I will know I have accomplished enough if two
players can play a match where they start with a flagship that they can order to deploy fighters and have a space dogfight.

###### What tools, techniques, or measurements will you use to evaluate your language design?
Since the educational aspects of this game will need to be built after the functionality is complete, for the scope of this project
I will mostly be focusing on making a game that is fun to play and provides detailed error messages. Although I will not be able
to implement everything I want within the timeframe of this project, I will keep notes on things I wish to implement so I can
design the infrastructure so these features can be added as easily as possible later. Furthermore, I will get some friends to beta 
test the game and provide feedback on the clarity and interface of the language. This language is intended to be clear for the users
while resembling C, so getting user input is important.


###### What tools or practices will you use to ensure that your language implementation is of high quality?
I will design a lot of test programs that can be used to determine if the game functions as intentended. This means I will need
to make test programs that both work and do not work. For programs that work, I will want to examine the ship's behavior and ensure
it does what was intended. For programs that do not work, I will need to ensure the error messages that are generated are clear
and as intended.

## Implementation plan

###### How much time do you think you'll need for the various components of your language (e.g., finding a host language, implementing the semantics, the parser, tools, etc)?

   * Creating server/client connection -- 2 hours
   * User Interface -- 4 hours
   * Designing Syntax -- 2 hours
   * Implementing syntax parser -- 8 hours
   * Generating useful error messages -- 4 hours
   * Implementing game behavior -- 8 hours
   * Gathering feedback -- 2 hours
   * Misc improvements -- 4 hours
   * Presentation -- 2 hours

Note: These estimate contain time for debugging. That being said, I generally underestimate these things.

######  Provide a brief schedule (e.g., with a deliverable every week) for your project.
Week 1
   * Creating server/client connection
   * User Interface
   * Designing Syntax

Week 2
   * Implementing syntax parser
   * Generating useful error messages

Week 3
   * Implementing game behavior

Remaining Time:
   * Gathering feedback
   * Misc improvements
   * Presentation



## Contingency plan

###### What obstacles (technical and logistical) might you encounter while working on your project?
I'm imagining getting some of the server/client stuff to work will be slightly challenging since I have never done this before
in Scala. That being said my initial Google searches have revealed it is very similar to how it is done in Java (since it is using
Java's libraries) and thus should be similar to code I've previously written.

I am also not certain how I will get good error messages while using parsers like the PackRat parser we used in class. I might need
to write a parser myself to get the granularity I desire or learn a more robust parser. Alternatively, if this granularity of
error detection is possible with the PackRat parser, I will need to learn more about how to fail gracefully.

I am not certain how Scala handles concurrency. Again, I imagine this will be similar to Java or perhaps even easier due to Scala
making extensive use of _val_ instead of _var_.

###### How will you look out for them, overcome them, or work around them?
Google! StackOverflow! Google! StackOverflow! Google! StackOverflow!

Although I believe I am making something that is overall unique, I do not believe I am doing anything from a technical perspective
that has never been done before. The resources on the internet should be able to teach me whatever I need to learn. Additionally, I 
will also probably ask Prof Ben and other students questions.

Furthermore, hopefully the critiques other students do on my work and I do on theirs will help me catch and overcome unforeseen 
challenges.



## Teamwork plan 

N/A
