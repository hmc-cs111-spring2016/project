
## Critique

### Introduction

Overall, it seems like you're making good progress on your project. It also seems like you have
a good idea of the direction you are interested in going in. The biggest thing that is standing
out to me at the moment is your lack of a solid testing framework. I see there are some example
`.dota` files





### Repository Structure

#### .gitignore
The first thing noticed when I began to look through your repo was the large number of `.pyc`
files. Generally speaking, it is frowned upon to commit `.pyc` files to a repo since they are
"compiled Python bytecode." This would be very similar to committing a `.class` file or `.exe`
file for Java or C++ respectively. To fix this, I suggest making use of a `.gitignore` file.
Any files, folders, or file extensions included in a `.gitignore` file will be omitted from
Git commits, pushes, etc. Since remembering all the files you should be ignoring for a given
language can be difficult, this link [here](https://github.com/github/gitignore) can be used
as a good resource for `.gitignore` starter files. For Python, the specific `.gitignore` you
are probably looking for can be found
[here](https://github.com/github/gitignore/blob/master/Python.gitignore).

#### Repo Name
I noticed "Code" is the name of your project implementation repo. While this name certainly
isn't incorrect, I think the name could be more specific and descriptive. Something like
"dota-simulator" would probably be more appropriate. Luckily, you can rename repos on
GitHub without needing to remake the repo. Additionally, the old link you posted on the
project pack should still forward to the repo if you choose to rename it.

#### README.md
I noticed your README file was partly out of date!





### Code Testing

#### Sample Programs Folder
I like that you have a folder dedicated to providing sample programs for users. That being
said I think a short comment or README in the folder would help users not familiar with your
DSL get up to speed.

#### Testing Framework
It might be a good idea to consider building some formalized test programs. While I imagine
your sample programs are currently filling this role, it might be benefitial to have well
documented/commented test files. These could even double up as sample programs.





### Code Implementation

#### dota.py

##### sys.path.insert
I noticed you use `sys.path.insert()` to include the extra folders in you Python system path.
Although it doesn't really matter, it is generally considered better practice to use
`sys.path.append()` over `sys.path.insert()`. You can find a StackOverflow post on this
[here](http://stackoverflow.com/questions/10095037/why-use-sys-path-appendpath-instead-of-sys-path-insert1-path)

##### File Open
This is minor but when opening a file in Python, it is standard convention to omit the `'r'` parameter
in my experience. By default, `open(FILENAME)` is an read stream.

##### File Path String
Rather than appending your program path with string concatination with a `'/'` character, it is
generally considered standard practice to use `os.path.join`. This function ensures the correct
`'/'` or `'\'` character is inserted in the path string depending on the OS. That being said, I
believe Python doesn't care if you use `'/'` on Windows. Since typing `os.path.join` is usually
a pain, I normally do `from os.path import join as pj` as a shorthand.



#### item.py

##### Exception Message
Although you do `print` an error message before you `raise Exception`, you might want to consider passing
a `str` argument to Exception to provide users with a more descriptive Exception. Python's Exception
documentation can be found [here](https://docs.python.org/2/tutorial/errors.html#handling-exceptions).

##### Iterating through Keys
Minor, but I believe it is generally more standard in Python to check if value is a key in a dictionary by simply typing `if item_name in SPECIAL_ITEMS:` instead of `if item_name in SPECIAL_ITEMS.keys():`.

##### Reloading the JSON File
I noticed everytime you construct an `Item` object, you reload the JSON data from the file. Although this
is fine since your program is not runtime sensitive, I believe it makes more sense to load the JSON
file once on initialization into a global variable, and simply reuse throughout. Additionally, as
mentioned above, I think it is generally more standard to omit the `'r'` from the `open()` command.



#### build.py

##### Primary Stat Constants
It might make sense to create constants for `STR = 0`, `AGI = 1`, `INT = 2` so you don't need to use
magic numbers when calculating base damage in `get_base_damage()`.

##### Regeneration
I noticed your language does not currently account for hero regeneration. Although this stat is
often negligible in fights, there are certain heros like Nyx Assassin, Orge Magi, Axe, and Night
Stalker who have non-trival base regenerations. Additionally, since regeneration scales with
Strength, tanky heroes like Centaur manage to accumulate quite a bit of regeneration.

For each point of strength, heroes gain 0.03 HP/s. Additionally, hero base regenerations can
found [here](http://dota2.gamepedia.com/Table_of_hero_attributes) in the right-most column.



#### combat.py

##### range vs xrange
Although it doesn't matter in most circumstances, when larger values are provided to `range`
you will get better performance by using `xrange`. In Python 2.7, `range` constructs a list
with the desired number of elements in it while `xrange` creates an iterator. Interestingly
enought, in Python 3, `xrange` was removed since the original functionality of `range` was
replaced with the `xrange` of Python 2.7.



#### JSON Data

##### Human Readable Data
One of the big advantages of the JSON data format is its human readability. Unfortunately, as
it currently resides in your repo, all your data files contain no newlines. You might consider
running an auto formatter on the data so users can read through the data files more intuitively.
