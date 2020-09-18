/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayBoardAndShapes;

import TetrisSwingPackage.Background;
import TetrisSwingPackage.PlayBoardJFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
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
    
   

    //checks if next position of the shape is occupied or not.
    private boolean continueAutomaticMoveDown;//this variable makes sure that while-cycle goes only ONE time (one shape´s move)

    private Random randomShape;

    private ArrayList arrayOfUniqueArrayListShapes;
    
    private static final int BLOCK_WIDTH=16;
    private static final int SPACE_BETWEEN_BLOCKS=2;
    
    //private PlayBoardJFrame playBoardJFrame=new PlayBoardJFrame();
    private Background background=new Background(this);
    
    
    /**
     *
     */
    public PlayBoard() {
        sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=17; 
        this.gameArrayWidth=17;
        this.playBoard=new boolean[gameArrayHeight][gameArrayWidth];
        this.currentShape=new ShapeI();
        this.sleepTime=500;
        this.pushNewShape=true;
        this.randomShape=new Random();
        this.arrayOfUniqueArrayListShapes=new ArrayList();
            this.arrayOfUniqueArrayListShapes.add(new ShapeI());
            this.arrayOfUniqueArrayListShapes.add(new ShapeL());
            this.arrayOfUniqueArrayListShapes.add(new ShapeLMirrored());
            this.arrayOfUniqueArrayListShapes.add(new ShapeSquare());
            this.arrayOfUniqueArrayListShapes.add(new ShapeT());
            this.arrayOfUniqueArrayListShapes.add(new ShapeZ());
            this.arrayOfUniqueArrayListShapes.add(new ShapeZMirrored());
        this.continueAutomaticMoveDown=true;
    }

    /**
     *Prints Welcome screen. User can choice to run a game in different modes.
     */
    public void prepareScreenBorders() {
        //fills first and last row with blocks (by this it makes playing ground
        for (int i = 0; i < (playBoard[1].length); i++) {
            playBoard[0][i]=true;//makes blocks upper row
            playBoard[(playBoard.length-1)][i]=true; //makes blocks lower row
            playBoard[i][playBoard.length-1]=true;//makes blocks on the right side
            playBoard[i][0]=true;//makes blocks on the left side

        }
        /*//inputShapeToPlayboard();
        System.out.println("THIS IS \033[37;7mjust   \033[34;7mT  e Tr i  s\033[0m");*/
    }

    public void inputShapeToPlayboard(String direction){
        //if new shape request is available program makes new shape
        if (pushNewShape){                                                                                              
            MakeNewShape(); //makes new shape
            pushNewShape=false; //stop making a new shape in next round
            
            if (checkIfShapeCanGoDown()) { //if there is a possibility to go down - new shape can co only down at first
                writeShapeToPlayBoardXYSystem();

//print by javaswing

            }
            else  { //if there is not a possibility to go ONLY down (so there is needed new shape {
                pushNewShape=true;//it means that this else will run only when is a Game OVER, becase there is no space for a new shape
            }
        } 
        else { //if the shape is NOT new
            
            if (("down".equals(direction)) && checkIfShapeCanGoDown()){
                removeShapeFromPlayBoardXYSystem();
                MoveDown();
                writeShapeToPlayBoardXYSystem();
                
                
                /*writeShapeToPlayBoardXYSystem();
                MoveDown();*/
            
                if (direction.equals("right") && checkIfShapeCanGoRight()){
                    removeShapeFromPlayBoardXYSystem();
                    MoveRight();
                    writeShapeToPlayBoardXYSystem();
                }
                    if (direction.equals("left") && checkIfShapeCanGoLeft()){
                        removeShapeFromPlayBoardXYSystem();
                        MoveLeft();
                        writeShapeToPlayBoardXYSystem();                    }
            }        
                    else {//when it can´t go down - it will stop at last position(will be NOT removed)
                        writeShapeToPlayBoardXYSystem();
                        pushNewShape=true;
                        //printPlayBoard(); THIS IS REPLACED BY DRAWING IN JAVA SWING
                    }
          }
    }
    
    public void MakeNewShape(){
            //makes new random shape to come to game
            this.currentShape=(Shape)arrayOfUniqueArrayListShapes.get(randomShape.nextInt(arrayOfUniqueArrayListShapes.size()));
    }
    
    /**
     * Makes input of a specific shape into the playboard (in to the game)
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void writeShapeToPlayBoardXYSystem(){
        //put xy position from shapes´s predefined position into playboard[][] positional system.
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=true;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=true;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=true;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=true;
    }

    public void removeShapeFromPlayBoardXYSystem(){
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=false;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=false;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=false;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=false;
    }
    /**
     *Push shape one row down
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void MoveDown() { 
    //add one point to the shape position on playboard. It pushes the shape one row down
    currentShape.shapeInitializationArray[0][0]+=1;
    currentShape.shapeInitializationArray[1][0]+=1;
    currentShape.shapeInitializationArray[2][0]+=1;
    currentShape.shapeInitializationArray[3][0]+=1;
}
    
    
    /**
     *Push shape one collumn right
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void MoveRight() { 
    //add one point to the shape position on playboard. It pushes the shape one collumn right
    currentShape.shapeInitializationArray[0][0]+=1;
    currentShape.shapeInitializationArray[0][1]+=1;
    currentShape.shapeInitializationArray[0][2]+=1;
    currentShape.shapeInitializationArray[0][3]+=1;
    }
    
    /**
     *Push shape one collumn leff
     * @param currentShape Shape which is currently next for puzzling(Lshape,Tshape etc.)
     */
    public void MoveLeft() { 
        
    //add one point to the shape position on playboard. It pushes the shape one collumn left
    currentShape.shapeInitializationArray[0][0]-=1;
    currentShape.shapeInitializationArray[0][1]-=1;
    currentShape.shapeInitializationArray[0][2]-=1;
    currentShape.shapeInitializationArray[0][3]-=1;
}

    /**
     *Checks if and only if there is any possibility for shape to go down.
     * @return True if there is a possibility for shape to move down.
     */
    public boolean checkIfShapeCanGoDown(){
        return playBoard[(currentShape.shapeInitializationArray[0][0])+2][currentShape.shapeInitializationArray[0][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[1][0])+2][currentShape.shapeInitializationArray[1][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[2][0])+2][currentShape.shapeInitializationArray[2][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[3][0])+2][currentShape.shapeInitializationArray[3][1]]==false;
        
        /*return playBoard[(currentShape.shapeInitializationArray[0][0])+1][currentShape.shapeInitializationArray[0][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[1][0])+1][currentShape.shapeInitializationArray[1][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[2][0])+1][currentShape.shapeInitializationArray[2][1]]==false &&
        playBoard[(currentShape.shapeInitializationArray[3][0])+1][currentShape.shapeInitializationArray[3][1]]==false;*/
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go right.
     * @return True if there is a possibility for shape to move right.
     */
    public boolean checkIfShapeCanGoRight(){
        return playBoard[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]+1]==false &&
                playBoard[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]+1]==false &&
                playBoard[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]+1]==false &&
                playBoard[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]+1]==false;
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go left.
     * @return True if there is a possibility for shape to move left.
     */
    public boolean checkIfShapeCanGoLeft(){
        return playBoard[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]-1]==false &&
                playBoard[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]-1]==false &&
                playBoard[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]-1]==false &&
                playBoard[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]-1]==false;
    }
    
    public void removingShapeAfterSuccesffulMoving(){
        removeShapeFromPlayBoardXYSystem();
        MoveDown();
    }
    
    
    /**
     * Prints playboard with actual state of blocks.
     */
    public void printPlayBoard(Graphics g){
    for (int i = 0; i < (playBoard.length); i++) {
        for (int j = 0; j < (playBoard[1].length); j++) {
            if ((playBoard[j][i])==true){
                g.setColor(Color.red);
                g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);

            //System.out.print("■");
            }
            else {
                g.setColor(Color.green);
                g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                //makes blocks white. After that it reset back to black color
                //System.out.print("\033[37;7m■\033[0m");
            }
        }
        System.out.println();
        System.out.print("");
    }
}

    public void repaintingAfterAnyKindOfMoveDownRightLeft(){
        
        
        
        
        
        
        
    }
    
    
    
    
}