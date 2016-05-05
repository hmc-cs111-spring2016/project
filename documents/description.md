# Project description

## Motivation

*Why is this project useful or interesting, what problem are you trying to address, and why is a DSL an appropriate solution?*

Given how popular electronic music has become, I imagine that several fans probably have a few of their own novel ideas, but no outlet for them. I know personally that most existing audio mixing software is not only very pricey, but also extremely complicated. Simply learning how to use the basics requires several hour long tutorials on Youtube. This project plans to help amateurs who simply want some exposure to audio mixing without the time or capital investments. If designed effectively, audio mixing can be made more user-friendly. Most audio mixing software emulates a DJ console (the thing people use with a lot of knobs and dials and what not). I felt audo mixing can be more effectively done through a well-designed language because ideally it will allow users to translate something they understand in nature language (increase volume, distort by x degree) and apply it to a particular piece of music. The idea is to make the learning curve necessary to edit music less intimidating. 

## Language domain

*What is the domain that this language addresses, and why is the domain useful? Who will benefit from this language? Are there any other DSLs for this domain? If so, what are they, and how might they influence your language design and implementation?*

The domain addressed by this language is anyone who wants to edit or remix music but lack the necessary tools and experience. This project is mostly targetted at people who have a general interest in audio mixing, but have neither the background nor the expensive software typically used (e.g. Ableton). Additionally, for the less serious music enthusiast, this DSL would be a significantly lighter weight and cheaper alternative to currently existing music software. This DSL would help users learn more about mixing music by providing them basic and easy to understand tools to manipualte sounds as well as allowing them to develop their own tools if necessary. For instance, some built-in features of the language might be basic volume adjustments at specified intervals or allowing for a certain segment of a sound to be looped.

The first notable/complete DSL that comes to mind in this domain would probably be [Audacity](http://www.audacityteam.org/) in that it allows users to edit music relatively easily. However, one key distinction is that this project will be mostly focused on the langauge design and less on the gui interface. With effective langauge design, I hope to take key features from Audacity and make them even easier to understand and to be applied to music through this project. As aforementioned, audio effects can also be taken from Ableton, [VirtualDJ](http://www.virtualdj.com/), among other existing music mixing software.

## Language design

*If you had to capture your DSL's design in one sentence, what would it be? What constitutes a program in your language? What happens when a program runs? What kinds of input might a program take, and what kinds of output might it produce? Are there data or control structures that you know will be useful? What kinds of things might go wrong in a program in this domain (e.g., syntax errors, compile-time errors, run-time errors)? How might you design your language to prevent such errors or to clearly communicate the results of errors to the user?*

My DSL's design in one sentence would be: "Do anything from editing a sound file for a school project to creating a new mixtape with just a few keystrokes." A program in my language would be a sequence of commands defining some change to the audio. When a program runs, these commands are interpreted and applied to a specified sound file. At the moment, I'm uncertain what data or control structures would be useful. I intend to create this DSL in Python and would probably use pyparsing, a Python package that makes parsing significantly easier. A few things that might go wrong in a program in this domain may be attempting to change a portion of an audio file that does not exist, attempting to apply commands that do not yet exist or are not defined, among others. To avoid such errors, I hope to implement some error-handling and potentially even Sublime text coloring that will make writing a program much easier and allow users to know specifically what went wrong.

## Example computations

*Describe some example computations in your DSL. These computations should describe what happens when a specific program in your DSL executes. Note that you shouldn't describe the syntax of the program. Rather you should describe some canonical examples that help someone else understand the kinds of things that your DSL will eventually be able to do.*

We can simulate how a user might use this project: Suppose our user listens to a song and thinks it would be even better if the bass was louder at specific portions of the song. They write a series of commands, specifying the time stamps at which they want the bass increased in a script-like environment. After they complete writing, they compile the commands and a resulting mp3 with the bass adjustment changes is available to them. 
