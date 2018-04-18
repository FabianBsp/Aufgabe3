package aufg3.interfaces;

import java.io.IOException;

/**
 * Holds all methods responsible for the game Player communication.
 * @author sinning
 * */
public interface PlayerDialog {
	/**
	 * responsible for getting the players Pick.
	 * @param playertyp the players identification
	 * @param choices the choices a player can have
	 * @throws IOException if an IO Error occurs
	 * */
	int input(int playertyp,int[]... choices) throws IOException;
	/**
	 * responsible for sending a message to the player.
	 * @param roundplayed the number of rounds that have been played
	 * @param playerpoints the Score of both Players
	 * */
	void output(int roundplayed,int[] playerpoints);
	/**
	 * closes all Connections to a player.
	 * @throws IOException if an IO Error occurs
	 * */
	void close() throws IOException;
	/**
	 * sends the lowest number a player can pick.
	 * @param choices that the player can select
	 * @return the lowest number of choices
	 * */
	int getMinChoice(int[] choices);
	/**
	 * sends the highest number a player can pick.
	 * @param choices that the player can select
	 * @return the highest number of choices
	 * */
	int getMaxChoice(int[] choices);
	/**
	 * checks if the players input was valid
	 * @param choice that the player has picked
	 * @param choices that the player can pick
	 * */
	boolean checkinput(int choice,int[] choices);
	/**
	 * sends the ending message to the players if the game ends.
	 * @param playerwinner the player who has won.
	 * */
	void messageEnding(int playerwinner);
}
