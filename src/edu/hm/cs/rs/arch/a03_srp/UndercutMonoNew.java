package edu.hm.cs.rs.arch.a03_srp;

import java.io.IOException;

import org.junit.Rule;

import aufg3.interfaces.PlayerDialog;
import aufg3.interfaces.Properties;
import aufg3.interfaces.Rules;
import edu.hm.cs.rs.arch.playmode.Chaos;
import edu.hm.cs.rs.arch.playmode.Normal;
import edu.hm.cs.rs.arch.rules.RuleNormal;

public class UndercutMonoNew {

	public static void main(String[] args) {
		Properties properties = new Normal();
		PlayerDialog inputinterface = new Playersnetwork();
		Rules rule = new RuleNormal();
		
		new UndercutMonoNew().play(properties,inputinterface,rule);
	}
	
	public void play(Properties properties,PlayerDialog dialog,Rules rule){
		int playerAScore = 0;
        int playerBScore = 0;
        
		while(true) {
			
			int[][] choices = getPlayerAandPlayerBChoices(properties);
			int[] inputAB = dialog.input(choices);
			int[] newscores = rule.calculateNewScores(playerAScore, playerBScore, inputAB[0], inputAB[1]);
			
			if(threeTimesEqualOrFinish(newscores,properties)) {
				try {
					dialog.close();
				}
				catch(IOException e){}
				return;
			}
			else {
				playerAScore = newscores[0];
				playerBScore = newscores[1];
				dialog.output(new int[]{playerAScore,playerBScore});
			}
			System.out.println("Player A : " +inputAB[0]+"  Player B : "+inputAB[1]);
			
		}
	}
	
	private boolean threeTimesEqualOrFinish(int[] scorePlayerAB,Properties properties) {
		return (scorePlayerAB[0] == -1 && scorePlayerAB[1] == -1) || (scorePlayerAB[0] >= properties.getScoreToWin() || scorePlayerAB[1] >= properties.getScoreToWin());
	}
	
	private int[][] getPlayerAandPlayerBChoices(Properties properties){
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
