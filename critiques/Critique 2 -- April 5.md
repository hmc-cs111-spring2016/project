## Response to Project Notebook

In response to Rebekah's critique last week, I really like the idea of separating key words for flavors with descriptions of those flavors. The example she gave was writing the key word "raspberries" with the description "like in my grandfather's garden." This would definitely make the database more search-able. 

You also mention later on when talking about [this critique](https://github.com/emeersman/project-notebook/blob/master/Entry%201%20--%20April%203.md) that you are struggling with the idea of how much you want to "focus on making different queries to the database versus just having the database as a basic storage mechanism." One suggestion I have from a project standpoint is, as much as possible, make sure to leave the database open for implementing querying later on in case you want to. You may find that you want to query by broader categories such as "berry" or "fruity" rather than the user's written description of "raspberry." I don't think you should make the user define these broader categories but just keep them in mind when you are creating your keywords. That way you can implement better queries later if you have time in your project. Also if you have broader categories or a hierarchy of keywords, it would allow for more extensibility of keywords over time so that you don't have to come up with keywords all at once.

## Response to Project Notebook Questions

_Would the visualization of a wine based on the notes be a good application?_

I think that if you find the correct visualization, it could be very powerful, however I would really like to see comparison of two or more different reviews in a visualization. Whether that comparison is between two different wines from the same user or two different reviews of the same wine, I think multiple visualizations would definitely be preferable. 

_Would it be all right if the users can't compare their opinions with something more professional?_

I think that it would be nice to link to notes of something more professional if that is an option. You don't necessarily have to compare notes in the same format that the user gives. If professional notes only consists of a paragraph of natural language, you could always just give that paragraph to the user and let them do a personal comparison. 

I think one alternative to comparison with professionals is being able to compare with friends. I wine tasting is usually done in groups, you could allow everyone in that group to write their own review of the wine and then compare the reviews in a visualization.

_If the notes in the cellar could be compared, would this also be appealing to users?_ 

I'm assuming here that the user's notes are in natural language format. If this is the case, I think like I mentioned above that you could just give the user the notes in that format and they could do the comparison themselves. Also if you are doing a visualization, putting the notes side by side would provide a pretty easy comparison. This would be an easy way to compare personal notes without doing any NLP. 


_Are your [initial wine tasting notes](https://goo.gl/photos/Kqqp9qn346wKMWQR8) in good format?_

For color/appearance, I'm not sure if you are planning on implementing a GUI for users but it would be nice to be able to see the color they're selecting. If you had a color selector, then the color would be represented internally as something like HEX of RGB which I think would be much easier to search by to find similar colors in a database. I don't think that keyword colors are a good idea. You didn't really specify how you were going to implement color but those are my suggestions. 

I like your idea of rating each each aroma out of 10 but it is a little confusing what that rating actually means. Does it mean the strength of the flavor of the quality of it. Maybe specifying that or adding a different category for each would be helpful.

About the overall rating, if you are also rating specific aspects of the wine, an overall rating might be redundant. You could possibly derive an overall rating from these subcategories unless there is an area that the subcategories do not cover that an overall rating would. I think deriving an overall rating from subcategories would also provide more consistency because different users might might rate different categories as more important than others. Because of this they might only want to search for high scores in specific categories. 

_If I end up doing the visualizations, any suggestions for a language I could use?_

The one visualization tool I know of is [Kibana](https://www.elastic.co/products/kibana) but that is more a visualization for big data and I'm not sure how customizable it is. If you were looking to visualize a picture of a wine glass I'm not sure if it would be able to do that. 



