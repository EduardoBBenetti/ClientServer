import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * class player is the where the information of each of the two players
 *  having name, board (same for both players), opponent and mark (being X or O)
 */
public class Player {
	/**
	 * Name is the name of the players
	 */
	String name;
	/**
	 * Board is where the information is kept, the same for both players 
	 */
	Board board;
	/**
	 * Opponent of class player, used to pass the turn to the next player
	 */
	Player opponent;
	/**
	 * Is the tag in the board, being X or O
	 */
	char mark;
	private Socket aSocket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;	
	
	
	/**
	 * Constructor of player with all the data from the class
	 * @param n is the String for name
	 * @param b is the Board of where the game is being played
	 * @param p is the Player, with all the information of the player
	 * @param c is the mark of the player
	 */
	Player(String n, Board b, Player p, char c){
		this.name = n;
		this.board = b;
		this.opponent = p;
		this.mark = c;
	}
	
	/**
	 * Overloaded player, where there only two parameters 
	 * @param n is the name of the player being constructed
	 * @param m is the char representation of the mark in the board
	 */
	Player(Socket s, char m){
		this.aSocket = s;
		this.mark = m;
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		} catch(IOException e) {
			System.err.println("There was an error when setting the socketIn or socketOut of the player");
		}
		if(mark == 'X') {
			socketOut.println("X player connected to the game");
		}
	}
	
	/**
	 * Used several times to insert information into the socket and flush it. Due to the high usage of the
	 * this was created with the intention to be used several times
	 * @param toSend
	 */
	private void sendString (String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	/**
	 * Responsible to get and set the name of each player
	 * @throws IOException
	 */
	public void getPlayerName() throws IOException {
		sendString("Please enter the name of the "+mark+" player: \0");
		name = socketIn.readLine();
		while(this.name == null) {
			sendString("Please enter again: \0");
			this.name = socketIn.readLine();
		}
	}
	
	/**
	 * return name
	 */
	private String getName(){
		return this.name;
	}
	
	/**
	 * return board
	 */
	private Board getBoard() {
		return this.board;
	}
	
	/**
	 * return player
	 */
	private Player getPlayer() {
		return this.opponent;
	}
	
	/**
	 * return the player mark
	 */
	private char getMark() {
		return mark;
	}
	
	/**
	 * set the name of the player
	 */
	private void setName(String n){
		this.name = n;
	}
	/**
	 * set the data from the player
	 */
	private void setPlayer(Player p) {
		this.opponent = p;
	}
	/**
	 * set the mark of the player (X or O) 
	 */
	private void setMark(char c) {
		this.mark = c;
	}
	
	/**
	 * Is the control of the game, passing the turns of the players, and also getting all the information
	 * passed onto and through the socket
	 * @throws IOException 
	 */
	public void play() throws IOException {
		
		this.sendString(name.toUpperCase()+", it is you turn to make the move\nThis is the board at this moment");
		this.sendString(board.display());
		opponent.sendString("The opponent is playing...\n");
		this.makeMove();
		
		if(board.xWins() == true || board.oWins() == true) {
			
			this.sendString("GAME OVER!\nYou won the game");
			opponent.sendString(board.display());
			opponent.sendString("GAME OVER!\nYou lost,  "+this.name.toUpperCase()+" won the game");
			this.sendString("QUIT");
			this.opponent.sendString("QUIT");
			return;
		}
		if(board.isFull()) {
			
			this.sendString("GAME OVER!\nThe game ended on a tie");
			opponent.sendString(board.display());
			opponent.sendString("GAME OVER!\nThe game ended on a tie");
			return;
		}
		this.opponent.play();		
	}
	
	/**
	 * This method is called by the play() and it is responsible to get the inputs from the player on which row or column
	 * he or she is playing
	 * @throws IOException 
	 */
	public void makeMove() throws IOException {
		int x = 0, y = 0, flag = 0;
		String resp = null;
		
		do {
			try {
				if(flag != 0) {
					this.sendString("Invalid input, try again...");
				}
				this.sendString("\n"+name.toUpperCase()+", chose the ROW of your mark ("+mark+") \0");
				resp = socketIn.readLine();
				x = Integer.parseInt(resp);
				this.sendString("\n"+name.toUpperCase()+" chose the COLUMN of your mark ("+mark+") \0");
				resp = socketIn.readLine();
				y = Integer.parseInt(resp);
				flag++;
			} catch (IOException e){
				e.printStackTrace();
			}
		} while(board.getMark(x, y) != ' ');
		board.addMark(x, y, mark);
	}
	
	/**
	 * sets opponent to p
	 * @param p is the opponent of the this.player
	 */
	public void setOpponent (Player p){
		this.opponent = p;
	}
	
	/**
	 * sets the board to its argument
	 * @param theBoard sets the board of the player to theBoard
	 */
	public void setBoard(Board theBoard) {
		this.board = theBoard;
	}
	
}
