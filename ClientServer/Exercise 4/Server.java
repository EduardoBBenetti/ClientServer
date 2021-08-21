/**
 * @author Eduardo Barros Benetti
 * @version 1.0
 * @ March 30, 2020
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private ExecutorService pool;       // to create as many games as needed

	/**
	 * The default server to set the member variables up and the executor service to hold 
	 * each game created, according to the number of clients
	 * @param port: is the port of the PC
	 */
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			System.err.println("Problem setting the server");
		}
		System.out.println("Server is running !");
	}
	
	/**
	 * Main method,to establish connection with the client
	 * Moreover, it creates two players and a referee, with that establish the game starts
	 */
	public void communicateWithClient() {
		try {
			while(true) {
				
				Player xPlayer = new Player (serverSocket.accept(), 'X');
				Player oPlayer = new Player (serverSocket.accept(), 'O');

				Referee theRef = new Referee();
				
				theRef.setxPlayer(xPlayer);
				theRef.setoPlayer(oPlayer);
				
				Game theGame = new Game();
				theGame.appointReferee(theRef);
				
				System.out.println("\n\nThe game started");
				
				pool.execute(theGame);
			}
		} catch (Exception e) {
			System.err.println("Error communication with the server");
			pool.shutdown();
			System.exit(0);
		}

	}
	
	/**
	 * Main method to create the server and make the first connection with the method that connects
	 * to the client
	 * @param args
	 */
	public static void main (String [] args)  {
		Server server = new Server (8099);
		server.communicateWithClient();
		
		
	}
}
