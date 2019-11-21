import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This is a server class. You run this class on your server computer before anything else!
 * This class will retrieve historical data (Messages and Username - Password) from your files
 * (If you have run it before) and then begin waiting for clients to connect.
 * Each time a client connects, the server will accept the client, create a Worker in a separate threads
 * and let that worker handle the client.
 * After that, the Server continue accepting new Client until you stop the program.
 * REMEMBER TO ENTER YOUR OWN DIRECTORY on line 28 and 29.
 * @author minh
 *
 */
public class Server {

	public static void main(String[] args) throws IOException
	{
		// Initialize stuff
		final int PORT = 1181;
		Map<String, String> userData = new HashMap<>();		// Name and password
		List<String> messageHistory = new ArrayList<>();
		List<Socket> listenClient = new ArrayList<>();
	    ServerSocket server = new ServerSocket(PORT);
	    
	    // Retrieve data
	    File userDataFile = new File("ENTER YOUR OWN DIRECTORY. Ex: /home/minh/Desktop/userData.txt");
	    File messageHistoryFile = new File("ENTER YOUR OWN DIRECTORY. Ex: /home/minh/Desktop/messageHistory.txt");
	    if(userDataFile.exists()) 
	    {
	    	Scanner sc = new Scanner(userDataFile);
	    	while(sc.hasNextLine())
	    	{
	    		String[] s = sc.nextLine().split(",");
	    		userData.put(s[0], s[1]);
	    	}
	    }
	    if(messageHistoryFile.exists())
	    {
	    	Scanner sc = new Scanner(messageHistoryFile);
	    	while(sc.hasNextLine())
	    	{
	    		messageHistory.add(sc.nextLine());
	    	}
	    }
	    
	    // Begin to take in new clients
		System.out.println("Waiting for client to connect . . . ");
	    try
	    {
			while (true)
		    {
		         Socket s = server.accept();
		         System.out.println("Client connected.");
		         listenClient.add(s);		// Add listenClient (socket) to the arrayList
		         
		         Worker worker = new Worker(s, listenClient, messageHistory, userData);
		         new Thread(worker).start();
		    }
	    }
	    catch(IOException e){}
	    finally
	    {
	    	server.close();
	    }
	}
}
