package edu.hm.cs.fs.shortgame;

import aufg3.interfaces.Parameters;

public class ShortGame implements Parameters {

	@Override
	public int getScoreToWin() {
		return 12;
	}

	@Override
	public int[][] getChoices() {
		return new int[][]{{1,3}};
	}

}
