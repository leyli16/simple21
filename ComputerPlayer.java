package simple21;

/**
 * Represents a computer player in this simplified version of the "21" card game.
 */
public class ComputerPlayer {

	/** 
	 * The name of the player.
	 */
    String name;
    
    /**
     * The player's one hidden card (a value from 1 - 10).
     */
    private int hiddenCard = 0;
    
    /** 
     * The sum of the player's cards, not counting the hidden card. 
     */
    private int sumOfVisibleCards = 0;
    
    /**
     * Flag indicating if the player has passed (asked for no more cards).
     */
    boolean passed = false;
    
    /**
     * Constructs a computer player with the given name.
     * @param name of the user.
     */
    public ComputerPlayer (String name) {
        this.name = name;
    }
    
    /**
     * Decides whether to take another card. In order to make this decision, this player considers 
     * their own total points (sum of visible cards + hidden card). 
     * This player may also consider other players' sum of visible cards, but not the value 
     * of other players' hidden cards.
     * @param human The other human player
     * @param player1 Another (computer) player
     * @param player2 Another (computer) player
     * @param player3 Another (computer) player
     * @return true if this player wants another card
     */
    public boolean offerCard(HumanPlayer human, ComputerPlayer player1, ComputerPlayer player2, ComputerPlayer player3) { 

    	
    	boolean response = true;
    	
    	// if total points is over 17, then the player will pass
    	if (getScore()> 17) {
    		response = false;
    	}else { // if total points is under 17, then player will take an another card
    		response = true;
    	}
    	
    	// set the passed to be true since no more card need
    	if (!response) {
    		this.passed = true;
    	}
    	
    	return response;
    	
    }
    
    /**    
     * Puts the specified card in this player's hand as the hidden card.
     * Prints a message saying that the card is being taken, but does not print the value of the hidden card.
     * @param card being taken
     */
    public void takeHiddenCard(int card) {
    	// Puts the specified card in this player's hand as the hidden card.
    	this.hiddenCard = card;
    	//Prints a message saying that the card is being taken, but does not print the value of the hidden card.
    	System.out.println(name+ " takes a hidden card.");	
 
    }
    
    /**
     * Adds the given card to the sum of the visible cards for this player.
     * Prints a message saying that the card is being taken.
     * @param card being taken
     */
    public void takeVisibleCard(int card) { 
    	// add given card into visible card pool
    	this.sumOfVisibleCards += card;
    	
    	//Prints a message saying that the card is being taken.
    	System.out.println(name +" takes "+ card + ".");
    }

    /**
     * Returns the total sum of this player's cards, not counting the hidden card. 
     * @return sumOfVisibleCards
     */
    public int getSumOfVisibleCards() { 
    	// return the total sum of this player's cards
    	return sumOfVisibleCards;
    	
    }
    
    /**
     * Return this player's total score (the total of all this player's cards).
     * That is to say, the sum of the visible cards + the hidden card.
     * @return total score 
     */
    public int getScore() { 
    	// Return this player's total score (the total of all this human player's cards)
    	return sumOfVisibleCards + hiddenCard;
    }
}
