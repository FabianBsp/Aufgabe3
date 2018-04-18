package edu.hm.cs.fs.playersfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import aufg3.interfaces.PlayerDialog;

/**
 * handles In and Output to a file that is the player.
 * @author sinning
 * */
public class Playersfile implements PlayerDialog {
	private BufferedReader fileinput;
	private PrintWriter fileoutput;
	
	/**
	 * Default Constructor.
	 * */
	public Playersfile() {
		System.out.println(System.getProperty("java.io.tmpdir"));
		initialize();
	}
	
	/**
	 * initializes all required sources to enable communication with the player.
	 * */
	private void initialize(){
		try {
			fileinput = new BufferedReader(new FileReader(new File(System.getProperty("java.io.tmpdir") + "undercut.in.txt")));
			fileoutput = new PrintWriter(new FileWriter(new File(System.getProperty("java.io.tmpdir") + "undercut.out.txt")));
			
			fileoutput.println("Undercut start");
			fileoutput.flush();
		}catch (IOException e) {}
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
			 		fileoutput.println(playertyp+", your choice ("+min+"-"+max+")?");
			 		fileoutput.flush();
			}
			else {
					fileoutput.println(playertyp+", your choice ("+choices[0]+"/"+choices[1]+"/"+choices[2]+")?");
					fileoutput.flush();
			}
	 }
	
	/**
	 * checks if the players input was valid.
	 * @param choice that player has
	 * @param eingabe input that the player has made
	 * @return true if the players input was valid, otherwise false
	 * */
	private boolean correctinput(int[] choice, int eingabe){
		if(choice.length > 2) {
			for(int elem: choice) {
				if(elem == eingabe) return true;
			}
			return false;
		}
		return (eingabe >= choice[0] && eingabe <= choice[1]);
	}

	/**
	  * handles the keyboard input of a player.
	  * @param playertyp the identification of the Player
	  * @param choices that the player can select
	  * @return the players input
	  * @throws IOException if an IO Error occurs
	  * */
	@Override
	public int input(int playertyp,int[]... choices) {
		int picka=0;
		int pickb=0;
		
		if(playertyp == 0) {
			int[] choiceA = choices[0];
			boolean b;
			
			setMessage("Player A", choices[0]);
			
			try {
				
				do{
					final int pickA = fileinput.read();
					int pick = pickA - '0';
					b = correctinput(choiceA,pick);
					if(b) {
						picka = pick;
						break;
					}
				 }
				 while(!b);
				
				return picka;
			}
			catch(IOException e) {}
			}
			else {
				int[] choiceB = choices[1];
				boolean b;
				setMessage("Player B", choices[1]);
				
				try {
					do{
						final int pickB = fileinput.read();
						int pick = pickB - '0';
						b = correctinput(choiceB,pick);
						if(b) {
							pickb = pick;
							break;
						}
					 }
					 while(!b);
					
					return pickb;
				}
				catch(IOException e) {}
			
			}
		return 0;
	}

	/**
	 * closes the IO Stream.
	 * @throws IOException if an IO Error occurs
	 * */
	@Override
	public void close() throws IOException {
		fileinput.close();
		fileoutput.close();
	}

	/**
	 * sends game information to the player.
	 * @param roundsplayed the number of rounds that have been played
	 * @param playerpoints that both players have
	 * */
	@Override
	public void output(int roundsplayed,int[] playerpoints) {
		fileoutput.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
		fileoutput.flush();
	}
	
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
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * sends the ending message to the player
	 * @param playerwinner the player who won the game.
	 * */
	@Override
	public void messageEnding(int playerwinner) {
		if(playerwinner == -1)
			fileoutput.println("Tie");
		else if(playerwinner == 0)
			fileoutput.println("Player A wins");
		else if(playerwinner == 1)
			fileoutput.println("Player B wins");
		fileoutput.close();
	}

}
