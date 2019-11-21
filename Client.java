import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * This is a Client class. When you run the main method of this class, it will connect to the server, 
 * let you login / create new accounts, create a listen client (to read incoming messages) and a speak client (read message from keyboard
 * and send it to the server) in separate threads.
 * Remember to ENTER YOUR SERVER PUBLIC IP ADDRESS on line 31
 * @author minh
 *
 */
public class Client {

	private static String name;
	private static Scanner userIn;
	private static PrintWriter userOut;
	private static Socket socket;
	private static DataInputStream in;
	private static DataOutputStream out;
	
	public static void main(String[] args)
	{
		final int PORT = 1181;
		userIn = new Scanner(System.in);
		userOut = new PrintWriter(System.out);
		
		try
		{
			// Connect with server
			socket = new Socket("ENTER YOUR SERVER PUBLIC IP ADDRESS", PORT);
			in = new DataInputStream(socket.getInputStream());
	        out = new DataOutputStream(socket.getOutputStream());
			
			// Log in
			logIn();
			
			// DoStuff
			new Thread(new ListenClient(userOut, socket, name)).start();
			new Thread(new SpeakClient(userIn, socket, name)).start();
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void logIn() throws IOException
	{
		while(true)
		{
			System.out.println("Welcome to the Chatroom!!!");
			System.out.print("Press 1 to log in or press 2 to create a new account: ");
			
			String command = userIn.nextLine();
			
			if(command.equals("1"))
			{
				// log in
				out.writeUTF("LOGIN");
				out.flush();
				
				System.out.print("Enter user name: ");
				String userName = userIn.nextLine();
				System.out.print("Enter password: ");
				String password = userIn.nextLine();
				
				out.writeUTF(userName);
				out.writeUTF(password);
				out.flush();
				
				String result = in.readUTF();
				if(result.equals("SUCCEED"))
				{
					System.out.println("Successfully logging in! Entering server ...");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {}
					System.out.println("Entered!");
					System.out.println("You can start sending messages");
					System.out.println();
					System.out.println("---------------------------------------------------");
					System.out.println();
					name = userName;
					return;		// Done logging in!
				}
				else	// fail
				{
					System.out.println("Login fail! Let's try again!");
					System.out.println();
				}
				
			}
			else if(command.equals("2"))
			{
				// create new account
				out.writeUTF("CREATE");
				out.flush();
				
				System.out.print("Enter user name: ");
				String userName = userIn.nextLine();
				System.out.print("Enter password: ");
				String password = userIn.nextLine();
				
				out.writeUTF(userName);
				out.writeUTF(password);
				out.flush();
				
				String result = in.readUTF();
				if(result.equals("SUCCEED"))
				{
					System.out.println("Successfully create a new account! Entering server ...");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {}
					System.out.println("Entered!");
					System.out.println("You can start sending messages");
					System.out.println();
					System.out.println("---------------------------------------------------");
					System.out.println();
					name = userName;
					return;		// Done creating new account
				}
				else	// fail
				{
					System.out.println("Creating a new account fail! Let's try again!");
					System.out.println();
				}
			}
			else
			{
				System.out.println("Wrong input! Let's try again!");
				System.out.println();
			}
		}
	}
}















