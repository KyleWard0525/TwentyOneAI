/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.ai;

import org.encog.ml.CalculateScore;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.ea.train.EvolutionaryAlgorithm;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;
import org.encog.neural.neat.NEATUtil;
import org.encog.neural.networks.training.TrainingSetScore;
import twentyoneai.game.Dealer;
import twentyoneai.game.GameEngine;
import twentyoneai.game.Player;

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
    private Player player;
    private Dealer dealer;
    private int rCount;
    
    public Agent(GameEngine ge)
    {
        this.engine = engine;
        init();
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
    
    public double[] getPlay()
    {
        MLDataSet trainSet = getTrainSet();
        
        CalculateScore score = new TrainingSetScore(trainSet);

        this.trainer = NEATUtil.constructNEATTrainer(pop, score);

        do {
            trainer.iteration();
        } while (trainer.getError() > 0.01);
        
        //Get best network
        network = (NEATNetwork) trainer.getCODEC().decode(trainer.getBestGenome());
        
        return network.compute(getInputData()).getData();
    }
    
    
    public MLDataSet getTrainSet()
    {
        double[] inputs = new double[numInputs];
        double[] ideal = new double[numOutputs];
        
        inputs[0] = player.getScore();
        inputs[1] = dealer.getScore() - dealer.getHand().get(0).getValue();
        inputs[2] = rCount;
        inputs[3] = player.getHand().size();
        inputs[4] = player.getWins();
        inputs[5] = player.getBalance();
        
        //Don't hit
        //Low running count and high score
        if(inputs[0] >= 16)
        {
            ideal = new double[]{0,1};
        }
        //Hit. High running count and low score
        else if(inputs[0] < 16)
        {
            ideal = new double[]{1,0};
        }
        
        MLData inputData = new BasicMLData(inputs);
        MLData outputData = new BasicMLData(ideal);
        
        MLDataSet trainSet = new BasicMLDataSet();
        trainSet.add(inputData, outputData);
        
        return trainSet;
    }
    
    public MLData getInputData()
    {
        double[] inputs = new double[numInputs];
        
        inputs[0] = player.getScore();
        inputs[1] = dealer.getScore() - dealer.getHand().get(0).getValue();
        inputs[2] = rCount;
        inputs[3] = player.getHand().size();
        inputs[4] = player.getWins();
        inputs[5] = player.getBalance();
        
        MLData inputData = new BasicMLData(inputs);
        
        return inputData;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public int getrCount() {
        return rCount;
    }

    public void setrCount(int rCount) {
        this.rCount = rCount;
    }
    
    

}
