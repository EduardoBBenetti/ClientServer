/*
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @ March 30, 2020
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * The class client is the class responsible for the relation with the server
 */
public class Client {
	private PrintWriter socketOut;
	private Socket palinSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	/*
	 * This is the default constructor, which mainly initialize the variables and connect
	 * to the port of the server, to establish connection
	 */
	public Client(String serverName, int portNumber) {
		try {
			palinSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(
					palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/*
	 * This is the main method of the program where the actual ocmmunication happens, including the 
	 * response to the server and the possible connection with the server to check is the String is
	 * a palindrome or not
	 */
	public void communicate()  {

		String line = "";
		String response = "";
		boolean running = true;
		while (running) {
			try {
				System.out.println("please enter a word: ");
				line = stdIn.readLine();
				if (!line.equals("QUIT")){
					System.out.println(line);
					socketOut.println(line);
					response = socketIn.readLine();
					System.out.println(response);	
				}else{
					running = false;
				}
				
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			System.out.println("Closing error: " + e.getMessage());
		}

	}

	/*
	 * main method to establish connection with the server
	 */
	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 8099);
		aClient.communicate();
	}
}