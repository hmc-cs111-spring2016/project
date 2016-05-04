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
Most of the operations users can perform on vectors are element-wise, meaning they operate on just the corresponding elements of all the vectors involved.
In a declarative language, these operations could be performed by storing all the values in arrays and performing the operations in a for loop that loops over every element.
In a functional language, these operations could be performed by storing all the values in lists and mapping the operation onto eacn element in the lists.
Currently, users can perform any common integer, logical, or bitwise operation on vectors in my DSL.
For example, vectors can be operated on element-wise with addition, logical and, XOR, shifts, and many other operations.
Element-wise operations on vectors look idential to their scalar equivalents in the host language.
So, `a + b` is just as valid if `a` and `b` were integers or vectors of integers.
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

## Language Implementation
