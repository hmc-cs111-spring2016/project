First: answering the questions from the project notebook.

- Syntax: I'm not sure how much your ideas have changed from Monday's critique meeting, but I think your three-tiered approach to syntax is good. I think this actually has the potential of knocking out two different problems by allowing users to specify the "recursion depth" of animations, but having some sort of default value provided they don't specify the depth. This could also be helpful in allowing users to specify different recursive depths for different animations?

- I think the metadata issue is best solved wtih the templated file as you mention. The templated file idea is also nice because it can show the user all the different "fixed inputs" that the user can specify, such as the canvas size and start shape, that they may not know they can control. This becomes more and more useful as the number of features in the program grow. Be careful that this template file doesn't have *too* much in it, though, as that can become a headache for your users.

- Can't think of any features in the IR that are *important* per se. I think it's more of a question of how much functionality you want to add, as opposed to keeping it simple/easy to use. I think the current form, insofar as the code I saw on Monday, is probably a pretty good balance of that.

- I think that if you *do* end up giving users the options to use both translate and rtranslate, then it's important to have better syntax to differentiate them. You sort of mention this in your notebook, but the two are pretty similar and should have some sort of obvious syntactical differentiation to help the user. That said, I think having just rtranslate as an option would probably suffice.

- It's hard to give too much feedback on refactoring for your IR. However, keeping extensibility in mind may help you with that. For example, modularizing and moving things into classes as we talked about on Monday; if you do this for a specific feature in the IR, what do you gain? How much of a difference does it make going forward? If it's substantial, then thinking about moving things into classes is probably worth the time. Also it's not the worst thing if your IR is kind of ugly (as nobody has to look at it really).

On a general level, I think that your project is in a superb place now, and you have a really good idea of what you want to have happen and how you want to represent it. With that in mind, here are some *general* comments to keep in mind going forward.
	1. Usability/Functionality. This seems like a project to which you can endlessly add functionality and added features. However, is it important to you to have the tool be easy to use? If yes, how do you limit the language functionality to keep the language simple? If no, why not? Are you happy to have a steep learning curve for your language?

	2. Syntax questions. This seems like the next step in creating a prototype (which is this week's task). I know the temptation is there to just continue to work on functionality, especially because this is a project that you seem very exctied about. For this week, I think it's important to answer: how much language functionality will exist in your prototype? How will you implement it?

	3. Metadata settings. How much of these are currently changeable by the user? For example, is there a default canvas size? Can the user change it? If so, how?

I think the main point from all my comments is: keep your user in mind. I'm really excited by the direction the project is taking, and what has already been accomplished. It would be good, in my opinion, to see how you intend to have the user interact with the IR you've already put in place.