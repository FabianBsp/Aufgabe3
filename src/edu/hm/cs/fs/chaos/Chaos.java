package edu.hm.cs.fs.chaos;

import aufg3.interfaces.Parameters;

public class Chaos implements Parameters {
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
