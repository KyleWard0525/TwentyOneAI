/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import twentyoneai.game.Card;
import twentyoneai.game.GameEngine;

/**
 *
 * @author user
 */
public class GameWindow extends javax.swing.JFrame {
    
    private GameEngine engine;
    public boolean AIplayer;
    
    /**
     * Creates new form GameWindow
     */
    public GameWindow() {
        initComponents();
        this.setBackground(new Color(0, 102, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHit = new javax.swing.JButton();
        btnStand = new javax.swing.JButton();
        btnSplit = new javax.swing.JButton();
        GamePanel = new javax.swing.JPanel();
        btnNewGame = new javax.swing.JButton();
        lblPlayerScore = new javax.swing.JLabel();
        lblDealerScore = new javax.swing.JLabel();
        lblRCountLabel = new javax.swing.JLabel();
        lblRunningCount = new javax.swing.JLabel();
        lblTCountLabel = new javax.swing.JLabel();
        lblTrueCount = new javax.swing.JLabel();
        lblBalanceLabel = new javax.swing.JLabel();
        lblBalance = new javax.swing.JLabel();
        txtBetAmount = new javax.swing.JTextField();
        btnBet = new javax.swing.JButton();
        lblWinner = new javax.swing.JLabel();
        btnToggleAI = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("21");
        setBackground(new java.awt.Color(0, 102, 0));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        btnHit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnHit.setText("Hit");
        btnHit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitActionPerformed(evt);
            }
        });

        btnStand.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnStand.setText("Stand");
        btnStand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStandActionPerformed(evt);
            }
        });

        btnSplit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSplit.setText("Split");
        btnSplit.setEnabled(false);
        btnSplit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSplitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GamePanelLayout = new javax.swing.GroupLayout(GamePanel);
        GamePanel.setLayout(GamePanelLayout);
        GamePanelLayout.setHorizontalGroup(
            GamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        GamePanelLayout.setVerticalGroup(
            GamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        btnNewGame.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnNewGame.setText("New Game");
        btnNewGame.setEnabled(false);
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        lblPlayerScore.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPlayerScore.setForeground(new java.awt.Color(0, 0, 0));
        lblPlayerScore.setText("player_score");

        lblDealerScore.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDealerScore.setText("dealer_score");

        lblRCountLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRCountLabel.setForeground(new java.awt.Color(0, 0, 0));
        lblRCountLabel.setText("R-Count: ");

        lblRunningCount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRunningCount.setText("0");

        lblTCountLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTCountLabel.setForeground(new java.awt.Color(0, 0, 0));
        lblTCountLabel.setText("T-Count: ");

        lblTrueCount.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTrueCount.setText("0");

        lblBalanceLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblBalanceLabel.setForeground(new java.awt.Color(0, 0, 0));
        lblBalanceLabel.setText("Balance: ");

        lblBalance.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblBalance.setText("0");

        txtBetAmount.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtBetAmount.setToolTipText("Enter  bet");

        btnBet.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBet.setText("Bet");
        btnBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBetActionPerformed(evt);
            }
        });

        lblWinner.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        btnToggleAI.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnToggleAI.setText("Toggle AI Player");
        btnToggleAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleAIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnHit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblBalanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBalance)))
                .addGap(151, 151, 151)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlayerScore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(txtBetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBet, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSplit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRunningCount)))
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTrueCount))
                    .addComponent(btnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addComponent(lblDealerScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnToggleAI)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addComponent(GamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDealerScore)
                        .addComponent(btnToggleAI))
                    .addComponent(lblWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayerScore)
                    .addComponent(lblRunningCount)
                    .addComponent(lblRCountLabel)
                    .addComponent(lblTCountLabel)
                    .addComponent(lblTrueCount)
                    .addComponent(lblBalanceLabel)
                    .addComponent(lblBalance)
                    .addComponent(txtBetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHit)
                    .addComponent(btnSplit)
                    .addComponent(btnNewGame)
                    .addComponent(btnStand)
                    .addComponent(btnBet))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitActionPerformed
        Card c = engine.deck.pull();
        engine.player.hit(c);
        engine.drawCard(c);
    }//GEN-LAST:event_btnHitActionPerformed

    private void btnStandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStandActionPerformed
        engine.player.setStand(true);
        btnHit.setEnabled(false);
        btnStand.setEnabled(false);
        btnSplit.setEnabled(false);
        engine.dealerPlay();
        engine.drawScores();
        engine.handleGameOver();
    }//GEN-LAST:event_btnStandActionPerformed

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        //Reset game
        engine.totalGames += 1;
        GamePanel.removeAll();
        engine.player.getHand().clear();
        engine.dealer.getHand().clear();
        engine.player.setStand(false);
        engine.dealer.setStand(false);
        engine.player.bust = false;
        engine.dealer.bust = false;
        lblWinner.setText("");
        btnHit.setEnabled(true);
        btnStand.setEnabled(true);
        btnNewGame.setEnabled(false);
        clearConsole();
        
        //Check player balance
        if(engine.player.getBalance() <= 0)
        {
            JOptionPane.showMessageDialog(this, "You are out of money!", "Game Over", JOptionPane.WARNING_MESSAGE);
            this.dispose();
            System.exit(0);
        }
        
        engine.deal();
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void btnSplitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSplitActionPerformed
        // TODO handle split function and draw split hands
        engine.player.split();
    }//GEN-LAST:event_btnSplitActionPerformed

    private void btnBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBetActionPerformed
        double bet = Double.valueOf(txtBetAmount.getText());
        double playerBalance = engine.player.getBalance();
        
        //Make sure player has enough balance
        if(bet > playerBalance)
        {
            JOptionPane.showMessageDialog(this, "Error: Bet amount is greater than current balance", "Insufficient funds", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            engine.setPlayerBet(bet);
            engine.player.setBalance(playerBalance - bet);
            lblBalance.setText(String.valueOf(engine.player.getBalance()));
            engine.player.hasBet = true;
            
            //Enable buttons
            btnBet.setEnabled(false);
            btnHit.setEnabled(true);
            btnNewGame.setEnabled(true);
            btnStand.setEnabled(true);
            
            txtBetAmount.setEnabled(false);
        }
        
    }//GEN-LAST:event_btnBetActionPerformed

    private void btnToggleAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleAIActionPerformed
        if(btnToggleAI.isSelected())
        {
            AIplayer = true;
        }
        else{
            AIplayer = false;
        }
    }//GEN-LAST:event_btnToggleAIActionPerformed

    public void clearConsole()
    {
        for(int i = 0; i < 30; i++)
        {
            System.out.println("\n");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }

    public JPanel getGamePanel() {
        return GamePanel;
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public JLabel getLblPlayerScore() {
        return lblPlayerScore;
    }

    public JButton getBtnHit() {
        return btnHit;
    }

    public JButton getBtnStand() {
        return btnStand;
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }

    public JLabel getLblWinner() {
        return lblWinner;
    }

    public JLabel getLblDealerScore() {
        return lblDealerScore;
    }

    public JLabel getLblRunningCount() {
        return lblRunningCount;
    }

    public JLabel getLblTrueCount() {
        return lblTrueCount;
    }

    public JButton getBtnSplit() {
        return btnSplit;
    }

    public JLabel getLblBalance() {
        return lblBalance;
    }

    public JButton getBtnBet() {
        return btnBet;
    }

    public JTextField getTxtBetAmount() {
        return txtBetAmount;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GamePanel;
    private javax.swing.JButton btnBet;
    private javax.swing.JButton btnHit;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnSplit;
    private javax.swing.JButton btnStand;
    private javax.swing.JToggleButton btnToggleAI;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblBalanceLabel;
    private javax.swing.JLabel lblDealerScore;
    private javax.swing.JLabel lblPlayerScore;
    private javax.swing.JLabel lblRCountLabel;
    private javax.swing.JLabel lblRunningCount;
    private javax.swing.JLabel lblTCountLabel;
    private javax.swing.JLabel lblTrueCount;
    private javax.swing.JLabel lblWinner;
    private javax.swing.JTextField txtBetAmount;
    // End of variables declaration//GEN-END:variables
}
