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

	@Override
	public int input(int playertyp,int maxchoice,int minchoice,int[]... choices) {
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
		try {
		if(playertyp == 0) {
			int cc = choices[choices.length-1][choices.length-1];
			System.out.printf("Player A, your choice (1-%d)?%n", cc);

			
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	            }
	            while(chu < 1 || chu > maxchoice);
		}
		else {
			int cc = choices[choices.length-1][choices.length-1];
			System.out.printf("Player B, your choice (1-%d)?%n",cc);
			
			 do {
	                final int input = System.in.read();
	                if(input < 0)
	                    throw new IOException();
	                chu = input - '0';
	            }
	            while(chu < 1 || chu > maxchoice);
		}
		}catch(IOException e) {}
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

}
