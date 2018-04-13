package edu.hm.cs.rs.arch.a03_srp;

import java.io.IOException;

import aufg3.interfaces.PlayerDialog;
import aufg3.interfaces.Properties;
import edu.hm.cs.rs.arch.playmode.Chaos;
import edu.hm.cs.rs.arch.playmode.Normal;

public class UndercutMonoNew {

	public static void main(String[] args) {
		Properties properties = new Chaos();
		PlayerDialog netinput = new Playerskeyboard();
		
		new UndercutMonoNew().play(properties,netinput);
	}
	
	public void play(Properties properties,PlayerDialog dialog){
		for(int i=0; i<3; i++) {
			
			int[][] choices = getPlayerAandPlayerBChoices(properties);
			int[] arr = dialog.input(choices);
			System.out.println("Player A : " +arr[0]+"  Player B : "+arr[1]);
			
		}
		try {
			dialog.close();
		}
		catch(IOException e){}
	}
	
	private int[][] getPlayerAandPlayerBChoices(Properties properties){
		if(properties instanceof Chaos) {	// 2 Wahlmöglichkeiten jeweils für SpielerA und SpielerB
			int[][] retarr = new int[][] {properties.getChoices()[0],properties.getChoices()[1]};
			((Chaos) properties).nextindices();
			return retarr;
		}
		else { // eine Wahlmöglichkeit für beide Spieler
			return new int[][] {properties.getChoices()[0],properties.getChoices()[0],};
		}
	}
	
	
}
