/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayBoardAndShapes;

import TetrisSwingPackage.PlayBoardJFrame;

/**
 *
 * @author Jozef
 */
public class ThreadMovingDown extends Thread {

    PlayBoardJFrame automaticRunningDown;
    
    /**
     *
     */
    @Override
    public void run() {
        //playBoardJFrame.updateScreen("down");;
            }

    /**
     *
     * @param name
     * @param playBoardJFrame
     */
    public ThreadMovingDown(String name, PlayBoardJFrame playBoardJFrame) {
        super(name);
        
    }










}
