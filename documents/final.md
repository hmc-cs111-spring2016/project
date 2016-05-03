## Introduction

Sight-reading (ie, reading and performing music at sight, without preparation) is an essential skill for any instrumentalist, but can be difficult to learn and requires a lot of practice.
The typical approach to practicing sight reading is simple - find some sheet music and then sight-read it. However, this method has its drawbacks.
First of all, one need access to a large quantity of sheet music.  An intermediate-level sight-reader might read through a page of sheet music in a couple minutes.  At this pace, one could finish a book of sheet music in just a few days.  

In addition to needing a large quantity of sheet music, musicians often need sheet music that's highly specific. A beginner trumpeter, for example, would want single-stave, treble clef sheet music with notes whose pitches range from F3 to G5 and whose rhythms aren't too complex. A progressive-metal/jazz fusion guitarist might want to specifically train fast pieces with odd time signatures that modulate key frequently. Finding enough sheet music to suit one's specific needs can be difficult and expensive.

The goal of this DSL is to alleviate these issues by allowing users to procedurally generate sheet music based on user-defined parameters.  This provides users with a virtually infinite amount of sheet music that is customized to their specific needs.

## Language Design Details

My DSL follows a data-interpretation model.  Users write programs by typing parameter names (and their corresponding values) and stave definitions into a text-area of a web page.  There are two types of parameters that users can define, "score" parameters and "stave" parameters.  Score parameters define characteristics of the entire piece, such as the number of measures in the piece and the key of the piece.  These are specified at the top of the program.  Stave parameters define characteristics of specific staves, such as the allowable pitch range (absRange) and the number of notes that can be played at the same time (polyphony).  Stave parameters can be defined either inside a specific stave object (which users specify with curly braces), in which case they define the characteristics of that specific stave, or outside a specific stave object, in which case they define the characteristics of any stave that doesn't define the same parameter itself.

This syntax helps users write programs more easily than a general purpose programming language would because the only thing users can do in it is define staves and music-related parameters.  The language does away with traditional control and data structures (though the stave definitions could be considered data structures) because they aren't necessary for describing sheet music.  The simple structure of a program's syntax nicely correlates with the sheet music that the program produces as output.  For each stave definition, there is a corresponding stave produced whose characteristics match the parameter values that were assigned to it.

Here's an example program:

![input](https://raw.githubusercontent.com/milohan/project/master/images/code_input.png)

And its corresponding output:

![output](https://raw.githubusercontent.com/milohan/project/master/images/code_output.png)

When writing programs in my language, there are many possible things that could go wrong syntactically.  Some examples of errors that could occur are as follows:
- Invalid parameter value
- Missing semicolon
- Missing parameter value
- Missing stave definition
- Missing closing curly brace

In each of these cases, the parser will present the user with an error message that describes the problem and points the user to the relevant line number.
Certain cases of error checking are made even easier because the development environment supports basic syntax highlighting.  Thus, even before trying to run the program, users should be aware of any misnamed parameters since they won't be highlighted with the appropriate color (purple).

In addition to writing code, beginner users also have the option of using a GUI interface to create a program.  This GUI version is less powerful than the code version of the DSL, but is easier to pick up.  It outputs corresponding code (in addition to sheet music), meaning that one can easily start writing a program with the GUI interface and later switch over to the code version if needed.

Sample input to the GUI interface:

![GUI input](https://raw.githubusercontent.com/milohan/project/master/images/gui_input.png)

Corresponding code output:

![code ouptut](https://raw.githubusercontent.com/milohan/project/master/images/gui_output_code.png)

Sheet music output:

![sheet music output](https://raw.githubusercontent.com/milohan/project/master/images/gui_output_score.png)

My DSL took inspiration from a few other DSLs that exist in the same domain.
- [randomsheetmusic.com](http://www.randomsheetmusic.com/) generates random simple sheet music.  It is very easily accessible, but also quite limited.  It only generates single-clef, monophonic melodies and has only a few parameters that users can change.
- [Melisma Stochastic Melody Generator](http://www.link.cs.cmu.edu/melody-generator/) gives a lot more control to users, but is monophonic and doesn't generate viewable sheet music (only MIDI).  Some of the parameter names are also a bit confusing (rhythmic anchoring, key profile type).
- [Impro-visor](https://www.cs.hmc.edu/~keller/jazz/improvisor/) is a flexible program that generates sheet music and MIDI of single-line  improvisations over given chord changes.  It's meant to help with improvising rather than sight-reading, so its more geared toward generating licks than generating customizable sheet music.
- [Sight Reading Factory](https://www.sightreadingfactory.com/) is a paid software that generates random sheet music for various instruments and difficulty levels.  The presentation is great and the underlying generation algorithms work really well.  However, it doesn't provide very much flexibility to users.

## Language Implementation

I used Javascript as my host language.  I originally chose Javascript because it had straightforward text-to-sheet-music libraries.  During development, I realized that there were several other benefits to using Javascript:
- The DSL is very easy to run since the development environment is just an html page.
- Html/CSS made it very easy to lay out the GUI interface
- JSON works as a perfect intermediate representation of data

Though I used Javascript as a host language, I didn't want my DSL to look anything like Javascript.  For this reason, and for the fact that my target audience may not be familiar with Javascript, my DSL is external.  

Below is the layout of the architecture of my DSL:

![diagram](https://raw.githubusercontent.com/milohan/project/master/images/architecture_diagram.png)

As input, the DSL takes code (even if the GUI interface is used, it still converts to corresponding code internally).  The parser (generated using [PEG.js](http://pegjs.org/)) converts this code into a JSON representation of an array of staves.  The score parameters, as well as any global stave parameters (stave parameters defined outside of a specific stave) are stored in the 0th index of the array.  In this way, the internal JSON representation of data closely mimics the structure of the code written by the user (since it's split up by stave).  

Once we have a JSON representation of the parameters, Javascript logic is performed to randomly generate a score that follow the constraints of the given parameters.  The score is laid out in [ABC notation](https://en.wikipedia.org/wiki/ABC_notation), one stave at a time.

Once we have a string of ABC notation that represents our final score, we use [abcjs](https://github.com/paulrosen/abcjs/blob/master/api.md) to convert it to sheet music, which is shown to the user as output.

For the sake of modularity, the code for each section of the language is confined to its own file.  
- The html code for the layout of pages can be found in [dsl_code.html](https://github.com/milohan/sheet-music-gen/blob/master/dsl_code.html) (for the code version) and [dsl_gui.html](https://github.com/milohan/sheet-music-gen/blob/master/dsl_gui.html) (for the GUI version).  Each of these files also contains some Javascript specific to their own versions that interacts with the html objects defined in the files.
- The parser can be found in [parser.js](https://github.com/milohan/sheet-music-gen/blob/master/parser.js).  It was generated using the syntax definitions found in [parserGrammar.js](https://github.com/milohan/sheet-music-gen/blob/master/parserGrammar.js).
- The Javascript functions used to convert code to ABC notation can be found in [script.js](https://github.com/milohan/sheet-music-gen/blob/master/script.js).  The code is called by both dsl_code.html and dsl_gui.html.  For this reason, this code is written in a functional style and doesn't involve any changes of state or any html interaction.

## Evaluation

My language is very DSL-y.  It contains no loops, conditionals, or variables, and is not Turing complete.  The only thing users can do in it is define staves and music-related parameters.

I'm most pleased with the general user experience of my language.  Since my language is web based, building and running it is simple (just open up the .html file with your browser of choice) and should feel somewhat familiar to anyone who's ever used the internet.
The syntax itself is easy, and it's made even easier to pick up when using the GUI-version of the DSL as a starting point.  Even if the users do input some sort of syntax error, the syntax highlighting and error messages should be enough to set them on the right track.  
There are many aspects of my DSL that could be improved.  First and foremost, it needs to support many more detailed parameters, especially ones involving harmony and rhythm.  As is, the language isn't really usable by its intended audience (sight-readers) since the sheet music it produces is harmonically and rhythmically very boring and often impossible to play.  A smaller, but still important aspect I'd like to improve is the organization of code for my parser.  Currently all of the grammar definitions for the parser are in a single file, parserGrammar.js, and it's difficult to modularize the code since I need to run it all through PEG.js as a single file.  Other features I'd like to add are MIDI support (so users can hear if they're playing the music correctly) and support for multiple staves in the GUI version of the DSL.

Early on I stated that "I'll know my language has accomplished its goals if it allows any aspiring musician aged 10+ to generate sheet music specific to their needs with relative ease."  I think I've accomplished the "with relative ease" part, but I haven't accomplished the "specific to their needs" part.  As stated above, the music my DSL generates is very unmusical and often unplayable.  However, I don't really mind this.  The reason I don't support a lot of different parameters isn't because I couldn't get them to work, but because I decided to listen to the advice of prof. Ben and my critique partners and focus on language-y aspects of my DSL instead of on algorithm-heavy parameters.  I originally set out to support complex rhythms and chord progressions, but instead decided to work on the GUI interface, better error checking, and syntax highlighting.  For the purposes of this class I think it was a solid decision.

While I had some difficulties when I first started writing my parser, I never got stuck on any one problem for too long.  I tried to work spread out my time on the project evenly throughout the week so that I'd be able to sleep on any issues that arose.