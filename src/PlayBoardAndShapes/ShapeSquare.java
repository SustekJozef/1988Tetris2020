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
        this.shapeInitializationArray= new int[][]{{BASEX, BASEY},{BASEX, BASEY-1},{BASEX-1, BASEY-1},{BASEX-1, BASEY}};
    }

    /**
     *
     * @param playBoard
     */
    @Override
    public void rotateShape(boolean [][] playBoard) {
    }
    









}
