# Language design and implementation overview

## Language design
#### How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?
Since my DSL is internal, it is mixed in with the host language. To maximize ease of use, code in the DSL should be able to be right next to regular host code and vice versa.

#### What is the basic computation that your language performs (i.e., what is the computational model)?
The DSL focuses on vector parallelism (doing the same operations on a large set of data) and is mostly limited to basic computations. It should also support reductions (necessary for operations like dot products) and perhaps a few other non-elementwise methods.

#### What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?
The DSL revolves around a `std::vector`-like data structure to maximize familiarity. This allows them access to convenient constructors (like the fill constructor) and operators (like the bracket data accessor operator).

#### What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?
The host DSL will handle large-scale control flow. For elementwise "control flow", there will likely be a ternary operator of sorts that will allow conditionally selecting data.

#### What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?
The DSL will take inputs from the host language in the form of a pointer, a `std::vector`, or a scalar. Outputs will be another vector data structure from the language or a scalar depending on the fucntion(s) called.

#### Error handling: How can programs go wrong, and how does your language communicate those errors to the user?
The major error that I've thought of right now is size mismatches, which I already check for and raise an exception when encountered.

#### What tool support (e.g., error-checking, development environments) does your project provide?
Since the DSL in integrated into the host language, standard host language debugging and development tools can be used.

#### Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?
There is nothing quite like this that I know of, but a lot of parallel libraries or languages share some similarities to my DSL.

## Language implementation
#### Your choice of an internal vs. external implementation and how and why you made that choice.
Since my DSL is aimed to make sinmple parallel computing as accessible to people who want good performance, it needs to be an internal DSL. Having to write separate code and integrate it into your main code is too much work and would discourage too many people from using my DSL.

#### Your choice of a host language and how and why you made that choice.
I chose C++ because many C++ programmers are concerned about performance, but don't have easy ways to integrate parallelism into their program. My DSL would help them easily exploit parallelism without the effort that typically comes from C++ parallel libraries.

#### Any significant syntax design decisions you've made and the reasons for those decisions.
Thank to the help of my critique partners, I've made some progress with how to specify ternary operations and reductions. The ternary operator will be specified by writing `A.choose(B,C)` and reductions will probably be specified with custon names and operators (e.g. `scalar = vector.sum()`).

#### An overview of the architecture of your system.
I'll probably try getting OpenCL working for doing the parallel computations. If I just can't work with it (since I'm less familiar with it), then I can always fall back to OpenMP and Thrust, since they will be closer to my current code and will be much less of a burden on programmers than getting Kokkos working.
