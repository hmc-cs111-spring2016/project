# Final write-up

## Introduction

This language is designed to help students write poetry which has known rhyme scheme, which would hopefully encourage them to be creative and write more poetry. I think this is interesting because nothing like it really exists, and it seems to be something with a fairly simple MVP with lots of extensibility, which makes it (in my opinion) a really cool project.

I think a DSL is a good idea because it will help with (minor) variability in structured poetry. For example, you can specify a "aabb" rhyme scheme, and in that way create a new form of structured poem; alternatively, you could use a known form like a sonnet or limerick, and check if you are appropriately following the known form.

The domain for this language is in poetry-writing, specifically in structured poetry. What I think makes this project cool is that nothing (that I've found) does anything remotely like this. Of course, there are rhyming suggestion/checking sites aplenty online, and lots of APIs that help with various aspects of language from synonyms to spellchecking, but there isn't really anything out there quite like this language. 

I think this is useful because for someone who is just learning about poetry, say, a middle-schooler, it could be helpful to have immediate input into whether or not you are understanding the structural concepts behind a specific poem type. I, as a seventh grader, had a phase after learning about Shakespeare's poetry where I tried writing sonnets and understood the difficulties inherent in writing sonnets. It would have been nice to take my newly written poems and verify their "correctness;" that is what this DSL will do.

When a program runs, the program takes in a text file and a description of the rhyme scheme of a poem and verifies its validity in that scheme. This basic structure is consistent through virtually all the available functionality in the language. It's a good language for the domain because it's easy to understand and use, and the error messages are descriptive and helpful; these are important elements for a DSL with a target audience who likely has little to no programming experience.

## Design Details

Currently, in order to use the language, a user must first instantiate an Interpreter object, which contains a dictionary describing the rhyme scheme of a poem. Once a user instantiates an interpreter, there are only three commands available to the user: checkPattern, checkForm, and addForm. checkPattern allows the user to check a poem against a scheme that is input as part of the command; checkForm allows a user to check a poem against a scheme that is stored in the interpreter; and addForm allows a user to add a scheme to the interpreter. The syntax options are intentionally sparse so as to make the program as simple as possible; this is done with the target audience in mind. Support for syllable counting and other added functionality were considered and partially implemented at various times, but ended up being removed to keep the program simple. The language is responsible for determining which functions and helper functions to call based off of these three commands; this makes it easier to use the language than it would be to use a general-purpose programming language directly.

In order to create data, such as a poem, the user writes a poem in a text file and makes sure that file is in the same folder as the interpreter. The user can also create a rhyme scheme using the addForm command. The poem, which initially is stored in a text file, is then stripped down to a (Python) list of lines, from which the last words on each line are extracted and used for analysis of the rhyme scheme. The only output produced by the language is print statements; the statements tell you if you've added a form successfully or not, if your poem matches the specified scheme, if there is the wrong number of lines, and so on. The inputs are text files (for poems) and strings (for rhyme schemes/forms). 

The language is designed to catch all possible errors, from bad input (file does not exist, invalid rhyme scheme) to mistakes in the poem or poem structure. Error handling and descriptive feedback are extremely important to the success of the language, as the language is designed to help students understand what they've done wrong and provide suggestions. Each error message provides some level of helpful feedback to the user; for certain errors, longer error messages are provided (for example, if the rhyme doesn't match the rhyme scheme, suggestions are provided, and the current rhyme assignments are provided as well).

There are no other DSLs similar to this one in the domain; a lot of the work in this project is very much proof of concept, because I wasn't even sure how to add certain functionality, or if it was even possible. A lot of my early implementation mistakes were also due (in part) to this fact; I was trying to create functionality, such as determining the scheme of a given poem based on the last words, and had no idea how this could be done, if at all.

## Example program

Through the example program, I hope to show not only how to use the program, but also why it may be helpful, through the error messages provided.

Say a user wants to test the following poem: "I am how // I am now // I am brown // I am blue // hello world." Let's say the user wants to test the poem against the rhyme scheme "aaba". The user could save the poem, say with name "simple.txt", then test it the following way: 

First, they would initialize the interpreter. Then they would call `checkPattern("simple.txt, "aaba")`. The output would be:

```
checking "simple.txt" on aaba
Valid rhyme scheme!
Incorrect number of lines for specified rhyme scheme!
```

Seeing that the number of rhymes was not correct, the user could then remove the last line from simple.txt and run the same command again. This time, the output would be:

```
checking "simple.txt" on aaba
Valid rhyme scheme!
Correct number of lines for specified rhyme scheme.
Checking rhymes for line number 1
Checking rhymes for line number 2
Checking rhymes for line number 3
Checking rhymes for line number 4

Poem did not match specified scheme!

Incorrect line is line 4, with last word: blue
The scheme expects the word to match the 'a' rhyme pattern
As such, the last word on the line should rhyme with how
Here are some rhyme suggestions that match with that word: 
sow, bow, now, allow, cow, endow, disavow, wow, plow, vow, bough, kowtow, plough, scow, tao, ciao, thou, avow, chow, brow
Current letter assignments: {
    "a": "how", 
    "b": "brown", 
    "c": "blue"
}
```

Seeing that line 4 does not match, the user could then change the last word in line 4 from "blue" to one of the words suggested, say "cow," and re-run the program. The output would become:

```
checking "simple.txt" on aaba
Valid rhyme scheme!
Correct number of lines for specified rhyme scheme.
Checking rhymes for line number 1
Checking rhymes for line number 2
Checking rhymes for line number 3
Checking rhymes for line number 4
The poem matches the scheme! Great work!
```

Notice that the user runs the same command every time, which makes it easier to use and become familiar with. 

The user can also save a scheme, using the addForm command: `addForm("(a)ba", "simpleForm"). Then, the user could use `checkForm("simple.txt", "simpleForm") and have the same output as above. The form could be input as `aaba` or `(a)ba`; both would return the same output.

## Implementation Details










