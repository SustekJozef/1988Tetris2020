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
public class ShapeZ extends Shape{

    /**
     *
     */
    public ShapeZ() {
        this.shapeInitializationArray= new int[][]{{baseX, baseY},{baseX, baseY+1},{baseX-1, baseY},{baseX-1, baseY-1}};
        super.maxNumberOfRotationPositionOfShape=1;

    }

    @Override
    public void rotateShape(boolean[][] playBoard) {
        chceckAndDecideRotationState();
        switch(currentNumberOfRotationPositionOfShape) {
                case 0:
                if (
                playBoard[(shapeInitializationArray[1][0])+1][shapeInitializationArray[1][1]+1]==false &&
                playBoard[(shapeInitializationArray[2][0])-1][shapeInitializationArray[2][1]+1]==false &&
                playBoard[(shapeInitializationArray[3][0])-2][shapeInitializationArray[3][1]]==false) {
                    //shapeI - rotate to 0st position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]+=1;
                    shapeInitializationArray[1][1]+=1;
                    
                    shapeInitializationArray[2][0]-=1;
                    shapeInitializationArray[2][1]+=1;
                    
                    shapeInitializationArray[3][0]-=2;
                    //shapeInitializationArray[3][1]+=0;
                }
                else {    //needed for reseting state of rotation
                    currentNumberOfRotationPositionOfShape-=1;
                }
                break;
                    
                case 1:
            if (
                playBoard[(shapeInitializationArray[1][0])-1][shapeInitializationArray[1][1]-1]==false &&
                playBoard[(shapeInitializationArray[2][0])+1][shapeInitializationArray[2][1]-1]==false &&
                playBoard[(shapeInitializationArray[3][0])+2][shapeInitializationArray[3][1]]==false) {                    
                    //shapeI - rotate to 1st position
                    //first block remains the same

                    //shapeInitializationArray[0][0]-=0;
                    //shapeInitializationArray[0][1]+=0;
                    
                    shapeInitializationArray[1][0]-=1;
                    shapeInitializationArray[1][1]-=1;
                    
                    shapeInitializationArray[2][0]+=1;
                    shapeInitializationArray[2][1]-=1;
                    
                    shapeInitializationArray[3][0]+=2;
                    //shapeInitializationArray[3][1]-=0;
            }
            else {    //needed for reseting state of rotation
                    currentNumberOfRotationPositionOfShape-=1;
                }
                break;
        }
    }









}
