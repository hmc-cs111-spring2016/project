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
