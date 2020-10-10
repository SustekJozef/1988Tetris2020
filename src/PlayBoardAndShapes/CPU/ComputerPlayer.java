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
import org.apache.commons.lang.SerializationUtils;

/**
 *Class represents CPU player.Contains methods which tryies all possibilities for putting current shape in
 * to the playboard, which mains to decide which possibillity is the same. Class is using current playboard state
 * to count different possibillities of putting shapes. It determines which possibillity will be chosen.
 * @author Jozef
 */
public class ComputerPlayer implements Serializable{
    /**
     *Array to save copy of original playboard of game.
     */
    //int[][] originPlayboardForCPU;
     /**
     *Array to save  playboard of game for using for counting of possibillities of game, when I put current
     * shape to different positions.
     */
    //int[][] currentPlayboardForCPU;
    
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
     *Inicializes new instance of class
     * @param playBoardArray Array for trying all possibiligies for playboard. 
     */
    public ComputerPlayer(PlayBoard playBoardForCPU) throws CloneNotSupportedException {
        this.originalPlayBoardForCPU=playBoardForCPU;

        renewOfTestingPlayBoardByOriginalOne();
        //getCurrentTestingPlayBoardForCPU().removeShapeFromPlayBoardXYSystem(getCurrentTestingPlayBoardForCPU().getCurrentShape(), getCurrentTestingPlayBoardForCPU().getPlayBoardArray());
        //getCurrentTestingPlayBoardForCPU().setPushNewShape(false);
        
        
        /*this.originalPlayBoardForCPU=new PlayBoard();
        this.originalPlayBoardForCPU.setPlayBoardArray(playBoardArray.clone());
        //this.originPlayboardForCPU=playBoardArray.clone();
        this.currentPlayboardForCPU=this.originalPlayBoardForCPU.getPlayBoardArray().clone();
        
        this.currentShapeCPU=(Shape) currentShape.clone();
        */
        /* // Serializes Student object to a byte[] array
        byte[] bytes = SerializationUtils.serialize(this.originalPlayBoardForCPU);
        // Deserializes Student object from an array of bytes
        PlayBoard clone = (PlayBoard) SerializationUtils.deserialize(bytes);
        */
        
        ;
    }
 
    public void testAllPositionsOfCurrentShapeInCurrentState() throws InterruptedException{
        
        moveShapeToStartingPositionLeft();
        
        /*setDownShape();
        checkAndSaveScoreOfCurrentPositionBySettingShapeDown();
        
        renewPlayboard();
        changeStateOfRotatingOfShape();*/
    }
    
    
    public void moveShapeToStartingPositionLeft() throws InterruptedException{
       do
        {
            //currentTestingPlayBoardForCPU.removeShapeFromPlayBoardXYSystem(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray());
            currentTestingPlayBoardForCPU.inputShapeToPlayboard(currentTestingPlayBoardForCPU.getCurrentShape(), currentTestingPlayBoardForCPU.getPlayBoardArray(), "left");
        }
               while (currentTestingPlayBoardForCPU.isCanGoLeft());
 
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
     * @return the currentTestingPlayBoardForCPU
     */
    public PlayBoard getCurrentTestingPlayBoardForCPU() {
        return currentTestingPlayBoardForCPU;
    }
    
    
    
}
