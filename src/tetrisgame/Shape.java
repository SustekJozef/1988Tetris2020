/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisgame;

/**
 *
 * @author Jozef
 */
public class Shape {

protected int[][] shapeI;
private int[][] shapeT;
private int[][] shapeZ;
private int[][] shapeMirroredZ;
private int[][] shapeSquar;
private int[][] shapeL;
private int[][] shapeMirroredL;

int baseX=0;
private int baseY=7;

Shape(){
    this.shapeI= new int[][]{{baseX, baseY},{baseX+1, baseY+1},{baseX+2, baseY+2},{baseX+3, baseY+3}};


}

public void MakeShapeRisingFromBackround(Shape currentShape){ //not sure if it is gooed defined
    
}

}
      






