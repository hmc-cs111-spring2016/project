Right now, it looks like the biggest part that you haven't put consideration into is your syntax. Awesome job on getting the
prototype up and running though!

I guess I don't know what progress you've made since your last design journal (given this critique is a bit behind the times), but 
have you had a chance to get good user feedback on how your syntax currently stands? Another thing you might try doing is looking at
other similar languages and seeing what sort of vocabulary/set up they use in order to create something that your typical user is
used to.

Also, I like how you note that writing your own API is a "double edged sword" because I think that really hits at some of the key design
problems of making a DSL. That in and of itself seems like it's been a good way to inform future progress.

I'm assuming piltest.py is your example program (awkward if it's not). The part that I'm a little confused about is 
"draw = ImageDraw.Draw(im)". Is this enabling your image to become drawable? Maybe there would be a better way to communicate this to the
user? Also, could you maybe replace the "import etc etc etc" with something a bit nicer to write out?
