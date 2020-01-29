/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.game;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Creates a deck of playing cards
 * 
 * @author kward60
 */
public class Deck {
    
    
    protected final int SIZE = 52;
    private ArrayList<Card> cards;
    private ArrayList<Card> pulledCards;
    protected int runningCount;
    
    public Deck()
    {
        init();
    }
    
    /**
     * Initialize variables
     */
    private void init()
    {
        cards = new ArrayList<>();
        pulledCards = new ArrayList<>();
        runningCount = 0;
        buildDeck();
        shuffle();
    }
    
    /**
     * Build the deck
     */
    private void buildDeck()
    {
        //Iterate through cards and add to deck
        for(Card.Suit s : Card.Suit.values())
        {
            for(Card.Rank r : Card.Rank.values())
            {
                Card c = new Card(s,r);
                
                //Add card to deck
                if(!cards.contains(c))
                {
                    cards.add(c);
                }
            }
        }
        
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    
    public void reset()
    {
        cards.clear();
        pulledCards.clear();
        buildDeck();
        shuffle();
    }
    
    /**
     * Pull the top card
     * @return 
     */
    public Card pull()
    {
        
        if(cards.size() < 1)
        {
            reset();
        }
        
        Card pulled = cards.get(0);
        
        //Update count
        runningCount += pulled.count;
        
        pulledCards.add(pulled);
        cards.remove(0);
        return pulled;
    }
    
    public int cardsLeft()
    {
        return cards.size();
    }
    
}
