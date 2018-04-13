package edu.hm.cs.rs.arch.rules;

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
			counterequals = 0;
		}
		else if(inputA == inputB) {
			counterequals++;
			if(counterequals == 3){
				return new int[] {-1,-1};
			}
		}
		else {
			scorePlayerA += inputA;
			scorePlayerB += inputB;
			counterequals = 0;
		}
		return new int[] {scorePlayerA,scorePlayerB};
	}

}
