package edu.hm.cs.fs.rulenormal;

import aufg3.interfaces.Rules;

/**
 * RuleNormal represents the same game rules as
 * UndercutMono.
 * @author sinning
 * */
public class RuleNormal implements Rules {
	
	/**
	 * Default Constructor.
	 * */
	public RuleNormal() {}

	/**
	 * Calculates the new scores of PlayerA and PlayerB.
	 * @author sinning
	 * @param scorePlayerA the score of PlayerA
	 * @param scorePlayerB the score of PlayerB
	 * @param inputA the input of PlayerA
	 * @param inputB the input of PlayerB
	 * */
	@Override
	public int[] calculateNewScores(int scorePlayerA, int scorePlayerB, int inputA, int inputB){
		if(Math.abs(inputA - inputB) == 1) {
			if(inputA < inputB) {
				scorePlayerA += (inputA + inputB);
			}
			else {
				scorePlayerB += (inputA + inputB);
			}
		}
		else if(inputA == inputB) {
			scorePlayerA += inputA;
			scorePlayerB += inputB;
		}
		else {
			scorePlayerA += inputA;
			scorePlayerB += inputB;
		}
		return new int[] {scorePlayerA,scorePlayerB};
	}

}
