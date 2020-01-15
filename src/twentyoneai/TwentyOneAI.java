/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai;

import twentyoneai.game.GameEngine;

/**
 *
 * @author user
 */
public class TwentyOneAI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        GameEngine ge = new GameEngine(gw);
        gw.setEngine(ge);
        ge.deal();
        gw.show();
    }
    
}
