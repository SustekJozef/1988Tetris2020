/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayBoardAndShapes;

import TetrisSwingPackage.Background;
import TetrisSwingPackage.*;
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
    protected int [][] playBoard;
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
    
    /**
     *Checks if there is a request to restart activ game.
     */
    private boolean restartGame;

   

    //checks if next position of the shape is occupied or not.
    private boolean continueAutomaticMoveDown;//this variable makes sure that while-cycle goes only ONE time (one shape´s move)

    private Random randomShape;

    //private ArrayList<Shape> arrayOfUniqueArrayListShapes;
    
    private static final int BLOCK_WIDTH=16;
    private static final int SPACE_BETWEEN_BLOCKS=2;
    
    //private PlayBoardJFrame playBoardJFrame=new PlayBoardJFrame();
    private Background background=new Background(this);
    
    
    private boolean setDownOrCanRotete;
    
    private int score;
    
    private int numberFullRowsInOneTurn;
    
    /**
     *
     */
    protected int speedBonusFromSpeedLevel;

    
    Color backroundColor;
    
    /**
     *
     */
    public PlayBoard() {
        sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=22; //22
        this.gameArrayWidth=12;  //12
        this.playBoard=new int[gameArrayHeight][gameArrayWidth];
        this.sleepTime=500;
        this.pushNewShape=true;
        this.randomShape=new Random();
        /*  this.arrayOfUniqueArrayListShapes=new ArrayList<>();
        this.arrayOfUniqueArrayListShapes.add(new ShapeI());
        this.arrayOfUniqueArrayListShapes.add(new ShapeL());
        this.arrayOfUniqueArrayListShapes.add(new ShapeLMirrored());
        this.arrayOfUniqueArrayListShapes.add(new ShapeSquare());
        this.arrayOfUniqueArrayListShapes.add(new ShapeT());
        this.arrayOfUniqueArrayListShapes.add(new ShapeZ());
        this.arrayOfUniqueArrayListShapes.add(new ShapeZMirrored());*/
        this.continueAutomaticMoveDown=true;
        setDownOrCanRotete=true;
        this.score=0;
        this.numberFullRowsInOneTurn=0;
        speedBonusFromSpeedLevel=1;
        backroundColor=new Color(177, 156, 129);
    }

    /**
     *Prints screen with borders. User can choice to run a game in different modes.
     */
    public void prepareScreenBorders() {
        //fills first and last row with blocks (by this it makes playing ground

        
        for (int i = 0; i < (playBoard.length); i++) {
            //playBoard[0][i]=true;//makes blocks upper row
            
            if (i<12){//-1 will be value for outside border
                playBoard[(playBoard.length-1)][i]=-1; //makes blocks lower row
            }
            playBoard[i][playBoard[0].length-1]=-1;//makes blocks on the right side
            playBoard[i][0]=-1;//makes blocks on the left side

        }
       
    }
    
    /**
     *Prints Welcome screen. User can choice to run a game in different modes.
     */
    public void deleteScreenBorders() {
        //fills first and last row with blocks (by this it makes playing ground
        for (int i = 0; i < (playBoard.length); i++) {
            for (int j = 0; j < playBoard[1].length; j++) {
               playBoard[i][j]=0;//makes blocks upper row
           
            }
              

        }
        
    }

    /**
     *
     * @param direction
     */
    public void inputShapeToPlayboard(String direction){
        //if new shape request is available program makes new shape
        if (pushNewShape){                                                                                              
            MakeNewShape(); //makes new shape
            pushNewShape=false; //stop making a new shape in next round
            setDownOrCanRotete=true; //can freely rotate
            
            if (checkIfShapeCanGoDown()) { //if there is a possibility to go down - new shape can co only down at first
                writeShapeToPlayBoardXYSystem();
                
//print by javaswing

            }
            else  { //if there is not a possibility to go ONLY down (so there is needed new shape {
                pushNewShape=true;//it means that this else will run only when is a Game OVER, becase there is no space for a new shape
            }
        } 
        else { //if the shape is NOT new
            removeShapeFromPlayBoardXYSystem(); //removes an old shape
            
            switch(direction) {
                case "down":
                    if (checkIfShapeCanGoDown()) {
                        MoveDown();
                        writeShapeToPlayBoardXYSystem();
                    }
                    else {
                        writeShapeToPlayBoardXYSystem(); //even though is the shape removed, it need to be there -so it is repainted
                        pushNewShape=true;
                        setDownOrCanRotete=false;
                        checkFullRowAndRemoveIt();
                    }
                    break;
                  case "right":
                    if (checkIfShapeCanGoRight()) {
                        MoveRight();
                        writeShapeToPlayBoardXYSystem();
                    }
                    else {
                        writeShapeToPlayBoardXYSystem();
                    }
                    break;
                  case "left":
                   if (checkIfShapeCanGoLeft()) {
                        MoveLeft();
                        writeShapeToPlayBoardXYSystem();
                    }
                   else {
                        writeShapeToPlayBoardXYSystem();
                    }
                    break;
                }
            
          }
    }
    
    /**
     *
     */
    public void MakeNewShape(){
            //makes new random shape to come to game
            //this.currentShape=arrayOfUniqueArrayListShapes.get(randomShape.nextInt(arrayOfUniqueArrayListShapes.size()));
            
            switch (randomShape.nextInt(7)){
                    case 0:
                    this.currentShape=new ShapeI();
                    break;
                    case 1:
                    this.currentShape=new ShapeL();
                    break;
                    case 2:
                    this.currentShape=new ShapeLMirrored();
                    break;
                    case 3:
                    this.currentShape=new ShapeSquare();
                    break;
                    case 4:
                    this.currentShape=new ShapeT();
                    break;
                    case 5:
                    this.currentShape=new ShapeZ();
                    break;
                    case 6:
                    this.currentShape=new ShapeZMirrored();
            }
    }
    
    /**
     * Makes input of a specific shape into the playboard (in to the game)
     */
    public void writeShapeToPlayBoardXYSystem(){
        //put xy position from shapes´s predefined position into playboard[][] positional system.
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=currentShape.numberOfShape;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=currentShape.numberOfShape;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=currentShape.numberOfShape;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=currentShape.numberOfShape;
    }

    /**
     *
     */
    public void removeShapeFromPlayBoardXYSystem(){
        playBoard[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=0;
        playBoard[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=0;
        playBoard[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=0;
        playBoard[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=0;
    }
    /**
     *Push shape one row down
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
     */
    public void MoveRight() { 
    //add one point to the shape position on playboard. It pushes the shape one collumn right
    currentShape.shapeInitializationArray[0][1]+=1;
    currentShape.shapeInitializationArray[1][1]+=1;
    currentShape.shapeInitializationArray[2][1]+=1;
    currentShape.shapeInitializationArray[3][1]+=1;
    }
    
    /**
     *Push shape one collumn leff
     */
    public void MoveLeft() { 
        
    //add one point to the shape position on playboard. It pushes the shape one collumn left
    currentShape.shapeInitializationArray[0][1]-=1;
    currentShape.shapeInitializationArray[1][1]-=1;
    currentShape.shapeInitializationArray[2][1]-=1;
    currentShape.shapeInitializationArray[3][1]-=1;
}

    /**
     *Checks if and only if there is any possibility for shape to go down.
     * @return True if there is a possibility for shape to move down.
     */
    public boolean checkIfShapeCanGoDown(){
       
        
        return playBoard[(currentShape.shapeInitializationArray[0][0])+1][currentShape.shapeInitializationArray[0][1]]==0 &&
        playBoard[(currentShape.shapeInitializationArray[1][0])+1][currentShape.shapeInitializationArray[1][1]]==0 &&
        playBoard[(currentShape.shapeInitializationArray[2][0])+1][currentShape.shapeInitializationArray[2][1]]==0 &&
        playBoard[(currentShape.shapeInitializationArray[3][0])+1][currentShape.shapeInitializationArray[3][1]]==0;
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go right.
     * @return True if there is a possibility for shape to move right.
     */
    public boolean checkIfShapeCanGoRight(){
        return playBoard[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]+1]==0 &&
                playBoard[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]+1]==0 &&
                playBoard[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]+1]==0 &&
                playBoard[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]+1]==0;
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go left.
     * @return True if there is a possibility for shape to move left.
     */
    public boolean checkIfShapeCanGoLeft(){
        return playBoard[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]-1]==0 &&
               playBoard[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]-1]==0 &&
               playBoard[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]-1]==0 &&
               playBoard[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]-1]==0;
    }
    
    
    
    /**
     * Prints playboard with actual state of blocks.
     * @param g Object of Graphic type
     */
    public void printPlayBoard(Graphics g){
    for (int i = 0; i < (playBoard[0].length); i++) {
        for (int j = 0; j < (playBoard.length); j++) {
            if ((playBoard[j][i])==0){
                g.setColor(backroundColor);
                g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);

            }
            else {
                switch(playBoard[j][i]) {
                case 1:
                   g.setColor(new Color(ShapeI.COLOR_OF_SHAPE[0], ShapeI.COLOR_OF_SHAPE[1], ShapeI.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case 2:
                   g.setColor(new Color(ShapeL.COLOR_OF_SHAPE[0], ShapeL.COLOR_OF_SHAPE[1], ShapeL.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break;
                case 3:
                   g.setColor(new Color(ShapeLMirrored.COLOR_OF_SHAPE[0], ShapeLMirrored.COLOR_OF_SHAPE[1], ShapeLMirrored.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case 4:
                   g.setColor(new Color(ShapeSquare.COLOR_OF_SHAPE[0], ShapeSquare.COLOR_OF_SHAPE[1], ShapeSquare.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case 5:
                   g.setColor(new Color(ShapeT.COLOR_OF_SHAPE[0], ShapeT.COLOR_OF_SHAPE[1], ShapeT.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case 6:
                   g.setColor(new Color(ShapeZ.COLOR_OF_SHAPE[0], ShapeZ.COLOR_OF_SHAPE[1], ShapeZ.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case 7:
                   g.setColor(new Color(ShapeZMirrored.COLOR_OF_SHAPE[0], ShapeZMirrored.COLOR_OF_SHAPE[1], ShapeZMirrored.COLOR_OF_SHAPE[2]));
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
                case -1:
                   g.setColor(Color.BLACK);
                   g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
                   break; 
              
            }
        }
        System.out.println();
        System.out.print("");
    }
}
    }  
    /**
     *Push shape to rotate to the next rotation
     */
    public void rotateAnyShape() { 
         if (setDownOrCanRotete){
             
         removeShapeFromPlayBoardXYSystem();
         currentShape.rotateShape(playBoard);
         writeShapeToPlayBoardXYSystem();
         }
}
    /**
    *Checks if there is any one full row of blocks and removes it immediately with falling other rows down.
    * Method is checking array of blocks from upper side to down side
    */
    public void checkFullRowAndRemoveIt(){
        boolean isFullRow=true;
        for (int i = 1; i < playBoard.length-1; i++) {
            for (int j = 1; j < playBoard[0].length; j++) {
                if ((playBoard[i][j]==0)){//if there is any white space - result will remains false
                    isFullRow=false;//if there is no false/white space - remains true - so that is full row
                    j=playBoard[0].length;//if there is even only one white space - it is not needed to look up further
                }
            }    
            if (isFullRow){
                for (int k = i; k > 1; k--) {//goes up to reach each row (till second row from top)
                    for (int l = 0; l < playBoard[1].length; l++) {//goes block by block in actual row 
                        //saves higher row to actual row (block by block)
                        playBoard[k][l]=playBoard[k-1][l];
                        playBoard[k-1][l]=0;

                        
                    }
                }
            score+=200*speedBonusFromSpeedLevel;
            numberFullRowsInOneTurn++;    
            }
        isFullRow=true;//renewing of variable
        }
        
        //bonuses depends on number of rows completed at once
        if (numberFullRowsInOneTurn>1){
            score+=numberFullRowsInOneTurn*200*speedBonusFromSpeedLevel;//bonus for more rows and increases by level of speed
        }
        numberFullRowsInOneTurn=0;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param speedBonusFromSpeedLevel
     */
    public void setSpeedBonusFromSpeedLevel(int speedBonusFromSpeedLevel) {
        this.speedBonusFromSpeedLevel = speedBonusFromSpeedLevel;
    }

/**
     * @param pushNewShape the pushNewShape to set
     */
    public void setPushNewShape(boolean pushNewShape) {
        this.pushNewShape = pushNewShape;
    }
    
    public void setRestartGame(boolean restartGame) {
        this.restartGame = restartGame;
    }


}