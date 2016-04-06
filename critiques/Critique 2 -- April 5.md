## Stuff in design\_and\_implementation.md

One thing I definitely want to caution you about here is your error handling method. Prof. Ben told me when I was describing my thoughts about my own error handling that things like actually interpreting the value of numbers should _not_ happen during the parse. I would suggest you consider that "10-8" case to be a semantic, not syntactic, error, and I guess if I understand what Prof. Ben said you shouldn't handle the error until you start trying to generate. I'm not actually
sure if it makes a difference in terms of functionality, but it's probably stylistically better if nothing else.

## Stuff in notebook
We already talked about some of this in class, so I'm not going to go hugely into detail for those parts. With regard to the note-length issue, like I said in class you _could_ just have too-long notes run into the next measure by splitting them up and tying the two notes together. I know you said that you didn't want to do it that way, since then it could leave the user with note types that they specifically asked the program not to include. My thinking on that, though, is that most
users probably care more about the literal duration of the notes than the way they're written; certainly if I asked for no quarter notes in 3/4 time but half notes and, say, eighth notes are allowed, I can't imagine I'd care if it had two "half notes" in a row where one of them was really two quarter notes tied together. I would ask some potential users before you discount that option altogether. If you find that people really do care about that, then I think you just
won't always be able to satisfy all the constraints perfectly.

The "Global Staff" doesn't really make too much sense to me, at least if you call it that. Maybe you could call it a "Template Staff" or a "Default Staff." Probably, though, you can put any settings that you want to apply to all staves in the score environment. There isn't any reason I can think of that you _can't_ put those items in the score environment, anyway.
