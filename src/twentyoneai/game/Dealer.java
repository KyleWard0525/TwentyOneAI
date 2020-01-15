/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.game;

import java.util.ArrayList;

/**
 * 
 * @author kward60
 */
public class Dealer extends Player {
    
    private ArrayList<Card> hand;
    private ArrayList<Card> splitHand;
    private boolean stand;
    
    public Dealer()
    {
        super();
        stand = false;
        hand = new ArrayList<>();
    }
    
    public void split()
    {
        splitHand = new ArrayList<>();
        
        //Add one card to split hand
        splitHand.add(hand.get(0));
        hand.remove(0);
    }
    
    /**
     * Method to control decision making
     * for dealer
     * 
     * return:
     * 0 = stand
     * 1 = hit
     * 2 = split
     */
    public int action()
    {
        int score = getScore();
        Card c;
        
        //Hit till 16
        if(score < 16)
        {
            return 1;
        }
        else{
            return 0;
        }
    }
    
    
}
