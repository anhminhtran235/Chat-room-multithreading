import java.io.*;
import java.net.*;

/**
 * This listenClient is created on a separate thread by Client. 
 * Its job is to take messages from Server and send it to Clients
 * @author minh
 *
 */
public class ListenClient implements Runnable{

	private PrintWriter userOut;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String name;
	
	public ListenClient(PrintWriter pw, Socket s, String n)
	{
		userOut = pw;
		socket = s;
		name = n;
	}


	public void run() {
		
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
	        	 socket.close();
	         }
	      }
	      catch(IOException exception)
	      {
	         exception.printStackTrace();
	      }
		
	}
	
	public void doService() throws IOException
	{
		// Read all historical messages and print it to the console
		String message = in.readUTF();
		userOut.print(message);
		userOut.flush();
		
		// Keep listening for new messages and print it to the console
		while(true)
		{
			message = in.readUTF();
			userOut.println(message);
			userOut.flush();
		}
	}
}













