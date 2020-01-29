/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.ai;

import java.util.ArrayList;
import java.util.Arrays;
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
import twentyoneai.game.Card;
import twentyoneai.game.Card.Rank;
import twentyoneai.game.Dealer;
import twentyoneai.game.GameEngine;
import twentyoneai.game.Player;

/**
 * This is the AI agent that will learn how to play the game.
 *
 * Network IO:
 *
 * Inputs: 1) Player score 2) Dealer score minus first card (hidden card) 3)
 * Running count 4) Number of cards in hand 5) Player wins 6) Balance 7) True
 * count
 *
 * Outputs: 1) Hit 2) Stand
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
    private int trueCount;
    private BasicStrategy basic;

    public Agent(GameEngine ge) {
        this.engine = engine;
        init();
    }

    public void init() {
        this.numInputs = 6;
        this.numOutputs = 2;
        this.popSize = 1000;
        this.pop = new NEATPopulation(numInputs, numOutputs, popSize);
        this.gen = 0;
        this.basic = new BasicStrategy();

        //Create random population
        pop.reset();
    }

    public double[] getPlay() {
        MLDataSet trainSet = getTrainSet();

        CalculateScore score = new TrainingSetScore(trainSet);

        this.trainer = NEATUtil.constructNEATTrainer(pop, score);

        do {
            trainer.iteration();
        } while (trainer.getError() > 0.01);

        //Get best network
        network = (NEATNetwork) trainer.getCODEC().decode(trainer.getBestGenome());

        double[] play = network.compute(getInputData()).getData();
        
        for(int i = 0; i < play.length; i++)
        {
            play[i] = Math.round(play[i]);
        }
        
        return play;
    }

    public MLDataSet getTrainSet() {
        double[] inputs = new double[numInputs];
        double[] ideal = new double[numOutputs];

        inputs[0] = player.getScore();
        inputs[1] = dealer.getHand().get(1).getValue();
        inputs[2] = rCount;
        inputs[3] = trueCount;
        inputs[4] = ((double) player.getWins() / (double)GameEngine.totalGames) * 100.00;
        inputs[5] = player.getWins();
        

        ideal = getTarget();

        MLData inputData = new BasicMLData(inputs);
        MLData outputData = new BasicMLData(ideal);

        MLDataSet trainSet = new BasicMLDataSet();
        trainSet.add(inputData, outputData);

        return trainSet;
    }

    public MLData getInputData() {
        double[] inputs = new double[numInputs];

        inputs[0] = player.getScore();
        inputs[1] = dealer.getHand().get(1).getValue();
        inputs[2] = rCount;
        inputs[3] = trueCount;
        inputs[4] = ((double) player.getWins() / (double)GameEngine.totalGames) * 100.00;
        inputs[5] = player.getWins();

        MLData inputData = new BasicMLData(inputs);

        return inputData;
    }

    public double[] getTarget() {
        double[] ideal = new double[2];
        ArrayList<int[]> strategyMatrix = new ArrayList<>();
        boolean hard = false;
        
        int dealerCard = dealer.getHand().get(1).getValue();
        int change = 2;
        
        if(dealerCard < 2) {
            change = 1;
        }

        //Check if soft or hard hand
        for (Card c : player.getHand()) {
            //Player has Ace, soft hand.
            if (c.getRank() == Rank.ACE) {
                for (int[] row : basic.getSoftMatrix()) {
                    strategyMatrix.add(row);
                }
            } //Player doesn't have Ace, hard hand.
            else {
                for (int[] row : basic.getHardMatrix()) {
                    strategyMatrix.add(row);
                    hard = true;
                }
            }
        }

        //Get ideal for hard matrix
        if (hard) {
            switch (player.getScore()) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    ideal = new double[]{strategyMatrix.get(0)[dealerCard - change], 0};
                    break;
                case 9:
                    ideal = new double[]{strategyMatrix.get(1)[dealerCard - change], 0};
                    break;
                case 10:
                    ideal = new double[]{strategyMatrix.get(2)[dealerCard - change], 0};
                    break;
                case 11:
                    ideal = new double[]{strategyMatrix.get(3)[dealerCard - change], 0};
                    break;
                case 12:
                    ideal = new double[]{strategyMatrix.get(4)[dealerCard - change], 0};
                    break;
                case 13:
                    ideal = new double[]{strategyMatrix.get(5)[dealerCard - change], 0};
                    break;
                case 14:
                    ideal = new double[]{strategyMatrix.get(6)[dealerCard - change], 0};
                    break;
                case 15:
                    ideal = new double[]{strategyMatrix.get(7)[dealerCard - change], 0};
                    break;
                case 16:
                    ideal = new double[]{strategyMatrix.get(8)[dealerCard - change], 0};
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    ideal = new double[]{strategyMatrix.get(9)[dealerCard - change], 0};
                    break;
            }
        }
        //Get ideal for soft matrix
        else{
            switch (player.getScore()) {
                case 13:
                    ideal = new double[]{strategyMatrix.get(0)[dealerCard - 2], 0};
                    break;
                case 14:
                    ideal = new double[]{strategyMatrix.get(1)[dealerCard - 2], 0};
                    break;
                case 15:
                    ideal = new double[]{strategyMatrix.get(2)[dealerCard - 2], 0};
                    break;
                case 16:
                    ideal = new double[]{strategyMatrix.get(3)[dealerCard - 2], 0};
                    break;
                case 17:
                    ideal = new double[]{strategyMatrix.get(4)[dealerCard - 2], 0};
                    break;
                case 18:
                    ideal = new double[]{strategyMatrix.get(5)[dealerCard - 2], 0};
                    break;
                case 19:
                case 20:
                case 21:
                    ideal = new double[]{strategyMatrix.get(6)[dealerCard - 2], 0};
                    break;
            }
        }
        return ideal;
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

    public int getTrueCount() {
        return trueCount;
    }

    public void setTrueCount(int trueCount) {
        this.trueCount = trueCount;
    }

}
