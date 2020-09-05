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
    
    
    private Shape shape;

    private int sleepTime;
    
    private Shape currentShape;
    
    private boolean pushNewShape;
    
    private boolean removeLast;

    //checks if next position of the shape is occupied or not.
    private boolean continueAutomaticMoveDown;//this variable makes sure that while-cycle goes only ONE time (one shape´s move)



    /**
     *
     */
    public PlayBoard() {
        sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=15; 
        this.gameArrayWidth=15;
        this.playBoard=new boolean[gameArrayHeight][gameArrayWidth];
        this.currentShape=new ShapeI();
        this.sleepTime=500;
        this.pushNewShape=true;
        this.removeLast=true;
        this.continueAutomaticMoveDown=true;
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
    automaticMoveDown();
    }

    public void automaticMoveDown() throws InterruptedException{
        //if new shape request is available program makes new shape
        if (pushNewShape){
            MakeNewShape(); //makes new shape
            pushNewShape=false; //stop making a new shape in next round
            
            if (checkIfShapeCanGoDown()) { //if there is possibility to go down
                inputShapeToPlayBoard();
                printPlayBoard();
                removeShapeFromPlayBoard();
                MoveDown();
                System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s");
            }
            else {
                pushNewShape=true;//it means that this else will run only when is a Game OVER, becase there is no space for a new shape

            }
        } else { //if the shape is NOT new
            if (checkIfShapeCanGoDown()) {
                inputShapeToPlayBoard();
                printPlayBoard();
                removeShapeFromPlayBoard();
                MoveDown();
                System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s");
            }
            else { //when it can´t go down - it will stop at last position(will be not removed)
                pushNewShape=true;
                inputShapeToPlayBoard();
                printPlayBoard();
                System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s");
            }
          }
    }
    
    public void MakeNewShape(){
        if (pushNewShape){
            //makes new shape to come to game
            this.currentShape=new ShapeL();
        }
    }
    
    /**
     * Makes input of a specific shape into the playboard (in to the game)
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void inputShapeToPlayBoard(){
        //put xy position from shapes´s predefined position into playboard[][] positional system.
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=true;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=true;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=true;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=true;
    }

    public void removeShapeFromPlayBoard(){
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=false;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=false;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=false;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=false;
    }
    /**
     *Automatically push shape one row down
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void MoveDown() throws InterruptedException{ //dorobiť dotyk s inou -- takže či dalšie pole je to, čo je)
          //(currentShape.shapeInitializationArray[0][0]+1) == (currentShape.shapeInitializationArray[1][0]+1) == (currentShape.shapeInitializationArray[2][0]+1) == (currentShape.shapeInitializationArray[3][0]+1) == true) {
    Thread.sleep(sleepTime);
    //add one point to the shape position on playboard. It pushes the shape one row down
    currentShape.shapeInitializationArray[0][0]+=1;
    currentShape.shapeInitializationArray[1][0]+=1;
    currentShape.shapeInitializationArray[2][0]+=1;
    currentShape.shapeInitializationArray[3][0]+=1;
}

    public boolean checkIfShapeCanGoDown(){
        if (playBoard[(currentShape.shapeInitializationArray[0][0])+1][currentShape.shapeInitializationArray[0][1]]==false &&
            playBoard[(currentShape.shapeInitializationArray[1][0])+1][currentShape.shapeInitializationArray[1][1]]==false && 
            playBoard[(currentShape.shapeInitializationArray[2][0])+1][currentShape.shapeInitializationArray[2][1]]==false && 
            playBoard[(currentShape.shapeInitializationArray[3][0])+1][currentShape.shapeInitializationArray[3][1]]==false)  {
     
            return true;
        }
        else {
            return false;
                    }
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