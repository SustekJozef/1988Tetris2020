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
        this.shapeInitializationArray= new int[][]{{baseX, baseY},{baseX, baseY-1},{baseX, baseY+1},{baseX, baseY+2}};
        super.maxNumberOfRotationPositionOfShape=1;
    }

     /**
     *Push shapeI to rotate to the next rotation
     */
    @Override
    public void rotateShape() { 
        chceckAndDecideRotationState();
        switch(currentNumberOfRotationPositionOfShape) {
                case 0:
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
                break;
                    
                case 1:
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
                break;
        }             
        
    }
        
}

    

   










