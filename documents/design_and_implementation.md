# Language design and implementation overview

## Language design

Users will interact with my language in the same way as ContextFree: writing a program, executing it, and getting a file out of it. The computational model has not changed: it keeps track of how different shapes are drawn in terms of other shapes and basic shapes and different modifications to those shapes, and draws them using python's PIL library. 

Data structures will likely work very differently from ContextFree. This is because specifying a static shape in ContextFree is different from specifying an animation. One of the next things I will need to design is a way for the user to create an animation. This will likely involve specifying either individual frames, or specifying an initial frame and then how it changes from one frame to the next, or some combination thereof. There will likely be a section within a well-written program devoted to defining these basic animations, and then another section devoted to higher-level rules that put copies of these animations together with additional modifications.

Control flow is simple: it's designated entirely by the user's rule specifications. The program is generative so control flow simply follows the rules that the user specifies by invoking them when appropriate. This is essentially exactly the same as ContextFree.

There won't be any input other than the program itself. However, there will also be a section in any program file for "constants" like the total number of frames it should generate, what kind of file to output, other details of the animation like framerate, etc. The program's output will be some video file, or a series of frames (I plan to give an option for simply generating the frames without converting to a video file so that the user can do this on their own if desired). 

Error handling is a bit tricky right now because I'm not totally sure how the recursive rules will work yet. There will likely be some errors pertaining to those, particularly if I allow the user to do it ContextFree-style where the program detects when images become too small. There may also be an error for images that are too large (which ContextFree also has, but mostly because it dynamically resizes the canvas). 

There will be syntax errors, of course, about which I will try to give as much information as possible. But the only really language-unique error I can think of is when recursively defined rules go wrong in one way or another, and these ways should all fall under the umbrella of infinite recursion. It is also certainly possible to define a program that just takes an absurd amount of time to execute, so I will need to provide some way to see how far along in the generation it is (probably something saying "frame x is being rendered" or "rules are being called", etc.) 

I don't think there are any particular tools I'm planning to support although I'd hope I have decent built-in error checking.

I hope this gives a good sense of how this compares to ContextFree - it will be simpler and clunkier in many ways, and it will probably lack a lot of features. But the additional feature of animation will expand it significantly. I don't know of any other similar DSLs.

## Language implementation

I actually already have it working as an internal DSL, essentially - I want an external DSL to get the same user-friendliness that I felt ContextFree had. I don't think I could get a sufficiently nice syntax as an internal DSL in Python. I chose Python really just because Prof. Ben said that Scala's image generation wasn't nice. I was willing to do it in either Python or Scala, looked up a Python image generation package, and got it working well. It was easy enough to work with, so I was happy with Python at that point. It does everything I need it to do and it's a good language for quickly writing early versions of a project like this.

I have not yet designed the syntax - that's my next step. Architecturally, the program looks like this:

At the top level is an image generator, which as a dictionary of rules (a dictionary by name so that rules can reference other rules). When a program is run, that image generator looks through the rule dictionary and executes the rules. When a rule is executed, it creates some number of elements and executes some number of other rules, each possibly with modifications. An element is essentially an atomic animation; it's something like a single rotating square. Each frame has to be a shape object (currently shapes are either rectangles or ellipses, but the code is modular so I could add other things like lines). Once all rules have been run and the canvas has all of the elements on it, it progresses through the frames of the animation, drawing them one at a time. Eventually the program will then have a final step that pastes these frames together into an animation with ffmpeg. 