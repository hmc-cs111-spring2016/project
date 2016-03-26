# Project description

## Motivation

Labanotation exists as commonly method used for notating movement. However, the language itself can be cumbersome and complicated to write and read. This makes it unaccessible to those who do not have serious training in transcribing and interpreting movement in Labanotation. As such, it would be beneficial to the movement analysis community to develop a language that retains the specifity of Labanotation while reducing the complexity. With this in mind, it would be useful to develop a language in which users can write out movement and see a simulation of that movement. Doing so would help both avoid the complexity of Labanotation and allow the user to see what their movement ideas look like in close to real time. 

## Language domain

As mentioned in the motivation, the domain of this language is movement transcription. This domain is useful as it helps both choreographers and dancers to easily record movement and revisit that movement. It is also useful to other performers and non-dancers as a way to understand how we move and interact with the world. 

As far as I can tell, there are few other DSL's for this domain. One of the best known ones is DanceForms (see http://www.charactermotion.com/), which was often used by modern choreographer Merce Cunningham. However, as far as I can tell, this software is not well maintained. Another popular movement software is Isadora (see http://troikatronix.com/). However, it looks like this is more for use in projections with dance rather than simulation of dance movement. There seem to be quite a few straight Labanotation softwares, but they don't seem to deal with the problem of Labanotation's complexity. Looking into DanceForms to see how it does simulations may be useful. It could also be useful to see if any of the Labanotation programs have API's with which I could interface after developing my syntax. 

## Language design

The design of this language is to take exisitng syntax and put it into a more comprehensible form, both for the sake of writing and reading. A program in this language is a sequence of commands defining some movement with potentially more details regarding its effort, time, etc. When a program runs, a simulation of the motion notated is desplayed through a graphical 3d figure. A program coult take input like time, and then ouput the same movement but at different speeds. At this point, I am not sure which data structures or control flow ideas I should use. Something that could go wrong is that a program could contain contradictory movement (such as left arm forward and back at the same time). This type of error should be caught at compile-time before attempting to create the simulation. Some sort of error message specifying the contradiction would be useful. To avoid such errors, it should be difficult to give multiple values for a single attribute (such as an arm, etc) so that users won't accidently do these types of things.


## Example computations

Here are is an example of a program that a user could run: A user could specify in the program that the whole body moves forward quickly while the right arm goes up lightly. This would result in a simulation of a person moving forward while their right arm goes up lightly. No other movements or characteristic would be depicted in the body. Since no effort quality was assigned to the body, the body will move with pre-assigned neutral quality. Similary, since no time quality was assigned to the right arm, it will move with a pre-assigned neutral quality.

Another situation: A user might write the same program as above, but make it possible to input an effort quality for the body overall. This means that at runtime the simulation will be the same, except that the body will move with whatever effort quality is inputed. Note that this will not affect the light effort assigned to the right arm.
