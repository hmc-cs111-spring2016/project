
## Critique

###### Introduction
I just finished reading through your entire code repo. Let me be the first to say great job!
You seemed to get a lot done in the first week. Furthermore, your code seems very readable
and well thought out.

Below I have separated my critique into a number of sections. The goal of each section is to
bring attention to something or provide some kind of suggestion. I hope this serves to be useful!

*NOTE: I think you may have forgotten to either do or push your Language design and implementation overview*

#### General Thoughts

###### Building the Program
I know it is early in the project, but considering how people are going to build your library is
important. As is, there is currently no Makefile or build script. Although this program is simple
enough that you don't really need one, even if it is obvious, it is good practice to add a line
to the README that says something like:

    # Build this with...
    g++ ParallelVector.h example.cpp -o runme

    # Run with...
    ./runme

###### File Extensions
I noticed you named your C++ headers with a `.h` extension. I know there are a million different
way to label extensions and history has been relatively indecisive, but I believe it is more
or less standard to reserve `.h` for C headers (especially if you're opting to use `.cpp` for
your implementation files).

Just to make sure I wasn't crazy (since everyone seems to tell you something different), here
are two StackOverflow posts I referenced as a sanity check.

   * http://stackoverflow.com/questions/152555/h-or-hpp-for-your-class-definitions
   * http://stackoverflow.com/questions/5171502/c-vs-cc-vs-cpp-vs-hpp-vs-h-vs-cxx

###### Code in Headers
I noticed your entire library is implemented in your header file and the tests are your
implementation file. Although I imagine this is likely only a temporary measure, this is generally
considered bad style for anything beyond simple getters and setters. Although none of these
functions by themselves are terribly complex, I think they warrant their own implementation file.

Another StackOverflow link I used to sanity check myself.
   * http://stackoverflow.com/questions/583255/c-code-in-header-files

###### Code Testing
Although your code is currently tested by using an implementation file, you might want to consider
using a testing framework. A common one is GoogleTest which can be found
[here](https://github.com/google/googletest).

###### Error Checking
I must say, I really like your use of the private function `do_operation`. That being said, since
you don't use it for all your functions, everything is not properly length checked.

For instance, the following operators, which rely on for loops, are not properly length checked:
   * `operator+=`
   * `operator-=`
   * `operator&=`
   * `operator|=`

###### Unsupported Operators
Since you support operators like `+=`, I think it might be a good idea to support other common
operators like:
   * `operator*=`
   * `operator/=`
   * `operator%=`
   * `operator^=`
   * `operator<<=`
   * `operator>>=`

A list of all C++ operators can be found [here](https://en.wikipedia.org/wiki/Operators_in_C_and_C%2B%2B).

###### C++ Passing Operators as Parameters
Although you seem to be doing something similar to this with your `do_operation` function, you
might find this post useful. This might allow you to even pass operators like `+=` to your
`do_operation` function (making error checking for lengths easier).
   * http://stackoverflow.com/questions/4530588/passing-operator-as-a-parameter

###### Increment/Decrement Parameters
Although there is nothing wrong with this if it works, I found the overloaded version of the
`operator++` and `operator--` to be a little unintuitive. I feel like these are usually uniary
operators (except the few instances where I've seen `++` be string concat). That being said,
this might be more standard than I think and I just haven't seen it.

###### C++ Version
I noticed you commented out some code intended for C++11. It might be a good idea to decide
on a version of C++ and note this in either a comment at the top of the library or in your README
(or, ideally, both!)

Especially since this is a library being written for a school project, it might be interesting to
use this as an excuse to try out C++14 (if it works with your parallel libraries). C++14 added
some cool lambda function features that might be useful since this library seems to try to make
C++ more functional (i.e. the entire library seems to be a giant `map` operation for vectors that
runs in parallel).

I can especially see lambdas being useful to make a cleaner interface for doing something like
this `thrust::reduce(d_vec.begin(), d_vec.end(), 0, thrust::plus<int>());`.

Here is a guide I found on some of the lambda features in C++14. Notice the line 
`std::for_each(V.begin(), V.end(), [](auto i) { std::cout << i << " "; });` very much
resembles the Thrust library, only with an useful anonymous function.
   * https://solarianprogrammer.com/2014/08/28/cpp-14-lambda-tutorial/

###### Parallel Library Choices
I don't really have any new input on which library to use when adding code parallelization since,
as you noted in detail, every library has a number of advantages and disadvantages. I will say
there is no ideal library so at some point you will need to pick one (even if a best option
doesn't exist), and progress forward.

Although this isn't a great reason, and I haven't used all of these libraries personally,
I will say I have used Thrust in the past to great success.




##### Revisiting Points from Monday

###### Reduce vs Foldl/Foldr
I was rethinking our conversation from class Monday about ways reducing a Vector via subtraction
or division concurrently. Although this might be common knowledge, I realized when thinking about
reduce and fold operations, I usually think of them as the same (this confusion seems to be rooted
in Python's `reduce` being equivalent to Haskell's `foldl`).

In reality the difference is `reduce` is normally the name used when order doesn't matter
(implying the code can be parallelized) as `foldl` and `foldr` imply order matters (and thus
directionality and an `O(n)` runtime is enforced).

Source:
   * http://stackoverflow.com/questions/25158780/difference-between-reduce-and-foldleft-fold-in-functional-programming-particula

###### Ternary operator

