package edu.hm.cs.rs.arch.a03_srp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

import aufg3.interfaces.PlayerInput;

public class Playersnetwork implements PlayerInput {
	private Socket socket1;
	private Socket socket2;
	
	public Playersnetwork() {
		initialize();
	}
	
	private void initialize() {
		try {
			ServerSocket ss = new ServerSocket(2001);
			
			socket1 = ss.accept();
			System.out.println("Sock1 verbunden");
			ss.close();
			ServerSocket ss2 = new ServerSocket(2002);
			
			socket2 = ss2.accept();
			System.out.println("Sock2 verbunden");
			ss2.close();
		}catch(IOException e) {}
	}
	
	private String getPlayerMessage(int[] choice, String playertyp) {
		String message = "";
		
		for(int i=0; i<choice.length; i++) {
			if(i==0){
				message += playertyp;
				message += " / choose [";
				message += choice[i];
			}
			else if(i==choice.length-1){
				message += ",";
				message += choice[i];
				message += "]";
			}
			else {
				message += ",";
				message += choice[i];
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
	public int[] input(int[]... choices) {
		int picka=0;
		int pickb=0;
		System.out.println("Start ---");
		
		try(BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			BufferedWriter out1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
			BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
				){

			int[] choiceA = choices[0];
			int[] choiceB = choices[1];
			
			System.out.println(choiceA.length + "        " + choiceB.length);
			
			boolean b;
			
			String messageA = "";
			String messageB = "";
			
				messageA = getPlayerMessage(choiceA,"PlayerA");
				messageB = getPlayerMessage(choiceB,"PlayerB");
				
				out1.write(messageA); 
				out1.flush();
			
				out2.write(messageB); 
				out2.flush();
				
			 do{
				final int pickA = Integer.parseInt(in1.readLine());
				b = correctinput(choiceA,pickA);
				if(b) {
					picka = pickA;
					break;
				}
				out1.write(messageA); 
				out1.flush();
			 }
			 while(!b);
			 
			 do{
				final int pickB = Integer.parseInt(in2.readLine());
				b = correctinput(choiceB, pickB);
				if(b) {
					pickb = pickB;
					break;
				}
				out2.write(messageB); 
				out2.flush();
			 }
			 while(!b);
		
		}
		catch (IOException e) {}
		
		return new int[]{picka,pickb};
	
	}
}