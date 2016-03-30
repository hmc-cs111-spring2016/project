# Language design and implementation overview

## Language design

### How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?

### What is the basic computation that your language performs (i.e., what is the computational model)?

The user writes out a program describing movement. The language reads the program, figures out what movement is being depicted, and translates the movement so that it can be displayed by a graphical simulation.

### What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?

### What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?

### What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?

### Error handling: How can programs go wrong, and how does your language communicate those errors to the user?

### What tool support (e.g., error-checking, development environments) does your project provide?

### Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?

From my original description: As far as I can tell, there are few other DSL's for this domain. One of the best known ones is DanceForms (see http://www.charactermotion.com/), which was often used by modern choreographer Merce Cunningham. However, as far as I can tell, this software is not well maintained. Another popular movement software is Isadora (see http://troikatronix.com/). However, it looks like this is more for use in projections with dance rather than simulation of dance movement. There seem to be quite a few straight Labanotation softwares, but they don't seem to deal with the problem of Labanotation's complexity. 

From current research: I found a DSL that requires use of a palm pilot...

## Language implementation

### Your choice of an internal vs. external implementation and how and why you made that choice.

### Your choice of a host language and how and why you made that choice.

### Any significant syntax design decisions you've made and the reasons for those decisions.

### An overview of the architecture of your system.
