package edu.hm.cs.fs.playerskeyboard;

import java.io.IOException;

import aufg3.interfaces.PlayerDialog;

/**
 * Handles keyboard communication with the players.
 * @author sinning
 * */
public class Playerskeyboard implements PlayerDialog {
	
	/**
	 * Default Constructor.
	 * */
	 public Playerskeyboard() {
		 System.out.printf("Undercut start%n");
	 }
	 
	 /**
	  * sends game Informations to the player.
	  * @param playertyp the identification of the Player
	  * @param choices that the player can select
	  * */
	 private void setMessage(String playertyp,int[] choices) {
		 if(choices.length==2) {
			 	int min = getMinChoice(choices);
			 	int max = getMaxChoice(choices);
				System.out.printf(playertyp+", your choice (%d-%d)?%n", min,max);
			}
			else {
				System.out.printf(playertyp+", your choice ("+choices[0]+"/"+choices[1]+"/"+choices[2]+")?%n");
			}
	 }

	 /**
	  * handles the keyboard input of a player.
	  * @param playertyp the identification of the Player
	  * @param choices that the player can select
	  * @return the players input
	  * @throws IOException if an IO Error occurs
	  * */
	@Override
	public int input(int playertyp,int[]... choices) throws IOException{
		int chu=0;
		if(playertyp == 0) {
			
			setMessage("Player A",choices[0]);

			boolean correctinput = false;
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	                correctinput = checkinput(chu,choices[0]);
	            }
	            while(!correctinput);
		}
		else {
			setMessage("Player B", choices[1]);
			
			boolean correctinput = false;
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	                correctinput = checkinput(chu,choices[1]);
	            }
	            while(!correctinput);
		}
		return chu;
	}

	/**
	 * sends game information to the player.
	 * @param roundsplayed the number of rounds that have been played
	 * @param playerpoints that both players have
	 * */
	@Override
	public void output(int roundsplayed,int[] playerpoints) {
			int cc1 = playerpoints[0];
			int cc2 = playerpoints[1];
			System.out.printf("Round %d, Player A: %d, Player B: %d%n",roundsplayed,cc1,cc2);
	}

	/**
	 * closes the IO Stream.
	 * @throws IOException if an IO Error occurs
	 * */
	@Override
	public void close() throws IOException {}

	/**
	 * selects the lowest number of choices.
	 * @param choices that the player has
	 * @return the lowest number of choices
	 * */
	@Override
	public int getMinChoice(int[] choices) {
		return choices[0];
	}

	/**
	 * selects the highest number of choices.
	 * @param choices that the player has
	 * @return the highest number of choices
	 * */
	@Override
	public int getMaxChoice(int[] choices) {
		int max = 0;
		for(int choice:choices) {
			if(choice>max) {
				max = choice;
			}
		}
		return max;
	}

	/**
	 * checks if the players input was valid.
	 * @param choice that player has made
	 * @param choices that the player has
	 * @return true if the players input was valid, otherwise false
	 * */
	@Override
	public boolean checkinput(int choice,int[] choices) {
		if(choices.length>2) {
			boolean retbool = false;
			for(int choice2 : choices) {
				if(choice2 == choice) {
					retbool = true;
					break;
				}
			}
			return retbool;
		}
		else {
			return choice >= choices[0] && choice <= choices[1];
		}
	}

	/**
	 * sends the ending message to the player
	 * @param playerwinner the player who won the game.
	 * */
	@Override
	public void messageEnding(int playerwinner) {
		if(playerwinner == -1)
			System.out.println("Tie");
		else if(playerwinner == 0)
			System.out.println("Player A wins");
		else if(playerwinner == 1)
			System.out.println("Player B wins");
	}

}
