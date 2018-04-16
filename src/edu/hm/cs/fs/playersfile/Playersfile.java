package edu.hm.cs.fs.playersfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import aufg3.interfaces.PlayerDialog;

public class Playersfile implements PlayerDialog {
	private BufferedReader fileinput;
	private PrintWriter fileoutput;
	
	public Playersfile() {
		System.out.println(System.getProperty("java.io.tmpdir"));
		initialize();
	}
	
	private void initialize(){
		try {
			fileinput = new BufferedReader(new FileReader(new File(System.getProperty("java.io.tmpdir") + "undercut.in.txt")));
			fileoutput = new PrintWriter(new FileWriter(new File(System.getProperty("java.io.tmpdir") + "undercut.out.txt")));
		}catch (IOException e) {}
	}

	@Override
	public int input(int playertyp,int maxchoice,int minchoice,int[]... choices) {
		int choiceA = 0;
		int choiceB = 0;
		try {
			 choiceA = fileinput.read();
			 choiceA = choiceA - '0';
			 choiceB = fileinput.read();
			 choiceB = choiceB - '0';
		}
		catch(IOException e){}
		return 0;//new int[]{choiceA,choiceB};
	}

	@Override
	public void close() throws IOException {
		fileinput.close();
		fileoutput.close();
	}

	@Override
	public void output(int roundsplayed,int[] playerpoints) {
		fileoutput.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
		fileoutput.flush();
	}

}
