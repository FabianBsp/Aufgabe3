package edu.hm.cs.fs.normal;

import aufg3.interfaces.Parameters;

/**
 * class that sends settings information to the Game.
 * That settings are equal to the one of UndercutMono
 * @author sinning
 * */
public class Normal implements Parameters {

	/**
	 * Getter to return the Score to win.
	 * @return the number where a Player win
	 * */
	@Override
	public int getScoreToWin() {
		return 40;
	}

	/**
	 * Getter to return the choices a Player has.
	 * @return the choices a player has.
	 * */
	@Override
	public int[][] getChoices() {
		return new int[][]{{1,5}};
	}

}
