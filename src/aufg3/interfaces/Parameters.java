package aufg3.interfaces;

/**
 * Holds all methods to controll game settings.
 * @author sinning
 * */
public interface Parameters {
	/**
	 * responsible for getting the highscore.
	 * @return the highscore
	 * */
	int getScoreToWin();
	/**
	 * responsible for getting the choices.
	 * @return the choices a player can select.
	 * */
	int[][] getChoices();
}
