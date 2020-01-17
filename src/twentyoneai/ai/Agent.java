/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.ai;

import org.encog.ml.ea.train.EvolutionaryAlgorithm;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import twentyoneai.game.GameEngine;

/**
 * This is the AI agent that will learn how to play the
 * game.
 * 
 * Network IO:
 * 
 * Inputs:
 * 1) Player score
 * 2) Dealer score minus first card (hidden card)
 * 3) Running count
 * 4) Number of cards in hand
 * 5) Player wins
 * 6) Balance
 * 
 * Outputs:
 * 1) Hit
 * 2) Stand
 * 
 * //TODO add true count to inputs, add split to outputs
 * 
 * @author kward60
 */
public class Agent {
    
    private GameEngine engine;
    private NEATPopulation pop;
    private EvolutionaryAlgorithm trainer;
    private NEATNetwork network;
    private int gen;
    private int numInputs;
    private int numOutputs;
    private int popSize;
    
    public Agent(GameEngine ge)
    {
        this.engine = engine;
    }
    
    public void init()
    {
        this.numInputs = 6;
        this.numOutputs = 2;
        this.popSize = 500;
        this.pop = new NEATPopulation(numInputs, numOutputs, popSize);
        this.gen = 0;
        
        //Create random population
        pop.reset();
    }
    
    /*
    public double[] getInputs()
    {
        double[] inputs = new double[numInputs];
        
        inputs[0] = engine.player.getScore();
        inputs[1] = engine.dealer.getScore() - engine.dealer.getHand().get(0).getValue();
        inputs[2] = engine.getRunningCount();
        inputs[3] = engine.player.getHand().size();
        inputs[4] = engine.player.getWins();
        inputs[5] = engine.player.getBalance();
    }
*/
}
