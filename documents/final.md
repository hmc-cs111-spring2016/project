## Introduction

I was raised on video games! When I was in preschool, my parents got me my first game console, a Gameboy Color. Although I initially started with a Tom & Jerry (my favorite cartoon) and an Elmo game, I soon evolved into Pokémon.

Pokémon was a challenging game for a barely literate preschooler. Although simplistic in mechanics, the game is chock full of words not commonly found in a preschoolers lexicon – i.e. aurora, guillotine, hypnosis, metronome, psychic, etc. My mom, who worked from home, would tell me to come ask her whenever I saw a word I didn’t know. As a result, I would often end up playing Gameboy alongside my mom sporadically asking her for a vocabulary lesson.

A few years later, after getting a Nintendo 64, I was now playing The Legend of Zelda: Ocarina of Time. Aside from large quantities of text, this game also had very sophisticated puzzles to solve and dungeons to traverse. As a result, I, as a first grader, needed a guide book to complete the game. The only snag was the guidebook, chock full of maps, complex instructions, and secrets, was also challenging for a first grader. As a result, my mom would sit next to me while I played the game, teach me how to read maps, teach me how to sound out words in the guidebook, and guide me in interpreting the complex instructions.

Years later, in fourth grade, I got into playing a computer game called Starcraft. A complex real-time strategy game, Starcraft allowed players to build up and control the armies one of three alien races in a variety of scenarios. For the adventurous individual, however, the game also had a built in Map Editor and scripting language for creating your own levels within the Starcraft engine. These custom games I designed became my first experience with programming.

As I got more interested in Starcraft’s level design, I began using advance design tools like SCM Draft 2 and StarForge, two 3rd party map editors with advance programming features.

Looking back on all these games and how much a learned, I am faced with two important lessons:
   * People will spend an inordinate amount of time on video games
   * People don’t mind games that have a challenging learning curve (sometimes this makes it more fun).

As a result, my goal is to create a game that is, in its essence, a concentrated version of these experiences. That is:
   * **The game will be fun.** This may sound like a given, but there are often educational games that, while potentially very informative, are basically just a UI wrapper around a math worksheet. Although this still serves an educational purpose, I would assert players will not play obviously educational games as much as “disguised” games.
   * **The game will have layers.** In other words, the game is accessible and fun to various skill levels. For instance, although I was a little young to play the Zelda game as a first grader, by using the guidebook, I was able to enjoy the same game as someone who was old enough to be solving the puzzles themselves.
   * **The game will have an educational core mechanic.** In other words, there is a valuable skill that is being exercised indirectly though the game. For instance, with Zelda, I was indirectly learning how to read maps!

Specifically, just as I learned with the Starcraft map editors, I am interested in my game facilitating the learning of programming skills. Ideally, the game should help inspire new or novice programmers to learn coding while also possessing a depth that makes it challenging experienced programmers. Ideally, the earlier levels will be focused towards basic tasks like using loops, variables, etc. and more advanced levels will revolve around AI and navigation problems.

A DSL is an appropriate solution for this project since a game that revolves around coding will need an embedded language inside the game. By creating an external DSL from scratch, I can have full control over the syntax (which I want to be a simplified, yet C-like language) as well as the compiler and runtime errors it produces. By hooking into to these very specific errors, I can then have the game provide very specific voice feedback that will assist novice programmers in understanding the kinds of errors common to many programs.

For the context of the game, I have decided to create a space scenario where the user can code for individual spaceships with different hardware. Each hardware device (such as weapons, shields, etc.) has a language level API-like interface the users can use to interact directly with the game environment.

## Language design details:
	
Although C has the reputation of being a challenging first language, I would assert this is not truly because of the syntax. I would venture that a majority of the challenging parts of C come from the wackiness of `structs`, `pointers`, `malloc`, etc. Since I do not foresee a need for these more frustrating aspects, modeling a language off of C should be fine. Sure languages that take advantage of more English-like syntax (like Python’s `in` operator) might be slightly more accessible to a complete novice, given the educational goals of this game, a C-style seems to make more sense. This is because many popular languages, like Java, C++, etc., more often resemble a C-like syntax than a Python-like syntax.

This language is conceptually interesting since, although I would strongly assert it is an internal DSL, it is trying to appear as a (limited) general purpose language. Therefore, user will write a program for this language by coding in any average text editor (text editor C-syntax highlighting in Sublime Text has been shown to work) and then upload it into the game. Given the typical computation model of games (i.e. update the game state update 30 times per second), the top level structure of a program will differ slightly from a typical C program. In other words, in addition to the typical `void main()` function required of all C programs, this language will also have an `void init()` function. The idea is when the script is first uploaded into the game, the `void init()` is called once. This allows for there to be a persistent state between individual runs of `void main()`. This means `void main()`, on the other hand, is run once per game update.

Since the language strongly resembles C, however, one could argue this is not much easier than just simply using C itself. This is a false conception! This language adds the advantage of the ship hardware API calls, which increases ease of doing interesting thing (doing graphics and game design in pure C is horrible) as well as improved error messages. Any difficulties, however, related to the syntax are completely intentional since a target goal for novice programmers is to learn to read and write code in the quirky conventions seasoned programs are used to.

As far as data structures in the DSL, for now there will only be function declarations and primitive variables (no `structs`). This allows user to perform computations often possible with pure C. This means typical C control flow structures are present in this language (i.e. loops, function declarations, returns, breaks, etc.) This means the primary difference between this language and C is I/O operations. Instead of having a suite of functions that allows our programs to interact with files and the OS, these programs will have the ship hardware API functions. For instance, instead of processing input read in from a file, a user might process input from the ship’s radar unit. This means the input to these programs is the current game state and the output is how the given ship will behave when transitioning to the next game state.

Programs can go wrong in two primary ways:
   * Typical C errors. Although this is more of a conglomerate of errors than a specific one, things that would make C unhappy will make this language unhappy. This means things like invalid syntax, using undefined variables, calling non-existent functions, assigning or calling functions with incompatible types, will cause errors. Since these errors are unavoidable, the approach to addressing these is having ways for the game to pleasantly inform users of these errors via dialog and clear error messages (rather than the spaghetti mess compilers like GCC spit out).
   * Termination/running duration errors. Since the `void main()` function needs to be called once per ship per game state, the `void main()` function needs to complete execution within a restricted time frame. Although maximum number of statements will need to be experimentally determined based of off minimum hardware, etc. there needs to be some runtime error not present in C that throws if programs are running for too long.

As far as I know, there are no other DSLs that attempt to integrate gameplay mechanics and programming quite as I do. That being said, the idea of a `void init()` and `void main()` functions strongly resembles animation and game design DSLs, such as Processing (although `void main()` is often called things like `void update()` or `void draw()`). Additionally, the mechanics of the game seem to be a heavily expanded command set of the popular CS education paradigm Turtle (i.e. top down view of a actor interacting with its environment).

## Example program(s)

Since my goal is to eventually have a game playable by multiple people simultaneously, I chose to structure my game in a typical network system design (i.e. server and client). The server will run the game logic, parse uploaded code, execute uploaded code, etc. The client, alternatively, will simply display the game from a given players perspective as specified by the server.

[IMAGE]

Since we have seen in class how convenient Scala is for implementing parsers and executing intermediate representations, I chose to write the client in Scala. For the client, however, since I am very comfortable doing GUI design and drawing loops in the Java library Processing, I chose to use Java. Although having a different language for the server and client may be frowned upon in many cases, it works out quite conveniently here since both languages run within the JVM. This means when transferring data from server to client, we can actually use the Java `InputObjectStream` and `OutputObjectStream` between the languages with minimal effort.

#### Sample Program

    void init() {
      int g_flee = 0;
      set_vel(get_vel_max());
    }
  
    void main() {
      double percent = get_hull() / get_hull_max();
      if (percent > .2) {
        aggressive();
      } else {
        flee()
      }
    }
    
    void aggressive() {
      if (radar(0) > 0 && can_fire()) {
        fire();
      }
    }
    
    void flee() {
      int i = 0;
      for (i = -15; i <= 15; i++) {
        if (radar(i) > 0) {
          g_flee = i;
          break;
        }
      }
      if (g_flee != 0) {
        int turn;
        if (g_flee > get_rot_max()) {
          set_rot(get_rot_max());
          g_flee -= get_rot_max();
        } else if (g_flee < -get_rot_max()) {
          set_rot(-get_rot_max());
          g_flee += get_rot_max();
        } else {
          set_rot(g_flee);
          g_flee = 0;
        }
    }

This language is very much an external DSL where the game engine serves as the compiler/interpreter. Below are some design aspects with corresponding diagrams in this repo:

#### Ship API
##### Weapons -- Basic Blaster
   * `void fire()`: If the basic blaster can fire, it fires. If it cannot fire, it throws an error.
   * `bool can_fire()`: Returns `true` if the basic blaster can fire and `false` otherwise.

##### Engine -- Movement
   * `void set_vel(int v)`: Sets the ship's velocity magnitute to the provided number. If the number is greater than the maximum velocity it throws an error.
   * `int get_vel()`: Returns the ship's current velocity magnitute.
   * `int get_vel_max()`: Returns the maximum ship velocity magnitute.
   * `void set_rot(int r)`: Sets the ship's rotation velocity in degrees/update. In otherwords, `r = 0` means directly forward, `r < 0` means some degrees off in the clockwise direction, and `r > 0` means some degrees off in the counter-clockwise direction.
   * `int get_rot()`: Returns the ship's current rotation velocity.
   * `int max_rot()`: Returns the maximum ship rotation velocity.

##### Hull -- Health
   * `int get_hull()`: Returns the ship's current health.
   * `int get_hull_max()`: Returns the ship's maximum health.

##### Sensor -- Detection
   * `int radar(int angle)`: Looks in the provided relative direction. If there is an object that is within the radar's maximum range, it will return the distance. Otherwise it will return `-1`.
   * `int radar_range_max()`: Returns the maximum range the radar can detect objects.

##### Communication Link -- Debugging
   * `void alert(String msg)`: Displays a string message above the ship in game. Useful for debugging.

#### Abstract Syntax Tree

      case class FuncDecl(rtnTy: Type, name: String, param: List[VarDecl], body: Block)
      
      case class Type() // TODO add ArrayTy
      case class IntTy()        extends Type
      case class DoubleTy()     extends Type
      case class BoolTy()       extends Type
      case class CharTy()       extends Type
      case class VoidTy()       extends Type
      
      case class VarDecl(ty: Type, name: String)
      
      case class Expr()
      case class Literal()                        extends Expr // TODO add ArrayLit
      case class IntLit(i: Int)                   extends Literal
      case class DoubleLit(d: Double)             extends Literal
      case class BoolLit(b: Boolean)              extends Literal
      case class CharLit(ch: Char)                extends Literal
      case class Var(name: String)                extends Expr
      
      // uniop
      case class NegateOp(expr: Expr)             extends Expr
      case class NotOp(expr: Expr)                extends Expr
      
      // binop
      case class AddOp(left: Expr, right: Expr)   extends Expr
      case class SubOp(left: Expr, right: Expr)   extends Expr
      case class MultOp(left: Expr, right: Expr)  extends Expr
      case class DivOp(left: Expr, right: Expr)   extends Expr
      case class ModOp(left: Expr, right: Expr)   extends Expr
      case class AndOp(left: Expr, right: Expr)   extends Expr
      case class OrOp(left: Expr, right: Expr)    extends Expr
      case class LtOp(left: Expr, right: Expr)    extends Expr
      case class GtOp(left: Expr, right: Expr)    extends Expr
      case class LeqOp(left: Expr, right: Expr)   extends Expr
      case class GeqOp(left: Expr, right: Expr)   extends Expr
      case class EqOp(left: Expr, right: Expr)    extends Expr
      case class NeqOp(left: Expr, right: Expr)   extends Expr
      
      case class SubscriptOp(left: Expr, right: Expr)  extends Expr
      
      case class Statement()
      case class Block(body: List[Statement])                                                     extends Statement
      case class IfThenElse(cond: Expr, bodyTrue: Block, bodyFalse: Block)                        extends Statement
      case class Call(name: String, args: List[Expr])                                             extends Statement
      case class For(init: Statement, cond: Expr, inc: Statement)                                 extends Statement
      case class While(cond: Expr)                                                                extends Statement
      case class Break()                                                                          extends Statement
      
      // side effect op
      case class Assign(name: String, expr: Expr)                                                 extends Statement
      case class DeclAssign(decl: VarDecl, expr: Expr)                                            extends Statement
      case class AddAssign(name: String, expr: Expr)                                              extends Statement
      case class SubAssign(name: String, expr: Expr)                                              extends Statement
      case class MultAssign(name: String, expr: Expr)                                             extends Statement
      case class DivAssign(name: String, expr: Expr)                                              extends Statement
      case class ModAssign(name: String, expr: Expr)                                              extends Statement
      case class PreIncOp(expr: Expr)                                                             extends Statement
      case class PreDecOp(expr: Expr)                                                             extends Statement
      case class PostIncOp(expr: Expr)                                                            extends Statement
      case class PostDecOp(expr: Expr)                                                            extends Statement

#### Ship Behaviors
   * `Acceleration`: A trait that inherits from `Velocity`. Possessing this trait means movement depends on velocity and acceleration.
   * `Allegiance`: A trait that inherits from `Solid`. Possessing this trait means the object has an alliance with other units. This means its collision behavior only affects enemies. This is useful for controlling friendly fire.
   * `Corporeal`: A trait that most things inherits from (last week this behavior was a part of `Drawable`, now `Drawable` possesses this trait). Possessing this trait means the object has a position and direction in the "world." I'm not sure how I feel about the name.
   * `Expiration`: A trait that inherits from `Mortal`. Possessing this trait means the object will die after a certain duration regardless of the circumstances.
   * `Explosive`: A trait that inherits from `Expiration` and `Solid`. Possessing this trait means the object will die upon collision with something and inflict damage.
   * `FFExplosive`: A trait that inherits from `Explosive` and `Allegiance`. Possessing this trait means the object will only explode and damage enemy units. The FF stands for Friendly Fire.
   * `Health`: A trait that inherits from `Mortal`. Possessing this trait means the object has health and will die when it reaches or goes below 0.
   * `Mortal`: A trait that means the object can die. This can be useful for differentiating between background indestructable objects and player objects.
   * `Movable`: A trait that means the object has an `updatePos()` function. This means the object can move between game loops.
   * `Solid`: A trait that inherits from `Corporeal`. Possessing this trait means the object can collide with other `Solid` objects.
   * `Velocity`: A trait that inherits from `Movable`. Possessing this trait means movement depends on velocity.
   * `Drawable`: Not a trait, but an important Abstract Class. Anything that inherits from this can be drawn on the client side display. Drawable uses the `Corporeal` trait since a drawn object needs a location on the map.

## Evaluation:

As I mentioned before, my program is a bit of an oddity in the DSL world: it is an external DSL that is attempting to feel like a general purpose language. It is for this reason the language does not feel very DSL-y by design (it is very closely related to C). This choice, however, makes sense since, while DSLs often exist to help ease the users with some task, the goal of this DSL is contrary to most. While languages like ContextFree exist to help ease expressiveness (in this case artistic expressiveness), my language aims to ease the process of familiarizing oneself with typical programming style and problems common to general purpose languages. It is for this reason, simplifying the syntax to be more superficially DSL-y would defeat the most DSL-y part of this language!

Although I am sad I did not get as far as I was hoping with this project (it was very ambitious of me), I am very pleased with the design process I executed and strong foundations I have laid. From the onset, I planned to continue working on this project beyond the scope of this class. This means by adequately planning my architecture, none of the code I wrote will have been wasted or need to be redone.

That being said, at the moment, the entire language does not work together. While this means someone could theoretically say “Nothing works well in the language,” I would not choose this language. Upon closer inspection, we see a well thought out architecture where many sophisticated individual parts are coming together. As it stands, I would say there are currently 8 individual parts to this project.

   * Parsing component. The most complete component of the language. This parser, stored in Parser.scala, can completely parse valid languages in this DSL and represent them in the correct intermediate representation. This is especially impressive because the syntax of C-like languages is very difficult when compared to things like Picobot, which we worked with earlier this semester. Although I did occasionally look at Prof Ben’s Garden language to better understand some of the parser operators used by Scala, I made sure not to look up any C parsers. A major part of this project for my personal growth was to figure out how to parse C myself to gain an appreciation for programs like the Scala compiler, which contains an even more complex parser than GCC. Although I did not have a chance to explore better error messages (currently the exceptions are the default PackratParser errors), I will in the future look into having a more verbose and explicit set of fail parses.
   * Intermediate representation component. Tied with the parser as the most complete component of the language, I have created an intermediate representation capable of representing most aspects of C. Like with the parser, a major part of this project for my personal growth was to figure this out without looking it up. Although I am sure there are many resources that would explicitly walk through the AST of C, I wanted to brood on it and ultimately “reinvent the wheel” myself. I was particularly proud of the `Eval` case class which allowed me to use a `Call` as an expression.
   * Type checking component. Although currently incomplete, a majority of the layout is there. The primary hold up here is the interactions with the interpreter. Since type checking expressions requires knowing what variables are currently declared in a given enclosure, the type checker needs knowledge of the environment and global function definitions. Since the interpreter is still incomplete, however, interacting with the environment is currently unfinished. I need to solidify how the environment works before I can complete this.
   * Interpreter/compiler/game logic component. The interpreter/compiler/game logic is the loadstone for this entire project. Within this eventually multi-threaded component, individual interpreters will run for each ship during each update of the game. These interpreters will be responsible for running the abstract syntax tree of the ships code in addition to sending network messages and updating the game state. Due to some challenges with handling side effects, this part is currently incomplete. As it stand, the current approach to be implemented is to have the environment be a map going from variable name to a memory address (i.e. a String key used in another map that will represent the interpreter’s memory). Then there will be a global map called memory that stores the type information and variables. This implementation allows there to be multiple variables with identical variable names in different scopes while allowing side effects to persist to their scope of origin.
   * Ship definition component. Defining ship the hierarchy of ship hardware is important in selecting what API functions a given interpreter has access to. Although the overall inheritance of this section has been defined, since the interpreter is incomplete, it cannot be put to use. I also need to finish defining mappings between the Scala primitives and my DSLs primitives. This is necessary since calling a ship’s API function happens using my DSLs primitives, but, since that function modifies the games state, it must execute as a Scala function.
   * Networking component. Although incomplete, a simple proof of concept exists that sends an object from the server to the client through the Java ObjectStream. Further progress has not been made on this since the specifics of a message are to be determined by the interpreter, which is incomplete.
   * Drawing component. Run entirely in the client, the drawing component takes the series of network messages received from the server and draws the entire scene. Although this part is currently incomplete, it is not terribly difficult given Processing. Since I found the sprites and the server is not ready to send meaningful messages, I chose to make this a low priority. This especially seemed like a good idea since I wanted to prioritize the language design aspects of this project over the UI for a DSLs class.
   * Testing component. This component remains mostly un-started, unfortunately. Although testing is a very important aspect of all projects, and is something I spent a lot of time mulling over, not enough parts of the project work yet for any of the tests to be verifiable or meaningful. That being said, I have run the parser on sample programs and confirmed the AST generated is correct manually. As the project progresses, this will become a more important aspect of the development process.

Although I am very proud of what I have accomplished and I seem to be on track for eventually achieving my ideal vision for this language, much can be improved (since it doesn’t fully work yet). Aside from further progressing on the project so things like well-structured documentation become more feasible (this code base, still, very much resembles a dev branch and the interacting parts are still very subject to change), something I can begin working on now is Scala convention. Although my Scala undoubtedly improved tremendously by coding this very ambitious project, I am still not sure how Scala-y my conventions are. For instance, I have caught myself on multiple occasions using a return statement, although those are mostly unneeded in Scala. Additionally, although I do honestly believe I need them in this program, I find myself using `Mutable Maps`, instead of the more typical `Immutable Maps` and the `var` identifier instead of `val` in several instances. Once the code is more complete, I would love to do an extensive code review with a veteran Scala programmer (would you be open to this Prof Ben?)

As far as my evaluation plan goes, I super underestimated literally every aspect of this project. Since I framed this project so much as an exploration and personal development experience, I found myself sinking magnitudes more time into individual parts than I ever though was possible. Although, given my desires, I would not have changed this, I do wish I could have shown off a bit more functionality by the end of the project. I cannot complain, however, since I achieved my two major goals for this course: make progress on this game and challenge myself to reinvent the wheel and thus gain a bigger appreciation for PLs and DSL work. Although my project, which mostly boiled down to a series of slow progress and elaborate thought experiments was often hard to critique for my partners, I found their critiques and, most importantly, the idea bouncing conversations to be invaluable. In the future, I hope to work on more project with the design philosophy of this class where I can feel free to be crazy and ambitious without the risk of utter academic failure.
