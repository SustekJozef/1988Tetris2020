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
        this.shapeInitializationArray= new int[][]{{super.BASEX, super.BASEY},{super.BASEX, super.BASEY-1},{super.BASEX-1, super.BASEY-1},{super.BASEX-1, super.BASEY}};
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
