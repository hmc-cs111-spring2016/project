# Critique for April 19

It seems like a theme of this week was people not making much progress on this project due to clinc. I know I am in the same boat. However, I do think that you've made a lot of simplifying assumptions, and have a plan for going forward, so I think (as long as you can put in a good amount of work this week) you'll be ok.

## Questions from Last Week

I like your plan to simplify function calls and returning values in expressions. This was a point of worry last week, as it seemed like your grammar was very complex. I'm happy to see it getting simpler. I think in general simplifying assumptions here are very good, as long as they don't contradict your design philosophy.

In terms of error checking in the interpreter, I think you don't necessarily need to go overboard with this. Just making sure you produce good error messages instead of the default parser error messages should be fine.

Other than that, I think your feedback from last week seemed to help a lot. 

## Progress this week

I'm pretty unfamiliar with the build environment that you're using, so I don't feel comfortable building and running your client or server code. I've looked through it, and it seems mostly logical, but I'd like to see some screenshots of what you have working so far, even if its just like, a basic screen with simple hard-coded graphics (especially now that you have some more sprites, if they are added at this point).

Your plan for implementing the grammar seems good, and from looking at your grammar and ast files in the code repos, it seems like you've already made good progress here, just need to debug it and get it finally working. I think with your new ideas that should be feasible, but if not, it might be worth discussing on Wednesday in order to possibly make simplifying solutions. 

## Testing

While I definitely agree with you in that large software projects really need solid testing, I've run into the same issue as you have with time constraints. It just doesn't seem like we have enough time, given that we're doing entirely single-handedly, to set up well-designed testing systems for these projects. What I've been doing is keeping sample scripts that I add to whenever I add a feature, and can run every time I make a change to the code. This has worked well for me, so I think I'm inclined to suggest making various sample scripts and doing whatever you need to be able to do to verify the output (by hand is fine here, as long as you know what you're looking for), and doing something similar.

Additionally, like you said, I think user input is going to be very important for your project, as it is part of your design philosophy. I like the idea of having the game be playable by next week, and having us all play it. I'd be happy to spend some time outside of class on this. I also think its important though for you to get outside opinions evne from the class, if there are people you know who would be willing to test your game and give feedback. I'm doing something similar - asking some of my high school friends who play dota to test my language. The issue with this that I've run into seems to be that the documentation really needs to be good for this to work... so I'm not quite sure how feasible that is given time constraints. Depends on where you want to put time.


