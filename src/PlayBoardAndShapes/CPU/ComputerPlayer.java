/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes.CPU;

import PlayBoardAndShapes.PlayBoard;
import PlayBoardAndShapes.Shape;
import PlayBoardAndShapes.TetrisSwingPackage1.PlayBoardJFrame;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang.SerializationUtils;

/**
 *Class represents CPU player.Contains methods which tryies all possibilities for putting current shape in
 * to the playboard, which mains to decide which possibillity is the same. Class is using current playboard state
 * to count different possibillities of putting shapes. It determines which possibillity will be chosen.
 * @author Jozef
 */
public class ComputerPlayer implements Serializable{
    /**;
     *Instance of Playboard.
     */
    PlayBoard originalPlayBoardForCPU;
    
    /**
     *Instance of PlayBoard for testing purposes.
     */
    private PlayBoard currentTestingPlayBoardForCPU;
    /**
     *Only for testing purposses.
     */
    boolean removeShape=true;
    /**
     *Saves actual score to count the difference between actual and new score in 
     * testing change of score after sitting testing shape.
     */
    private int testingScore;
    
    /**
     *Checks if there is any testing position right from current position.
     */
    private boolean canMoveRight=true;
    
    /**
     *Checks if there is any testing position left from current position.
     */
    private boolean canMoveLeft=true;
    
    /**
     *Checks what is starting position testing shape in y-axis.
     * This is starting positon on the left.
     */
    private int leftMovementCounter;
    
    
    /**
     *Checks which is current position of testing shape in y-axis.
     * This is distance from starting positon on the left.
     */
    private int rightMovementCounter;
    
    /**
     *Counts current rotation state. Max rotation state determine max number
     * of rotation of shape stored in belonging to each shape specific variable.
     */
    private int counterOfRotation;
    
    /**;
     *ArrayList which saves all data about earned score,next informations about current move and
     * information of rotation state.
     */
    ArrayList<int[]> scoreMoveRotationArrayList;
    
    
    
    
    
    
    
    
    
    /**
     *Inicializes new instance of class
     * @param playBoardArray Array for trying all possibiligies for playboard. 
     */
    public ComputerPlayer(PlayBoard playBoardForCPU) throws CloneNotSupportedException {
        this.originalPlayBoardForCPU=playBoardForCPU;
        renewOfTestingPlayBoardByOriginalOne();
        this.testingScore=0;
        this.rightMovementCounter=0;//first move right
        this.leftMovementCounter=0;//first move left
        this.counterOfRotation=0;
        this.scoreMoveRotationArrayList= new ArrayList<>();
    }
 
    public void testAllPositionsOfCurrentShapeInCurrentState(){
        while(counterOfRotation<=currentTestingPlayBoardForCPU.getCurrentShape().getMaxNumberOfRotationPositionOfShape()){
            boolean whileContinue=true;    

            //1st score for current state of rotation without any moving in playboardArray
            moveShapeToPositionSitDown();
            countAndSaveScoreAndMoveInformationsAndRotationOfCurrentTryInArray(0,0);      
            renewOfTestingPlayBoardByOriginalOne();                            
            //move to the starting position

            
            //moves steb by step right and tests what score it earns when it put a shape
            while (whileContinue){    
                for (int i = 0; i <= (rightMovementCounter); i++) {
                    if (currentTestingPlayBoardForCPU.isCanGoRight()){
                    moveShapeOnePositionRight(); //moves shape one more position after old 
                                                //one which was testet before
                    }
                }
                rightMovementCounter++;
                
                moveShapeToPositionSitDown();
                countAndSaveScoreAndMoveInformationsAndRotationOfCurrentTryInArray(rightMovementCounter,0);      
                
                if (!currentTestingPlayBoardForCPU.isCanGoRight()){
                    whileContinue=false;
                }
                renewOfTestingPlayBoardByOriginalOne();
            }
            canMoveRight=!canMoveRight;//restoring variable /// DELETE ALLL VARIABLE
            rightMovementCounter=0;
            whileContinue=true;
            
            
             //moves steb by step left and tests what score it earns when it put a shape
            while (whileContinue){    
                for (int i = 0; i <= leftMovementCounter; i++) {
                    moveShapeOnePositionLeft(); //moves shape one more position after old 
                                                //one which was testet before
                }
                leftMovementCounter++;
                         
                moveShapeToPositionSitDown();
                countAndSaveScoreAndMoveInformationsAndRotationOfCurrentTryInArray(0,leftMovementCounter);  
                
                if (!currentTestingPlayBoardForCPU.isCanGoLeft()){
                    whileContinue=false;
                }
                renewOfTestingPlayBoardByOriginalOne();
            }
            canMoveLeft=!canMoveLeft;//restoring variable
            leftMovementCounter=0;
            
            
            //change rotation into next one state
            currentTestingPlayBoardForCPU.rotateAnyShape(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray());
            counterOfRotation++; //this ensure that all rotation states will be tested
        }
    }
    ////// PROBLEM WITH ROTATION --- neotáča sa, lebo sa opäť vymaže ked sa aj otočí, treba dalšiu kopiu vytvoriť - a nanovo celé porzeiť
    
    /**
     *Moves shape fully left to find shape´s starting position(shape in current rotating state).
     * Method counts how much positions had to be managed to get that state, so this can be used to 
     * decrease these positons to another border on the right.
     */
    public void moveShapeToStartingPositionLeft(){
        
        do
        {
            currentTestingPlayBoardForCPU.inputShapeToPlayboard(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray(), "left");
            leftMovementCounter++;
        }
        while (currentTestingPlayBoardForCPU.isCanGoLeft());
}
        

    /**
     * Method stores all informations which are usable for reproducing needed movement of CPU player.
     * It saves earned score,move specification for replication the move and rotation state of shape.
     * @param rightMovementCounter Save position for movement to the right
     * @param rightMovementCounter Save position for movement to the left
     */
    public void countAndSaveScoreAndMoveInformationsAndRotationOfCurrentTryInArray(int rightMovementCounter,int leftMovementCounter){
        //saves 3 attributes - earned score (is old score and new score after putting shape, and information about rotation state of shape
        int[] scoreAndMoveFromStartingPositionAndRotation={(currentTestingPlayBoardForCPU.getScore()-testingScore),rightMovementCounter,leftMovementCounter,counterOfRotation};
        //stored all informations which are usable for reproducing needed movement of CPU player
        scoreMoveRotationArrayList.add(scoreAndMoveFromStartingPositionAndRotation);
             
        testingScore=0;//default state of variable
}
    /**
     *Moves shape(shape in current rotating state) down to fully sit.
     * Method automatically moves shape fully down. After it sits there can be counted if this shape
     * moved down gain score and how how score it gets. It also save old state of "score" to find out
     * how was the score increased after this sit down of shape.
     */
    public void moveShapeToPositionSitDown(){
        testingScore=currentTestingPlayBoardForCPU.getScore();//gets score before putting down shape. Used next for comparision.
        do
        {
           currentTestingPlayBoardForCPU.inputShapeToPlayboard(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray(), "down");
        }
        while (currentTestingPlayBoardForCPU.isCanGoDown());//if isCanGoDown is false that means that shape sits fully down
}
        

     /**
     *Renewing of playBoardObject for another testing.
     */
    private void renewOfTestingPlayBoardByOriginalOne(){
        // Serializes Student object to a byte[] array
        byte[] bytes = SerializationUtils.serialize(this.originalPlayBoardForCPU);
        // Deserializes Student object from an array of bytes
        this.currentTestingPlayBoardForCPU = (PlayBoard) SerializationUtils.deserialize(bytes);    
    }

    
    /**
     *Moves shape on step to right(shape in current rotating state).
     * Method moves shape one position right for testing, what score it gets from that position
     * by putting it down.
     */
    public void moveShapeOnePositionRight(){
        if ((currentTestingPlayBoardForCPU.isCanGoRight())){
           currentTestingPlayBoardForCPU.inputShapeToPlayboard(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray(), "right");
           
        }
        else {
            canMoveRight = false;
        }
}
    
        /**
     *Moves shape on step to left(shape in current rotating state).
     * Method moves shape one position left for testing, what score it gets from that position
     * by putting it down.
     */
    public void moveShapeOnePositionLeft(){
        if ((currentTestingPlayBoardForCPU.isCanGoLeft())){
           currentTestingPlayBoardForCPU.inputShapeToPlayboard(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray(), "left");
  
        }
        else {
            canMoveLeft = false;
        }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @return the currentTestingPlayBoardForCPU
     */
    public PlayBoard getCurrentTestingPlayBoardForCPU() {
        return currentTestingPlayBoardForCPU;
    }
    
    
    
}
