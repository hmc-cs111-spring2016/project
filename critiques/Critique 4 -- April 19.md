#### Saving history and undoing changes:
- Make sure to check for array out of bounds to avoid the error that came up in class.
- I'd change 'record' to a less confusing name, like 'history.'  'Record' makes it sound like you're letting users record audio from their computer mics.   
- Instead of having a function that users call to start recording history, why not always have the option of checking history?  So, at any point in my editing I can call 'history' to see all previous changes (up to a certain point, maybe 10 steps back), or 'history 4' to see only the past four changes I've made.  Kind of like browser history - automatically recorded for you to check whenever you want.  This shouldn't shake things up too much in the back-end, as you already have the history recorded (this is basically your array of previous sound files that the "undo" function uses).
- In class I said that allowing users to undo the last command is pretty standard and all you need.  However, if you're going to implement the above suggestion (history), then giving the users the ability to undo to a specific point in history could actually make sense.  Imagine this use case:
	- User is unhappy with how the sound file he's working on is turning out.  He calls "jbieber.wav history 4" to see the past 4 changes he's made.  The output might look something like this: 
		- bieberClip history 4
		- (1) volume +4
		- (2) reverb 3
		- (3) volume +2
		- (4) pitch +1
	- The user thinks he might want to revert back 3 steps.  Before he does so, he calls "play bieberClip 3" (maybe "play bieberClip -3," or something else?) to hear how it sounds.  That would play what the clip sounded like 3 changes ago.  He likes it, so he calls "undo bieberClip 3," or maybe "bieberClip revert 3."
- I think the above ^ would be a cool languagey feature to implement, and would set your language apart from other similar DSLs while taking advantage of your REPL interface.  However, it would introduce some potentially difficult challenges, such as the following:
	- How do you deal with history involving concatenations, or other functions that require multiple sound files?
	- When you reverting multiple steps back, what does the "history" for that change look like?  In the example above, each step had the function that was called next to it (e.g. "volume +4"), but how do you represent all the functions that you just undid in one step?

#### Misc

- Pitch adjustment would be better with half-step as unit.
- For your sample parse: 'action' -> 'effect' -> 'value' (e.g. + volume 3)
	- I would change "action" to something that better describes either a '+' or '-'.
	- "volume + 3" or "volume +3" seems more natural imo (you can use [ \t\r]* for optional white spaces).

- For your 'play' method hangup: Try calling pydub's "play" method from a new thread (in your backend).  This might let the REPL free up, since the code it's directly calling (the code in your backend) will complete almost immediately.

#### Specific Questions:
- In terms of the REPL interface, the main things I'd like are what I've described above - ability to always check history (and possibly undo to specific points) - and the ability to load + work with multiple sound files at once.
- We discussed your parsing model in class with Aaron, so hopefully you're on track with that.  It makes sense that your parser is very specific now, since you're only supporting a few specific commands.  As your language grows your parser should naturally follow.  For example, your current "function" rule will probably become just a specific family of functions.  Then you have a new "function" rule that points to it, or to other function types.
