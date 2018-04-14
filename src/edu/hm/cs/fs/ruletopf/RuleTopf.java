package edu.hm.cs.fs.ruletopf;

import aufg3.interfaces.Rules;

public class RuleTopf implements Rules {
	private int counterequals = 0;
	private int topf = 0;
	
	@Override
	public int[] calculateNewScores(int scorePlayerA, int scorePlayerB, int inputA, int inputB) {
		if(Math.abs(inputA - inputB) == 1) {
			if(inputA < inputB) {
				scorePlayerA += topf;
			}
			else {
				scorePlayerB += topf;
			}
			counterequals = 0;
		}
		else if(inputA == inputB) {
			counterequals++;
			if(counterequals > 3){
				return new int[] {-1,-1};
			}
			topf += (inputA+inputB);
		}
		else {
			scorePlayerA += inputA;
			scorePlayerB += inputB;
			counterequals = 0;
		}
		return new int[] {scorePlayerA,scorePlayerB};
	}
}
