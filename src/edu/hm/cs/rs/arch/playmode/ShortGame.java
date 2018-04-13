package edu.hm.cs.rs.arch.playmode;

import aufg3.interfaces.Properties;

public class ShortGame implements Properties {

	@Override
	public int getScoreToWin() {
		return 12;
	}

	@Override
	public int[][] getChoices() {
		return new int[][]{{1,3}};
	}

}
