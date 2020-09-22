/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes;

/**
 *
 * @author Jozef
 */
public abstract class Shape{

    /**
     * Array in which is saved position of all of blocks of current shape. 
     */
protected int[][] shapeInitializationArray;
//shape´s starting position na x axis of array

    /**
     *
     */
final protected int baseX=3; //pozrieť
//shape´s starting position na x axis of array

    /**
     *
     */
protected int baseY=8;

 /**
 *Starting position of shape´s rotation - each shape has it´s own positions.
 */
final protected int startingNumberOfRotationPositionOfShape=0;
/**
 *Max number of position of shape´s rotation- each shape has it´s own positions. Must be 1 point less than real number of positions.
 */
protected int maxNumberOfRotationPositionOfShape;

protected int currentNumberOfRotationPositionOfShape;

    public Shape() {
        this.currentNumberOfRotationPositionOfShape=startingNumberOfRotationPositionOfShape;
    }

  abstract public void rotateShape(boolean [][] playBoard);
      
 
  
   public void chceckAndDecideRotationState(){ 
        if (currentNumberOfRotationPositionOfShape<maxNumberOfRotationPositionOfShape){
            currentNumberOfRotationPositionOfShape++;
        }
        else {
            currentNumberOfRotationPositionOfShape=startingNumberOfRotationPositionOfShape;
        }
  }






}
      






