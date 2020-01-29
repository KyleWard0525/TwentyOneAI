/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.game;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import twentyoneai.GameWindow;
import twentyoneai.ai.Agent;

/**
 * This class handles all the game logic, rules, and behavior
 *
 * Betting odds:
 *
 * Normal win - 1:1 Blackjack - 3:2
 *
 * @author kward60
 */
public class GameEngine {
    
    public static Deck deck;
    private ArrayList<Deck> allDecks;
    private ArrayList<Deck> usedDecks;
    public Dealer dealer;
    public Player player;
    private GameWindow gw;
    private BufferedImage cardBackImg;
    private boolean gameOver;
    private int dealerY;
    private int playerY;
    private int startX;
    private int cardSpacing;
    private JPanel GamePanel;
    protected int trueCount;
    protected int runningCount;
    private int currDeck;
    public int totalGames = 1;
    private double playerBet;
    private DecimalFormat df;
    public Agent bob;
    
    public GameEngine(GameWindow gw) {
        
        this.gw = gw;
        this.GamePanel = gw.getGamePanel();
        
        init();
        
        try {
            cardBackImg = ImageIO.read(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\TwentyOneAI\\img\\PNG\\blue_back.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initialize variables
     */
    private void init() {
        dealer = new Dealer();
        player = new Player();
        currDeck = 0;
        this.allDecks = new ArrayList<Deck>(8);
        this.usedDecks = new ArrayList<>(8);
        
        //Fill list of decks
        for(int i = 0; i < allDecks.size() - 1; i++)
        {
            allDecks.add(new Deck());
        }
        
        //Create starting deck and add to deck list
        this.deck = new Deck();
        allDecks.add(deck);
        
        this.dealerY = 0;
        this.playerY = GamePanel.getHeight() - 150;
        this.startX = GamePanel.getWidth() / 7;
        this.cardSpacing = 125;
        this.GamePanel.setBackground(new Color(0, 102, 0));
        this.trueCount = 0;
        this.df = new DecimalFormat("##.##");
        this.bob = new Agent(this);
        
    }
    
    public void deal() {

        //Enter bet before play
        enterBet();

        //Dealer, player, dealer, player
        dealer.hit(deck.pull());
        drawCard(dealer.getHand().get(0));
        
        player.hit(deck.pull());
        drawCard(player.getHand().get(0));
        
        dealer.hit(deck.pull());
        drawCard(dealer.getHand().get(1));
        
        player.hit(deck.pull());
        drawCard(player.getHand().get(1));
        
        GamePanel.revalidate();
        GamePanel.repaint();

        //Check if player can split
        if (player.canSplit()) {
            gw.getBtnSplit().setEnabled(true);
        }

        //Draw player balance
        gw.getLblBalance().setText(String.valueOf(df.format(player.getBalance())));
        
        timer.start();        
        
    }

    /**
     * Acquire player bet before the game start
     */
    public void enterBet() {
        //Disable all buttons except for bet button
        gw.getBtnBet().setEnabled(true);
        gw.getBtnHit().setEnabled(false);
        gw.getBtnNewGame().setEnabled(false);
        gw.getBtnSplit().setEnabled(false);
        gw.getBtnStand().setEnabled(false);
        gw.getTxtBetAmount().setEnabled(true);

        //Prompt user for bet
        System.out.println("Please enter your bet.\n");
        
    }
    
    public void drawCard(Card c) {
        JPanel cardPanel = new JPanel();
        JLabel cardLabel = new JLabel(c.getName());
        
        cardPanel.setSize(c.width, c.height);
        cardPanel.add(cardLabel);
        cardPanel.setBackground(Color.GRAY);
        cardPanel.repaint();

        //Paint card border
        switch (c.getSuit()) {
            case SPADES:
                cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                break;
            case CLUBS:
                cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                break;
            case HEARTS:
                cardPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
                break;
            case DIAMONDS:
                cardPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
                break;
        }

        //Check if hidden card
        if (dealer.getHand().get(0) == c) {
            cardPanel.removeAll();
            cardPanel.setBackground(Color.BLACK);
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }

        //Check if player card or dealer card
        if (player.getHand().contains(c)) {
            //Set JPanel bounds
            cardPanel.setLocation(startX + (cardSpacing * player.getHand().size()), playerY);
        } else if (dealer.getHand().contains(c)) {
            //Set JPanel bounds
            cardPanel.setLocation(startX + (cardSpacing * dealer.getHand().size()), dealerY);
        } else {
            System.out.println("Error: Card not found.");
        }

        //Set count
        this.runningCount = deck.runningCount;
        
        GamePanel.add(cardPanel);
        GamePanel.repaint();
    }
    
    public void drawSplitHands() {
        //TODO move cards and draw
    }
    
    public void setGw(GameWindow gw) {
        this.gw = gw;
    }
    
    public boolean isGameOver() {
        //21 or bust
        if (player.getScore() > 21) {
            player.bust = true;
            return true;
        } else if (dealer.getScore() > 21) {
            dealer.bust = true;
            return true;
        } else if (player.getScore() == 21 && dealer.getScore() == 21) {
            return true;
        } //Both players stand
        else if (player.isStand() && dealer.isStand()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void handleGameOver() {
        //Handle buttons
        gw.getBtnHit().setEnabled(false);
        gw.getBtnStand().setEnabled(false);
        gw.getBtnNewGame().setEnabled(true);

        //Determine winner
        if (player.bust) {
            System.out.println("Dealer wins via bust!");
            gw.getLblWinner().setForeground(Color.RED);
            gw.getLblWinner().setText("Dealer wins!");
        } else if (dealer.bust) {
            System.out.println("Player wins via bust!");
            
            gw.getLblWinner().setForeground(Color.BLUE);
            gw.getLblWinner().setText("Player wins!");
            
            player.addWin();
            player.setBalance(player.getBalance() + (playerBet * 2));
        } else {
            if (dealer.getScore() > player.getScore()) {
                System.out.println("Dealer wins with higher score!");
                gw.getLblWinner().setForeground(Color.RED);
                gw.getLblWinner().setText("Dealer wins!");
            } else if (player.getScore() > dealer.getScore()) {
                System.out.println("Player wins with higher score!");
                
                gw.getLblWinner().setForeground(Color.BLUE);
                gw.getLblWinner().setText("Player wins!");
                
                player.addWin();
                player.setBalance(player.getBalance() + (playerBet * 2));
            } else if ((player.getScore() == dealer.getScore())) {
                System.out.println("Push!\n");
                
                gw.getLblWinner().setForeground(Color.ORANGE);
                gw.getLblWinner().setText("Push!");
                
                player.setBalance(player.getBalance() + playerBet);
            }
            
        }
        //Stop timer
        timer.stop();
        
        //Print game information
        printGameData();

        //Clear players' hands
        player.getHand().clear();
        dealer.getHand().clear();

        //Update player balance label
        gw.getLblBalance().setText(String.valueOf(df.format(player.getBalance())));
        
        gw.getBtnNewGame().doClick();
        
        gw.revalidate();
        gw.repaint();
    }
    
    public void drawScores() {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        gw.getLblPlayerScore().setText(String.valueOf(playerScore));
        gw.getLblDealerScore().setText(String.valueOf(dealerScore));
    }
    
    public void printGameData()
    {
        System.out.println("Player wins: " + player.getWins());
        System.out.println("Games played: " + totalGames + "\n");
        System.out.println("Win percentage: " + df.format(((double)player.getWins() / (double)totalGames)*100.00) + "%");
    }
    
    public void dealerPlay() {
        int action = dealer.action();

        //Base case
        if (dealer.isStand()) {
            return;
        }

        //Stand
        if (action == 0) {
            dealer.setStand(true);
            return;
        } //Hit
        else if (action == 1) {
            Card c = deck.pull();
            dealer.hit(c);
            drawCard(c);
        }
        //Run until dealer is at 16 or more
        while (dealer.getScore() < 16) {
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            dealerPlay();
        }
    }
    
    public void checkDeck() {
        //Current deck is almost empty, change deck
        if (deck.cardsLeft() < 2) {
            usedDecks.add(allDecks.remove(currDeck));
            currDeck += 1;
            deck = allDecks.get(currDeck);
        }
        //Only one deck remaining
        if(allDecks.size() <= 1)
        {
            //Loop through used decks and shuffle
            for(int i = 0; i < usedDecks.size(); i++)
            {
                usedDecks.get(i).shuffle();
                
                //Add shuffled decks back into allDecks
                allDecks.add(usedDecks.get(i));
            }
        }
    }
    
    public void computeTrueCount() {
        if (runningCount > 0) {
            trueCount = runningCount / allDecks.size();
        }
    }
    
    public void updateCountLabels() {
        gw.getLblRunningCount().setText(String.valueOf(runningCount));
        gw.getLblTrueCount().setText(String.valueOf(trueCount));
    }
    
    public void playAI() {
        //Set player and dealer
        bob.setPlayer(player);
        bob.setDealer(dealer);
        //Enter AI Bet
        gw.getTxtBetAmount().setText(String.valueOf(df.format(player.getBalance() * 0.1)));
        gw.getBtnBet().doClick();

        //Get AI play
        double[] output = bob.getPlay();
        int[] play = new int[output.length];
        
        //Round outputs to integers
        for(int i = 0; i < play.length; i++)
        {
            play[i] = (int) Math.round(output[i]);
        }
        
        System.out.println("AI play = " + Arrays.toString(play));

        //Hit
        if (play[0] == 1 && play[1] == 0) {
            gw.getBtnHit().doClick();
        } //Stand
        else if (play[0] == 0 && play[1] == 1) {
            gw.getBtnStand().doClick();
        }
    }
    
    public ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            bob.setrCount(runningCount);
            bob.setTrueCount(trueCount);
            
            if (gw.AIplayer) {
                
                playAI();
            }

            checkDeck();
            computeTrueCount();
            drawScores();
            updateCountLabels();
            GamePanel.revalidate();
            GamePanel.repaint();
            
            if (isGameOver()) {
                System.out.println("\nGame Over!");
                handleGameOver();
            }
            
        }
    };
    
    Timer timer = new Timer(100, listener);
    
    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }
    
    public void setRunningCount(int runningCount) {
        this.runningCount = runningCount;
    }
    
    public int getTrueCount() {
        return trueCount;
    }
    
    public int getRunningCount() {
        return runningCount;
    }
    
    public double getPlayerBet() {
        return playerBet;
    }
    
    public void setPlayerBet(double playerBet) {
        this.playerBet = playerBet;
    }
    
}
