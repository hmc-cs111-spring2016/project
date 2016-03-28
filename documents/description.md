# Project description

## Motivation
DOTA 2 is a very hard, complex game. In order to even be competent, one must memorize all 120+ heroes (characters) in the game, each of which has at least 4 unique abilities (skills), as well as all of the 100+ items that all do unique things. Then, in order to be good at the game, you must know which of these hero/skill/item (which is commonly referred to as a "build") combinations are effective, and against what other hero/skill/item combinations. Some of this can be figured out through logic and intuition from playing the game for a long time, but for the most part, there is no concrete way to determine how much damage one build does against another; the mental math required is too complex. Even professional players experiment with new builds, and might not be using the optimal ones in every situation.

Thus, I want to create an easy way for people who play the game, primarily newer players, but not exclusively, to test various dota 2 builds against similar builds, and other players' builds. Because the goal is to enable lots of comparisons at once, I feel that a language is best to do this, as all of the existing tools force the user to input an entire build one at a time, with very little ability to copy-past builds with slight modifications.

## Language domain
The domain of this language is DOTA 2. The user should be able to (in an ideal world) write a program to test any combination of dota 2 heroes, items and skills with as much specificity as possible. It should be impossible to do anything other than test DOTA 2 elements. 

## Language design
The language should be designed with three main elements. First, the user should be able to specify a build. This includes:
 + The hero
 + The hero's level
 + The Hero's items
 + The levels of each ability the hero has

The user can then store builds like variables in a conventional programming language. 

The second element of the language is essentially dota-specific queries. So, one can take one of the builds that is specified, and ask for various stats on that build (for example, damage, hit points, attack speed, DPS, etc). 

Lastly, the language needs control-flow elements. These are essentially ways to compare different builds. Some things that the language might have is a foreach-style loop and list elements, so one can make a list of builds and run the same command on all of them. Additionally, I'd want things that compare two builds. Commands like max(stat, build1,build2), and WhoWins(build1, build2). Obviously, this isn't the syntax, but it describes the general structure of the control flow. 

## Example computations
The first kind of computation is the definition of a build, which would look something like:
```
build1 = ember spirit level 21 with (boots of travel, battle fury, manta style, crystalis, divine rapier) skills(4,4,4,4,5)
```
This would then just store that build as an object in the internal representation. Next, one could do things like (numbers are made up):

```
build1.damage
> 600 
build1.attackSpeed
> 60
build1.hitPoints
> 1800
```

Now, if one had multiple builds, you could do things like:
```
builds = [build1,build2,build3,build4]
foreach build get damage
> 600
> 150
> 200
> 300
```
Similarly, one could get the max damage build like:
```
max(damage, builds)
> build1: 600
```
And finally, comparing two builds would probably work like:

```
build1 VS build2
>build1 wins (90% confidence)
```

These are all three kinds of commands that the language should support.

## Language Evaluation
Evaluation of the language should follow the pattern of parsing the input, then going to an internal data layer that contains all of the stats for dota heroes and items, and then storing the result as an object in my internal representation. This should internally store all the damage, HP, etc stats in the object, so at this point I never have to go back to the data layer. 

Then, when people ask for data queries, the language simply returns the result stored in the object, or does a calculation. The calculations that need to be done are basically things that are based on the dota 2 engine. For example, I might want to know the amount of physical damage resistance that a build has. This is a formula based on the hero's armor value, as well as any skills the hero has leveled up that give damage resistance. These would be stored in the language evaluation methods (most likely in scala).

Lastly, the control flow will be implemented as an external dsl, so commands simple result in a parse tree that I can evaluate to get the result I want. The actual computations here aren't that hard, but I think there might be a few that will be tricky to implement (for example handling random chance in damage calculations for the "VS" command might be a little weird). I'll have to consult people who also play the game ot make sure I get those right. 

## Implementation Plan
My implementation plan is fairly simple. First, I need to compile the internal data I need to use. For the most part, this is done (I just need skill data, which might be really hard to get. More on this in the next section). 

Next, I want to build up the internal computation mechanisms a little at a time, while simultaneously designing the front-end syntax for them. The first thing I want to do is the build specification, as its the part that relies on the most things (namely all the data accessing) and also is relied upon by every other part of the language. Once this is done and I have a good syntax for it, I can just focus on adding as many features as possible one at a time, based on how important I and others in my group feel like they are to the language. 

## Contingency Plan
The most likely issue with this is hero abilities. These can be EXTREMELY complicated, and most of them wouldn't add anything to the queries the user cares about anyway. I have no idea how I'll automatically code in most of them. In fact, I probably won't. Instead, what will likely happen is one of two things.
 + First, I might find a list of skills that apply buffs (positive bonuses) and debuffs (negative bonuses) to heroes, as well as all passive bonuses. If I can use that list, then I'll just include those in the computation.
 + the other case is that I can't find such a list, and then I'll essentially have a hard-coded version of some of them for the purposes of demonstration.

Finally, if things are harder to do automatically (i.e. for all heroes if data accessing is hard) I'll code in data myself, and work on parts of the language that use that data as an example of what the fill implementation would look like. This would look no different on the front end, except for the lack of some hero/item data. 

One possible final issue is the specification of a build. I'm imagining allowing the user to specify heroes and items in many different ways (using abbreviations, misspellings, etc). This might be hard, or lead to problems, so I might have to think of a different way to make the specification as user-friendly as possible. 

