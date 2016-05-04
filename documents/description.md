# Project description

## Motivation
[ContextFree](contextfreeart.org) is a wonderful DSL that allows users to create a very interesting kind of abstract art quickly and easily. However, it is limited to creating static images, for the most part. This language will attempt to be very similar to ContextFree, with much of the same power and one key additional feature: animation primitives. That is, instead of just being able to specify a square or a circle or similar shapes as primitives, you will be able to define and specify small animations.

## Language domain
The domain is animation, and specifically animating the kind of art that ContextFree is great at creating. Because most of the inspiration for this language is from ContextFree, it will likely generate very similar static images.

## Language design
A program should, like in ContextFree, be a list of rules defined recursively and in terms of other rules. When a program is run, the program is interpreted in terms of those rules and a series of images is rendered based on them. Those images are then pasted together to create an animation.

## Example computations
We should be able to, for instance, define a 10-frame animation of a circle growing and then shrinking back to its original size. We could then create a "snake" image that consists of a horizontal line of those circles, each one delayed by one frame relative to the one right before it, that shrinks as they go farther. This would create an oscillating pattern as each animation is played individually.

We could also take advantage of the nondeterminism that ContextFree offers and make these circles randomly be squares instead, or have different colors or randomized delays.