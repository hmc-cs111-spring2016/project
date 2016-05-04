## py-DJ
### Introduction

Given how popular electronic music has become, several fans probably have a few of their own novel ideas, but no outlet for them. Most existing [audio](https://www.ableton.com/en/shop/) [mixing](https://itunes.apple.com/us/app/logic-pro-x/id634148309?mt=12) software is not only very pricey, but also extremely complicated. Even open-sourced [alternatives](http://www.audacityteam.org/) that are significantly cheaper remain are no easier to use than their expensive counterparts. It is important to note that these existing pieces of software are extremely powerful. However, as a result, they have also become increasingly unintuitive and genuinely intimidating for first-time users. Simply learning how to perform the most basic of tasks such as adjusting volume or splitting files can take anywhere from 25 minutes to as long as 2 hours. 

This language hopes to help amateurs who simply want some exposure to audio mixing without the time or capital investments. Most audio mixing softwares aim to emulate all the functionality associated with a DJ console, including allowing for most effect units and raw manipulation of the sound files. To facilitate this emulation, most audio editting software has developed a GUI, which, to most, seems like a natural decision. A GUI provides visualization of abstract concepts, responsiveness, and, in theory, ease of use. However, to me, I believe there is a fine line separating the complexity of features and ease of use that has long since been crossed. Although a GUI still provides visualization and responsiveness, I would argue that the user interface could be more effectively designed to accomodate both amateur and professional audio editors. In particular, I felt audio mixing could be more effectively done through a well-designed language and an entirely text-based interface. Through effective language design, a text-based interface could provide responsivness and a sense of "visualization" while maintaining the "easy to learn" aspect made possible because of the simple interface. The idea is to make the learning curve necessary to edit music significantly less intimidating.

### Currently
py-DJ is a free domain-specific language for editting audio files on the fly in a way that is intuitive and easy to learn, with the eventual goal of making even professional audio-mixing more user friendly. Currently, py-DJ is an entirely text-based command-line interface. py-DJ's interface is significantly simpler and more lightweight in comparison to existing audio editting softwares. While there may be concerns with visualization, responsiveness, and loss of detail resulting from scaling a GUI down to a text-based interface, it is important to keep in mind that py-DJ was developed with the user experience as a priority. In addition to a responsive and user-friendly interface, py-DJ offers support for features such as volume adjustment, pitch adjustment, splitting files, concatenation of files, overlaying of files, among others, and will be later expanded to be more feature-rich.

## Language Design Details 
Give a high-level overview of your language's design. Be sure to answer the following questions:

How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?
How does the syntax of your language help users write programmers more easily than the syntax of a general-purpose language?
What is the basic computation that your language performs (i.e., what is the computational model)?
What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?
What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?
What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?
Error handling: How can programs go wrong, and how does your language communicate those errors to the user?
What tool support (e.g., error-checking, development environments) does your project provide?
Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?
Note: Your language design overview can serve as a good first draft of the language design details section.

Example program(s): Provide one or more examples that give the casual reader a good sense of your language. Include inputs and outputs. Think of this section as "Tutorial By Example". You might combine this section with the previous one, i.e., use examples to help describe your language.

Language implementation: Describe your implementation. In particular, answer the following questions:

What host language did you use (i.e., in what language did you implement your DSL)? Why did you choose this host language (i.e., why is it well-suited for your language design)?
Is yours an external or an internal DSL (or some combination thereof)? Why is that the right design?
Provide an overview of the architecture of your language: front, middle, and back-end, along with any technologies used to implement these components.
"Parsing": How does your DSL take a user program and turn it into something that can be executed? How do the data and control structures of your DSL connect to the underlying semantic model?
Intermediate representation: What data structure(s) in the host language do you use to represent a program in your DSL?
Execution: How did you implement the computational model? Describe the structure of your code and any special programming techniques you used to implement your language. In particular, how do the semantics of your host language differ from the semantics of your DSL?
Note: Your language implementation overview can serve as a good first draft of language implementation section.

Evaluation: Provide some analysis of the work you did. In particular:

How "DSL-y" is your language? How close or far away is it from a general- purpose language?
What works well in your language? What are you particularly pleased with?
What could be improved? For example, how could the user's experience be better? How might your implementation be simpler or more cohesive? Are there more features you'd like to have? Does your current implementation differ from your larger vision for the language?
Re-visit your evaluation plan from the beginning of the project. Which tools have you used to evaluate the quality of your design? What have you learned from these evaluations? Have you made any significant changes as a result of these tools, the critiques, or user tests?
Where did you run into trouble and why? For example, did you come up with some syntax that you found difficult to implement, given your host language choice? Did you want to support multiple features, but you had trouble getting them to play well together?
If you worked as a pair, describe how you have divided your labor and whether that division has worked well.
