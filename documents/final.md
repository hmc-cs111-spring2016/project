# Final Documentation - Programming Movement

## Motivation

Labanotation exists as commonly method used for notating movement. However, the language itself can be cumbersome and 
complicated to write and read. This makes it unaccessible to those who do not have serious training in transcribing 
and interpreting movement in Labanotation. As such, it would be beneficial to the movement analysis community to develop 
a language that retains the specifity of Labanotation while reducing the complexity. With this in mind, I worked on
developing a language in which users can write out movement and see a simulation of that movement. Doing so would help 
both avoid the complexity of Labanotation and allow the user to see what their movement ideas look like in close to real time. 

## Language domain

As mentioned in the motivation, the domain of this language is movement transcription. This domain is useful as it helps 
both choreographers and dancers to easily record movement and revisit that movement. It is also useful to other performers 
and non-dancers as a way to understand how we move and interact with the world. 

As far as I can tell, there are few other DSL's for this domain. One of the best known ones is DanceForms (see
http://www.charactermotion.com/), which was often used by modern choreographer Merce Cunningham. However, as far as I can 
tell, this software is not well maintained. Another popular movement software is Isadora (see http://troikatronix.com/). 
However, it looks like this is more for use in projections with dance rather than simulation of dance movement. There 
seem to be quite a few straight Labanotation softwares, but they don't seem to deal with the problem of Labanotation's 
complexity. 

## Overview of Language design

The design of this language is to take an exisiting syntax and put it into a more comprehensible form, both for the sake of 
writing and reading. A program in this language is a sequence of movements, or a phrase. Movements are defined by their vertical, 
lateral, and sagittal position in addition to the time duration and are specified using a web-based GUI. When a program runs, these 
inputs are translated in a list of lists, which can then be exported to create the representation in Labanotation. Given time 
constraints, I did not get to actually connecting to the 3D visualization. However, I replaced that with just generating the Labanotation
instead. The only area in which the user might have an error is if they input an impossible time. As such, there are messages that 
let the user know that the time entered is invalid. 

### GUI Design

The GUI is set up so that each movement the user creates is seperated by a green box. To add new movements, the user clicks the "Add
a Movement" button. To generate a phrase, the user clicks the "Create Phrase" to generate the output. In each box, the user can specify the vertical, lateral, and sagittal positioning along with the time duration. The positioning is specified through dropdown menus, and the user manually types the time in.

The GUI is set up so there are few ways for a user to input an invalid phrase. There is always at least one box to input a movement on the screen. The dropdown menus only have feasible options, so the user cannot input an impossible positioning. Retrospectively, it would have been better to make it so that it was harder to input an incorrect time. 


