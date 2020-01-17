/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.game;

import java.util.ArrayList;
import twentyoneai.game.Card.Rank;

/**
 *
 * @author user
 */
public class Player {
    
    public enum Action {
        HIT, STAND, SPLIT, DOUBLE_DOWN
    }
    
    private ArrayList<Card> hand;
    private ArrayList<Card> splitHand;
    private boolean stand;
    public Card cardShowing;
    public boolean bust;
    private int wins;
    private double balance;
    public boolean hasBet;
    
    public Player()
    {
        hand = new ArrayList<>();
        stand = false;
        this.balance = 1000.0;
    }
    
    public void hit(Card c)
    {
        //Check if card should be showing
        if(hand.size() == 1)
        {
            cardShowing = c;
            hand.add(c);
        }
        else {
            hand.add(c);
        }
        
        //Check if over
        if(getScore() > 21)
        {
            bust = true;
        }
    }
    
    public void hitSplit(Card c)
    {
        //Ensure splitHand exists
        if(splitHand.size() > 0) {
        splitHand.add(c);
        }
        
        //Check score
        if(getSplitScore() > 21)
        {
            bust = true;
        }
    }
    
    public void split()
    {
        splitHand = new ArrayList<>();
        
        //Add one card to split hand
        splitHand.add(hand.get(0));
        hand.remove(0);
    }
    
    /**
     * Check if player can split
     * @return 
     */
    public boolean canSplit()
    {
        //Check if cards are of the same rank
        if(hand.get(0).getRank() == hand.get(1).getRank())
        {
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getScore()
    {
        int score = 0;
        
        for(Card c : hand)
        {
            //Convert ACE from 11 to 1
            if(score > 10 && c.getRank() == Rank.ACE)
            {
                c.setValue(1);
            }
            
            score += c.getValue();
        }
        return score;
    }
    
    public int getSplitScore()
    {
        int score = 0;
        
        for(Card c : splitHand)
        {
            score += c.getValue();
        }
        return score;
    }
    
    public boolean busted()
    {
        return bust;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isStand() {
        return stand==true;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
    
    public void addWin()
    {
        wins++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
