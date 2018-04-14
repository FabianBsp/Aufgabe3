package edu.hm.cs.fs.normal;

import aufg3.interfaces.Properties;

public class Normal implements Properties {

	@Override
	public int getScoreToWin() {
		return 40;
	}

	@Override
	public int[][] getChoices() {
		return new int[][]{{1,5}};
	}

}
