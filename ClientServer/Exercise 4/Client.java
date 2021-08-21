/**
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @ March 30, 2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	/**
	 * Default constructor, to set the variables and establish connection with the server
	 * @param serverName: localhost
	 * @param portNumber: PC port 
	 */
	public Client(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	/**
	 * The main method of the client, to establish connection with the server, going both way through the socket, 
	 * to receive and insert information into the socket
	 */
	public void communicateWithServer() {
		try {
			while(true) {
				String read = "";
				while(true) {	
					read = socketIn.readLine();
					if(read.contains("\0")) {
						read = read.replace("\0", "");
						System.out.println(read);
						break;
					}
					if(read.equals("QUIT")) {
						return;
					}
					System.out.println(read);
				}
				read = stdIn.readLine();
				socketOut.println(read);
				socketOut.flush();
			}	
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				aSocket.close();
				stdIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Main method to create client and establish connection
	 * @param args: input from program
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException  {
		Client aClient = new Client("localhost", 8099);
		aClient.communicateWithServer();
	}
	
	
	
	
	
}
