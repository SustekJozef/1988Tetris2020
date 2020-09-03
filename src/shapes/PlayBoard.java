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

    private boolean[][] playBoard;
    private String userChoice;
    private Scanner sc;
    private boolean [][] gameArray;
    private int gameArrayHeight;
    protected int gameArrayWidth;
    
    public PlayBoard() {
        sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=15; 
        this.gameArrayWidth=15;
        this.playBoard=new boolean[gameArrayHeight][gameArrayWidth];

    }

    /**
     *Welcome screen. User can choice to run a game in different modes.
     */
    public void printWelcomeScreen() {
        //fills first and last row with blocks (by this it makes playing ground
        for (int i = 0; i < (playBoard[1].length); i++) {
            playBoard[0][i]=true;
            playBoard[(playBoard.length-1)][i]=true;
        }
        
                ShapeI shapeI=new ShapeI();
        playBoard[shapeI.shapeInitializationArray[0][0]][shapeI.shapeInitializationArray[0][1]]=true;
        playBoard[shapeI.shapeInitializationArray[1][0]][shapeI.shapeInitializationArray[1][1]]=true;
        playBoard[shapeI.shapeInitializationArray[2][0]][shapeI.shapeInitializationArray[2][1]]=true;
        playBoard[shapeI.shapeInitializationArray[3][0]][shapeI.shapeInitializationArray[3][1]]=true;
        printPlayBoard();
        System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s");
        
    }

    /**
     * Prints playboard with actual state of blocks
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
        
        ShapeI shapeI=new ShapeI();
        System.out.print("");
    }
}

}