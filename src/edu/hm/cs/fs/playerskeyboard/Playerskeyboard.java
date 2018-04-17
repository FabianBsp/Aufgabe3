package edu.hm.cs.fs.playerskeyboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import aufg3.interfaces.PlayerDialog;

public class Playerskeyboard implements PlayerDialog {
	//private BufferedReader reader;
	//private BufferedWriter writer;
	
	 public Playerskeyboard() {
		// reader = new BufferedReader(new InputStreamReader(System.in));
		// writer = new BufferedWriter(new OutputStreamWriter(System.out));
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
		/*int playerAChoice = 0;
		int playerBChoice = 0;
		
		try {
			// PlayerA input
        	//String inputA = reader.readLine();
        	playerAChoice = System.in.read();
        	// PlayerB input
        	//String inputB = reader.readLine();
        	playerBChoice = System.in.read();
		}
		catch(IOException e) {}
		return 0;//new int[] {playerAChoice,playerBChoice}; */
		int chu=0;
	//	try {
		if(playertyp == 0) {
			//int cc = choices[choices.length-1][choices.length-1];
			int minchoice = getMinChoice(choices[0]);
			int maxchoice = getMaxChoice(choices[0]);
			
			setMessage("Player A",choices[0]);

			boolean correctinput = false;
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	                correctinput = checkinput(chu,choices[0]);
	            }
	            while(!correctinput);//(chu < minchoice || chu > maxchoice);//(!correctinput);
		}
		else {
			//int cc = choices[choices.length-1][choices.length-1];
			int minchoice = getMinChoice(choices[1]);
			int maxchoice = getMaxChoice(choices[1]);
			//System.out.printf("Player B, your choice (1-%d)?%n",maxchoice);
			setMessage("Player B", choices[1]);
			
			boolean correctinput = false;
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	                correctinput = checkinput(chu,choices[1]);
	            }
	            while(!correctinput);//(chu < minchoice || chu > maxchoice);//(!correctinput);
		}
		//}catch(IOException e) {
		//	e.printStackTrace();
		//}
		return chu;
	}

	@Override
	public void output(int roundsplayed,int[] playerpoints) {
		//try {
			/*writer.write("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
			writer.flush();
			writer.write("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
			writer.flush(); */
			int cc1 = playerpoints[0];
			int cc2 = playerpoints[1];
			System.out.printf("Round %d, Player A: %d, Player B: %d%n",roundsplayed,cc1,cc2);
		//	System.out.printf("Round %d, Player B: %d, Player B: %d%n",roundsplayed,playerpoints[0],playerpoints[1]);
		//}
		//catch(IOException e){}

	}

	@Override
	public void close() throws IOException {
		//reader.close();
	}

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

}
