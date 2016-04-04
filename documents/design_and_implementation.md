# Language design and implementation overview

## Language design

My DSL is a data interpretation DSL.  Users write programs in my language by typing parameters into a text-area of a web page.  As a stretch goal, an alternate form of input will be a UI/GUI-based (prof Ben suggested this).  As output, the DSL generates random sheet music that corresponds to the parameters passed in by the user.

The internal data structures used in my DSL will mostly be wrapper objects.  The current plan is to have three types of wrapper objects:
- Score: stores "score parameters," such as "measureCount," "timeSignature," and "key."
- Global Stave: stores "stave parameters" such as "avoidNotes," "clef," "relRange."
- Stave: also stores stave parameters, but defines a specific stave.  Individual stave objects will inherit any parameters defined in the Global Stave that they themselves don't define.

The control structures that users use will mirror the above wrapper objects.  Though the code they write will essentially be a list of parameters, they will have to put specific parameters in the proper section of their code - either in the Global Stave section, a specific Stave section, or outside of any stave section (which acts as the Score section).  To see what this looks like in action, see the example program in this repository's [description.md](https://github.com/milohan/project/blob/master/documents/description.md).

Errors can occur if users enter invalid syntax or if users enter valid syntax that makes it impossible to generate sheet music.  An example of the latter is if a user enters as a parameter, "measures: 10-8".  Based off the syntax, this means the user wants the program to generate at least 10 measures, but at most 8 measures of sheet music.  This isn't possible.  Currently the way I handle this is by printing out an error message to the console that says, "Uncaught SyntaxError: Min value must be greater than max value."  The parser-generator I'm using (described below) makes it simple to create such error messages.  Eventually I'll display errors to users on the webpage itself (instead of the console) to make them more user-friendly.

The development environment of my DSL will be a webpage where users can enter syntax and see the output of that syntax (whether it's an error message or sheet music).  See below for a description of the architecture behind this.

There are several DSLs that already exist in this domain, such as [randomsheetmusic.com](http://www.randomsheetmusic.com/) and [Sight Reading Factory](https://www.sightreadingfactory.com/).  The main difference between my DSL and existing ones is that my DSL aims to provide users with greater, lower-level control over the output of their sheet music.  A breakdown of some of the existing DSLs can be found in [description.md](https://github.com/milohan/project/blob/master/documents/description.md).

## Language implementation

I'm doing an external implementation for my DSL.  My target audience is any instrumentalist who's learning how to sightread, meaning I'm not assuming any programming experience.  Also, the intended use of my DSL (generating sheet music) isn't something people would normally want to do within the confines of a pre-existing general purpose programming language.  Rather, it's meant to be a standalone DSL/application.

I chose Javascript as my host language.  My original reason for using Javascript was that it was the only language I could find with a [sheet music rendering library](https://github.com/paulrosen/abcjs/blob/master/api.md) that was simple and flexible enough for my needs.  However, since deciding to use it I've found some other benefits:
- Javascript has a parser-generator library called [PEG.js](http://pegjs.org/documentation#using-the-parser) that's very straightforward to use.  The [online version](http://pegjs.org/online) allows you to test out the parser before you incorporate it into your project.  This has been super helpful.
- Final product should be very easy to share with people since it's a web-page.
- Very little boiler-plate.  I don't need any build tools, I don't need to touch the terminal, and I can include libraries with a simple script tag.
- HTML + CSS makes user layout easy.

I've decided to develop my DSL as a data-interpretation DSL.  I figured that from a user's perspective, the easiest way to describe sheet music is to define certain parameters (or ranges of parameters), such as "timeChange," and "measureCount."

Since my DSL is web-based, the architecture is all centered around the main HTML page.  As input, users type syntax into a text area div.  Assuming the syntax is valid, the input is passed to a parser (included as a .js file), which converts the syntax to my internal data representations (described above) by returning a Javascript array of wrapper objects.  I then have Javascript logic that generates music (represented as an [ABC Notation](http://trillian.mit.edu/~jc/music/abc/doc/ABCprimer.html) string) based on the given parameters.  The ABC Notation string is then rendered as sheet music using abcjs.
Most of this (in very basic form) is viewable/usable in my [code repository](https://github.com/milohan/sheet-music-gen).