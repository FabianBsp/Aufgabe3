package edu.hm.cs.fs.chaos;

import aufg3.interfaces.Parameters;

/**
 * class that sends settings information to the Game.
 * Each Player gets a different choice
 * @author sinning
 * */
public class Chaos implements Parameters {
	/**
	 * Index of choices that refer to PlayerA choice.
	 * */
	private int indexA;
	/**
	 * Index of choices that refer to PlayerB choice.
	 * */
	private int indexB;
	/**
	 * All choices a player can have.
	 * */
	private int[][] choices = new int[][] {{1,4},{2,5},{1,3,5}};

	/**
	 * Default Constructor.
	 * */
	public Chaos() {
		indexA = 0;
		indexB = 1;
	}
	
	/**
	 * Getter to return the Score to win.
	 * @return the number where a Player win
	 * */
	@Override
	public int getScoreToWin() {
		return 42;
	}

	/**
	 * Getter to return the choices a Player has.
	 * @return the choices a player has.
	 * */
	@Override
	public int[][] getChoices() {
		return new int[][] {choices[indexA],choices[indexB]};
	}
	
	/**
	 * initializes the next indices of the choice array.
	 * */
	public void nextindices(){
		indexA++;
		indexA = indexA % 3;
		indexB++;
		indexB = indexB % 3;
	}

}
