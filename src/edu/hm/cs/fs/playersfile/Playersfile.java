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
			
			fileoutput.println("Undercut start");
			fileoutput.flush();
		}catch (IOException e) {}
	}
	
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
	
	private boolean correctinput(int[] choice, int eingabe){
		if(choice.length > 2) {
			for(int elem: choice) {
				if(elem == eingabe) return true;
			}
			return false;
		}
		return (eingabe >= choice[0] && eingabe <= choice[1]);
	}


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
		// TODO Auto-generated method stub
		return false;
	}

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
