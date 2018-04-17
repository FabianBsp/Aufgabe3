package edu.hm.cs.fs.ruletopf;

import aufg3.interfaces.Rules;

public class RuleTopf implements Rules {
	private int counterequals;
	private int topf;
	
	public RuleTopf() {
		counterequals = 0;
		topf = 0;
	}
	
	@Override
	public int[] calculateNewScores(int scorePlayerA, int scorePlayerB, int inputA, int inputB) {
		if(Math.abs(inputA - inputB) == 1) {
			if(topf>0) {
					if(inputA < inputB) {
						scorePlayerA += topf;
						scorePlayerA += (inputA+inputB);
					}
					else {
						scorePlayerB += topf;
						scorePlayerB += (inputA+inputB);
					}
					counterequals = 0;
					topf = 0;
				}
			else {
				if(inputA < inputB) {
					scorePlayerA += (inputA+inputB);
				}
				else {
					scorePlayerB += (inputA+inputB);
				}
				counterequals = 0;
			}
		}
		else if(inputA == inputB) {
			counterequals++;
			if(counterequals > 3){
				return new int[] {-1000+scorePlayerA,-1000+scorePlayerB};
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
