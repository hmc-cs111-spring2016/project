
# Critique 1 - Andrew Fishberg

###### What do you like about the project?
I am a Dota player. I started playing the first Dota back in 8th grade and
continue to play it today (although I eventually switched to Dota 2 in high
school, which is essentially a stand alone remake of Dota 1). Thus, almost any
project having something to do with Dota, I will like.

For those who don't know, Dota is an incredibility complex game that, while
sporting some simple basic mechanics, has an absurd number of options and
depth. Furthermore, Dota is increasingly becoming a serious enough ESport that
it will soon be no different than more established sports like Baseball and
Basketball. [Here](https://www.youtube.com/watch?v=w-9LyFLrW3Q) is a recent
TEDx talk by a professional Dota player discussing Dota.

This project is especially interesting because it is attempting to simulate
aspects of Dota, which is no simple task. There are tons of tools that exist
on the internet to help Dota players optimize plays and counterpick enemies.
Tools like this are in high demand.


###### What are some pitfalls/rabbit holes/risks for the project?
Dota is a very complex game. There are currently 111 heroes each with at least 
4 unique spells (but some with as many as 10). Additionally there are 145 items
that greatly vary the heroes abilities and fighting. While you can try to take
all of these options into consideration, I fear you may spend more time
implementing unique and quirky spells instead of working on language design.

Thus, I think one of your early tasks should be to very clearly define what
parts of the game you want to simulate. You might want to only consider carry
heroes, meta heroes, etc. This way, while you can still make a Dota tool, you
can also spent more time on the language design aspects than simulation
aspects.


###### How can you (the critiquer) help the implementer?
I have extensive knowledge of Dota and have even written some mods for it
in the past. Also, not only can I critique the code, but as a Dota player I can
also serve as a user. Thus, I believe my feedback can be very valuable.

As the project stands now, while I think it is very interesting, I'm not sure
how useful it is. That is, this tool specifically simulates battles between
heroes given ideal situations. That being said, ideal situations virtually
never happen in Dota. Dota is a 5v5 team game. If a  hero is weaker than
(assuming the players are competent) they will very rarely straight up fight
each other. Rather, players should coordinate and only take favorable fight
based on vision, tactics, and important timings.

The complexity of the game makes it almost impossible to simulate straight up.
Thus, I think you might find some use gathering data for your simulations
using sources like
   * http://www.dotabuff.com/
   * https://yasp.co/
   * http://dotaedge.com/

For instance, look at the information you can harvest from this 
[replay](https://yasp.co/matches/2211445242) through yasp.

You might even consider making your DSL be more of a query tool that
gathers information from the billions of logged games online and makes
suggestions by relating the data.


###### What do you (the critiquer) think the implementer should do first?
Look at some of the links I provided above and think about what you
specifically want to simulate. Will your simulations be the most useful if
you keep it as strictly combat or will they be better if you try to
aggregate data from other sources. Basically think about these two questions:

   * How do you want to do your simulations?
   * What subset of Dota do you think will be the most useful to simulate
   (since you can't do it all)?

