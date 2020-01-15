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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import twentyoneai.GameWindow;

/**
 * This class handles all the game logic, rules, and behavior
 *
 * @author kward60
 */
public class GameEngine {

    public static Deck deck;
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
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
        this.dealerY = 0;
        this.playerY = GamePanel.getHeight() - 150;
        this.startX = GamePanel.getWidth() / 7;
        this.cardSpacing = 125;
        this.GamePanel.setBackground(new Color(0, 102, 0));
    }

    public void deal() {
        
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
        
        timer.start();
        
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

        /*
        //Check if hidden card
        if (dealer.getHand().get(0) == c) {
            cardPanel.removeAll();
            cardPanel.setBackground(Color.BLACK);
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
        */

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

        
        GamePanel.add(cardPanel);
        GamePanel.repaint();
    }

    public void setGw(GameWindow gw) {
        this.gw = gw;
    }

    public boolean isGameOver() {
        //21 or bust
        if (player.getScore() > 21) {
            player.bust = true;
            return true;
        }
        else if(dealer.getScore() > 21)
        {
            dealer.bust = true;
            return true;
        }
        else if(player.getScore() == 21 && dealer.getScore() == 21)
        {
            return true;
        }
        //Both players stand
        else if (player.isStand() && dealer.isStand()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void handleGameOver()
    {
        String winner = "";
        
        //Stop timer
        timer.stop();
        
        //Handle buttons
        gw.getBtnHit().setEnabled(false);
        gw.getBtnStand().setEnabled(false);
        gw.getBtnNewGame().setEnabled(true);
        
        //Determine winner
        if(player.bust)
        {
            System.out.println("Dealer wins via bust!\n");
        }
        else if(dealer.bust)
        {
            System.out.println("Player wins via bust!\n");
        }
        else{
            if(dealer.getScore() > player.getScore())
            {
                System.out.println("Dealer wins with higher score!\n");
            }
            else if(player.getScore() > dealer.getScore())
            {
                System.out.println("Player wins with higher score!\n");
            }
            
            else if((player.getScore() == dealer.getScore()) && (player.isStand() && dealer.isStand()))
            {
                System.out.println("Push!\n");
            }
            
        }
        
        //Clear players' hands
        player.getHand().clear();
        dealer.getHand().clear();
        
        gw.revalidate();
        gw.repaint();
    }
    
    public void drawScores()
    {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        gw.getLblPlayerScore().setText(String.valueOf(playerScore));
        gw.getLblDealerScore().setText(String.valueOf(dealerScore));
    }
    
    public void dealerPlay()
    {
        int action = dealer.action();
        
        //Base case
        if(dealer.isStand())
        {
            return;
        }
        
        //Stand
        if(action == 0)
        {
            dealer.setStand(true);
            return;
        }
        //Hit
        else if(action == 1)
        {
            Card c = deck.pull();
            dealer.hit(c);
            drawCard(c);
        }
        //Run until dealer is at 16 or more
        while(dealer.getScore() < 16)
        {
            dealerPlay();
        }
    }

    public ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            drawScores();
            GamePanel.revalidate();
            GamePanel.repaint();
            
            //TODO: Check and handle game over
            if(isGameOver())
            {
                System.out.println("\nGame Over!");
                handleGameOver();
            }
            
        }
    };

    Timer timer = new Timer(300, listener);

}
