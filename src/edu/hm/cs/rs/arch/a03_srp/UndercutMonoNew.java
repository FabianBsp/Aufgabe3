package edu.hm.cs.rs.arch.a03_srp;

import java.io.IOException;

import aufg3.interfaces.PlayerInput;

public class UndercutMonoNew {

	public static void main(String[] args) {
		PlayerInput netinput = new Playersnetwork();
		for(int i=0; i<3; i++) {
			int[] arr = netinput.input(new int[] {1,5},new int[] {2,4});
			System.out.println("Player A : " +arr[0]+"  Player B : "+arr[1]);
		}
		try {
			netinput.close();
		}
		catch(IOException e){}
	}
}
