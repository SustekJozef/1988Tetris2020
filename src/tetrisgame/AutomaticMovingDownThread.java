/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrisgame;

import TetrisSwingPackage.*;
import java.util.concurrent.atomic.AtomicBoolean;



/**
 *
 * @author Jozef
 */
public class AutomaticMovingDownThread implements Runnable{

    private Thread mower;
    private final AtomicBoolean runningAtomicBoolean=new AtomicBoolean(false);
    private int interval;
    //PlayBoardJFrame playBoardJFrame=new PlayBoardJFrame();
    
    
    public AutomaticMovingDownThread(int sleepInterval){
        interval=sleepInterval;
    }
    
    public void start(){
        mower=new Thread(this);
        mower.start();
        
    }
    
    public void stop(){
        runningAtomicBoolean.set(false);
    }
    
    
    @Override
    public void run() {
        runningAtomicBoolean.set(true);
            while (runningAtomicBoolean.get()) {            
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interreupted");
                }
      //          playBoardJFrame.updateScreen("down");


//code here
        }
    }








}
