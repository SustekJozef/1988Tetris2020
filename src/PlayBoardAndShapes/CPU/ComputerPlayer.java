/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes.CPU;

import PlayBoardAndShapes.PlayBoard;
import PlayBoardAndShapes.Shape;

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
    
    /**
     *Instance of Playboard.
     */
    PlayBoard playBoard;
    
    /**
     *Instance of Shape.
     */
    Shape currentShape;

    /**
     *Inicializes new instance of class
     * @param playBoardArray Array for trying all possibiligies for playboard. 
     */
    public ComputerPlayer(int[][] playBoardArray,Shape currentShape) {
        this.originPlayboardForCPU=playBoardArray.clone();
        this.currentPlayboardForCPU=this.originPlayboardForCPU.clone();
        
        this.currentShape=currentShape;
    }
 
    public void testAllPositionsOfCurrentShapeInCurrentState(){
        /* moveShapeToStartingPosition();
        setDownShape();
        checkAndSaveScoreOfCurrentPositionBySettingShapeDown();
        
        renewPlayboard();
        changeStateOfRotatingOfShape();*/
    }
    
    
    public void moveToStartingPosition(){
        playBoard.MoveLeft(currentShape);
    }
    


    
    
    
    
    
}
