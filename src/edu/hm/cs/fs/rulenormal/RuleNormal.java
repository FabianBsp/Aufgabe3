package edu.hm.cs.fs.rulenormal;

import aufg3.interfaces.Rules;

public class RuleNormal implements Rules {
	private int counterequals = 0;
	
	public RuleNormal() {}

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
