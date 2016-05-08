# Project plan

## Language evaluation
I'll know that my language has accomplished its goals when it is possible to get from it any output that is even possible using the back end I have in mind. It should also take less time to take already-existing tab and re-write it in this language than it takes to re-write it in a graphical tab editor. Ideally, it should require fewer characters in this language to invoke certain behaviors (e.g. tripleted notes) than the back end requires.

## Implementation plan
I've already found a fully-functioning back end, so I should spend my entire time working on a parser and plugging that in to the back end. As such I think I'll spend as long as I feel is necessary on language design, which will probably include some tweaking to make it play nice with the back end. If I meet all my goals as stated in the language evaluation section above and still have time to spare, I'll try adding an additional type of output that the user can request when a
program runs. This output would be in a format readable by graphical tab editors. Then, if a user checks some tab, finds that it isn't very good, and wants to fix it in a tab editor, they can just run their program again with different output, and load that output straight into the editor. This will save users from having to enter the tab a second time, since at that point it would be faster to have entered it in the editor to begin with, no matter how much faster using my DSL might end
up being.

## Contingency plan
I think that the biggest problem I could encounter would be if I design a syntax altogether different than that of the back end, the parser for which might then require a _lot_ of work. In the worst case, I'll consider modifying my syntax. More likely, I'll need to add some kind of intermediate represenatation to make it easier to go from my syntax to the back end. Since I don't have to write the back end at all, there shouldn't be much in the way of technical difficulties.

## Teamwork plan

*If applicable*
