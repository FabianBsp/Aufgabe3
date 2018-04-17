package edu.hm.cs.rs.arch.a03_srp;

import java.io.IOException;

import aufg3.interfaces.Parameters;
import aufg3.interfaces.PlayerDialog;
import aufg3.interfaces.Rules;
import edu.hm.cs.fs.chaos.Chaos;
import edu.hm.cs.fs.normal.Normal;
import edu.hm.cs.fs.playerskeyboard.Playerskeyboard;
import edu.hm.cs.fs.ruledifferencetwo.RuleDifferenceTwo;
import edu.hm.cs.fs.rulenormal.RuleNormal;
import edu.hm.cs.fs.shortgame.ShortGame;

/**
 * The new version of UndercutMono.
 * @author sinning
 */
public class UndercutMonoNew {

	/**
     * Entry point.
     * @param args Commandline args: none.
     * @exception IOException on incomplete input.
     */
	public static void main(String[] args) throws IOException{
		Parameters properties = new ShortGame();
		PlayerDialog inputinterface = new Playerskeyboard();
		Rules rule = new RuleNormal();
		
		new UndercutMonoNew().play(properties,inputinterface,rule);
	}
	
	/**
     * Runs an Undercut game.
     * Gets input from System.in, prints output to System.out.
     * @exception IOException on incomplete input.
     */
	public void play(Parameters properties,PlayerDialog dialog,Rules rule) throws IOException{
		int playerAScore = 0;
        int playerBScore = 0;
        int playerAChoice;
        int playerBChoice;
        int roundsPlayed = 1;
        int scoreToWin = properties.getScoreToWin();
        
        
		while(playerAScore < scoreToWin && playerBScore < scoreToWin &&
				(playerAScore > -1 && playerBScore > -1)) {
			
			int[][] choices = getPlayerAandPlayerBChoices(properties);
			
                final int input = dialog.input(0,choices);
                
                playerAChoice = input;
         
                final int input2 = dialog.input(1,choices);
              
                playerBChoice = input2;
			
			int[] newscores = rule.calculateNewScores(playerAScore, playerBScore, playerAChoice, playerBChoice);
			
				playerAScore = newscores[0];
				playerBScore = newscores[1];
				dialog.output(roundsPlayed,new int[]{playerAScore,playerBScore});
				roundsPlayed++;
			
		}
		// announce final results to both players
        if(playerAScore == playerBScore)
        	dialog.messageEnding(-1);
        else if(playerAScore > playerBScore)
        	dialog.messageEnding(0);
        else
        	dialog.messageEnding(1);
	}
	
	/**
	 * Returns the choices for PlayerA and PlayerB
	 * @author sinning
	 * @return The choices for PlayerA and PlayerB
	 * */
	private int[][] getPlayerAandPlayerBChoices(Parameters properties){
		if(properties instanceof Chaos) {	// 2 Wahlmöglichkeiten jeweils für SpielerA und SpielerB
			int[][] retarr = new int[][] {properties.getChoices()[0],properties.getChoices()[1]};
			((Chaos) properties).nextindices();
			return retarr;
		}
		else { // eine Wahlmöglichkeit für beide Spieler
			return new int[][] {properties.getChoices()[0],properties.getChoices()[0],};
		}
	}
	
	
}
