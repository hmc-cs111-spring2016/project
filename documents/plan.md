# Project plan

## Language evaluation

I think my design will have succeeded if it is similarly intuitive to write code in my language as it is intuitive to write code in ContextFree. This would, of course, have to be judged by users. It should have most of the features that ContextFree offers in terms of power, as well. I think my implementation will be successful so long as it is efficient and extensible. Efficiency is important because long animations may take a long time to render, and extensibility is important because a ton of features could potentially be added that are out of scope for this project that I or someone else may want to add later.

## Implementation plan

This is, of course, a loose plan, but:
* By April 3: have determined a host language and image generation back-end, and a method for creating animations from images (likely ffmpeg)
* By April 10: have a basic prototype working that can produce simple images, possibly with many features missing (like recursive functions and user-defined animations)
* By April 17: have some degree of user-defined animations working, at least at the parsing level, so that a user can define animations and specify where they go on an image.
* By April 24: have the backend for generating animations from code working, and generally the entire project working.


## Contingency plan
I may run into problems generating animations; if this happens I will likely have to resort to simply generating a series of images and then manually turning these into an animation after the program has run. If even image generation turns out to be too difficult, then I suppose I will have learned lessons about the difficulty of creating a DSL in this domain, as image generation is certainly central to the project.

If I have difficulty implementing or extending the parser then I will likely just seek help; I don't imagine I'll run into insurmountable technical challenges that I can't tackle by seeking help. If I do, then I will get my technical deliverables into as developed a state as I can.

## Teamwork plan 

I do not plan to work in a team.