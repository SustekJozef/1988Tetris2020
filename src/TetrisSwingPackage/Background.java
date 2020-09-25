/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TetrisSwingPackage;

import PlayBoardAndShapes.PlayBoard;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *Represents backround of grafical bacround of game
 * @author Jozef
 */
public class Background extends JPanel{

    /**
     * Playboard
     */
    private PlayBoard playBoard;
    
    
    /**
     * Saves instantion of backround
     * 
     * @param playBoard Playboard of game
     */
    public Background(PlayBoard playBoard){
        super();
        this.playBoard=playBoard;
    }
       
    /**
     * Overpainting of panel
     * @param g Playboard of game
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        playBoard.prepareScreenBorders();
        playBoard.printPlayBoard(g);
    }

    






}
