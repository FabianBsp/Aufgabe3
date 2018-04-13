package edu.hm.cs.rs.arch.playmode;

import aufg3.interfaces.Properties;

public class Chaos implements Properties {
	private int indexA;
	private int indexB;
	private int[][] choices = new int[][] {{1,4},{2,5},{1,3,5}};

	public Chaos() {
		indexA = 0;
		indexB = 1;
	}
	
	@Override
	public int getScoreToWin() {
		return 42;
	}

	@Override
	public int[][] getChoices() {
		return new int[][] {choices[indexA],choices[indexB]};
	}
	
	public void nextindices(){
		indexA++;
		indexA = indexA % 3;
		indexB++;
		indexB = indexB % 3;
	}

}
