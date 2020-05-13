# Chat room multithreading

# 1/ How to run this Chat room?
- Add your own server Public IP Address (It could be your computer IP address. Google search: "My public IP Address") in Client.java on line 31
- Enter your own directory to save userName, Password and Historical Messages in 2 files: Server.java (line 28 29) and Worker.java (line 32 33). Those directories should match. You'll understand once you open the files.
- When you run it, run Server.java on your server Computer first. And then run client.java (Either on the same computer or a different computer). You only need to run those 2 files.

# 2/ How my Chat room functions?
- Server.java will be run on a Server Computer. It will constantly receive new clients. Whenever a client is connected, Server will create a Worker (in Worker.java) in a separate thread to deal with the client. The server is then free to receive new clients.
- Each client will have a ListenClient (To receive messages sent from the Server) and a SpeakClient (To read input from the keyboard and send it to the Server)

# 3/ About my Chat room
- It is Multithreaded. Multiple clients can connects with my server at the same time.
- It is Object Oriented. I created multiple classes with specific purposes and let them communicate with each other.
- It will let you login / create new accounts, save your messages, your user data (User name, password) in a text file and retrieve it when you close the program and open it back again.
- It is not perfect. This is a fun project I did after I finished my Object Oriented Programming course using Java in Langara College. I didn't focus too much on handling exceptions, protect UserData,... The main purpose is to create an functionable multithreaded chat room. Basically I ignores all exceptions and store data in a text file (Which, I understand, is not the best thing to do). But it's fun :))) I love it!

I hope anybody interested in my project have fun trying it out :))
