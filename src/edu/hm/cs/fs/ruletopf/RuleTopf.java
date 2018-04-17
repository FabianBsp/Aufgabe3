package edu.hm.cs.fs.ruletopf;

import aufg3.interfaces.Rules;

/**
 * RuleTopf handles the logic of the game.
 * If both players have set the same choices, both choices
 * are added and saved in a variable.If one player has a lower number in
 * the next round that player gets that variable and the sum of both choices.
 * @author sinning
 * */
public class RuleTopf implements Rules {
	private int counterequals;
	private int topf;
	
	/**
	 * Default Constructor.
	 * */
	public RuleTopf() {
		counterequals = 0;
		topf = 0;
	}
	
	/**
	 * calculates the new scores of both players.
	 * @param scorePlayerA the scores of playerA
	 * @param scorePlayerB the scores of playerB
	 * @param inputA the choice of playerA
	 * @param inputB the choice of playerB
	 * @return the new scores of PlayerA and PlayerB
	 * */
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
