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
public class ShapeI extends Shape implements ShapeRotateInterface{

    /**
     *
     */
    public ShapeI() {
        this.shapeInitializationArray= new int[][]{{BASEX, BASEY},{BASEX, BASEY-1},{BASEX, BASEY+1},{BASEX, BASEY+2}};
        super.maxNumberOfRotationPositionOfShape=1;
    }

     /**
     *Push shapeI to rotate to the next rotation
     * @param playBoard
     */
    @Override
    public void rotateShape(boolean [][] playBoard) { 
        chceckAndDecideRotationState();
        switch(currentNumberOfRotationPositionOfShape) {
                case 0:
                if (
                playBoard[(shapeInitializationArray[1][0])+1][shapeInitializationArray[1][1]-1]==false &&
                playBoard[(shapeInitializationArray[2][0])-1][shapeInitializationArray[2][1]+1]==false &&
                playBoard[(shapeInitializationArray[3][0])-2][shapeInitializationArray[3][1]+2]==false) {
                    //shapeI - rotate to 0st position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]+=1;
                    shapeInitializationArray[1][1]-=1;
                    
                    shapeInitializationArray[2][0]-=1;
                    shapeInitializationArray[2][1]+=1;
                    
                    shapeInitializationArray[3][0]-=2;
                    shapeInitializationArray[3][1]+=2;
                }
                else {    //needed for reseting state of rotation
                    currentNumberOfRotationPositionOfShape-=1;
                }
                break;
                    
                case 1:
            if (
                playBoard[(shapeInitializationArray[1][0])-1][shapeInitializationArray[1][1]+1]==false &&
                playBoard[(shapeInitializationArray[2][0])+1][shapeInitializationArray[2][1]-1]==false &&
                playBoard[(shapeInitializationArray[3][0])+2][shapeInitializationArray[3][1]-2]==false) {                    
                    //shapeI - rotate to 1st position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]-=1;
                    shapeInitializationArray[1][1]+=1;
                    
                    shapeInitializationArray[2][0]+=1;
                    shapeInitializationArray[2][1]-=1;
                    
                    shapeInitializationArray[3][0]+=2;
                    shapeInitializationArray[3][1]-=2;
            }
            else {    //needed for reseting state of rotation
                    currentNumberOfRotationPositionOfShape-=1;
                }
                break;
        }             
        
    }
        
}

    

   










