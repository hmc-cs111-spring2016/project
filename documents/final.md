# Final Write-Up

## Introduction

Around 10 to 15 years ago, chip designers hit a limit in how fast they could make CPUs run.
However, during this time, the number of transistors that could be fit on a chip increased exponentially.
In response, chip makers used these extra transistors to add multiple cores to CPUs, allowing them to perform multiple tasks at the same time.
In more recent years, chip makers have been pushing the concept of multicore computing even further through GPUs, which are optimized for performing many identical tasks at the same time.
However, while the potential power provided by multicore CPUs and GPUs has increased dramatically, tools for programmers to take advantage of these reletively new architectures are still lacking.
Many of the popular libraries and languages for parallel programming do not hide the details of the parallel platform being used and require programmers to write parallel code in a different form than the rest of their code.
Furthermore, few languages and libraries support most of the parallel devices available, requiring programmers to have to write and debug parallel code multiple times in order to support most major platforms.
It is clear that there is much room for improvement.

My DSL aims to solve many of the problems that prevent the average programmer from writing parallel code.
First of all, it focuses on the most ideal form of parallelism: vector parallelism.
Vector parallelism  is simply performing the same operations on multiple pieces of data.
This often happens when programmers write for loops in declarative languages or use map and filter functions in functional languages.
My DSL allows programmers to exploit vector parallelism by writing their code as if they were operating on a single piece of data, but actually performing the operations (in parallel) on all of the data of interest.
This allows programmers to think and program in a non-parallel manner, but still get the benefits of running their code in parallel on a CPU or GPU.
Speaking of which, my DSL also supports practially every parallel device.
This means that users with just multicore CPUs can benefit from it as well as users with powerful GPUs (from both AMD and Nvidia).
My DSL also makes it easy for programmers to get started with parallel programming.
It doesn't make them grapple with any of the intricacies of parallel devices and is relatively easy to compile.
All of these features should combine to make using my DSL much easier than other competing languages and libraries.

## Language Design
My DSL revolves around vectors which contain arbitrary numbers of ordered scalar values.
Data can be copied into and out of my DSL's vectors through a variety of ways.
Vectors can be initialized with a constant value or from the contents of another data structure by iterator or pointer (or directly from `std::vector`s).
Elements within a vector (including entire ranges of elements) can also be modified or read from in a similar fashion through the `get` and `set` methods.
Vectors also support the accessor operator, but only for reading.
This could be changed in a future update; however, this would come at the cost of any references to elements in the vector becomming invalid anytime a computation is performed on it.
Finally, users can also add or remove data from vectors by using the `push_back` and `pop_back` methods from `std::vector`. However, these can be very inefficient unless the user uses the `reserve` method to guarantee that more space will not have to be allocated when adding elements to the vector.
The size of the vector can also be changed with the `resize` method and queried with the `size` method.

Most of the operations users can perform on vectors are element-wise, meaning they operate on just the corresponding elements of all the vectors involved.
In a declarative language, these operations could be performed by storing all the values in arrays and performing the operations in a for loop that loops over every element.
In a functional language, these operations could be performed by storing all the values in lists and mapping the operation onto eacn element in the lists.
Currently, users can perform any common integer, logical, or bitwise operation on vectors in my DSL.
For example, vectors can be operated on element-wise with addition, logical and, XOR, shifts, and many other operations.
Element-wise operations on vectors look idential to their scalar equivalents in the host language.
So, `a + b` is just as valid regardless of whether `a` and `b` are integers or vectors of integers.
My DSL also includes a form of the ternary and filter operations.
Due to the constrainst of the host language, the ternary operator is the only element-wise operator that differs between scalars and vectors.
Instead of looking like `a ? b : c` (or  `if (a) then b else c)`, it is written as `a.choose(b,c)`.
The `filterBy` method, written as `A.filterBy(b)` generates a new vector that only includes the elements from `A` if the corresponding element in `B` is `true`.
The order and number of elements in the returned vector are not guaranteed to be the same as the original vectors.
While floating point methods are not currently supported (like max, min, rounding, or power functions), they could be added in a future update.

My DSL also supports a few handy operations that involve more than one element from the same vector.
These operations are `A.sum()`, `A.product()`, and `A.rotate(b)`.
`Sum` and `product` compute the sum and product of all the elements of a vector.
`Rotate` moves all the elements in the vector to the right by the specified number.
For example, rotating a vector containing `[0,1,2,3,4]` by 2 would result in a vector containing `[3,4,0,1,2]`.
To rotate elements to the left, a negative number can be used with the rotate function.

Finally, my DSL supports almost all scalar datatypes. It can operate on 8, 16, 32, and 64 bit integers (both signed and unsigned), boolean values, and 32 bit floating point numbers. Unfortunately, the backend used to implement my language does not always support 64 bit floats (also known as doubles in some languages), so my DSL cannot universally support them.

## Example Programs

Three example programs are included with the DSL. The tests program checks to make sure each method and operator is working at least on a basic level. It is also useful for seeing all the possible things you can do with the DSL.

The kmeans program does a simple kmeans (unsupervised machine learning) calculation with two centroids. If the program is working correctly, the centroids should generally converge on two different locations (which would normally represent the centers of two clusters of 2D data).

The keys program breaks a very simple (~50 bit) key made up of two "large" prime numbers through brute force. This is an ideal problem for vector parallelism as we can many potential numbers to see if they are one of the two primes in the key.

## Language Implementation
I chose to implement my DSL in C++ as many C++ programmers are concerned about performance and would likely enjoy better ways to write parallel code.
One advantage of using C++ is that it supports operator overloading, which allows vector objects to share the same operators as scalar values.
While this means users can work with vectors like scalars, this may lead to some confusion when operators are implemented differently than expected.
For instance, in my DSL, vector operations that modify the original vector (like `+=` or `--`) do not return anything.
This is because operators that return references to the original objects can lead to code getting very confusing for programmers, (2) operators that return a copy of the original object require an expensive copy operation, even if the copy is never used, and (3) these kinds of tricks are generally only used by advanced C++ programmers and can be confusing for other programmers.
As mentioned earlier, the accessor operator (`A[b]`) also differs from the standard implementation due to constraints with the back-end for my DSL.

To actually perform computations in my DSL, I use OpenCL, which has a long list of drawbacks and a few benefits.
While many of the drawbacks would normally discourage users from using OpenCL, my DSL handles many of its nasty details so that users can enjoy the biggest advantage of OpenCL: cross platform compatibility.
OpenCL can run on Windows, OS X, and Linux and is supported on CPUs and GPUs made by AMD, Intel, and Nvidia. This means that practically everyone owns a computer with at least one OpenCL capable device in it.
One additional advantage is that OpenCL support can be added to C++ programs without having to figure out how to compile a massive library, change compilers, or significantly update build systems.
In fact, on OS X, OpenCL is already installed and ready to be used.
For Windows and Linux, SDKs are available from AMD, Intel, and Nvidia that provide the files necessary for compiling OpenCL programs for their devices.
Windows and linux users will also need to add the locations of these SDKs to their build system, which isn't convenient, but is still much easier than dealing with other parallel languages and libraries.

However, in one way or another, some of the drawbacks of OpenCL managed to creep into my DSL.
For instance, OpenCL generally requires the parallel portions of programs (called kernels) to be written and compiled separately from the rest of the program.
This means that many errors that would be caught at compile time now occur at run time, which can make debugging significantly harder.
While I have already dabugged many parts of of the OpenCL code, it is possible that a runtime error could arise (like the computation timing out or being too large for the device).
While it could be possible for my code to eventually catch and handle all these errors automatically, this is beyond the scope of what I could accomplish in the limited time frame for this project.
However, I have implemented static detection of unsupported types (that are not available in OpenCL) and runtime detection of vector size mismatches.
Runtime errors are raised as string exceptions that can be caught and printed out to help with debugging.
Unfortunately, run time exceptions do not necessarily indicate which line caused the error, which can make debugging difficult without the use of a debugger like GDB.
While my DSL generally tries to run computations on the GPU, it can also detect the inavailability of a GPU and use the CPU with OpenCL instead.
In future revisions of my DSL, it may even be able to switch between using the CPU and GPU depending on which is faster.
My current code already has methods that will aid with this transition that are currently unused at the moment.

Another disadvantage of using OpenCL is that compiling kernels at run time can be expensive.
To help mitigate this problem, my DSL automatically saves compiled kernels so that they can be reused.
For programs that use the same operation on the same data types, this can significantly reduce the amount of work required to use OpenCL.

Another optimization my DSLs uses is it stores all data on the GPU.
This means that when a computation needs to be performed, the data will already be ready to go and the computation can be started immediately.
If data on the GPU needs to be updated or the program requests data from the vector, only the needed data is transfered, which is much faster than maintaining copies on both the CPU and GPU and keeping both up-to-date.

## Evaluation

My DSL is somewhere between a general-purpose language and a "true" DSL.
Since it is designed for vector parallel problems, it can perform a wide varity of computations and may even be Turing complete.
In the most extreme case, single-element vectors could actually be used as scalars for all kinds of general purpose computations.
However, in regular use, my DSL should compliment the general-purpose features of C++ instead than compete with them.
Users should hopefully find that they can eliminate some for loops in their code while also getting the advantage of using a GPU for parallel operations.

As of right now, I am most proud of how easy it is to do operations on vectors.
You can practically treat them like scalars when doing computations, which is really convenient.
I think this DSL (or a DSL like this) would be quite helpful for solving problems that can be solved using verctor parallelism.

Unfortunately, there is still a lot of room for improvement.
The biggest issue right now seems to be performance.
On my comptuer, performance results seem backwards.
My dedicated GPU runs slower than my weaker integrated GPU, which already runs slower than my multicore CPU.
While neither of the GPUs on my computer are particularly powerful, they should both be faster than my CPU, especially for vector parallel problems.
Sinec this is my first time actually using OpenCL, perhaps this is due to me misunderstanding how exactly it works.
The performance problem could also be due to issues with memory allocation and copying being slower on the GPUs than on the CPU.
However, this should not have been a problem for the keys example program as there should be very little data movement between the CPU and GPU.

There are also a lot of operations that could be added. As mentioned earlier, float methods have not been added yet (e.g. `sqrt()`). I also never got around to implementing a parallel scan method either, but this would be difficult to do well and is not used very often. Finally, I'm not sure if my code currently supports every assignment operator or working with const vectors very well. However, none of these prevented me from writing the example programs included, meaning the DSL is at least functional on a basic level.

One very important feature that really should be added is support for vector / scalar operations. Currently my DSL requires creating a vector from a scalar, which is very inefficient. However, I am not sure how easy this would be with operator overloading in C++ or implicit conversions.

Revisiting my original evaluation, I accomplished far more than the minimal goals I set for myself (except for getting good performance). Since this was my first experience creating a large C++ library and my first time really using OpenCL, I ended up learning quite a bit about what I could and could not (easily) do along the way and and am glad I was able to accomplish what I did. Unfortunately this did mean that my vision for this DSL had to change from what I originally envisioned (a complete `std::vector` replacement). Regardless, I hope the example programs show just how powerful and simple this DSL can be.

Below are the times it takes to run each of the example programs on my dGPU (Nvidia 650M), iGPU (Intel HD Graphics 4000), CPU (2.3 GHz 4-core hyperthreaded i7-3615QM), and on the same CPU serially. Note that all of these were run using my DSL with OpenCL and that an optimized serial implementation would likely run much faster without the overhead of OpenCL. It's also worth noting that these results will not be perfect as other programs were running on my computer during these tests (though they were not doing a significant amount of work during the time of the tests). Ideally the CPU and iGPU would be completely unoccupied when the tests were running.

| File   | Device | Run time |
|--------|--------|----------|
| Tests  | dGPU   | 14.701s  |
|        | iGPU   | 8.332s   |
|        | CPU    | 4.447s   |
|        | serial | 7.511s   |
| Kmeans | dGPU   | 1.971s   |
|        | iGPU   | 1.432s   |
|        | CPU    | 1.249s   |
|        | serial | 1.247s   |
| Keys   | dGPU   | 7.094s   |
|        | iGPU   | 5.214s   |
|        | CPU    | 0.856s   |
|        | serial | 1.632s   |
