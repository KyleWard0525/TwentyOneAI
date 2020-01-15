/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.game;

/**
 * Playing card
 * 
 * @author kward60
 */
public class Card {
    
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }
    
    public enum Rank {
        ACE, TWO, THREE, FOUR, 
        FIVE, SIX, SEVEN, EIGHT, 
        NINE, TEN, JACK, QUEEN, KING
    }
    
    private Suit suit;
    private Rank rank;
    private int value;
    protected int width = 120;
    protected int height = 150;
    private String name;
    
    
    public Card (Suit s, Rank r)
    {
        this.suit = s;
        this.rank = r;
        setCardValue();
        this.name = r + " of " + s;
    }
    
    private void setCardValue()
    {
        switch(rank) {
            
            case ACE:
                this.value = 11;
                break;
            case TWO:
                this.value = 2;
                break;
            case THREE:
                this.value = 3;
                break;  
            case FOUR:
                this.value = 4;
                break;
            case FIVE:
                this.value = 5;
                break;
            case SIX:
                this.value = 6;
                break;
            case SEVEN:
                this.value = 7;
                break;
            case EIGHT:
                this.value = 8;
                break;
            case NINE:
                this.value = 9;
                break;
            case TEN:
                this.value = 10;
                break;
            case JACK:
                this.value = 10;
                break;
            case QUEEN:
                this.value = 10;
                break;
            case KING:
                this.value = 10;
                break;
        }
    }
    
    /**
     * Default Constructor
     */
    public Card()
    {
        
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Rank getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card { " + "name = " + name + '}';
    }
    
    
}
