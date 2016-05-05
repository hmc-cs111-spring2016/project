# Language design and implementation overview

## Language design

###### How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?
Although future incarnations of the game may attempt to incorperate a syntax highlighting text editor inside the game, that
is more of UI and tool design than actual DSL work. Thus, for this project I intend to allow the users to "upload" an external
text file into the game from their computer. This way, users can utiliize their favorite external text editors. Additionally,
since the game's language is similar to C, it is likely C-syntax highlighting will also work for this game. That being said, I
intend to look into the syntax specification DSL used to make syntax scripts in Sublime Text 2, Notepad++, and Vim. If time
allows it and it seems necessary, I might specify a syntax highlighting script for a popular text editor.

###### What is the basic computation that your language performs (i.e., what is the computational model)?
The basic computation my language performs is defining game behavior. My game is essentially an interpreter (i.e. like the
Python Interpreter) that needs to first _parse the syntax_ and then _type check_ before executing the code in game. Additionally,
since I cannot allow actual runtime errors to occur in my interpreter (since that would cause the game server to crash during
gameplay) I need to successfully detect various types of runtime errors so the game can provide realtime _feedback_ while
avoiding hanging or crashing itself.

###### What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?
The language is very similar to C in appearence. Thus the basic "data structures" will be a local variable environment for
each enclosure and a global scoped persistent environment. For each update in the game loop the code will be executed by 
initially calling the `void main()` function. Additionally, global variables must be defined in a `void init()` function
that serves as a "constructor" when the script is first uploaded to a ship. I may also require all global variables be
named with a leading `g_` for both good style and so there aren't conflict between local and global variables -- this is important
since there is no `this` reference which would allow a user to differentiate between a local and global copy.

Additionally, although I am not certain I will have time to do this, I would like to support arrays, some sort of 
pseudo-data-pointers, and structs. That being said, this adds a lot of complexity to an already complex project. If I did
support arrays, I would boundry check them (unlike C) and create virtual addresses. If I did support pointers, I wouldn't allow 
pointer to stack variables and I would refer to heap memory addresses with strings like `addr0`, `addr1`, etc. Although this
is atypical of actual memory addresses, this would allow me to simply use these strings as keys to a hash map "heap" in memory.
I would also not allow arbitrary memory manipulation (i.e. untyped pointer memory manipulations). Furthermore, depending on how
I choose to implement arrays (since they sound more like objects/structs), I could likly build them as internally generated
extensions of predefined structs. All of that aside, how important do you think arrays, pointers, and structs are for this first 
incarnation of the project?

###### What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?
Since this is trying hard to resemble a subset of C, I will support many common control flow structures seen in many programming
languages. Although this may not be an exhaustive list, the most important control flow structures I plan to support are:

   * if / else
   * for
   * while
   * function declaration
   * function calls
   * break
   * return
   * (Did I forget any?)

Since I am not planning to support structs initially, I should really support multiple return values. Although this is contrary
to C, this feature would circumvent what I see as the primary usage of structs in this language (aside from being a way to implement
arrays).

Additionally, for structures that allow code reuse (i.e. for, while, and recursive function calls) need to be watched
for endless loops. Although endless loops may not usually cause a runtime error (unless it uses up the entire stack), I cannot
let any program 

###### What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?
I am going to be brief in this answer since these points have been mostly addressed in the other questions so far. The input of
a program in my DSL is a script that defines the behavior of a ship. The output is game behavior. That being said, game behavior
can be the ship flying and behaving as specified or the game providing error messages to the user.

###### Error handling: How can programs go wrong, and how does your language communicate those errors to the user?
Again, I am going to be brief in this answer since these points have been mostly addressed in the other questions so far. Since
the game will catch any errors to prevent runtime errors of the server itself, I will provide errors in game. I envision this
as a character "yelling" at the player for something going wrong and there being a combination of audio and output text files for
the user to read. I am not sure how many voice lines I will have time to record during this project, but I will certainly support
a few for a proof of concept. Additionally, although I could provide the error message information within game itself, I believe
providing it in a text log file will be more benifitial to the user since they are also using an external text editor. I am currently
looking at parsers trying to figure out how I can keep and provided useful line information if an error is encountered.

###### What tool support (e.g., error-checking, development environments) does your project provide?
The primary support for users will be the characters in game dialog and error logs. In some ways the game client itself will serve
as a IDE of sorts, although I am intentionally leaving the text editor aspect out. Rather, the user will supply a file path into the
game that would then send the script file to the server for parsing and interpretation.

###### Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?
Although there are scripting languages for coding character behavior in video game scenario editors, I have not quite encountered
anything quite like this game/DSL. My language is attempting to be aestheticly similar to C with a few modifications for practicality
and implementation ease. These modifications are mentioned in the previous answers.


## Language implementation

###### Your choice of an internal vs. external implementation and how and why you made that choice.
This choice made itself. Since my game is an interpreter, I needed to make the language external so it can process code without
needed to recompile the game server each time new code is written.

###### Your choice of a host language and how and why you made that choice.
My project is interesting since I am using two host languages.

The server of the game will be written entirely in Scala. I made this choice since the server will be doing all the parsing
and interpretation of a user's code. This makes sense since, as we saw in class, Scala's plentiful functional features and
pattern matching is very handy for creating external DSL. Additionally, although I have modified Scala code outside of CS111,
I have never done a major project from scratch using it. I am looking forward to my first major project in Scala.

The client of the game will be written entirely in Java. I made this choice since the client will be where the game is rendered
and displayed to the user. Additionally, the client will handle uploading scripts to the server for parsing and interpretation
and gathering all user input. Although Java itself may not be the best tool for these kinds of tasks, Processing, a internal DSL
and library designed in Java is very useful for these kinds of tasks. Aside from having a lot of experience working with Processing,
meaning I don't need to spend much time learning about displaying sprites and detecting input, the advantage of working in Java means
I can communicate easier with the server. Since Scala still runs in the JVM, I can call any Java code natively from within Scala.
This is advantageous since it allows me to use the same Java libraries for serializing and sending classes over sockets. This means
I can simply my network communication by sending entire Java classes as messages between the server and client.

###### Any significant syntax design decisions you've made and the reasons for those decisions.
I have outlined some of my syntax decisions and reasonings in earlier sections. Overall, I want my language to resemble C since
this game is supposed to teach general coding skills. Thus, rather than diving into all the individual details of C-like syntax, I
will instead provide an example script I wrote on a whiteboard for a ship. Any feedback on this script would be greatly appriciated.

    void init() {
      int g_flee = 0;
    }
  
    void main() {
      double percent = getHull() / getHullMax();
      if (percent > .2) {
        aggressive();
      } else {
        flee()
      }
    }
    
    void aggressive() {
      if (radar(0) > 0 && canFire()) {
        fire();
      }
    }
    
    void flee() {
      int i = 0;
      for (i = -15; i <= 15; i++) {
        if (radar(0) > 0) {
          g_flee = i;
          break;
        }
      }
      if (g_flee != 0) {
        int turn;
        if (g_flee > getRotMax()) {
          setRot(getRotMax());
          g_flee -= getRotMax();
        } else if (g_flee < -getRotMax()) {
          setRot(-getRotMax());
          g_flee += getRotMax();
        } else {
          setRot(g_flee);
          g_flee = 0;
        }
    }

Note: You can find descriptions of the ship API functions in this week's Notebook entry.

###### An overview of the architecture of your system.
Again, I am going to be brief in this answer since these points have been mostly addressed in the other questions so far. The
game server will be written in Scala. This will run the game loop which will in turn interpret and execute code. After each game
loop update, the server will send a message classes to connected clients so they can draw an updated version of the game and provide
any error messages to the correct players.

The client will have a thread that listens to its network connection with the server and queues up commands to be drawn for the next
frame. Although I am hoping to run this game at 30fps, I am not sure if there will be lag (I don't imagine this entire process is
that slow, especially on a local network). Additionally, the client will send important click information and "uploaded scripts" to
the server so the game state can reflect these changes.
