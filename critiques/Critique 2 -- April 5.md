### Comments on your Syntax Design

In class and in your notebook you describe a potential data-interpretation model.  In this model, users enter a list of functions they want to use on a specific sound file and then run the program.  The output would be the sound file after all the functions have been run on it.  
 +The main issue I see with this is that users won't be able to hear what the sound file sounds like between each function call.  This kind of immediate feedback is crucial for doing any sort of complex, multi-step alterations to sound.  
 +Let's say, for example, that I'm trying to turn a snapping sound, "snap.wav" into a punching sound.  To accomplish this I might need to lower the pitch, stretch out the sound file, and add saturation, reverb, and delay/echo.  Even if I might be able to pinpoint exactly which functions I'll need to use, there's no way I could tell what order would be best to call them in (and changing the order of calls will drastically change the final output).  
 +For this reason, I'd go with the REPL interface.  This would allow users to hear what the sound file sounds like at each step, and either go from there or undo the last function they called and start over.  I'm guessing that using the console would probably be fine for the purposes of this project.
 +

### Other Comments
