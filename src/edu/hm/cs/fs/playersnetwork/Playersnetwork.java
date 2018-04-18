package edu.hm.cs.fs.playersnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import aufg3.interfaces.PlayerDialog;

/**
 * Handles communication with a network client.
 * @author sinning
 * */
public class Playersnetwork implements PlayerDialog {
	/**
	 * Socket to playerA.
	 * */
	private Socket socket1;
	/**
	 * Socket to playerB.
	 * */
	private Socket socket2;
	
	/**
	 * BufferedReader to playerA.
	 * */
	private BufferedReader in1;
	/**
	 * Socket to playerB.
	 * */
	private BufferedReader in2;
	/**
	 * PrintWriter to playerA.
	 * */
	private PrintWriter out1;
	/**
	 * Socket to playerB.
	 * */
	private PrintWriter out2;
	
	/**
	 * Default Constructor.
	 * */
	public Playersnetwork() {
		initialize();
	}
	
	/**
	 * initializes everything to handle client-server communication.
	 * @author sinning
	 * */
	private void initialize() {
		try {
			ServerSocket ss = new ServerSocket(2001);
			
			socket1 = ss.accept();
			ss.close();
			ServerSocket ss2 = new ServerSocket(2002);
			
			socket2 = ss2.accept();
			ss2.close();
			
			 in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			 in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			 out1 = new PrintWriter(new OutputStreamWriter(socket1.getOutputStream()));
			 out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()));
			 out1.println("Undercut start");
			 out1.flush();
			 out2.println("Undercut start");
			 out2.flush();
		}catch(IOException e) {}
	}
	
	/**
	 * tests if the input is correct.
	 * @param choice the clients choice
	 * @param eingabe the clients input
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
	 * Sends message to the client.
	 * @param playertyp the identification of a client
	 * @param choices the choices a player has
	 * */
	private void setMessage(String playertyp,int[] choices) {
		 if(choices.length==2) {
			 	int min = getMinChoice(choices);
			 	int max = getMaxChoice(choices);
			 	if(playertyp.matches("Player A")) {
			 		out1.println(playertyp+", your choice ("+min+"-"+max+")?");
			 		out1.flush();
			 	}
			 	else {
			 		out2.println(playertyp+", your choice ("+min+"-"+max+")?");
			 		out2.flush();
			 	}
			}
			else {
				if(playertyp.matches("Player A")) {
					out1.println(playertyp+", your choice ("+choices[0]+"/"+choices[1]+"/"+choices[2]+")?");
					out1.flush();
				}
				else {
					out2.println(playertyp+", your choice ("+choices[0]+"/"+choices[1]+"/"+choices[2]+")?");
					out2.flush();
				}
			}
	 }

	/**
	 * input is called to get the Players input.
	 * @param playertyp the identification of a player
	 * @param choices the choices a player has
	 * @return the players input
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
					final int pickA = Integer.parseInt(in1.readLine());
					b = correctinput(choiceA,pickA);
					if(b) {
						picka = pickA;
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
						final int pickB = Integer.parseInt(in2.readLine());
						b = correctinput(choiceB,pickB);
						if(b) {
							pickb = pickB;
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
	 * closes all resources which handles client server communication.
	 * @throws IOException if an IO Error occurs
	 * */
	@Override
	public void close() throws IOException {
		socket1.close();
		socket2.close();
		in1.close();
		in2.close();
		out1.close();
		out2.close();
	}

	/**
	 * sends Round information to the client.
	 * @param roundsplayed the number of rounds that have been played
	 * @param playerpoints the scores of both players
	 * */
	@Override
	public void output(int roundsplayed,int[] playerpoints) { //Player A: 1, Player B: 1
			out1.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
			out1.flush();
			out2.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
			out2.flush();
	}

	/**
	 * returns the minimum choice number of a player.
	 * @param choices the choices a player has
	 * @return the minimum choice
	 * */
	@Override
	public int getMinChoice(int[] choices) {
		return choices[0];
	}

	/**
	 * returns the maximum choice number of a player.
	 * @param choices the choices a player has
	 * @return the maximum choice
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
	 * checks if the input is correct.
	 * @param choice the choice a player has
	 * @param choices the choices a player has
	 * @boolean returns if the player input is correct
	 * */
	@Override
	public boolean checkinput(int choice,int[] choices) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * informs both players who has won the game.
	 * @param identification of a player
	 * */
	@Override
	public void messageEnding(int playerwinner) {
		if(playerwinner == -1) {
			out1.println("Tie");
			out1.flush();
			out2.println("Tie");
			out2.flush();
		}
		else if(playerwinner == 0) {
			out1.println("Player A wins");
			out1.flush();
			out2.println("Player A wins");
			out2.flush();
		}
		else if(playerwinner == 1){
			out1.println("Player B wins");
			out1.flush();
			out2.println("Player B wins");
			out2.flush();
		}
		out1.close();
		out2.close();
	}
}
