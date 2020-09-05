/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import TetrisSwingPackage.MainJFrame;
import shapes.PlayBoard;

/**
 *
 * @author Jozef
 */
public class TetrisGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        
         MainJFrame mainFrame=new MainJFrame();
        //makes frame set to middle of the screen
        mainFrame.setLocationRelativeTo(null);
        
        mainFrame.setVisible(true);

        //forbids frame to change of size of window
        mainFrame.setResizable(true);
        //sets that minimal size of window is the standart size of main JFrame
        mainFrame.setMinimumSize(mainFrame.getSize());
        
        
        
        
        /*
        PlayBoard p=new PlayBoard();
        
        for (int i = 0; i < 100; i++) {
        p.printEmptyScreen();
        }*/
       
    
    
    
    
    
    
    
    
    
    
    }
    
}
