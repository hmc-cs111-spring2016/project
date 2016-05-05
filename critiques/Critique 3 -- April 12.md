## General Feedback

Overall, I think you're definitely on the right track with this. Since your language is an internal DSL, you don't have to worry about any of the parsing/AST/Evaluation stuff, so the testing your language with OpenCL seems to be the last step. For this reason, I feel like the fact that you've had trouble with OpenCL is totally fine - you have plenty of time to get this working. With that said, I do think there are some design decisions that will come up (a lot of which you've already addressed this week - nice!).

Other than that though, How do you plan to test your language? Right now, it looks like, from my limited understanding of your code base (my c++ skills are realllllly rusty) you're just testing your functions to make sure they run, and produce the right results. Once you get OpenCL working, how are you going to benchmark your code to make sure its actually getting good performance?  

## Question "Answers"

From what I've seen, your implementation makes sense. The enum vs string thing seems pretty minor, its somewhat awkward, but enums look nicer than strings in some cases, so I think its fine. Unfortunately though, like I said, my knowledge of the low-level c++ mechanics that you have to deal with is really minimal. I don't have any idea how to answer most of these questions. 

One last thing. In terms of the question of forcing people to cast vectors to the types they want, I think that could be fine. I'd like to know what kinds of syntaxes exist for doing this, if there are any besides the default type cast syntax. If there's one that's reasonably well known, and not terrible to write, having that be the standard for doing your operations would be fine. 