import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * This speakClient is created on a separate thread by Client. 
 * Its job is to read message from keyboard and send it to Servers
 * @author minh
 *
 */
public class SpeakClient implements Runnable{

	private Scanner userIn;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String name;

	public SpeakClient(Scanner sc, Socket s, String n)
	{
		userIn = sc;
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
		while(true)
		{
			String message = userIn.nextLine();
			message = name + ": " + message;
			
			out.writeUTF(message);
			out.flush();
		}
	}
}










