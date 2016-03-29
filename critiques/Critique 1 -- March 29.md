## What do you like about the project?

I actually think this is an awesome idea for a DSL. As I said in person, I had a previous experience with a similar DSL and it was really, really fun to work with. If done right, this could combine elements of complex strategy games with programming to create an experience that is uncommon, enjoyable, and competitive.

## What are some pitfalls / rabbit holes / risks for the project?

Just to get your DSL working on a minimal level will require getting many different systems working in harmony with each other. Coding a GUI with processing, transfering objects over sockets, and maintaining a separate client and server involves a lot of diverse and non-trivial work. There's also a lot of potential rabbit holes that you could get stuck in. No matter how hard you work, you could probably further improve whatever graphics you come up with. Also, depending on how much you care about this DSL, a lot of time could be spent on balancing the DSL and making sure each side has a fair chance (so both sides would tie if they used the same strategy). You will also have to ultimately decide what you want to do when it comes to penalizing programs for long-running code. Do you allow arbitrarily complex programs or will they be penalized by taking forever to move (or never making a move because they don't finish executing before the frame needs to be drawn)? Another potential pitfall is the number and design of units in your game. For sanity's sake, you should probably start small and work your way up so that you can guarantee you'll finish in good time.

## How can I (the critiquer) help the implementer?

I probably won't be very helpful with processing and you probably know sockets and object serialization about as well as I do, so I won't be able to help much there. However, I could definitely help with design decisions and implementing parallelism to help your game run smoothly (which could turn out to be surprisingly hard depending on how complex user's programs can get). If I have lots of time, I could also help do balance testing on your game to make sure that it is competitive.

## What do I (the critiquer) think the implementer should do first?

Design will obviously be important. Work out the details for a good set of starting units (complete with information about what sensors / weapons they will have and how they will work) and what the DSL will look like. Writing some example programs could be very helpful during this stage. Also, it would be good to start figuring out any other details that will be necessary for developing other parts of the DSL so you don't get behind later on.
