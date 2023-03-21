package simple21;

import java.util.Scanner;
import java.util.Random;

/**
 * This is a simplified version of a common card game, "21". 
 */
public class GameControl {
    
	/**
	 * Human player.
	 */
    HumanPlayer human;
    
    /**
     * Computer player.
     */
    ComputerPlayer player1;
    
    /**
     * Computer player.
     */
    ComputerPlayer player2;
    
    /**
     * Computer player.
     */
    ComputerPlayer player3;
    
    /** 
     * A random number generator to be used for returning random "cards" in a card deck.
     * */
    Random random = new Random();
      
    /**
     * The main method just creates a GameControl object and calls its run method.
     * @param args Not used.
     */
    public static void main(String args[]) {    
        new GameControl().run();
    }
    
    /**
     * Prints a welcome method, then calls methods to perform each of the following actions:
     * - Create the players (one of them a Human)
     * - Deal the initial two cards to each player
     * - Control the play of the game
     * - Print the final results
     */
    public void run() {

        Scanner scanner = new Scanner(System.in);
        
    	// prints a welcome message
    	System.out.println("Welcome to Simple 21!\n"
    			+ "You'll play against 3 other players (computers).\n"
    			+ "Try to get as close to 21 as possible, without going over.\n");
    	System.out.println("What is your name?");
    	
    	// takes the input as human player's name
    	String name = scanner.next();
    	
    	// call createPlayers method to create players.
    	createPlayers(name);
    	
    	// Deals two "cards" to each player
    	deal();	
    	
    	while(checkAllPlayersHavePassed()== false) {
    		controlPlay(scanner);
    	}
    	
    	if (checkAllPlayersHavePassed()==true) {
    		printResults();
    	}
        scanner.close();
    }
    
    /**
     * Creates one human player with the given humansName, and three computer players with hard-coded names.
     * @param humansName for human player
     */
    public void createPlayers(String humansName) {
    	// creates a human player
       this.human = new HumanPlayer(humansName);
       
	   // creates three computer players
	   this.player1 = new ComputerPlayer("Player 1");
	   this.player2 = new ComputerPlayer("Player 2");
	   this.player3 = new ComputerPlayer("Player 3");
    }
    
    /**
     * Deals two "cards" to each player, one hidden, so that only the player who gets it knows what it is, 
     * and one face up, so that everyone can see it. (Actually, what the other players see is the total 
     * of each other player's cards, not the individual cards.)
     */
    public void deal() { 
    	
    	//Deals two "cards" to human player
        human.takeHiddenCard(nextCard());
        human.takeVisibleCard(nextCard());
        
        //Deals two "cards" to player 1
        player1.takeHiddenCard(nextCard());
        player1.takeVisibleCard(nextCard());
        
        //Deals two "cards" to player 2
        player2.takeHiddenCard(nextCard());
        player2.takeVisibleCard(nextCard());
        
        //Deals two "cards" to player 3
        player3.takeHiddenCard(nextCard());
        player3.takeVisibleCard(nextCard());
    	
    }
    
    /**
     * Returns a random "card", represented by an integer between 1 and 10, inclusive. 
     * The odds of returning a 10 are four times as likely as any other value (because in an actual
     * deck of cards, 10, Jack, Queen, and King all count as 10).
     * 
     * Note: The java.util package contains a Random class, which is perfect for generating random numbers.
     * @return a random integer in the range 1 - 10.
     */
    public int nextCard() { 
    	// randomly creates an integer between 1 and 13, inclusive
    	int num = 1 + (int)(Math.random()*13);
    	
    	// The odds of returning a 10 are four times as likely as any other value
    	if (num == 11 || num == 12 || num == 13) {
    		num = 10;
    	}
    	
    	return num;
    }

    /**
     * Gives each player in turn a chance to take a card, until all players have passed. Prints a message when 
     * a player passes. Once a player has passed, that player is not given another chance to take a card.
     * @param scanner to use for user input
     */
    public void controlPlay(Scanner scanner) { 
        while (checkAllPlayersHavePassed() == false) {
        	// Human player's turn to take a card
        	if (!human.passed) {
        		boolean humanResponse = human.offerCard(human, player1, player2, player3, scanner);
        		
        		if (humanResponse == true) {
        			human.takeVisibleCard(nextCard());
        		}
        		else { // prints a message when human player passes
        			System.out.println(human.name+" passes.");
        		}
        	}
        	
        	// player 1's turn to take a card
        	if (!player1.passed) {
        		boolean player1Response = player1.offerCard(human, player1, player2, player3);

        		if (player1Response == true) {
        			player1.takeVisibleCard(nextCard());
        		}
        		else {// prints a message when this player passes
        				System.out.println(player1.name+" passes.");
        		}
        	}
        	
        	// player 2's turn to take a card
        	if (!player2.passed) {
        		boolean player2Response = player2.offerCard(human, player1, player2, player3);

        		if (player2Response == true) {
        			player2.takeVisibleCard(nextCard());
        		}
        		else {// prints a message when this player passes
        				System.out.println(player2.name+" passes.");
        		}
        	}
        	
        	// player 3's turn to take a card
        	if (!player3.passed) {
        		boolean player3Response = player3.offerCard(human, player1, player2, player3);

        		if (player3Response == true) {
        			player3.takeVisibleCard(nextCard());
        		}
        		else {// prints a message when this player passes
        				System.out.println(player3.name+" passes.");
        		}
        	}

        }
    }
     
    /**
     * Checks if all players have passed.
     * @return true if all players have passed
     */
    public boolean checkAllPlayersHavePassed() {
    	// Checks if all players have passed.
    	if(human.passed && player1.passed && player2.passed && player3.passed) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    /**
     * Prints a summary at the end of the game.
     * Displays how many points each player had, and if applicable, who won.
     */
    public void printResults() { 
    	// Prints a summary at the end of the game.
    	System.out.println("\nGame over.\n"
    			+human.name + " has " + human.getScore() + " total point(s).\n"
    			+player1.name + " has " + player1.getScore() + " total point(s).\n"
    			+player2.name + " has " + player2.getScore() + " total point(s).\n"
    			+player3.name + " has " + player3.getScore() + " total point(s).\n"
    			);
		printWinner();	
    }

    /**
     * Determines who won the game, and prints the results.
     */
    public void printWinner() { 
    	
    	//initialize a boolean variable, will turn to true if tie
    	boolean tie = false;
    	
    	//initialize a integer variable to count how close to 21
    	int scoreCount = 0;
    	
    	//initialize a integer variable to store the index of the scoresList and later apply to namesList 
    	int winnerIndex = -1;
    	
    	//creates a list of the players scores
    	int[] scoresList = {human.getScore(), player1.getScore(), player2.getScore(), player3.getScore()};
    	
    	//creates a list of the players name in same order of the scores (score and player names have the same index)
    	String[] namesList = {human.name, player1.name, player2.name, player3.name};

    	
    	//iterate a for loop the scoresList to find who will win the game
    	for (int i = 0; i < scoresList.length; i++) {
    		
    		//skip if the score is greater than 21
    		if (scoresList[i] > 21) {
    			continue;
    		}
    		//find the score that is closest to 21 by comparing the scoreCount, replace the scoreCount if it is less than the following one
    		else if (scoresList[i] > scoreCount) {
    			
    			//stores the index of this player
    			winnerIndex = i;
    			
    			//stores the scoreCount of this player
    			scoreCount = scoresList[i];
    			
    			//set tie to false since there is a winner
    			tie = false;
    		}
    		//set tie when there are two players that have the same closest score
    		else if (scoresList[i] == scoreCount) {
    			tie = true;
    			}
    	}
    	
    	//if all players have over 21 score, then nobody wins.
    	if (winnerIndex == -1) {
    		System.out.println("Nobody wins.");
    		}
    	//if tie is true, then nobody wins
    	else if (tie==true) {
    		System.out.println("Tie, nobody wins.");
    		}
    	//else, print the name and score using the winnerIndex.
    	else {
    		System.out.println(namesList[winnerIndex] + " wins with " + scoresList[winnerIndex] + " points!");
    		}
    	
    }

    	
 }



