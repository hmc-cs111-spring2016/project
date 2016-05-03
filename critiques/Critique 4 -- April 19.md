### Critique
Since you're mostly working on implementation stuff, I won't have much to say. I have gone through your TODO list and commented on it below.

##### add optimize command to the backend / add optimize command to the grammar, ast, and eval
Since I was a huge proponent of the optimize command, it should be no surprise that I think this is something worth focusing on. However, as you suggest, it would be good to add the hero ability data and improve combat simulations first so that you don't have to revise your code later. Depending on what else you're going to add, you may just want to save this for much later so that you don't have to worry about changes from any of the other features affecting your code.

##### add hero ability data
While I'm not the most familiar with Dota 2, I think I understand what you mean by this. Hero abilities will likely make a significant difference in the quality of a build and could help identify items that compliment a hero's abilities best.

##### add efficiency somehow (probably with faked item data for buildup items)
Is this like gold-gathering efficiency or something? If so, this would also be really good to account for since more gold means more / better items. While I'm no Dota 2 expert, you may want to account for differences in starting efficiency as some heroes / items may be better at gathering gold early on than other heroes / items.

##### beef up combat simulation
Not sure what this entails, but if it will siginificantly affect the accuracy of your results, it's worth at least a little time. Just don't let this become too much of a time sink unless you can guarantee that it will make a huge difference.

##### make specifying hero/item/data names smart / include other syntax elements when I think of them
I would leave this for last. While it's a nice feature, it's not really going to make that much of a difference to a user and could potentially become really annoying. Sometimes it's best to force users to be explicit and fully type things out.

##### include hero abilities to grammar/ast/eval
I don't really understand this feature. Won't specifying a hero automatically imply that you also get all of their abilities? Or would this be for something like examining the impact of an item set on a specific ability?
