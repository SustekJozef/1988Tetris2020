/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes.CPU;

import PlayBoardAndShapes.PlayBoard;
import PlayBoardAndShapes.Shape;
import org.apache.commons.lang.SerializationUtils;

/**
 *Class represents CPU player.Contains methods which tryies all possibilities for putting current shape in
 * to the playboard, which mains to decide which possibillity is the same. Class is using current playboard state
 * to count different possibillities of putting shapes. It determines which possibillity will be chosen.
 * @author Jozef
 */
public class ComputerPlayer{
    /**
     *Array to save copy of original playboard of game.
     */
    int[][] originPlayboardForCPU;
     /**
     *Array to save  playboard of game for using for counting of possibillities of game, when I put current
     * shape to different positions.
     */
    int[][] currentPlayboardForCPU;
    
    /**;
     *Instance of Playboard.
     */
    PlayBoard playBoardForCPU;
    
    /**
     *Instance of Shape.
     */
    Shape currentShapeCPU;
    /**
     *Only for testing purposses.
     */
    boolean removeShape=true;

    /**
     *Inicializes new instance of class
     * @param playBoardArray Array for trying all possibiligies for playboard. 
     */
    public ComputerPlayer(PlayBoard playBoardForCPU) throws CloneNotSupportedException {
        /*this.playBoardForCPU=new PlayBoard();
        this.playBoardForCPU.setPlayBoardArray(playBoardArray.clone());
        //this.originPlayboardForCPU=playBoardArray.clone();
        this.currentPlayboardForCPU=this.playBoardForCPU.getPlayBoardArray().clone();
        
        this.currentShapeCPU=(Shape) currentShape.clone();
        */
        // Serializes Student object to a byte[] array
        byte[] bytes = SerializationUtils.serialize(this.playBoardForCPU);
        // Deserializes Student object from an array of bytes
        PlayBoard clone = (PlayBoard) SerializationUtils.deserialize(bytes);
        
        
        
    }
 
    public void testAllPositionsOfCurrentShapeInCurrentState(){
        
        moveShapeToStartingPositionLeft();
        
        /*setDownShape();
        checkAndSaveScoreOfCurrentPositionBySettingShapeDown();
        
        renewPlayboard();
        changeStateOfRotatingOfShape();*/
    }
    
    
    public void moveShapeToStartingPositionLeft(){
        //simulating of moving shape throught the playboard.     
        
        
        if (removeShape) {
            playBoardForCPU.removeShapeFromPlayBoardXYSystem(currentShapeCPU, currentPlayboardForCPU);
            if (playBoardForCPU.checkIfShapeCanGoLeft(currentShapeCPU, currentPlayboardForCPU)) {
                playBoardForCPU.MoveLeft(currentShapeCPU);
                playBoardForCPU.writeShapeToPlayBoardXYSystem(currentShapeCPU, currentPlayboardForCPU);
    }
            else {
                removeShape=false;
                playBoardForCPU.writeShapeToPlayBoardXYSystem(currentShapeCPU, currentPlayboardForCPU);
            }
        }
    for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print(currentPlayboardForCPU[i][j]);
                    }
                    System.out.println("");
                }
    }


    
    
    
    
    
}
