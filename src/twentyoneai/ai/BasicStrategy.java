/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twentyoneai.ai;

/**
 * This class is used as target
 * data for the AI
 * 
 * @author kward60
 */
public class BasicStrategy {
    
    private int[][] softMatrix;
    private int[][] hardMatrix;
    
    public BasicStrategy()
    {
        this.softMatrix = new int[7][10];
        this.hardMatrix = new int[10][10];
        init();
    }
    
    private void init()
    {
        softMatrix = new int[][]{{1,1,1,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1,1},
        {0,0,0,0,0,0,0,1,1,1}, {0,0,0,0,0,0,0,0,0,0}};
        
        hardMatrix = new int[][]{{1,1,1,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1}, {1,1,1,1,1,1,1,1,1,1}, {1,1,0,0,0,1,1,1,1,1},
            {0,0,0,0,0,1,1,1,1,1}, {0,0,0,0,0,1,1,1,1,1}, {0,0,0,0,0,1,1,1,1,1}, 
            {0,0,0,0,0,1,1,1,1,1}, {0,0,0,0,0,0,0,0,0,0}};
    }

    public int[][] getSoftMatrix() {
        return softMatrix;
    }


    public int[][] getHardMatrix() {
        return hardMatrix;
    }

    
    
    
    }
