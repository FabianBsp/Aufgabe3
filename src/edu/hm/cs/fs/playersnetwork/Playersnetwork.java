package edu.hm.cs.fs.playersnetwork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

import aufg3.interfaces.PlayerDialog;

public class Playersnetwork implements PlayerDialog {
	private Socket socket1;
	private Socket socket2;
	
	private BufferedReader in1;
	private BufferedReader in2;
	private PrintWriter out1;
	private PrintWriter out2;
	
	public Playersnetwork() {
		initialize();
	}
	
	private void initialize() {
		try {
			ServerSocket ss = new ServerSocket(2001);
			
			socket1 = ss.accept();
		//	System.out.println("Sock1 verbunden");
			ss.close();
			ServerSocket ss2 = new ServerSocket(2002);
			
			socket2 = ss2.accept();
		//	System.out.println("Sock2 verbunden");
			ss2.close();
			
			 in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			 in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			 out1 = new PrintWriter(new OutputStreamWriter(socket1.getOutputStream()));
			 out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()));
		}catch(IOException e) {}
	}
	
	private String messageAsString(int[] choice, String playertyp) {
		String message = "";
		message += playertyp;
		message += ", your choice ";
		if(choice.length == 2) {
			message += "(";
			message += choice[0];
			message += ",";
			message += choice[1];
			message += ")?";
		}
		else {
			for(int i=0; i<choice.length; i++) {
				if(i==0){
					message += "(";
					message += choice[i];
				}
				else if(i==choice.length-1){
					message += "/";
					message += choice[i];
					message += ")";
				}
				else {
					message += "/";
					message += choice[i];
				}
			}
		}
		return message;
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
	public int input(int playertyp,int maxchoice,int minchoice,int[]... choices) {
		int picka=0;
		int pickb=0;
		
		if(playertyp == 0) {
			String messageA = "";
			int[] choiceA = choices[0];
			boolean b;
			
			messageA = messageAsString(choiceA,"Player A");
			
			try {
				out1.write(messageA); // potentielles Problem: a wird gesendet, b aber nicht. a steht in file,
									  // beide m�ssen ihren Wert neu eingeben -> ein Wert zu viel bei a.
				out1.flush();
				
				do{
					final int pickA = Integer.parseInt(in1.readLine());
					b = correctinput(choiceA,pickA);
					if(b) {
						picka = pickA;
						break;
					}
					out1.println(messageA); 
					out1.flush();
				 }
				 while(!b);
				
				return picka;
			}
			catch(IOException e) {}
			}
			else {
			
				String messageB = "";
				int[] choiceB = choices[1];
				boolean b;
				messageB = messageAsString(choiceB,"Player B");
				try {
					out2.write(messageB); // potentielles Problem: a wird gesendet, b aber nicht. a steht in file,
										  // beide m�ssen ihren Wert neu eingeben -> ein Wert zu viel bei a.
					out2.flush();
					
					do{
						final int pickB = Integer.parseInt(in2.readLine());
						b = correctinput(choiceB,pickB);
						if(b) {
							pickb = pickB;
							break;
						}
						out2.println(messageB); 
						out2.flush();
					 }
					 while(!b);
					
					return pickb;
				}
				catch(IOException e) {}
			
			}
		return 0;
		

			//int[] choiceA = choices[0];
			//int[] choiceB = choices[1];
			//boolean b;
			
			//String messageA = "";
			//String messageB = "";
			
			//	messageA = messageAsString(choiceA,"Player A");
			//	messageB = messageAsString(choiceB,"Player B");
		/*	
			try {
				//out1.write(messageA); // potentielles Problem: a wird gesendet, b aber nicht. a steht in file,
									  // beide m�ssen ihren Wert neu eingeben -> ein Wert zu viel bei a.
				//out1.flush();
			
				out2.write(messageB); 
				out2.flush();
				
			/* do{
				final int pickA = Integer.parseInt(in1.readLine());
				b = correctinput(choiceA,pickA);
				if(b) {
					picka = pickA;
					break;
				}
				out1.println(messageA); 
				out1.flush();
			 }
			 while(!b); */
			 
			/* do{
				final int pickB = Integer.parseInt(in2.readLine());
				b = correctinput(choiceB, pickB);
				if(b) {
					pickb = pickB;
					break;
				}
				out2.println(messageB); 
				out2.flush();
			 }
			 while(!b);
			}
			catch(IOException e) {}
		
		return new int[]{picka,pickb}; */
	
	}
	
	@Override
	public void close() throws IOException {
		socket1.close();
		socket2.close();
		in1.close();
		in2.close();
		out1.close();
		out2.close();
	}

	@Override
	public void output(int roundsplayed,int[] playerpoints) { //Player A: 1, Player B: 1
			out1.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
		//	out1.flush();
			out2.println("Round "+roundsplayed+", Player A: "+playerpoints[0]+", Player B: "+playerpoints[1]);
		//	out2.flush();
	}
}
