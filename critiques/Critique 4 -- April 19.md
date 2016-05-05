
## Critique

#### Introduction
I just finished looking through your entire code repo. It seems like you have made great progress!
Furthermore, I mostly agree that your design descisions have mostly all been made at this point. That
being said, I have a few suggestions (some I've actually made before) that I think will be useful when
transitioning from "development mode" to "final submission."

#### README
One of the first things to jump out at me was an old outdated README file when I visited your repo.
I soon noticed, however, that you actually had two README files. One, called `README.md` (the one GitHub
looks at) was old and outdated. The other, called `Readme`, seemed like the updated README with instructions
on how to build and run your code. I am relatively certain this was just a simple oversight. That being said,
you should probably copy the contents of `Readme` into `README.md` and then run `git rm Readme`.

#### Compiling Code
I tried compiling your code for about 20 minutes with no luck. Although I looked at your instructions,
I must not have something installed correctly on my Windows computer. Additionally, I tried compiling your
code on Knuth (although I wasn't too surprised this didn't work). Since I don't mind installing the necessary
software on my computer to build your code, I think it might be a good idea to use my computer as a guinea
pig in Wednesday's class. This way, you can get it running on a Windows computer and then create 
corresponding documentation for your users.

#### Implementing AST
Although I do agree the AST idea would be cool, I don't think it is necessary -- especially given the
remaining time for this project and that the paper you linked to has already done something like that.
That being said, if you do choose to implement it, I think you might want to reconsider your
design philosophy for the feature. While I agree you will get improved performance by preforming the
`+` and `-` operations together with a single kernel, I think always creating this cumulative kernel is
unnecessary overhead when I feel like many users will only want to do single operations at a time.

To address this, I think it makes the most sense to formulate a slightly different representation that
tells the code you're creating a cumulative kernel rather than preforming in order operations. Although
I have no idea what this representation looks like, I think it is a very reasonable thing to do given the
`C++` design philosophy. `C++`, especially compared to languages like `Python`, expects programmers to
have an understand "what's going on under the hood." For instance, it is not unreasonable for `C++` to
assume a developer knows the preformance distinction between `i++` and `++i`. By this same logic, I think
it is okay to make the cumlative kernel feature have a slightly different syntax that the users must 
specifically invoke if they want the preformance benifits.

#### Serial Testing
I agree that testing the code serially is important. If running the code with a single thread in OpenCL is
satisfactory, this seems like a reasonable path of least resistance. Althernatively, you might consider
running your code in a VM with only "one" processor. This would probably be a bit of a hassle, however...

#### Sample Results
I like the peak at your sample runtime results. That being said, I was hoping you would give a brief 
explanation describing your initial reaction to the results. Specifically, a few numbers I thought were 
surprising (although I personally have theories on why they ended up the way they are). Specifically
I noticed:
   * The integrated GPU was only slightly faster than the Serial CPU (I assume this is because the integrated GPU has a lot of overhead)
   * The dedicated GPU was slower than the Serial CPU (I assume this is because the dedicated GPU has even more overhead than the integrated GPU)
   * The threaded CPU is significantly faster than the other three methods (I assume this is because the CPU has the least overhead)

Although I assume you plan to do more benchmarking before your presentation and final report, here are some
cool numbers I would like to see (if you think they're valuable):
   * How many threads is your threaded CPU using?
   * What are the specs of your CPU, iGPU, and dGPU?
   * When does the iGPU become faster than the Serial CPU on your system (a value < 10,000,000)?
   * When does the dGPU become faster than the Serial CPU on your system (a value > 10,000,000)?
   * When does iGPU and dGPU become faster than the threaded CPU (a value >> 10,000,000)?
   * When does dGPU become faster than the iGPU (a value > 10,000,000)?

#### Eliminating Return Values
I think it is fine to eliminate the return values for operations like `+=`, `++`, and `--`. This is 
especially true since using multiple of these operators in a single line generally (although not
always) indicates difficult code to read. That being said, whatever you decide to do, I think the
important thing is to be consistent. Either you support returning values for all of these "side effect 
operators" or you support none of them.

#### Project Organization
Although changing the directory structure of a project is always a pain while developing, I think you
might seriously want to consider reworking the project organization. Although your `Readme` file does
direct users how to build the code, I think at this point in the project you might consider `/src/`
and `/bin/` folders and a `Makefile`.

#### Improving Testing
Although I really like your example.cpp, I think you should consider either adding some `assert()`
statements or comments that tell the user what the correct output is. This way, if a user runs the
example code, they can know the code is behaving properly.

#### Citing cl.hpp
Although I'm pretty sure you don't _need_ to do this (since the code was provided for free use), it might be
useful to note where you got `cl.hpp` in your README. I would imagine some users may want to fetch this file
from the source for other projects they're working on.

#### NULL vs nullptr
Since you are specficially using `C++11`, you might want to consider using `nullptr` instead of `NULL` in
`ParallelVector.hpp`. Although these two variants provide similar features, `nullptr` is mostly prefered in
modern `C++` for the extra safety.

#### Implementation in Header Files
Although I have mentioned this before, you might seriously want to consider making `ParallelVector.hpp` into
two files: a header and implementation file. Although a lot of standards say a lot of different things, it 
is generally considered poor style to code in headers (or implement an entire API in one).

#### Comments
Although you have interspersed comments throughout the files, and your coding style is generally formulaic  
and easy to follow, you should consider adding more comments. Especially as the project draws to a close,
these comments will become valuable if people who are less familiar with this project take a look at your
source code.
