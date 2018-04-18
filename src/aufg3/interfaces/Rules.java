package aufg3.interfaces;

/**
 * Holds methods that control the game logic.
 * @author sinning
 * */
public interface Rules {
	/**
	 * calculates the new Scores of both players and returns the new Scores.
	 * @param scorePlayerA the score of the first player
	 * @param scorePlayerB the score of the second player
	 * @param the choice of the first player
	 * @param the choice of the second player
	 * @return the new scores of the first and second player
	 * */
	int[] calculateNewScores(int scorePlayerA,int scorePlayerB, int inputA, int inputB);
}
