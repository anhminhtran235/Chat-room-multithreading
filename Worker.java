import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * This worker is created on a separate thread by Server to handle 1 clients.
 * Its job is to send Server messages to client and receive messages from clients.
 * REMEMBER TO ENTER YOUR OWN DIRECTORY on line 32 and 33
 * @author minh
 *
 */
public class Worker implements Runnable{

	 private Socket socket;
	 private DataInputStream in;
	 private DataOutputStream out;
	 private List<Socket> listenClient;
	 private List<String> messageHistory;
	 private Map<String, String> userData;
	 private String name;
	 
	 private FileWriter outFileData;
	 private FileWriter outFileMessages;

	
	public Worker(Socket s, List<Socket> lc, List<String> mh, Map<String, String> ud) throws IOException
	{
		socket = s;
		listenClient = lc;
		messageHistory = mh;
		userData = ud;
		outFileData = new FileWriter("ENTER YOUR OWN DIRECTORY. Ex: /home/minh/Desktop/userData.txt", true);
		outFileMessages = new FileWriter("ENTER YOUR OWN DIRECTORY. Ex: /home/minh/Desktop/messageHistory.txt", true);
	}
	
	public void run()
	{
		try
	      {
	         try
	         {
	            in = new DataInputStream(socket.getInputStream());
	            out = new DataOutputStream(socket.getOutputStream());
	            doService();
	         }
	         finally
	         {
	        	 listenClient.remove(socket);
	        	 socket.close();
	        	 outFileData.close();
	        	 outFileMessages.close();
	         }
	      }
	      catch(IOException exception)
	      {
	         System.out.println( name + " just quitted");
	         try
	         {
	        	for(Socket s: listenClient)
				{
	        	    DataOutputStream tempOut = new DataOutputStream(s.getOutputStream());
					String message = name + " just left the conversation";
					tempOut.writeUTF(message);
					tempOut.flush();
				}
	         }
	         catch(IOException e) {System.out.println("Something very unexpected just happened");}
	      }
	}
	
	public void doService() throws IOException
	{
		// Log in (either already have an account or register new account)
		logIn();
		
		// When client first connected, send all historical messages
		String messages = "";
		for(String str : messageHistory)
		{
			messages += str + "\n";
		}
		out.writeUTF(messages);
		out.flush();
		
		while(true)
		{
			// listen for message from our client (or speakClient). Note: this worker only handle one client
			String message = in.readUTF();
			messageHistory.add(message);		// Add that message to messageHistory ArrayList
			outFileMessages.append(message + "\n");
			
			// send the message to the rest of the clients (not including this client)
			for(Socket s: listenClient)
			{
				if(s != socket)
				{
					DataOutputStream tempOut = new DataOutputStream(s.getOutputStream());
					tempOut.writeUTF(message);
					tempOut.flush();
				}
			}
			
			
		}
	}
	
	public void logIn() throws IOException
	{
		while(true)
		{
			String command = in.readUTF();
			if(command.equals("LOGIN"))
			{
				// login
				String userName = in.readUTF();
				String password = in.readUTF();
				if(userData.get(userName) != null && userData.get(userName).equals(password))
				{
					this.name = userName;
					out.writeUTF("SUCCEED");
					out.flush();
					return;					// If succeed, continue right away
				}
				else
				{
					out.writeUTF("FAIL");
					out.flush();
				}
				
			}
			else if(command.equals("CREATE"))
			{
				// create new account
				String userName = in.readUTF();
				String password = in.readUTF();
				if(!userData.containsKey(userName))
				{
					userData.put(userName, password);
					this.name = userName;
					out.writeUTF("SUCCEED");
					out.flush();
					outFileData.append(userName + "," + password + "\n");	// Save it to file
					return;
				}
				else
				{
					out.writeUTF("FAIL");
					out.flush();
				}
			}
		}
	}
}










