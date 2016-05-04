# py-DJ
## Introduction

Given how popular electronic music has become, several fans probably have a few of their own novel ideas, but no outlet for them. Most existing [audio](https://www.ableton.com/en/shop/) [mixing](https://itunes.apple.com/us/app/logic-pro-x/id634148309?mt=12) software is not only very pricey, but also extremely complicated. Even open-sourced [alternatives](http://www.audacityteam.org/) that are significantly cheaper remain are no easier to use than their expensive counterparts. It is important to note that these existing pieces of software are extremely powerful. However, as a result, they have also become increasingly unintuitive and genuinely intimidating for first-time users. Simply learning how to perform the most basic of tasks such as adjusting volume or splitting files can take anywhere from 25 minutes to as long as 2 hours. 

This language hopes to help amateurs who simply want some exposure to audio mixing without the time or capital investments. Most audio mixing softwares aim to emulate all the functionality associated with a DJ console, including allowing for most effect units and raw manipulation of the sound files. To facilitate this emulation, most audio editing software has developed a GUI, which, to most, seems like a natural decision. A GUI provides visualization of abstract concepts, responsiveness, and, in theory, ease of use. However, to me, I believe there is a fine line separating the complexity of features and ease of use that has long since been crossed. Although a GUI still provides visualization and responsiveness, I would argue that the user interface could be more effectively designed to accommodate both amateur and professional audio editors. In particular, I felt audio mixing could be more effectively done through a well-designed language and an entirely text-based interface. Although an unconventional approach, through effective language design, a text-based interface could provide responsiveness and a sense of "visualization" while maintaining the "easy to learn" aspect made possible because of the simple interface. The idea is to make the learning curve necessary to edit music significantly less intimidating.

### Currently
py-DJ is a free domain-specific language for editing audio files on the fly in a way that is intuitive and easy to learn, with the eventual goal of making even professional audio-mixing more user friendly. Currently, py-DJ is an entirely text-based command-line interface. py-DJ's interface is significantly simpler and more lightweight in comparison to existing audio editing softwares. While there may be concerns with visualization, responsiveness, and loss of detail resulting from scaling a GUI down to a text-based interface, it is important to keep in mind that py-DJ was developed with the user experience as a priority. In addition to a responsive and user-friendly interface, py-DJ offers support for features such as volume adjustment, pitch adjustment, splitting files, concatenation of files, overlaying of files, among others, and will be later expanded to be more feature-rich.

## Language Design Details 
The philosophies behind py-DJ's language design are:

1. To be accessible to all, not just those well-versed in editing audio, or only those new to audio editing
2. To be both clear and concise with the syntax design

Reflecting the first philosophy, py-DJ's design is almost entirely motivated by the user experience. As such, although it may not be as feature rich as similar existing audio editing software, it provides the necessary foundation with its rich user interface for several more features to be developed in the future. Given the unconventional interface, it is difficult to achieve the first philosophy without achieving the second. Because py-DJ is a command-line interface, having verbose and vague syntax design not only further alienates users who are already uncomfortable with audio editing, but also users unfamiliar with command-line. Concise and clear syntax makes the language significantly easier to learn, and falls in line with the "simple interface" theme, thus also further enhancing the user experience.

Users write "programs" in py-DJ through a series of single expressions that indicate some audio effect change in the py-DJ command line interface. The expressions that users can provide are broadly categorized as either an *effects expression* or a *interface expression*. 

* Effects expression are directly related to modifying audio files. Users provide an effects expression that corresponds to some implemented feature in py-DJ to apply some effect to the current audio file being edited. There is some flexibility for describing effects. For instance, if the user wants to increase the volume, the user types "+ volume 10" to increase the volume of the current audio file by 10 decibels, or, alternatively, "* volume 3" to increase the volume by a factor of 3. All effects expressions are summarized in py-DJ when the user types ` help ` or `?`. More detailed explanations are provided when the user types ` help <effects expression>`.
* Interface expressions are related to interacting with the interface. Users provide an interface expression that corresponds to some interface feature. `help` and `?` are examples of interface expressions. These interface expressions help make py-DJ easier to use, and effectively supplement the visualization provided by the GUI. For instance, users can type `history` to bring up a well-formatted list of recent edits starting from the most recent, `files` to bring up a well-formatted list of audio files in the work space, among others.

Users effectively build up their programs incrementally, applying a wide variety of edits to a variety of songs in their workspace, made possible with a REPL interface. A REPL interface, also known as interactive toplevel, is a simple interactive computer programming environment that takes single user inputs, evaluates them, and returns the result to the user. The REPL interface allows for extreme responsiveness, as users are able to receive feedback almost immediately once their input is parsed, interpreted, and applied. As such, py-DJ naturally has very effective and immediate error-handling which will be discussed in more detail later. To illustrate the REPL interface, effects expressions, interface expressions, and to generally showcase py-DJ, an example "program" is provided below. The example program walks through all effects expressions and interface expressions and attempts to emulate as closely as possible how a user might use py-DJ.

As an aside, I hesitate to use the word "program" to describe the user interactions with py-DJ as one of the primary goals of this DSL is to abstract as much programming out of the program. 

### Example Program
![alt-text](https://github.com/williumchen/project/blob/master/documents/REPL1.png)
![alt-text](https://github.com/williumchen/project/blob/master/documents/REPL2.png)
![alt-text](https://github.com/williumchen/project/blob/master/documents/REPL3.png)

The syntax of my language helps users write programs more easily than the syntax of a general-purpose language simply because of the limited scope. Anything beyond the scope of the domain of py-DJ, which is audio editing, is impossible. The only thing users can do in py-DJ is define expressions related to audio editing or the user interface. Additionally, py-DJ lacks traditional control-flow structures as well as explicit data structures because, for the scope and time frame of this particular project, they were not absolutely essential. In an abstract sense, the audio files themselves could potentially be considered internal data structures (although they are not implemented as such), as they essentially store all the edits the user provides. More generally, the syntax was designed to be as minimalistic as possible: as aforementioned, the syntax was designed to be concise and clear. However, it's not always easy to be concise and clear, and a lot of thought was placed in syntax design. Even then, planning alone ultimately was not enough, and receiving actual user feedback turned out to be necessary. To summarize a few reconsiderations in syntax design:

* I abandoned the idea of syntax revolving around natural language because I felt it was neither concise nor clear. While the use of natural language was tempting because it seemed as if it would attract non-programmers, the associated verboseness and ambiguity was simply not worth it. This isn't to say natural language has no place in py-DJ. Several of the keywords in the syntax are natural langauge-esque. For instance, splitting the current audio file is done by typing `cut 0:03 to 0:10`.
*  Does `file1 + file2` suggest overlaying the two audio files, or concatenating? In a blind user test, where users were given no context, all said it suggested overlaying, which was contrary to how I had implemented it at first.
*  Ordering of `history`. I originally had `history` sorted by oldest edit first, as this was how the actual history of edits were stored in the back-end. However, user feedback and critiques suggested it be ordered by most recent first.
*  `revert 3`. Should the integer simply `undo` the 3 most recent edits, or fetch the third state in `history`. Again, user feedback suggested semantically, `revert` seemed as if it fetches an older state. I later allowed `undo` to also optionally take in an integer.

In terms of language design, I had originally planned to parse a text file with all effects expressions, interpret the parsed result, and apply the results to an audio file. In theory, this seemed like a great idea, and I surprisingly received positive feedback. However, in practice, this was less than ideal for the users. Although easier to implement than the REPL interface, this version of the language was extremely unresponsive, forced the user to complete all edits before listening to them, and delayed all errors until edits were complete.

As the above hopefully demonstrates, I ultimately ended up dedicating significantly more time on user interface and language/syntax design than expected. 

What is the basic computation that your language performs (i.e., what is the computational model)?

What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?

What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?

What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?
Error handling: How can programs go wrong, and how does your language communicate those errors to the user?

What tool support (e.g., error-checking, development environments) does your project provide?

Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?


## Language Implementation
Describe your implementation. In particular, answer the following questions:

What host language did you use (i.e., in what language did you implement your DSL)? Why did you choose this host language (i.e., why is it well-suited for your language design)?
Is yours an external or an internal DSL (or some combination thereof)? Why is that the right design?
Provide an overview of the architecture of your language: front, middle, and back-end, along with any technologies used to implement these components.
"Parsing": How does your DSL take a user program and turn it into something that can be executed? How do the data and control structures of your DSL connect to the underlying semantic model?
Intermediate representation: What data structure(s) in the host language do you use to represent a program in your DSL?
Execution: How did you implement the computational model? Describe the structure of your code and any special programming techniques you used to implement your language. In particular, how do the semantics of your host language differ from the semantics of your DSL?
Note: Your language implementation overview can serve as a good first draft of language implementation section.

## Evaluation
Provide some analysis of the work you did. In particular:

How "DSL-y" is your language? How close or far away is it from a general- purpose language?

What works well in your language? What are you particularly pleased with?

What could be improved? For example, how could the user's experience be better? How might your implementation be simpler or more cohesive? Are there more features you'd like to have? 

Does your current implementation differ from your larger vision for the language?

Re-visit your evaluation plan from the beginning of the project. Which tools have you used to evaluate the quality of your design? What have you learned from these evaluations? Have you made any significant changes as a result of these tools, the critiques, or user tests?

Where did you run into trouble and why? For example, did you come up with some syntax that you found difficult to implement, given your host language choice? Did you want to support multiple features, but you had trouble getting them to play well together?

