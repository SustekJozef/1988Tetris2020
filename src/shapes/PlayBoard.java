/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.util.Scanner;
/**
 * Represents playboard of Tetris.
 *
 * @author Jozef
 */
public class PlayBoard {
   /**
     * Game´s playboard, where are saved all existing blocks. 
     */
    private boolean[][] playBoard;
   /**
     *
     */
    private String userChoice;
    /**
     *
     */
    private Scanner sc;
    
    
    /**
     *
     */
    private int gameArrayHeight;

    /**
     *
     */
    protected int gameArrayWidth;
    
    
    private Shape shapeI;

    private int sleepTime;
    
    private Shape currentShape1;
    
    /**
     *
     */
    public PlayBoard() {
        sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=15; 
        this.gameArrayWidth=15;
        this.playBoard=new boolean[gameArrayHeight][gameArrayWidth];
        this.shapeI=new ShapeI();
        this.shapeI=new ShapeL();
        this.currentShape1=new ShapeI();
        this.sleepTime=500;
    }

    /**
     *Prints Welcome screen. User can choice to run a game in different modes.
     */
    public void printWelcomeScreen() throws InterruptedException {
        //fills first and last row with blocks (by this it makes playing ground
        for (int i = 0; i < (playBoard[1].length); i++) {
            playBoard[0][i]=true;
            playBoard[(playBoard.length-1)][i]=true;
        }
        
                
                inputShapeToPlayBoard(this.currentShape1);
                printPlayBoard();
                removeShapeFromPlayBoard(this.currentShape1);
                automaticMoveDown(this.currentShape1);


        System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s");
        
    }

    /**
     * Makes input of a specific shape into the playboard (in to the game)
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void inputShapeToPlayBoard(Shape currentShape){
        //put xy position from shapes´s predefined position into playboard[][] positional system.
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=true;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=true;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=true;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=true;
    }

    public void removeShapeFromPlayBoard(Shape currentShape){
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=false;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=false;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=false;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=false;
    }
    /**
     *Automatically push shape one row down
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void automaticMoveDown(Shape currentShape) throws InterruptedException{ //dorobiť dotyk s inou -- takže či dalšie pole je to, čo je)
    //checks if next position of the shape is occupied or not.
    
    //(currentShape.shapeInitializationArray[0][0]+1) == (currentShape.shapeInitializationArray[1][0]+1) == (currentShape.shapeInitializationArray[2][0]+1) == (currentShape.shapeInitializationArray[3][0]+1) == true) {
    Thread.sleep(sleepTime);
    //add one point to the shape position on playboard. It pushes the shape one row down
    currentShape.shapeInitializationArray[0][0]+=1;
    currentShape.shapeInitializationArray[1][0]+=1;
    currentShape.shapeInitializationArray[2][0]+=1;
    currentShape.shapeInitializationArray[3][0]+=1;
   
    }
    
    /**
     * Prints playboard with actual state of blocks.
     */
    public void printPlayBoard(){
    for (int i = 0; i < (playBoard.length); i++) {
        for (int j = 0; j < (playBoard[1].length); j++) {
            if ((playBoard[i][j])==true){
                System.out.print("■");
            }
            else {
                //makes blocks white. After that it reset back to black color
                System.out.print("\033[37;7m■\033[0m");
            }
        }
        System.out.println();
        
        System.out.print("");
    }
}

}