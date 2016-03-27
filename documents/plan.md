# Project plan

## Language evaluation
I think what's really nice about my language is that I have a very clear MVP. I would consider my language a success if it could look at a given rhyme scheme for a poem and check that the rhyme scheme works. Perhaps the very next feature above the MVP would be suggesting alternative rhymes to words that do not fit the rhyme scheme. 

As far as implementation practices go, I'm not totally sure. I hope my critiquers and reviewers have some sort of input on this. I've never relied on an API as much as I'm about to for this project, so I'm wondering if are some good practices that I should know about. Of course, I'll try to limit API calls as much as possible. 

I think testing will be really important and I really want to make a good faith effort to follow test driven development practices for this project. I think the more rigorously I test, the more I will know that my code is doing what it should. Additionally, lots of testing will help me ensure that I know exactly what behaviours I want from my code. I'm a little nervous that I don't quite know what a real prototype for my program will look like, as opposed to an MVP; it seems there aren't too many steps in between. That said, I'll probably let testing of smaller functions and parts of the program help me granularize the problem and make sure everything along the way is going well.

## Implementation plan
Along with the testing that goes along with every stage, I have this tentative schedule for my project. I have a hellish week for the week beginning April 4th so I tried to reduce deliverables there, but otherwise here's the plan:
* By April 3: Stretch: Prototype taking in a text file with all rhyming words and making sure the program can verify all words rhyme
  * Minimum: being able to do API calls to make sure two words rhyme, and being able to turn a text file into a list of lines (each of which is a list of words)
* By April 10: Stretch: Checking that a poem fits a given rhyme scheme (not input by user: something like ABAB) and maybe even achieving "*" implementation
  * Minimum: being able to check that all end words in a poem are the same rhyme
* By April 17: Stretch: User inputs their own rhyme scheme to the language and then checks it against a poem.
  * Minimum: MVP making sure that the rhyme scheme is adhered to, possibly giving suggestions for incorrect lines and erroring on incorrect number of lines.
* By presentation day: Stretch: Counting syllables.
  * Minimum: user inputs their own rhyme scheme.

I should say I have absolutely no idea whether or not this is a reasonable timeline. I always find that my time estimation on these things is awful, and things I think will take forever are done relatively quickly, and instead I get held up on some absurdly small task for unreasonably long. I hope this is a reasonable timeline, as I feel this is a fairly well scoped project and hope to have it done in time.

## Contingency plan

## Teamwork plan 
*If applicable*
Not applicable! 