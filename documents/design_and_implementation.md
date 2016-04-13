# Language design and implementation overview

## Language design

### How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?

There will be a visual/graphical tool. I haven't decided exactly what it should look like, but I think given the visual nature of movement it makes more sense to write it out as a visual language.

### What is the basic computation that your language performs (i.e., what is the computational model)?

The user writes out a program describing movement. The language reads the program, figures out what movement is being depicted, and translates the movement so that it can be displayed by a graphical simulation.

### What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?

If there are overall conditions for the movement (everything is at a low level, all leg movements are done in turnout, etc), then there should be a structure to indicate what the definition of "neutral" is for the movement phrase. Otherwise, the user is specifying for particular amounts of time how the body should move, so data will be stored with respect to body part and time. There should be a hierarchy of body parts (from entire, to core, to arms/legs, to feet/hands, etc) so that the conditions trickle down to the smaller sections of a body part.

### What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?

Maybe I sort of answered this in the above question, but the control really comes from the neutral overall definition down. The hierachy of rules based on time and body part will decide which movements execute when and what sort of qualities they have. 

### What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?

If the user wants there to be room to experiment with different qualities, there could be room to leave characterstics like effort and levels open. It would be harder to do so with specific body parts. The output is a 3d simulation of the movement, so that the user can get a sense of what the phrase they programmed looks like.

### Error handling: How can programs go wrong, and how does your language communicate those errors to the user?

If a user specifies conflicting ideas or anatomically impossible ideas, then the program would have a problem. However, I hope to have a gui such that making these sorts of decisions would be impossible to do (such as if you have the the left leg defined to be going forward, there is no way to also define it to be going backwards at the same time).

### What tool support (e.g., error-checking, development environments) does your project provide?

The development environement will have mild visualizations so that the user can be more visual in specifying a phrase.

### Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?

From my original description: As far as I can tell, there are few other DSL's for this domain. One of the best known ones is DanceForms (see http://www.charactermotion.com/), which was often used by modern choreographer Merce Cunningham. However, as far as I can tell, this software is not well maintained. Another popular movement software is Isadora (see http://troikatronix.com/). However, it looks like this is more for use in projections with dance rather than simulation of dance movement. There seem to be quite a few straight Labanotation softwares, but they don't seem to deal with the problem of Labanotation's complexity. 

From current research: I found a DSL that requires use of a palm pilot...if it wasn't obvious why this project could be useful I hope it now is...

## Language implementation

### Your choice of an internal vs. external implementation and how and why you made that choice.

I will be making an external implementation. Honestly, it's because I can't think of any way to make this an internal DSL. Also, since I want to have a more graphical interaction with the language, I think an external DSL is just more natural.

### Your choice of a host language and how and why you made that choice.

I'll be working in Python, because of the easiness of modeling with vpython. My plan is to start with a simple ball/joint model of human movement. I think it'll be much easier to get something worked out in vpython than to try to jump into a complex animation system with super smooth and realistic rendering. I would however like to maybe demo what a realistic animation could look like, so if time permits I'd like to look into making a model with Maya. 

### Any significant syntax design decisions you've made and the reasons for those decisions.

Probably the most significant syntax design decision is the idea of being graphically based. I've uploaded a picture to my design notebook of one possibility of what this could look like, and I have more ideas running around in my head of different ways to improve it.

### An overview of the architecture of your system.

The user will use the gui to design a movement phrase. This will affect what the "neutral" is, in addition to displaying a sequence of movements. This is translated so that the 3d simulator can show a model of the movement.
