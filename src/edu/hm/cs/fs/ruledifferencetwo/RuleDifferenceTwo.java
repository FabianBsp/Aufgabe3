package edu.hm.cs.fs.ruledifferencetwo;

import aufg3.interfaces.Rules;

public class RuleDifferenceTwo implements Rules {
	private int counterequals;
	
	public RuleDifferenceTwo() {
		counterequals = 0;
	}

	@Override
	public int[] calculateNewScores(int scorePlayerA, int scorePlayerB, int inputA, int inputB){
		if(Math.abs(inputA - inputB) >= 2) {
			if(inputA > inputB) {
				scorePlayerA += (inputA + inputB);
			}
			else {
				scorePlayerB += (inputA + inputB);
			}
			counterequals = 0;
		}
		else if(inputA == inputB){
			counterequals++;
			scorePlayerA += inputA;
			scorePlayerB += inputB;
			if(counterequals > 3) {
				return new int[]{-1*scorePlayerA,-1*scorePlayerB};
			}
		}
		else if(Math.abs(inputA - inputB) == 1) {
			if(inputA < inputB) {
				scorePlayerA += (inputA+inputB);
			}
			else {
				scorePlayerB += (inputA+inputB);
			}
			counterequals = 0;
		}
		else {
			scorePlayerA += inputA;
			scorePlayerB += inputB;
			counterequals = 0;
		}
		return new int[] {scorePlayerA,scorePlayerB};
	}

}
