# Project plan

## Language evaluation
At a bare minimum, the DSL will need to be able to load and unload data to and from something and perform basic math operations on all elements in the vector. However, I expect that any decent implementation will have a lot more to it than that and will have more of the "fancy" features outlined in the description. Example code can be used to verify functionality (that the DSL does what it should using the given syntax) and benchmarks can verify that we are getting a performance increase over a standard implementation. I figure I will also get feedback on my syntax from my critique group as well.

## Implementation plan
I already have a host language: C++. The library I want to work with to do parallel computations requires it and I figure C++ programmers would appreciate a performance-oriented DSL. Since this will be an internal DSL, I won't need to bother with parsers either. However, it does mean I will have to work within the confines of C++ (classes, templates, operator overloading, etc.) to integrate my DSL as well as I can into C++.

I figure it should take about a week or so to get the DSL working for basic math on at least a minimal level (will compute correct results for common math operations using some kind of parallelism) and another week to work on more advanced stuff (like reductions, optimizing where the computation is performed, and queuing up operations to significantly reduce memory operations). In the following week, I can work on adding some of the fancier features, like interfacing with more data structures and supporting practically all `std::vector` operations. The last week can be left for catching up (in case anything took longer than expected), further improving the implementation, or adding even more stuff from Thrust that users might like (for instance, parallel scans and fancy initializations with simple sequences of numbers).

## Contingency plan
While this project could become overwhelming, even a basic implementation could be very useful to users. I've also left time in the plan for unexpected difficulties so that I can catch up before presentations. While I'd like to add control flow to my DSL, I also recognize that it's probably far outside the realm of what's possible (and would probably require nasty syntax even if it was) and so I will only work on "nice to haves" like it if I have enough time left over from more important stuff.

## Teamwork plan 
N/A
