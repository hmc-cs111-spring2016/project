# Language design and implementation overview

## Language design

*How does a user write programs in your language (e.g., do they type in commands, use a visual/graphical tool, speak, etc.)?*

With my current project design, a user writes programs in the language by typing tasting notes into a file structured with specific headers (Name, Color/Appearance, Aroma/Flavor, Characteristics, Notes, Score). Ideally, the user would be given a GUI with a specific structure. However, due to time constraints, a very specifically structured file will represent a program. One file corresponds to a tasting note, so one wine is reviewed per file. The user can then perform operations on the wines.


*What is the basic computation that your language performs (i.e., what is the computational model)?*

The current plan is for the language to produce visualizations of wines based on the user's tasting notes. For example, a user would write their tasting notes into a file called "merlot.txt" and could then run a command like `visualize merlot` to get a graphic representing their opinions on that wine. In that sense, it's a visualization language so the computation occurs by parsing user notes into a comprehensive wine description and creating images with that description.


*What are the basic data structures in your DSL, if any? How does a the user create and manipulate data?*

I gave a brief overview of these structures above, and [this image](https://goo.gl/photos/zMGjqN2WkctZZsH7A) shows how a main program would be structured. A user would create data in files (like on the left side of that image) and would be able to tweak these files. The data would be manipulated by running various commands for visualization. Time permitting, multiple wines could be compared side by side, or wines could be stored into a user database and the language could be helpful in searching that database for particular types of wine.


*What are the basic control structures in your DSL, if any? How does the user specify or manipulate control flow?*

The basic control structures are the attributes of wine that the user would write about in their tasting notes. I discussed this near the end of my weekly notebook at length, but one example of a structure in my DSL would be the Aroma/Flavor attributes of the tasting notes. This section in the tasting notes would permit users to list as many aromas or flavors that they detect in the wine. They would include a one or two word description of that attribute and could then specify the strength of the attribute and where in the tasting they detected it. This would be a very particular structure in the DSL, since it is only one of the many sections involved in the evaulation of a wine. However, the Aroma/Flavor section would be composed of many attributes which all follow the same general structure.


*What kind(s) of input does a program in your DSL require? What kind(s) of output does a program produce?*

As discussed, the program would require a specifically structured tasting notes file. Each section would have a different format. I explained the intricacies of the Aroma/Flavor section, but something like the Notes section of a program would just be a space for the user to write additional impressions of the wine. The user input from that section would just be a paragraph. If the language were to evolve, that section could always take on a more specific structure if necessary for certain applications. The DSL would produce a visualization of the user's tasting notes. This would include a graphic with all the text information given about the wine as well as graphical charts for the Aroma/Taste section and possibly other sections. If I include a Characteristics section (rating tannins, acidity, sweetness, etc) I could produce a graph for that, as well. I still need to think more about what this visualization would look like, but I want it to be a user-friendly visual representation of how they perceive a wine. I could always make it so that the user could specify the amount of information in a visualization and/or how it is formatted in the output, but that would be ambitious given the scope of the project.


*Error handling: How can programs go wrong, and how does your language communicate those errors to the user? What tool support (e.g., error-checking, development environments) does your project provide?**

Programs can easily go wrong with incorrect structure. At this point, the language would communicate these errors to the user through error messages if the user tried to run a visualization from the command line. The biggest source of error I could see is if users don't format a tasting notes file right and end up with oddly structured Aroma/Flavor attributes. I will include a lot of error catching statements and then throw messages such as "Tasting notes file not structured correctly." Depending on how specific the errors are, I could try to mention the section of the file where the user needs to fix the formatting. A lot of these problems could be fixed with a more rigidly structured GUI. I could also see errors popping up in the visualizations. I will try to account for these by having good things to display to the user if, for example, the program runs and produces an erroneous visualization. The language could include a default sad face image in place of a missing chart to communicate that error to the user. The project provides the error-checking and development tools available in the Scala IDE. I expect to run preliminary programs similarly to the piconot external syntax and have the same support as that project.


*Are there any other DSLs for this domain? If so, what are they, and how does your language compare to these other languages?*

I have seen a lot of helpful tasting notes formats and visualizations about wine. These visualizations have helped me learn about wine and difference between types of wine, regions, etc. This inspired me to write this DSL, since I want to have a way to visually represent my perception of a wine. I haven't found any other DSLs that visualize personal tasting notes. I have only found wine infographics. The closest that I have found for wine DSLs are several tools [here](http://tashian.com/wine-flavors/) and [here](https://public.tableau.com/profile/xky1231#!/vizhome/WAWineprototype_111314/Dashboard1) that map out different flavors primarily associated with types of wine. The second link is particularly helpful for visualizing the flavors and information about specific wines, but it doesn't allow for users to input their specific impressions of those wines. I still think that my projects has a much more personal component, especially since it's centered around the individual experience of tasting notes.


## Language implementation

*Your choice of an internal vs. external implementation and how and why you made that choice.*

I chose an external implementation because I want the users of the DSL to be able to write tasting notes as the programs. I think it's important for a wide variety of users to be able to use this language. Implementing it as an external DSL means that the tasting notes are the program in the language and a range of users can write and run programs with relative ease.


*Your choice of a host language and how and why you made that choice.*

I chose Scala for the host language because it is flexible and will let me create and combine different components of my language. I know it is possible to define a language format in Scala and parse that language with relative ease. A large part of my decision was based around the fact that I had experience doing piconot in Scala and I can see many similarities with this language design and that of our external syntax for piconot. One question that I still have for the project is how to do the visualizations. This question came up quite recently as I tried to come up with new applications for my project. It is likely that I will need to integrate a visualization language with Scala, which is a questionable decision that might prove to be challenging in the near future. 

*Any significant syntax design decisions you've made and the reasons for those decisions.*

After a lot of research into the format of tasting notes, I made the decision to give users a lot of freedom in their notes but to constrain them within a general note structure. I go into this a lot in my journal entry for this week, but since tasting notes are for the writer, I wanted to ensure that there was space for personal expression. However, I tried taking notes in some formats that didn't have enough categories and that didn't give me enough ideas for what to write. I decided to stick with an involved but flexible notes format. The Aroma/Flavor section formatting that I discussed above was structured in order to easily create a visualization if desired. This can let users make pie charts or other graphs from the notes based on the stregth of unique flavors that they experienced in a given wine.


*An overview of the architecture of your system.*

I've discussed the system in depth, but the general system architecture is that the user will write a program in a specifically structured file (ideally a GUI, though this isn't feasible given time constraints). The program corresponds to the user's tasting notes for a specific wine, so it will include sections for Wine Information, Appearance/Color, Aroma/Flavor, possibly Characteristics, Notes, and Score. Once tasting notes have been written, a user can create visualizations from them to see what they thought about a particular wine. The tasting notes language will be implemented in Scala, though I might bring in a different graphical language to do the visualizations for wines. It depends on how much flexibility I want with the visualizations and whether I want users to be able to tweak the graphical parameters or compare wines with one another.

