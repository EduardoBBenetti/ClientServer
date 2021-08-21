import java.io.IOException;

/**
	 * The Referee class is where the "rules" are taken care of
	 * along with functionalities as displaying the game, and 
	 * checking if anyone won the game throughout the game
	 */
public class Referee {
		/**
		 * xPlayer is of class player with a mark x
		 */
		Player xPlayer;
		/**
		 * oPlayer is of class player with a mark x
		 */
		Player oPlayer;
		/**
		 * Board is where the data of the game was kept
		 */
		Board board;
		
		/**
		 * sets up the game, by connecting the players and displaying the board
		 * Lastly, is starts the game
		 * @throws IOException 
		 * 
		 */
		public void runTheGame() throws IOException {
			xPlayer.setOpponent(oPlayer);
			oPlayer.setOpponent(xPlayer);
			try {
				xPlayer.getPlayerName();
				oPlayer.getPlayerName();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Referee started the game between "+xPlayer.name.toUpperCase()
						+" and "+oPlayer.name.toUpperCase());
			xPlayer.play();
			System.out.println(board.display() + "\nThe game ended between "
					+ xPlayer.name.toUpperCase()+ " and "+ oPlayer.name.toUpperCase());
			
		}
		
		/**
		 * Board setter
		 * @param b is the board to be created
		 */
		public void setBoard(Board b) {
			this.board = b;
		}
		
		/**
		 * Player O setter
		 * @param for oPlayer
		 */
		public void setoPlayer (Player oPlayer) {
			this.oPlayer = oPlayer;
		}
		
		/**
		 * Player X setter
		 * @param for oPlayer
		 */
		public void setxPlayer (Player xPlayer) {
			this.xPlayer = xPlayer;
		}
		
		/**
		 * Getter of the x player
		 * @return xPlayer
		 */
		public Player getxPlayer() {
			return this.xPlayer;
		}
		
		/**
		 * Getter of the o Player
		 * @return oPlayer
		 */
		public Player getoPlayer() {
			return this.oPlayer;
		}
}
