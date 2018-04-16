package edu.hm.cs.rs.arch.a03_srp;

import java.io.IOException;

import aufg3.interfaces.PlayerDialog;
import aufg3.interfaces.Properties;
import aufg3.interfaces.Rules;
import edu.hm.cs.fs.chaos.Chaos;
import edu.hm.cs.fs.normal.Normal;
import edu.hm.cs.fs.playersfile.Playersfile;
import edu.hm.cs.fs.playerskeyboard.Playerskeyboard;
import edu.hm.cs.fs.playersnetwork.Playersnetwork;
import edu.hm.cs.fs.rulenormal.RuleNormal;
import edu.hm.cs.fs.shortgame.ShortGame;

public class UndercutMonoNew {

	public static void main(String[] args) {
		Properties properties = new Normal();
		PlayerDialog inputinterface = new Playerskeyboard();
		Rules rule = new RuleNormal();
		
		new UndercutMonoNew().play(properties,inputinterface,rule);
	}
	
	public void play(Properties properties,PlayerDialog dialog,Rules rule) {
		int playerAScore = 0;
        int playerBScore = 0;
        int playerAChoice;
        int playerBChoice;
        int roundsPlayed = 1;
        int maxChoice;
        int scoreToWin = properties.getScoreToWin();
        
        
		while(playerAScore < scoreToWin && playerBScore < scoreToWin) {
			
			int[][] choices = getPlayerAandPlayerBChoices(properties);
			maxChoice = choices[choices.length-1][choices.length-1];
			
			//System.out.println(maxChoice);
			//do {
                final int input = dialog.input(0,maxChoice,1,choices);
                //if(input < 0)
                //   throw new IOException(); // bomb out on end of input
                playerAChoice = input;//input - '0';
          //  }while(playerAChoice < 1 || playerAChoice > maxChoice);
			
		//	do {
                final int input2 = dialog.input(1,maxChoice,1,choices);
                //if(input < 0)
                //   throw new IOException(); // bomb out on end of input
                playerBChoice = input2;//input - '0';
        //   }while(playerBChoice < 1 || playerBChoice > maxChoice);
			
			int[] newscores = rule.calculateNewScores(playerAScore, playerBScore, playerAChoice, playerBChoice);
			
		/*	if(threeTimesEqualOrFinish(newscores,properties)) {
				try {
					dialog.output(roundsPlayed,new int[]{playerAScore,playerBScore});
					dialog.close();
				}
				catch(IOException e){}
				return;
			} */
		//	else {
				playerAScore = newscores[0];
				playerBScore = newscores[1];
				dialog.output(roundsPlayed,new int[]{playerAScore,playerBScore});
				roundsPlayed++;
		//	}
		//	System.out.println("Player A : " +inputAB[0]+"  Player B : "+inputAB[1]);
			
		}
		// announce final results to both players
        if(playerAScore == playerBScore)
            System.out.println("Tie");
        else if(playerAScore > playerBScore)
            System.out.println("Player A wins");
        else
            System.out.println("Player B wins");
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
