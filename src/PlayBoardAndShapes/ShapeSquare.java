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

        protected static int[] COLOR_OF_SHAPE=new int[]{0,255,0};


    /**
     *
     */
    public ShapeSquare() {
        this.shapeInitializationArray= new int[][]{{BASEX, BASEY},{BASEX, BASEY-1},{BASEX-1, BASEY-1},{BASEX-1, BASEY}};
        super.numberOfShape=4;
    }

    /**
     *
     * @param playBoard
     */
    @Override
    public void rotateShape(int [][] playBoard) {
    }
    









}
