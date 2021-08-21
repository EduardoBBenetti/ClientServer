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

public class DateClient {

		private Socket aSocket;
		private PrintWriter socketOut;
		private BufferedReader socketIn;
		private BufferedReader stdIn;
	
	/*
	 * This is the default constructor of the client 
	 * @param name: is the name of the server the client will be connected to
	 * @param portNumber: is the number of the port where the connection is being established 
	 */
	public DateClient(String name, int portNumber) {
		try {
			aSocket = new Socket (name, portNumber);
			stdIn = new BufferedReader (new InputStreamReader (System.in));
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This is the main function of the class where the communication between the server and
	 * the client is established.
	 * Gets output from the server previously established and outputs what he/ she wants to be
	 * required, the DATE OR THE TIME
	 */
	private void commmunicate() {

		String option = "", answer = "";
		while (true) {
			try {
				System.out.println("Please select an option (DATE/TIME)");
				answer = stdIn.readLine();
				if(answer.equals("QUIT")) {
					break;
				}
				System.out.println(answer);
				socketOut.println(answer);
				option = socketIn.readLine();
				System.out.println(option);
			} catch (IOException e) {
				System.out.println("Sending error: " + e.getMessage());
			}
		}
		try {
			stdIn.close();	
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * The main method of the class, creates a client with local host and portNumber 9090
	 * @param arg: arguments given by the user to the program
	 */
	public static void main(String[] args) {
		DateClient client = new DateClient ("localhost", 9090);
		client.commmunicate();
	}


}
