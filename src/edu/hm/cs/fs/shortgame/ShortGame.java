package edu.hm.cs.fs.shortgame;

import aufg3.interfaces.Parameters;

/**
 * That class represents a Short game.
 * @author sinning
 * */
public class ShortGame implements Parameters {

	/**
	 * Getter for the highscore.
	 * @author sinning
	 * */
	@Override
	public int getScoreToWin() {
		return 12;
	}

	/**
	 * Getter for the Choices both players have.
	 * @author sinning
	 * */
	@Override
	public int[][] getChoices() {
		return new int[][]{{1,3}};
	}

}
