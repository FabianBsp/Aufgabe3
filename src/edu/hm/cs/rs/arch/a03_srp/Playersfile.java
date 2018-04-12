package edu.hm.cs.rs.arch.a03_srp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import aufg3.interfaces.PlayerDialog;

public class Playersfile implements PlayerDialog {
	private FileInputStream fileinput;
	private PrintWriter fileoutput;
	
	public Playersfile() {
		initialize();
	}
	
	private void initialize(){
		try {
			fileinput = new FileInputStream(new File(System.getProperty("java.io.tmpdir") + "undercut.in.txt"));
			fileoutput = new PrintWriter(new FileWriter(new File(System.getProperty("java.io.tmpdir") + "undercut.in.txt")));
		}catch (IOException e) {}
	}

	@Override
	public int[] input(int[]... choices) {
		int choiceA = 0;
		int choiceB = 0;
		try {
			 choiceA = fileinput.read();
			 choiceB = fileinput.read();
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
