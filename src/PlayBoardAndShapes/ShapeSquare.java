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
public class ShapeSquare extends Shape{

    /**
     *
     */
    public ShapeSquare() {
        this.shapeInitializationArray= new int[][]{{baseX, baseY},{baseX, baseY-1},{baseX-1, baseY-1},{baseX-1, baseY}};
    }

    @Override
    public void rotateShape(boolean [][] playBoard) {
    }
    









}
