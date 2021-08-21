/*
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @ March 30, 2020
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;

	
	/*
	 * This is the default constructor, basically creating the serverSocket, in order to have 
	 * the connection with the client
	 * @param port is the int of the PC port
	 */
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server is running!");
	}
	
	/*
	 * This is the main function of the palindrome calculation
	 * It calls the palRec to check if each word is a palindrome recursively
	 * Prints the result to the client by lauching the data into the socket 
	 * (connection with the client)
	 */
	public void isPalindrome() {
		String newT = null;	
		while(true) {

			try {
				newT = socketIn.readLine();    // read from client
				if(newT.equals("QUIT")) {
					break;
				}
				if(palRec(newT,0,newT.length()-1)) {
					socketOut.println(newT+ " is a palindrome"); // pass the content to the client
				}
				else {
					socketOut.println(newT + " is NOT a palindrome"); // pass the result to the client
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch(NullPointerException e) {
				break;
			}
		}
	}
	
	/*
	 * This is the function (recursive) responsible for the calculation of the palindrome
	 * @param s is the string with the word to be checked
	 * @param l is the index of the lower index
	 * @param h is the index of the high index
	 * @return a boolean variable if s is a palindrome of not
	 */
	private boolean palRec(String s, int l, int h) {
		
		if(l >= h) {
			return true;
		}
		if(s.charAt(l) != s.charAt(h)) {
			return false;
		}
		return palRec(s,l+1,h-1);
		
	}
	/*
	 * This is the main method, to relate the socket with the server and the client (including the socketIn and socketOut)
	 */
	public static void main (String [] args) {
		try {
			Server s = new Server (8099); // connection with the port 
			s.aSocket = s.serverSocket.accept();
			System.out.println("Connection accepted by server!"); // message to check 
			s.socketIn = new BufferedReader (new InputStreamReader (s.aSocket.getInputStream())); // sockets to write and read the data from it
			s.socketOut = new PrintWriter((s.aSocket.getOutputStream()), true);
			s.isPalindrome();  // starting the communication
			
			s.socketIn.close(); 
			s.socketOut.close();
			}catch (IOException e) {
				e.getStackTrace();
			}
	}
	
	
	
}
