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
public class ShapeZMirrored extends Shape{

    /**
     *
     */
    public ShapeZMirrored() {
        this.shapeInitializationArray= new int[][]{{baseX, baseY},{baseX, baseY-1},{baseX-1, baseY},{baseX-1, baseY+1}};
        super.maxNumberOfRotationPositionOfShape=1;

    }

    @Override
    public void rotateShape() {
        chceckAndDecideRotationState();
        switch(currentNumberOfRotationPositionOfShape) {
                case 0:
                    //rotate to 0st position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]+=1;
                    shapeInitializationArray[1][1]-=1;
                    
                    shapeInitializationArray[2][0]-=1;
                    shapeInitializationArray[2][1]-=1;
                    
                    shapeInitializationArray[3][0]-=2;
                    //shapeInitializationArray[3][1]+=0;
                break;
                    
                case 1:
                    //rotate to 2nd position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]-=1;
                    shapeInitializationArray[1][1]+=1;
                    
                    shapeInitializationArray[2][0]+=1;
                    shapeInitializationArray[2][1]+=1;
                    
                    shapeInitializationArray[3][0]+=2;
                    //shapeInitializationArray[3][1]+=0;
                break;
        }
    }









}
