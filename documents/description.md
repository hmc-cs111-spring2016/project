# Project description

## Motivation


Sight-reading (ie, reading and performing music at sight, without preparation) is an essential skill for any instrumentalist, but can be quite a pain to learn.  
The typical approach to practicing sight reading is simple - find some sheet music and then sight-read it.  However, this method presents a couple issues:
- You need access to a large quantity of sheet music (the second time you go through a piece, you're no longer sight-reading).  Furthermore, this sheet music might need to be highly specific.  A beginner trumpeter, for example, would want single-stave, treble clef sheet music with notes whose pitches range from F3 to G5 and whose rhythms aren't too complex.  A progressive-metal/jazz fusion guitarist might want to specifically train fast pieces with odd time signatures that modulate key frequently.  Finding enough sheet music to suit your specific needs can be difficult and expensive.
- Musical pieces of specific genres rely on a limited set of patterns and cliches.  Thus, instrumentalists will often find themselves playing the same phrases over and over when sight-reading "new" sheet music. This is good because it allows instrumentalists to quickly recognize common patterns, but it's bad because it's not entirely sight-reading. Practicing this way creates a sight-reading "crutch" that can't always be depended upon.  Who knows when a musician will have to sight-read in a drastically different genre than what they are used to?

The goal of this DSL is to alleviate these issues by allowing users to generate an infinite amount of random sheet music based on highly-customizable parameters.

## Language domain

The domain of this DSL is parameterized random (is there a word for this?) sheet music generation.  Instrumentalists or vocalists who wish to practice sight-reading/singing would benefit from this DSL.
There exist a few programs/DSLs in this domain.  
- [randomsheetmusic.com](http://www.randomsheetmusic.com/) generates random simple sheet music.  It is very easily accessible, but also quite limited.  It only generates single-clef, monophonic melodies and has only a few parameters that users can change.  The simple design (visually) is something I'm after, but I'd of course like to have more expanded functionality in my DSL.
- [Melisma Stochastic Melody Generator](http://www.link.cs.cmu.edu/melody-generator/) gives a lot more control to users, but is also monophonic and doesn't generate viewable sheet music (only MIDI).  Some of the parameter names are also a bit confusing (rhythmic anchoring, key profile type).  I'd take some cues from some of the parameters, but I'd strive to make them easier to understand for beginner instrumentalists and for young kids (11 to 12-year-olds, maybe).
- [Impro-visor](https://www.cs.hmc.edu/~keller/jazz/improvisor/) is a flexible program that generates sheet music and MIDI of single-line  improvisations over given chord changes. This program has a level of refinement and sophistication that I hope to achieve, but its focus is slightly different than mine.  It's meant to help with improvising rather than sight-reading, so its more geared toward generating licks than generating customizable sheet music.
- [Sight Reading Factory](https://www.sightreadingfactory.com/) is a paid software that generates random sheet music for various instruments and difficulty levels.  The presentation is great and the underlying generation algorithms work really well.  However, it doesn't provide as much flexibility to users as I'd like my DSL to (for example, it doesn't support key modulation, key signature changes, arbitrary numbers of staves, detailed customization of chords or rhythm, etc).

Note:  All of the above are GUI-based, while my DSL won't be (at least in its first iteration).

## Language design

My DSL will be designed as a data interpretation DSL.  As input, it will take a list of parameters that describe constraints on musical aspects (such as rhythm, pitch, length, etc).  As output it will generate sheet music.  When a program runs, a parser will convert the list of parameters into an internal data type - perhaps a list of "Staves," each with their own instance variables corresponding to the listed parameters.  Logic would then be performed to choose random notes that fall within the constraints given by the user, and these notes would be stored in [ABC notation](http://abcnotation.com/).  The ABC-notated score would then be converted to sheet music using a [pre-existing API](https://github.com/paulrosen/abcjs/blob/master/api.md).  
In addition to your typical errors (typos, missing brackets, etc), users might input syntactically-valid parameters that make it impossible to generate sheet music.  For example, a user might try to generate sheet music in 7/8 time that uses only quarter notes.  Such a request isn't possible (I'm pretty sure), and ideally my DSL would be able to detect (and report) this fact before getting stuck in some infinite loop while attempting the impossible.

## Example computations

Note: the writeup states that I "shouldn't *describe* the syntax of the program."  Wasn't sure if that meant I shouldn't *include* a sample program.  Went ahead and included one because it helps with understanding the DSL's design.

Below we have an example program that might have been written by a pianist.  The program would create piano-friendly sheet music with simple, but frequent key and time signature changes.  It also specifies some specific ranges and avoid notes that closely tailor the sheet music to the pianist's needs.  As described in a previous answer, when this program runs the parser would create two "Stave" objects with instance variables corresponding the the parameters written by the user.  Another object (perhaps the object containing the Stave objects) would hold the global variables ("length", "time", etc), and would perform logic to generate notes that fit the requirements of the parameters.  These notes would be stored as a string of ABC notation and then converted to sheet music using the API linked in a previous question.

**length: 40-50**				*[score will be 40-50 measures long]*  
**time: 3/4, 4/4** 				*[time signature will start at either 3/4 or 4/4 and can randomly change between the two throughout the piece]*  
**time: start /4, /8** 		*[time signature will start at /4 (any common time signature with 4 on the bottom), and can change to /4 or /3]*  
**timeChange: 8-20**			*[each time signature will last at least 8 but no more than 20 measures]*  
**key: G Major, F Major**		*[time signature will start at either G or F major and can modulate between the two]*  
**repeats: yes** 				*[repeats can occur]*  
**repeatsLength: 10-** 			*[repeated sections must be at least 10 measures long]*  

**Staff Global {**				*[Global parameters affect all staves unless overridden by local def.]*  
	**avoid: F#, D4**			*[No F#'s (of any octave) and no D4's will be generated]*  
**}**

**Staff “R”{**
	**clef: Treble**
	**simultaneous: 1-3** 		*[1 (min) to 3 (max) notes played at a time]*  
	**absRange: C3-C5** 		*[min and max notes in this staff]*  
	**relRange: 1-12** 			*[1-12 halfsteps total range within any chord]*  
	**seqRange: -4** 			*[no more than 4 halfsteps from one note to next (how would this work for chords?)]*  
	**subdivision: 1-8** 		*[anything from whole note to eight notes allowed]*  
	**duplet: 10% 3, 5% 5** 	*[triplets and quintuplets allowed]*  
	**avoid: F#, D4** 			*[No F# (any octave) or D (octave 4) allowed]*  
}

**Staff “L” {**  
	**clef: Bass**  
	**simultaneous: 70% 1, 30% 2**  
	**range: C2-C3**  
	**only: G, D, A, E**  
**}**