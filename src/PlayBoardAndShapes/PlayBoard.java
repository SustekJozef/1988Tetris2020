/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayBoardAndShapes;


import PlayBoardAndShapes.TetrisSwingPackage1.Background;
import PlayBoardAndShapes.CPU.ComputerPlayer;
import sounds.SoundEffect;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.SerializationUtils;


/**
 * Represents playboard of Tetris.
 *
 * @author Jozef
 */
public class PlayBoard implements Serializable{
   /**
     * Game´s playboard, where are saved all existing blocks. 
     */
    private int [][] playBoardArray;
   /**
     *
     */
    private String userChoice;
    /**
     *
     */
    //private Scanner sc;
    
    
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
    * Uses current playboard to checks all possibilities of putting a shape to the board.
    */
    ComputerPlayer computerPlayer;
    
    /**
     *
     */
    public PlayBoard() {
        //this.sc = new Scanner(System.in,"Windows-1250");
        this.userChoice = "";
        this.gameArrayHeight=22; //22
        this.gameArrayWidth=12;  //12
        this.playBoardArray=new int[gameArrayHeight][gameArrayWidth];
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
        this.setDownOrCanRotete=true;
        this.score=0;
        this.numberFullRowsInOneTurn=0;
        this.speedBonusFromSpeedLevel=1;
        this.backroundColor=new Color(177, 156, 129);
       // this.computerPlayer=new ComputerPlayer(this);
    }

    /** 
     *Prints screen with borders. User can choice to run a game in different modes.---bad writen - new things this does!!!!!!!!!!!!!!!!!!!!
     */
    public void prepareScreenBorders() {
        //fills first and last row with blocks (by this it makes playing ground

        
        for (int i = 0; i < (getPlayBoardArray().length); i++) {
            //playBoard[0][i]=true;//makes blocks upper row
            
            if (i<12){//-1 will be value for outside border
                playBoardArray[(getPlayBoardArray().length-1)][i]=-1; //makes blocks lower row
            }
            playBoardArray[i][getPlayBoardArray()[0].length-1]=-1;//makes blocks on the right side
            playBoardArray[i][0]=-1;//makes blocks on the left side

        }
       
    }
    
    /**
     *Prints Welcome screen. User can choice to run a game in different modes.
     */
    public void deleteOnlyScreenNotBorders() {
        //fills first and last row with blocks (by this it makes playing ground
        for (int i = 0; i < (getPlayBoardArray().length); i++) {
            for (int j = 0; j < getPlayBoardArray()[1].length; j++) {
               playBoardArray[i][j]=0;//makes blocks upper row
           
            }
              

        }
        
    }

    /**
     *
     * @param direction
     */
    public void inputShapeToPlayboard(Shape currentShape, int [][] playBoardArray,String direction){
        //if new shape request is available program makes new shape
        if (isPushNewShape()){                                                                                              
            if (currentShape==null){//it means that it is the first shape in activ game
            currentShape=MakeNewShape(); //makes new shape 
            this.currentShape=currentShape; //these two lines of code is only for first time starting of game
            }
            else {
                currentShape=MakeNewShape(); ///reworkreworkreworkreworkreworkreworkreworkreworkreworkrework reworkreworkreworkreworkrework
                this.currentShape=currentShape;
            }
            
            
            setPushNewShape(false); //stop making a new shape in next round
            setDownOrCanRotete=true; //can freely rotate
            
            if (checkIfShapeCanGoDown(currentShape, playBoardArray)) { //if there is a possibility to go down - new shape can co only down at first
                writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                
//print by javaswing

            }
            else  { //if there is not a possibility to go ONLY down (so there is needed new shape {
                setPushNewShape(true);//it means that this else will run only when is a Game OVER, becase there is no space for a new shape
            };
        } 
        else { //if the shape is NOT new
            removeShapeFromPlayBoardXYSystem(currentShape, playBoardArray); //removes an old shape
            
            switch(direction) {
                case "down":
                    if (checkIfShapeCanGoDown(currentShape, playBoardArray)) {
                        MoveDown(currentShape);
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                    }
                    else {
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray); //even though is the shape removed, it need to be there -so it is repainted
                        setPushNewShape(true);
                        setDownOrCanRotete=false;
                        checkFullRowAndRemoveIt();
                     //   SoundEffect.SIT.play(); //play a sound of rotate movement
                    }
                    break;
                  case "right":
                    if (checkIfShapeCanGoRight(currentShape, playBoardArray)) {
                        MoveRight(currentShape);
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                    }
                    else {
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                    }
                    break;
                  case "left":
                   if (checkIfShapeCanGoLeft(currentShape, playBoardArray)) {
                        MoveLeft(currentShape);
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                    }
                   else {
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);
                    }
                    break;
                  case "doNothingOnlyPaintShapeIntoPlayboard":
                        writeShapeToPlayBoardXYSystem(currentShape, playBoardArray);//needed to write again, because it was removed one step before
                    break;
                }
            
          }
        
        
        
        
        
        /*
        // Serializes Student object to a byte[] array
        byte[] bytes = SerializationUtils.serialize(this);
        // Deserializes Student object from an array of bytes
        PlayBoard clone = (PlayBoard) SerializationUtils.deserialize(bytes);
        
        
        try {
        this.computerPlayer=new ComputerPlayer(clone);
        } catch (CloneNotSupportedException ex) {
        Logger.getLogger(PlayBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        computerPlayer.moveShapeToStartingPositionLeft();
        */
        
        
    
    
    
    }
    
    /**
     * 
     */
    public Shape MakeNewShape(){
            //makes new random shape to come to game
            //this.currentShape=arrayOfUniqueArrayListShapes.get(randomShape.nextInt(arrayOfUniqueArrayListShapes.size()));
            
            switch (randomShape.nextInt(7)){
                    case 0:
                    return new ShapeI();
                    case 1:
                    return new ShapeL();
                    case 2:
                    return new ShapeLMirrored();
                    case 3:
                    return new ShapeSquare();
                    case 4:
                    return new ShapeT();
                    case 5:
                    return new ShapeZ();
                    case 6:
                    return new ShapeZMirrored();
            }
            return new ShapeZMirrored(); ///opraviť
    }
    
    /**
     * Makes input of a specific shape into the playboard (in to the game)
     */
    public void writeShapeToPlayBoardXYSystem(Shape currentShape, int [][] playBoardArray){
        //put xy position from shapes´s predefined position into playboard[][] positional system.
        playBoardArray[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=currentShape.numberOfShape;
        playBoardArray[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=currentShape.numberOfShape;
        playBoardArray[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=currentShape.numberOfShape;
        playBoardArray[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=currentShape.numberOfShape;
    }

    /**
     *
     */
    public void removeShapeFromPlayBoardXYSystem(Shape currentShape, int [][] playBoardArray){
        playBoardArray[currentShape.shapeInitializationArray[0][0]][currentShape.shapeInitializationArray[0][1]]=0;
        playBoardArray[currentShape.shapeInitializationArray[1][0]][currentShape.shapeInitializationArray[1][1]]=0;
        playBoardArray[currentShape.shapeInitializationArray[2][0]][currentShape.shapeInitializationArray[2][1]]=0;
        playBoardArray[currentShape.shapeInitializationArray[3][0]][currentShape.shapeInitializationArray[3][1]]=0;
    }
    /**
     *Push shape one row down
     */
    public void MoveDown(Shape currentShape) { 
    //add one point to the shape position on playboard. It pushes the shape one row down
    currentShape.shapeInitializationArray[0][0]+=1;
    currentShape.shapeInitializationArray[1][0]+=1;
    currentShape.shapeInitializationArray[2][0]+=1;
    currentShape.shapeInitializationArray[3][0]+=1;
}
    
    
    /**
     *Push shape one collumn right
     */
    public void MoveRight(Shape currentShape) { 
    //add one point to the shape position on playboard. It pushes the shape one collumn right
    currentShape.shapeInitializationArray[0][1]+=1;
    currentShape.shapeInitializationArray[1][1]+=1;
    currentShape.shapeInitializationArray[2][1]+=1;
    currentShape.shapeInitializationArray[3][1]+=1;
    }
    
    /**
     *Push shape one collumn leff
     */
    public void MoveLeft(Shape currentShape) { 
        
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
    public boolean checkIfShapeCanGoDown(Shape currentShape, int [][] playBoardArray){
       
        
        return playBoardArray[(currentShape.shapeInitializationArray[0][0])+1][currentShape.shapeInitializationArray[0][1]]==0 &&
        playBoardArray[(currentShape.shapeInitializationArray[1][0])+1][currentShape.shapeInitializationArray[1][1]]==0 &&
        playBoardArray[(currentShape.shapeInitializationArray[2][0])+1][currentShape.shapeInitializationArray[2][1]]==0 &&
        playBoardArray[(currentShape.shapeInitializationArray[3][0])+1][currentShape.shapeInitializationArray[3][1]]==0;
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go right.
     * @return True if there is a possibility for shape to move right.
     */
    public boolean checkIfShapeCanGoRight(Shape currentShape, int [][] playBoardArray){
        return playBoardArray[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]+1]==0 &&
                playBoardArray[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]+1]==0 &&
                playBoardArray[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]+1]==0 &&
                playBoardArray[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]+1]==0;
    }
    
    /**
     *Checks if and only if there is any possibility for shape to go left.
     * @return True if there is a possibility for shape to move left.
     */
    public boolean checkIfShapeCanGoLeft(Shape currentShape, int [][] playBoardArray){
        return playBoardArray[(currentShape.shapeInitializationArray[0][0])][currentShape.shapeInitializationArray[0][1]-1]==0 &&
               playBoardArray[(currentShape.shapeInitializationArray[1][0])][currentShape.shapeInitializationArray[1][1]-1]==0 &&
               playBoardArray[(currentShape.shapeInitializationArray[2][0])][currentShape.shapeInitializationArray[2][1]-1]==0 &&
               playBoardArray[(currentShape.shapeInitializationArray[3][0])][currentShape.shapeInitializationArray[3][1]-1]==0;
    }
    
    
    
    /**
     * Prints playboard with actual state of blocks.
     * @param g Object of Graphic type
     */
    public void printPlayBoard(Graphics g){
    for (int i = 0; i < (getPlayBoardArray()[0].length); i++) {
        for (int j = 0; j < (getPlayBoardArray().length); j++) {
            if ((getPlayBoardArray()[j][i])==0){
                g.setColor(backroundColor);
                g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);

            }
            else {//NEEDED TO REWORK THIS CODE - SIMPLER
                switch(getPlayBoardArray()[j][i]) {
                case 1:
                   g.setColor(new Color(ShapeI.COLOR_OF_SHAPE[0], ShapeI.COLOR_OF_SHAPE[1], ShapeI.COLOR_OF_SHAPE[2]));
                   break; 
                case 2:
                   g.setColor(new Color(ShapeL.COLOR_OF_SHAPE[0], ShapeL.COLOR_OF_SHAPE[1], ShapeL.COLOR_OF_SHAPE[2]));
                   break;
                case 3:
                   g.setColor(new Color(ShapeLMirrored.COLOR_OF_SHAPE[0], ShapeLMirrored.COLOR_OF_SHAPE[1], ShapeLMirrored.COLOR_OF_SHAPE[2]));
                   break; 
                case 4:
                   g.setColor(new Color(ShapeSquare.COLOR_OF_SHAPE[0], ShapeSquare.COLOR_OF_SHAPE[1], ShapeSquare.COLOR_OF_SHAPE[2]));
                   break; 
                case 5:
                   g.setColor(new Color(ShapeT.COLOR_OF_SHAPE[0], ShapeT.COLOR_OF_SHAPE[1], ShapeT.COLOR_OF_SHAPE[2]));
                   break; 
                case 6:
                   g.setColor(new Color(ShapeZ.COLOR_OF_SHAPE[0], ShapeZ.COLOR_OF_SHAPE[1], ShapeZ.COLOR_OF_SHAPE[2]));
                   break; 
                case 7:
                   g.setColor(new Color(ShapeZMirrored.COLOR_OF_SHAPE[0], ShapeZMirrored.COLOR_OF_SHAPE[1], ShapeZMirrored.COLOR_OF_SHAPE[2]));
                   break; 
                case -1:
                   g.setColor(Color.BLACK);
                   break; 
            }
            g.fillRect(i * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), j * (BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS), BLOCK_WIDTH, BLOCK_WIDTH);
        }
        System.out.println();
        System.out.print("");
    }
}
    }  
    /**
     *Push shape to rotate to the next rotation
     */
    public void rotateAnyShape(Shape currentShape, int [][] playBoardArray) { 
         if (setDownOrCanRotete){
             
         removeShapeFromPlayBoardXYSystem(currentShape,playBoardArray);
         currentShape.rotateShape(playBoardArray);
         writeShapeToPlayBoardXYSystem(currentShape,playBoardArray);
         }
}
    /**
    *Checks if there is any one full row of blocks and removes it immediately with falling other rows down.
    * Method is checking array of blocks from upper side to down side
    */
    public void checkFullRowAndRemoveIt(){
        boolean isFullRow=true;
        for (int i = 1; i < getPlayBoardArray().length-1; i++) {
            for (int j = 1; j < getPlayBoardArray()[0].length; j++) {
                if ((getPlayBoardArray()[i][j]==0)){//if there is any white space - result will remains false
                    isFullRow=false;//if there is no false/white space - remains true - so that is full row
                    j=getPlayBoardArray()[0].length;//if there is even only one white space - it is not needed to look up further
                }
            }    
            if (isFullRow){
                for (int k = i; k > 1; k--) {//goes up to reach each row (till second row from top)
                    for (int l = 0; l < getPlayBoardArray()[1].length; l++) {//goes block by block in actual row 
                        //saves higher row to actual row (block by block)
                        playBoardArray[k][l]=getPlayBoardArray()[k-1][l];
                        playBoardArray[k-1][l]=0;

                        
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
        
        switch (numberFullRowsInOneTurn) {
            case 1: 
                SoundEffect.REMOVE1.play(); //play a sound of rotate movement
                break;
            case 2: 
                SoundEffect.REMOVE2.play(); //play a sound of rotate movement
                break;
            case 3: 
                SoundEffect.REMOVE3.play(); //play a sound of rotate movement
                break;
            case 4: 
                SoundEffect.REMOVE4.play(); //play a sound of rotate movement
                break;
            default:
                SoundEffect.SIT.play(); //play a sound of rotate movement

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

 
   

    /**
     * @return the playBoardArray
     */
    public int[][] getPlayBoardArray() {
        return playBoardArray;
    }

    /**
     * @return the currentShape
     */
    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * @return the pushNewShape
     */
    public boolean isPushNewShape() {
        return pushNewShape;
    }
    

}