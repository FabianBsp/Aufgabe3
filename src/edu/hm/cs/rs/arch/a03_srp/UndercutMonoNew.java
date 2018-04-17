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

public class UndercutMonoNew {

	public static void main(String[] args) throws IOException{
		Parameters properties = new Chaos();
		PlayerDialog inputinterface = new Playerskeyboard();
		Rules rule = new RuleDifferenceTwo();
		
		new UndercutMonoNew().play(properties,inputinterface,rule);
	}
	
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
