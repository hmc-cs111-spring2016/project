# Project plan

## Language evaluation
*How will you know that your language has accomplished its goals? What tools, techniques, or measurements will you use to evaluate your language design? What tools or practices will you use to ensure that your language implementation is of high quality?*

Since the novel aspects of the language are the ability to create new wines and translate outside wine reviews into the new format, I think that the language will have accomplished its goals when I can perform a successful comparison between personal and professional tasting notes for a single wine. I will evaluate this success by choosing a wine containing all matching fields in a professional database. By matching fields, I mean that the wine has attributes which would match up with the attributes of my own tasting notes language. 

I will evaluate the language design in several ways. I intend to talk with a variety of wine consumers to see how they would write tasting notes. One of the biggest successes for me will be if a wide range of users can create notes that are helpful to them. If a certain type of user is dissatisfied by the format of the tasting notes, I will refine my design (depending the timeline of the project) to better accommodate their needs while ensuring that new features could be compared to professional tasting notes. 

In this case, I would think of a high quality language to be one that is stable but extensible. I want to create a basic framework for a person to talk about wine in a way that makes sense to them. Again, I will consult with people throughout the language design process so that the user is prioritized. I also plan to consult with people throughout the implementation process to ensure that user groups do not get lost along the way. I will use existing wine databases to implement this language and to perform the professional comparisons. Choosing the correct database is tricky, since I need to ensure that it contains a range of information and ratings that could be found in tasting notes. My current plan is to examine the information stored in many different wine databases, develop my own tasting notes language, then finally return to the databases and pick one that suits the needs of my language.


## Implementation plan
*How much time do you think you'll need for the various components of your language (e.g., finding a host language, implementing the semantics, the parser, tools, etc)? Provide a brief schedule (e.g., with a deliverable every week) for your project.*

I think that the development and refinement of the language will take a significant amount of time. It shouldn't take long to find a wine database. After that, I will need to set up my own database (representing the personal wine cellar of a user). Combined, the database configuration should take a week. This does not count the construction of my language, which will span at least one week as I read literature on wine tasting notes and conduct interviews. I will probably spend a week each on semantics and parsing. I anticipate that it will be a challenge to represent wines in the syntax. I also think that parsing outside wine notes to make them consistent with this new syntax will be difficult. The last week will be used for overflow of work that didn't get done in the first few weeks as well as the creation of a simple UI for the project. Obviously, this is a rough schedule and is subject to dramatic change. I think that a lot of the tasks in the project will take longer than expected and that the last week will involve a lot of scrambling to finish parsing and wine comparisons. 

Week 0 - Set up database and look into wine databases.
Week 1 - Conduct interviews, design tasting notes format, choose a professional database.
Week 2 - Implement the tasting notes syntax for a sample wine.
Week 3 - Add the ability to parse outside wine descriptions into the tasting notes language format.
Week 4 - Overflow for unfinished work, adding additional error-checking, UI implementation, etc.


## Contingency plan
*What obstacles (technical and logistical) might you encounter while working on your project? How will you look out for them, overcome them, or work around them?*

One obstacle that I might encounter is spending too much time on the design of the language and not leaving enough time for implementation. By virtue of writing this now, I will know to look out for it and try to schedule my time so that I am required to start coding by the second week of the project. 

I also might run into issues finding an appropriate third party database. It is possible I will need to hard-code some sample "professional" tasting notes to a file for testing and eventual demonstration. This project lends itself well to partial development. Even if I only develop a grammar for the language and reach the point where I can represent tasting notes in that format, it is very possible to put in fake wines for testing. The success of the language design is not entirely reliant on interfacing with other databases, so I see this as a helpful workaround if I run into major problems in the project.

I did not leave much time for UI design, though that was intentional. I know that I would spend too much time designing a nice UI for the project at the expense of the language itself. Therefore, I have tried to look out for this obstacle and overcome it before it becomes a problem. I can see myself starting the development of the UI earlier than scheduled, but I know that I would not do that with some other aspects of the project.
