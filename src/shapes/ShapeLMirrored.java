/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

/**
 *
 * @author Jozef
 */
public class ShapeLMirrored extends Shape{

    /**
     *
     */
    public ShapeLMirrored() {
        this.shapeInitializationArray= new int[][]{{baseX, baseY},{baseX, baseY-1},{baseX-1, baseY},{baseX-2, baseY}};
    }
    /*
    @Override
    public void automaticMoveDown(){
    
    }*/








}
