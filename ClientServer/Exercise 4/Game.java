
//Game.java
import java.io.*;

public class Game implements Constants, Runnable{

	private Board theBoard;
	private Referee theRef;
	
	/**
	 * creates a board for the game
	 */
    public Game( ) {
        theBoard  = new Board();
	}
    
    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game 
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.setBoard(theBoard);
		theRef.getoPlayer().setBoard(theBoard);
		theRef.getxPlayer().setBoard(theBoard);
        
    }

    /**
     * To start the game with from the class referee
     */
	@Override
	public void run() {
    	try {
			theRef.runTheGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
