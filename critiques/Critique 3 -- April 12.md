#### Features to focus on

- The biggest thing I'd focus on next is being able to notate multiple notes played at the same time.  It's important - almost all tabs will require some sort of polyphony, and it'll also be an interesting design decision to make.  For reference: in Vextab, notes in a chords are grouped together with parenthesis and separated by periods.  In ABC Notation, they're grouped with square brackets and separated by spaces.
- Fun guitar-specific features like hammer-ons, pull-offs, tapped notes, etc.  I'd recommend sitting down and trying to tab out a portion of your favorite song (using your DSL).  That should give you a good sense of what features you need to prioritize.
- Ability to see note lengths in the output.  I think you mentioned that Lily-pond will do this for you, so it should just be a matter of hooking things up properly.

#### Note lengths

I do agree that using letters instead of number for note durations is a good idea, since it prevents people confusing note lengths for pitches.  One downside of this is that you'd have to choose some arbitrary note duration as the smallest that you'll support (e.g. 64th note).  Coming up with intuitive letters for smaller durations might also prove difficult.  

While on the topic of note lengths, another idea to consider is to have a default note value that users could set as a global parameter.  Then, when tabbing out a song that's mostly eighth notes, users won't have to type "/e" at the end of every group of notes.

#### On parsing

(From class) - We discussed the possibility of having our parsers directly output our intermediate representations of data.  Since then I've started modifying mine to do just that, and it so far it's working out fine.  However, I wouldn't recommend you do the same, mostly because of what Prof Ben was saying about adaptability/flexibility.  My intermediate representation is an array of simple JSON-like objects, which is universal enough that I'd be able to easily adapt it for use with other versions of my DSL that may not use Javascript.  So there's no real downside that I see (so far) to directly outputting it from my parser.  In your case, however, from what I understand you're creating python classes that represent notes and staves.  Directly outputting your IR from your parser would therefore be more difficult (I'd imagine) and would cause you to sacrifice adaptability.

#### Misc

In class we discussed the benefits of having users type string/fret vs note/octave.  String/fret is the obvious choice if you have to choose just one method of notation, but you might want to support both.  If a user is trying to convert standard notation to tab, ideally he/she'd be able to type note/octaves, get them converted into a tab with "suggested fingerings," and go from there.
