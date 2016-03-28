# Project description

## Motivation
I have found in my experience as a non-classically-trained guitarist (and others have too--I've asked) that a lot of the guitar tablature for popular music (as in, not art music) out there on the internet is just plain bad. Some tab websites (well, one that I've found at least) let users submit tab in other formats than just plaintext and can play the tab using MIDI and a software synthesizer, and even show exactly which part of the tab is playing. I believe this feature is
supposed to help with learning to play songs, but I've also found it very useful to determine whether the tab is correct.

## Language domain
The domain is guitar tab of unknown quality. This will be useful to guitarists who want to play songs in just about any genre besides classical and jazz (for the most part classical and jazz sheet music can be found easily enough in standard notation). The closest thing in the domain of guitar tab is graphical tab editors. Thanks to keyboard shortcuts, it can be faster to use some of these than a mouse-only program, but they're best at making totally new tab and tweaking tab that you already
know isn't quite right.

## Language design
Mostly, I want this to be an alternate, tab-focused syntax for LilyPond. I think I've mostly covered the rest of the design elsewhere in this file and also in plan.md.

## Example computations
A program will come in the form of a text file, and running it will be similar to LaTeX or, more to the point since it's the back end I'll be using, LilyPond. Most of the file will correspond directly to guitar tab, although it may not look exactly the same (just like LilyPond doesn't _look_ that much like standard musical notation). There will be at least one line in a program that says what kind of output to give. No matter what that line says, the program will be translated
into a LilyPond program that represents the same notes. Then some script or other will run the new LilyPond program, which will output either standard musical notation (generally as a PDF, but I think it supports other formats, too) or a midi file. As I've said in the project plan, eventually I would also like to allow for output readable by tab editors (I'll probably focus on [TuxGuitar](www.tuxguitar.com.ar) if I get that far since it's open source and as such its file i/o component is
as well).
