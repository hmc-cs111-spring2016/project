# Project plan

## Language evaluation

I can't think of any purely objective goals, but in essence I'll know my language has accomplished its goals if it allows any aspiring musician aged 10+ to generate sheet music specific to their needs with relative ease.    
In order to evaluate my language design I'll compare it to the already-existing DSLs I discussed in 'description.md' and I'll also ideally get feedback from domain experts (ie instrumentalists/musicians), of which there are many on campus.  
To ensure that my implementation is of high quality, I'll try to follow standard object-oriented practices and keep my code modular and "safe".  I've never done unit testing with Javascript (my planned host language) before, so I'll have to try out a few [different](https://facebook.github.io/jest/) [testing](http://unitjs.com/) [frameworks](https://qunitjs.com/) before I settle on one.

## Implementation plan

The following are some languages/technologies I'll be using (all subject to change):
- Javascript (host language)
- [ABC Notation](http://abcnotation.com/), a shorthand method of writing down sheet music in ASCII format.
- [abcjs](https://github.com/paulrosen/abcjs) for rendering abc-notation as sheet music (this seems to be more flexible than [VexTab](http://www.vexflow.com/vextab/), which is what I was originally planning on using to render sheet music).
- [PEG.js](http://pegjs.org/documentation#using-the-parser), a program that generates parsers in javascript.
- Some Javasript testing framework (there are many online but I haven't tried any out yet).

Here's a tentative schedule for implementation:
- Week 1: Finish this assignment.
- Week 2: Iron out language/technology choices and work on the specifics of initial syntax design.  Become familiar with the abcjs API.  Begin working on back-end logic (ie the algorithm for generating random notes under variable constraints).  Possibly begin implementing the parser.
- Week 3: Have a basic form of the entire DSL working, from user input to actually generating sheet music.  Ideally be able to do everything that [randomsheetmusic.com](http://www.randomsheetmusic.com/) can do at this point.
- Week 4: Continue adding more advanced/specific parameters that will give users greater control over the sheet music they generate.  Get feedback on current build from domain experts and iterate on design.  Start adding basic error checking.
- Week 5: More iteration, more language features (basically continuation of week 4).

Not included, but part of each week: Notebook entries, debugging, critiques.


## Contingency plan

Given that Javascript isn't one of my most familiar languages, it may take some time for me to get a handle on some of the Javascript frameworks I'll be using.  This is especially likely for the parser (I'm new to parsing - haven't taken PLs and picobot external took me a while to figure out).  I'll look at a lot of sample code to hopefully familiarize myself with how PEG.js works enough that the parser won't be too much of a holdup.  
I'm a pretty slow writer, so another big time sink will likely be writeups and notebook entries.  I don't think there's any fix for this, as the written portions of this project seem pretty important and I wouldn't want to skimp on them.

## Teamwork plan 

I'll be working solo.