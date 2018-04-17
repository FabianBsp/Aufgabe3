package edu.hm.cs.fs.playerskeyboard;

import java.io.IOException;

import aufg3.interfaces.PlayerDialog;

public class Playerskeyboard implements PlayerDialog {
	
	 public Playerskeyboard() {
		 System.out.printf("Undercut start%n");
	 }
	 
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

	@Override
	public void output(int roundsplayed,int[] playerpoints) {
			int cc1 = playerpoints[0];
			int cc2 = playerpoints[1];
			System.out.printf("Round %d, Player A: %d, Player B: %d%n",roundsplayed,cc1,cc2);
	}

	@Override
	public void close() throws IOException {}

	@Override
	public int getMinChoice(int[] choices) {
		return choices[0];
	}

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
