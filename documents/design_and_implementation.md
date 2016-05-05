# Language design and implementation overview

## Language design
*How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?*

The user will write programs in my language by writing a series of concise commands. One stretch-goal I intend to have for this DSL is allowing users to "extend" the language in a sense by defining their own effects using some combination of existing effects in the DSL. So in regards to this, it will involve more than just writing a series of concise commands. 

*What is the basic computation that your language performs (i.e., what is the computational model)?*

The user will write a program describing desired changes to an audio file. The language will interpret the program and will then apply such changes to the audio file. 

*What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?*

As per Prof. Ben's suggestions, each audio effect will be its own object, and these objects will comprise most of the data structures in the DSL. By making each of the audio effects their own objects, it will hopefully allow users later on to create their own effects by leveraging these existing objects and manipulating them in some combination, although this is a bit of a stretch goal. Aside from this feature, I feel like this DSL will be primarily data intepretation such that the user does not necessarily have to create or manipulate data so much as write a series of commands that the parser and interprer can then apply. 

*What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?*

Again, as aforementioned, given that this DSL will probably end up being mostly data interpretation, I don't think there will be a lot of control structures in my DSL. Even with the extending the effects feature mentioned, I don't imagine the user needing things like condition control statements, as it will be essentially grouping a series of specific audio effects together under one name. 

*What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?*

A program in my DSL requires a specific audio file to be editted. Any desired effects can be described, but are not "required" for the program (though it would be quite pointless to just load an audio file and do nothing to it). The program is expected to output an editted audio file with the desired audio effects in place.

*Error handling: How can programs go wrong, and how does your language communicate those errors to the user?*
*What tool support (e.g., error-checking, development environments) does your project provide?*

There are several ways that programs can go wrong. For instances, several problems could be encountered if an audio file is not loaded prior to writing audio edit commands. Or perhaps a required field in a command is left blank (e.g. the decibel increase in volume is left out). Additionally, because I intend to use natural language, users might attempt to use language that is not defined but is similar in meaning (e.g. using "raise" instead of "increase" as a key word), which is a common drawback when using natural language in implementing a language. Hopefully, these concerns can be addressed by some error class that will produce an error message telling the user what part of their input was invalid and why it may have failed either in parsing or in interpretation. For instance, if the user types a command that the parser does not expect, the error class associated with the parser will be able to return the line number where the invalid input was. I don't intend to provide a development environment.

*Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?*

I think the closest DSL for this domain would be [Audacity](http://www.audacityteam.org/). Audacity is free, open-source, cross-platform software for recording and editing sounds. The first and most distinct difference between my DSL and Audacity is that my DSL focuses entirely on editing sounds and does nothing with regards to recording. My DSL is also entirely language based while Audacity has a GUI, which has both its merits and drawbacks. As described in my project description, not having a GUI a) better aligns with the intentions of this project and b) can be simpler to express desired changes. A GUI can be nicer in specific instances, such as when working with specific time intervals as it is often difficult to work solely off of time stamps as is the case with my DSL.

I think the most effective way to illustrate the differences in terms of the type of editing that my DSL will provide compared to Audacity is that my DSL is a high-level description of edits while Audacity is an extremely-low level description. In Audacity, to apply an audio effect like increasing volume involves increasing the decibels of a certain portion of the song. This might not be immediately obvious to a novice trying to edit music. In my DSL, to increase volume would involve simply writing a command like:

> "+ volume 30"

which to me, seems far more intuitive. This gap in complexity only further widens as we begin considering increasingly more complex audio effects. 

## Language implementation
*Your choice of an internal vs. external implementation and how and why you made that choice.*

I decided on an external implementation as it seemed like a better choice for achieiving the desired natural-language syntax. Additionally, I'm not familiar with any language available specifically for editing music. As the piconot lab demonstrated, an internal DSL seems to be very inflexibile in terms of syntactical design choices. I believe most groups ended up with a very Lisp-like program, as parantheses were unavoidable. By using a parser, I can  more effectively implement the syntax I desigend rather than settling for something that looks similar, but is not exactly the same. However, at the same time there are the drawbacks associated with not having the functionality of a general-purpose language to fall back on, as is the case for an internal implementation. This can be addressed with more thorough planning and design, as well as a good understanding of the domain (which is more or less the purpose for this week in terms of my project plans!). 

*Your choice of a host language and how and why you made that choice.*

I chose Python as a host language as it is a popular language that I am personally familiar with, and has perhaps the most extensive libraries available for audio manipulation. Additionally, because Python is a high level language, I hope to be able to develop quickly and efficiently, while trying out several ideas without the fear of sinking too much time in the case where these ideas end up not working/being necessary. Although I have experience in developing software in Python, I have no experience doing parsing or using any of the audio manipulation libraries. Because Python is so popular, the resources available will hopefully help faciliate the learning process very quickly. At the same time, I think it'll still be an interesting and challenging experience.

A library I intend to use to supplement the back-end with is [pydub](https://github.com/jiaaro/pydub), which is a simple and easy high level interface to manipulate audio, add effects, and slicing/concatenating audio files. Similarly, Python also has what appears to be a rather easy to use parser called [pyparsing](http://pythonhosted.org/pyparsing/). 

*Any significant syntax design decisions you've made and the reasons for those decisions.*

Perhaps the most significant syntax design decision I've made thus far is using a natural-language-esque design to faciliate ease of use. As of now, the syntax is extremely simple and the natural-language aspects aren't extremely apparent. The tools that I intend to focus on for the prototype are volume adjustment and pitch adjustment, both of which are applied to the entirety of the audio file. I have decided to step away from time-specific edits for now as I feel the language can be easily extended to address it later on if time allows (e.g. increasing volume between 2:30 and 3:30). The syntax for volume and pitch are:

> +/- volume (1 - 100)

> +/- pitch (1 - 100)

However, later on, the more complicated types of [effects units](https://en.wikipedia.org/wiki/Effects_unit#Types) are implemented, such as distortion, phaser effects, chorus effects, or vibrato effects, cannot be accurately described simply with a +/- and a range of 1 to 100. They will necessitate further descriptions given their complexity, and it is then that the natural-language syntax will be made apparent. 

*An overview of the architecture of your system.*

The architecture is hopefully laid out to be extensible and modular. It seems the architecture will be subject to change as development occurs, but as of now, this is how I imagine the major components of the DSL: The first and perhaps most important component will be the parser. The parser will define the grammar and methods to parse individual commands the user writes. Any natural langauge additions will be incoporated in the parser. This parser will also have an error class to handle potential errors. There will be some intermediate representation of the DSL which will tie together the parser and the semantics. The semantics will contain most of the audio effects. The intermediate representation will then be passed to some interpreter which will apply the final changes to the audio file. 
