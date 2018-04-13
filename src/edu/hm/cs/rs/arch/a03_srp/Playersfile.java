package edu.hm.cs.rs.arch.a03_srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	public int[] input(int[]... choices) {
		int choiceA = 0;
		int choiceB = 0;
		try {
			 choiceA = fileinput.read();
			 choiceA = choiceA - '0';
			 choiceB = fileinput.read();
			 choiceB = choiceB - '0';
		}
		catch(IOException e){}
		return new int[]{choiceA,choiceB};
	}

	@Override
	public void close() throws IOException {
		fileinput.close();
		fileoutput.close();
	}

	@Override
	public void output(int[] playerpoints) {
		fileoutput.println(playerpoints[0]+" "+playerpoints[1]);
	}

}
