# Chat-room-multithreading
This Chat room is created using Multithreading in Java.

In order to successfully run this Chat room, you need to make some changes:
- Add your own server Public IP Address (It could be your computer IP address. Google search: "My public IP Address") in Client.java on line 31
- Enter your own directory to save userName, Password and Historical Messages in 2 files: Server.java (line 28 29) and Worker.java (line 32 33). Those directories should match. You'll understand once you open the files.
- When you run it, run Server.java on your server Computer first. And then run client.java (Either on the same computer or a different computer). You only need to run those 2 files.

ABOUT MY CHATROOM:
- It is Multithreaded. Multiple clients can connects with my server at the same time.
- It is Object Oriented. I created multiple classes with specific purposes and let them communicate with each other.
- It will let you login / create new accounts, save your messages, your user data (User name, password) in a text file and retrieve it when you close the program and open it back again.
- It is not perfect. This is a fun project I did after I finished my Object Oriented Programming course using Java in Langara College. I didn't focus too much on handling exceptions, protect UserData,... The main purpose is to create an functionable multithreaded chat room. Basically I ignores all exceptions and store data in a text file (Which, I understand, is not the best thing to do). But it's fun :))) I love it!

I hope anybody interested in my project have fun trying it out :))
