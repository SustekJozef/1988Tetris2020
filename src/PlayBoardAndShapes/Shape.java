/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes;

import java.io.Serializable;

/**
 *
 * @author Jozef
 */
public abstract class Shape implements Serializable{

    /**
     * Array in which is saved position of all of blocks of current shape. 
     */
protected int[][] shapeInitializationArray;
//shape´s starting position na x axis of array

    /**
     *
     */
final protected int BASEX=3; //pozrieť
//shape´s starting position na x axis of array

    /**
     *
     */
final protected int BASEY=6;

 /**
 *Starting position of shape´s rotation - each shape has it´s own positions.
 */
final protected int startingNumberOfRotationPositionOfShape=0;
/**
 *Max number of position of shape´s rotation- each shape has it´s own positions. Must be 1 point less than real number of positions.
 */
protected int maxNumberOfRotationPositionOfShape;

    /**
     *Current number represents position of rotation. 
     * Basic position is 0
     */
    protected int currentNumberOfRotationPositionOfShape;

    /**
     *Specific color which differs shape to shape. 
     * It is represented by int numbers of rgb.
     * So it is needed to be array of 3 numbers.
     */
    protected static int[] COLOR_OF_SHAPE;
    
     /**
     *
     */
    protected int numberOfShape;
    
    /**
     *Represents any shape in a game.
     */
    public Shape() {
        this.currentNumberOfRotationPositionOfShape=startingNumberOfRotationPositionOfShape;
    }

    /**
     *
     * @param playBoard
     */
    abstract public void rotateShape(int [][] playBoard);
      
    /**
     *
     */ 
    public void chceckAndDecideRotationState(){ 
        if (currentNumberOfRotationPositionOfShape<maxNumberOfRotationPositionOfShape){
            currentNumberOfRotationPositionOfShape++;
        }
        else {
            currentNumberOfRotationPositionOfShape=startingNumberOfRotationPositionOfShape;
        }
  }






}
      






