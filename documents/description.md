# Project description

## Motivation
I am not really trying to address and existing problem with this project as much as I am trying to create a neat tool to help (approximately) middle-school aged students to write and check their poetry. I want the project to help students write poetry which has known meter and rhyme scheme, which would hopefully encourage them to be creative and write more. I think this is interesting because nothing like it really exists, and it seems to be something with a fairly simple MVP with lots of extensibility, which makes it (in my opinion) a really cool project.

I think a DSL is a good idea because it will help with (minor) variability in structured poetry. For example, you would be able to specify a "AABB" rhyme scheme, or a certain number of syllables, and in that way create a new form of structured poem; alternatively, you could use a known form like a sonnet or limerick, and check if you are appropriately following the known form.

## Language domain
The domain for this language is in poetry-writing, specifically in metered poetry. What I think makes this project cool is that nothing (that I've found) does anything remotely like this. Of course, there are rhyming and syllable suggestion/checking sites aplenty online, and lots of APIs that help with various aspects of language from synonyms to spellchecking, there isn't really anything out there quite like what I want to do. 

I think this is useful because for someone who is just learning about poetry, say, a middle-schooler, it could be helpful to have immediate input into whether or not you are understanding the structural concepts behind a specific poem type. I, as a seventh grader, had a phase after learning about Shakespeare's poetry where I tried writing sonnets and understood the difficulties therein. It would have been nice to take my newly written poems and verify their "correctness;" that is what I am trying to do with this DSL.

There's nothing out there that I can find that's anything like what I'm trying to do!

## Language design
In one sentence: when a program runs, the program takes in a text file and a description of the meter and rhyme scheme of a poem and verifies its validity in that meter/scheme. 

I suppose what would constitute a program in my language would be some sort of terminal/console communication with the user of the language. That is, when a program "runs," it simply opens some communication method with the user and waits for input. Then, the program would take some sort of rhyme scheme (or have some preset rhyme schemes) in a form like "ABABC", meaning a five line poem in that scheme, or perhaps "(ABABC*)", which would be an arbitrary number of repetitions of that rhyme scheme. It may also take in an input of syllables per line (and maybe, in the future, different numbers of syllables for different lines, or maybe require certain words to be in certain lines -- there is a lot of extensibility here).

I will definitely want the poem to be expressed as a text file with line breaks. This would be best because then lines can be split up easily into an array or Scala Collection, and from there it woudl be easy to count syllables, check rhymes (by looking at the last words of lines) and make it easier to give feedback -- i.e., "on line 5 incorrect rhyme" or something like that.

It doesn't seem like there will be too much need for control flow, as poems are meant to be read sequentially and I can't think of a reason why not to check a poem in order. I probably won't even have to parse -- just break the poem up by line for checking against the meter/rhyme scheme. 

Syntax errors would probably be something like calling a nonexistent file or mismatched parentheses on a "*" type rhyme scheme. I can't think of too much else that would go wrong on a syntax level. A runtime error would possibly come from a failed API call, which could be caused by not finding a word on an API call (possibly because it doesn't exist), or by failing to adhere to a rhyme scheme; I think these things can be communicated to the user through good error handling and informative error messages. These types of errors should not error the program and should be handled gracefully. I'm not totally sure how failed API calls will return (hopefully an empty JSON) but this is something that should be established and dealt with as well.

## Example computations

I am not _totally_ sure what's being asked for in this question, but if, say, the program was given a ryhme scheme of "AABB*" and a text file, the program would: 

1. Split up the text file by newlines, and then split each line into a list of words. The program would also eliminate "paragraph" breaks, though this may be something that is handled differently in the future.

2. Store the word ending the first line. This word is important because it will be the rhyme of every other pair of lines.

3. Verify that the word ending the second line rhymes with the word ending the first line.

4. Store the word ending the third line (the "B" rhyme).

5. Continue through the poem verifying the last words of every line rhyme with each other, and that the poem has the correct (divisible by 4) number of lines.

For lines that do not rhyme, the poem could output something like: "Line 5 does not match rhyme scheme. Should rhyme with word x. Alternative words that rhyme include: [y, z, w]". It could also output something like: "Not enough lines in final stanza. Need x more lines which are in rhyme pattern ABB, as first A is included."

Eventually, I hope the program will also be able to scan through lines counting syllables, and, if I'm getting really ambitious, also be able to count meters like iambic or trochaic.