# JamJoin-Android-App
An app built in 24hs during the great HackKing's Hackathon.

#Inspiration
We were looking for a service that woud help us make a team at the hackathon, so we came up with this app that suggests you people you should talk to at a given event based on common interests.

#What it does
Jamjoin makes you connnect with the people you share interests with. Join an event and when you get there you can see the people at the event ranked by common interests with you. You can also search for someone you already know or someone with a particular interest. Then just go talk to them! This way we want to encourage human interaction with people who could be your new best friend.

#How we built it
Jamjoin runs on a node.js server on AWS using express.js and MongoDB. The andorid client is built in Java using the Andorid SDK and the webapp using Angular 2 and CSS. Both client connect to the server through the API we created using express.js.

#Challenges we ran into
Building the server withouth any prior knowledge of node.js nor mongoDB queries has been challenging especially when managing queries and authentication requests, but luckily we were peer-programming. On the client side the most challenging part was building the recycler views on the AndoridSDK.

#Accomplishments that we're proud of
We are proud of getting the whole project fully working both client and server. We're proud of having worked as a team, doing often pair programming both to catch bugs and build new staff.

#What we learned
During the wekend we learnt so many new technologies. Andrea learnt how to program in node.js starting from the workshop from CapitalOne, and how to interact with mongoDB. Emanuele strenghtened his skills by building the app in no time during the morning, having spent the day before pair programming with Andrea on the server. Pietro learnt how to setup a web server by deploying on AWS, both the client Angular.JS code and the server Node.JS one.

#What's next for Jamjoin
In the near future we are planning to complete the webapp in Angular and to add a geolocalization feature so that when the server receives a request it first checks if the client is in the event location using the Google Maps API. After that we are planning to integrate it with the Slack APIs and the EventBrite API before releasing it to the public.
