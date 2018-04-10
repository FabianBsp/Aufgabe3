/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
package edu.hm.cs.rs.arch.a03_srp; //X

import java.io.IOException;

/**
 * Monolithic version of Undercut. Violates lots of design principles.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 * @see <a href="http://www.ams.org/publicoutreach/msamhome/96-undercut-index.html">Undercut</a>
 */
public class UndercutMono {
    /**
     * Entry point.
     * @param args Commandline args: none.
     * @exception IOException on incomplete input.
     */
    public static void main(String... args) throws IOException {
        new UndercutMono().play();
    }

    /**
     * Runs an Undercut game.
     * Gets input from System.in, prints output to System.out.
     * @exception IOException on incomplete input.
     */
    public void play() throws IOException {
        final int scoreToWin = 40;
        final int maxChoice = 5;
        int playerAScore = 0;
        int playerBScore = 0;
        int roundsPlayed = 0;

        System.out.printf("Undercut start%n"); // initial message to both players

        // loop until a player wins ...
        while(playerAScore < scoreToWin && playerBScore < scoreToWin) {
            int playerAChoice;
            // read players' choices; if invalid, discard and retry
            System.out.printf("Player A, your choice (1-%d)?%n", maxChoice); // to player A
            do {
                final int input = System.in.read();
                if(input < 0)
                    throw new IOException(); // bomb out on end of input
                playerAChoice = input - '0';
            }
            while(playerAChoice < 1 || playerAChoice > maxChoice);

            int playerBChoice;
            System.out.printf("Player B, your choice (1-%d)?%n", maxChoice); // to player B
            do {
                final int input = System.in.read();
                if(input < 0)
                    throw new IOException();
                playerBChoice = input - '0';
            }
            while(playerBChoice < 1 || playerBChoice > maxChoice);

            // update scores
            if(playerAChoice == playerBChoice - 1)
                playerAScore += playerAChoice + playerBChoice;
            else if(playerBChoice == playerAChoice - 1)
                playerBScore += playerAChoice + playerBChoice;
            else {
                playerAScore += playerAChoice;
                playerBScore += playerBChoice;
            }
            roundsPlayed++;

            // publish scores to both players
            System.out.printf("Round %d, Player A: %d, Player B: %d%n", roundsPlayed, playerAScore, playerBScore);
        }

        // announce final results to both players
        if(playerAScore == playerBScore)
            System.out.println("Tie");
        else if(playerAScore > playerBScore)
            System.out.println("Player A wins");
        else
            System.out.println("Player B wins");
    }

}