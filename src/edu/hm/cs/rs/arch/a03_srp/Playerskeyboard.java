package edu.hm.cs.rs.arch.a03_srp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import aufg3.interfaces.PlayerDialog;

public class Playerskeyboard implements PlayerDialog {
	private BufferedReader reader;
	private BufferedWriter writer;
	
	 public Playerskeyboard() {
		 reader = new BufferedReader(new InputStreamReader(System.in));
		 writer = new BufferedWriter(new OutputStreamWriter(System.out));
	 }

	@Override
	public int[] input(int[]... choices) {
		int playerAChoice = 0;
		int playerBChoice = 0;
		
		try {
			// PlayerA input
        	String inputA = reader.readLine();
        	playerAChoice = Integer.parseInt(inputA);
        	// PlayerB input
        	String inputB = reader.readLine();
        	playerBChoice = Integer.parseInt(inputB);
		}
		catch(IOException e) {}
		return new int[] {playerAChoice,playerBChoice};
	}

	@Override
	public void output(int[] playerpoints) {
		try {
			writer.write("PlayerA: "+playerpoints[0]+"  PlayerB: "+playerpoints[1]);
			writer.flush();
			writer.write("PlayerA: "+playerpoints[0]+"  PlayerB: "+playerpoints[1]);
			writer.flush();
		}
		catch(IOException e){}

	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
