/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisSwingPackage;

import PlayBoardAndShapes.PlayBoard;
import sounds.SoundEffect;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


/**
 *
 * @author Jozef
 */ 
public class PlayBoardJFrame extends javax.swing.JFrame {

    /**
     * Playboard
     */
    private PlayBoard playBoardForPlayer1=new PlayBoard();
    private PlayBoard playBoardForPlayer2OrCPU=new PlayBoard();

    Action downArrow;
    Action rightArrow;
    Action leftArrow;
    Action rotateShapeEnter;
    
    Action downS;
    Action rightD;
    Action leftA;
    Action downSpacebar;
   // ThreadMovingDown threadMovingDown=new ThreadMovingDown("Druhé");
    private int speed;
    



    /*////make it much more better than for 1000 repeating cycle
    Thread automaticMovingDownThread = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
    
    
    
    try {
    Thread.sleep(speed);
    } catch (InterruptedException ex) {
    Logger.getLogger(PlayBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    updateScreen("down");
    }                }, "Druhe");
    */
    private int speedLevel;
    
    /*//AutomaticMovingDownThread automaticMovingDownThread1=new AutomaticMovingDownThread(1000);
    
    Thread t1;
    */
    
        AutomaticMovingDownThread thread1111;
    
        AutomaticMovingDownThread thread2222;
    
    
    //Vlakno threadForRepainting;
    /**
     * Creates new form MainJFrame
     */
    public PlayBoardJFrame() {
        initComponents();
        
        //First player
        downArrow=new PlayerMoveBindingAction(playBoardForPlayer1,"down",playBoardJPanelPlayer1,displayScoreJLabel1);
        rightArrow=new PlayerMoveBindingAction(playBoardForPlayer1,"right",playBoardJPanelPlayer1,displayScoreJLabel1);
        leftArrow=new PlayerMoveBindingAction(playBoardForPlayer1,"left",playBoardJPanelPlayer1,displayScoreJLabel1);
        rotateShapeEnter=new PlayerRotateBindingAction(playBoardForPlayer1,playBoardJPanelPlayer1);
        //Second player or CPU
        downS=new PlayerMoveBindingAction(playBoardForPlayer2OrCPU,"down",playBoardJPanelPlayer2OrCPU,displayScoreJLabel2);
        rightD=new PlayerMoveBindingAction(playBoardForPlayer2OrCPU,"right",playBoardJPanelPlayer2OrCPU,displayScoreJLabel2);
        leftA=new PlayerMoveBindingAction(playBoardForPlayer2OrCPU,"left",playBoardJPanelPlayer2OrCPU,displayScoreJLabel2);
        downSpacebar=new PlayerRotateBindingAction(playBoardForPlayer2OrCPU,playBoardJPanelPlayer2OrCPU);

        
        
        
        /*jPanel1.getInputMap().put(KeyStroke.getKeyStroke("UP"), "downArrow");
        jPanel1.getActionMap().put("downArrow", downArrow);
        jPanel1.getInputMap().allKeys();
        jPanel1.getActionMap().allKeys();*/
        
        //Player1
        //move down
        startJButton.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downArrow");
        startJButton.getActionMap().put("downArrow", downArrow);
        //move right
        startJButton.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightArrow");
        startJButton.getActionMap().put("rightArrow",rightArrow );
        //move left
        startJButton.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftArrow");
        startJButton.getActionMap().put("leftArrow", leftArrow);
        //rotate shape
        startJButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterToRotate");
        startJButton.getActionMap().put("enterToRotate", rotateShapeEnter);
        

        //Player2
        //move down
        startJButton.getInputMap().put(KeyStroke.getKeyStroke('s'), "downS");
        startJButton.getActionMap().put("downS", downS);
        //move right
        startJButton.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightD");
        startJButton.getActionMap().put("rightD",rightD );
        //move left
        startJButton.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftA");
        startJButton.getActionMap().put("leftA", leftA);
        
        //rotate shape
        startJButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "spaceKeyToRotate");
        startJButton.getActionMap().put("spaceKeyToRotate", downSpacebar);
        startJButton.getActionMap().allKeys();
        //threadForRepainting = new Vlakno("PrveVlakno");
        speed=1000;
        jComboBox1.setEnabled(true);
        this.speedLevel=1;
        
        //this.t1=new Thread(new AutomaticMovingDownThread(500));
        
        //this.thread1111=new AutomaticMovingDownThread(this.speed);
        
        startJButton.setVisible(true);
       // startJButton.setEnabled(true);
        restartjButton.setVisible(true);
        //restartjButton.setEnabled(false);


//        this.thread1111=new AutomaticMovingDownThread(this.speed);

       
    }
    
    
    
    
    
    /**
     *
     */
    public class PlayerMoveBindingAction extends AbstractAction{

        private PlayBoard playboardOfCurrentPlayer;
        private JPanel playBoardJPanelForCurrentPlayer;
        private JLabel displayScoreJLabelForCurrentPlayer;
        private String direction;
        
        public PlayerMoveBindingAction(PlayBoard playboardOfCurrentPlayer,String direction,JPanel playBoardJPanelForCurrentPlayer,JLabel displayScoreJLabelForCurrentPlayer) {
        this.direction=direction;
        this.playboardOfCurrentPlayer=playboardOfCurrentPlayer;
        this.playBoardJPanelForCurrentPlayer=playBoardJPanelForCurrentPlayer;
        this.displayScoreJLabelForCurrentPlayer=displayScoreJLabelForCurrentPlayer;
        }

        /**
         *Moving left
         * @param e Action event of moving right
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen(playboardOfCurrentPlayer,direction,playBoardJPanelForCurrentPlayer,displayScoreJLabelForCurrentPlayer);
            playBoardJPanelForCurrentPlayer.repaint();
        }
    }
    
    /**
     *Class for rotate a shape.
     */
    public class PlayerRotateBindingAction extends AbstractAction{
         private PlayBoard playboardOfCurrentPlayer;
            private JPanel playBoardJPanelForCurrentPlayer;
        
        public PlayerRotateBindingAction(PlayBoard playboardOfCurrentPlayer,JPanel playBoardJPanelForCurrentPlayer) {
            this.playboardOfCurrentPlayer=playboardOfCurrentPlayer;
            this.playBoardJPanelForCurrentPlayer=playBoardJPanelForCurrentPlayer;  
        }

        /**
         *Rotates a shape.
         * @param e Action event for rotate a shape
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            playboardOfCurrentPlayer.rotateAnyShape();
              SoundEffect.ROTATE.play(); //play a sound of rotate movement
            playBoardJPanelForCurrentPlayer.repaint();
        }
    }
    
    
    

    /**
     *
     * @param direction
     */
    public void updateScreen(PlayBoard playboardOfCurrentPlayer,String direction,JPanel playBoardJPanelForCurrentPlayer,JLabel displayScoreJLabel){
            playboardOfCurrentPlayer.inputShapeToPlayboard(direction);
            displayScoreJLabel.setText(Integer.toString(playboardOfCurrentPlayer.getScore()));//shows actual score
            playBoardJPanelForCurrentPlayer.repaint();
    }
    
    
    
    /*  public class Vlakno extends Thread{
    
    public Vlakno(String name) {
    super(name);
    }
    
    @Override
    public void run() {
    repaint();
    }
    
    }*/
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        playBoardJPanelPlayer2OrCPU = new TetrisSwingPackage.Background(playBoardForPlayer2OrCPU);
        startJButton = new javax.swing.JButton();
        restartjButton = new javax.swing.JButton();
        displayScoreJLabel2 = new javax.swing.JLabel();
        scoreJLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        playBoardJPanelPlayer1 = new TetrisSwingPackage.Background(playBoardForPlayer1);
        scoreJLabel1 = new javax.swing.JLabel();
        displayScoreJLabel1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(800, 50));
        setLocationByPlatform(true);

        playBoardJPanelPlayer2OrCPU.setRequestFocusEnabled(false);
        playBoardJPanelPlayer2OrCPU.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout playBoardJPanelPlayer2OrCPULayout = new javax.swing.GroupLayout(playBoardJPanelPlayer2OrCPU);
        playBoardJPanelPlayer2OrCPU.setLayout(playBoardJPanelPlayer2OrCPULayout);
        playBoardJPanelPlayer2OrCPULayout.setHorizontalGroup(
            playBoardJPanelPlayer2OrCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );
        playBoardJPanelPlayer2OrCPULayout.setVerticalGroup(
            playBoardJPanelPlayer2OrCPULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        startJButton.setBackground(new java.awt.Color(255, 255, 51));
        startJButton.setText("Start");
        startJButton.setMultiClickThreshhold(1L);
        startJButton.setRequestFocusEnabled(false);
        startJButton.setVerifyInputWhenFocusTarget(false);
        startJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startJButtonActionPerformed(evt);
            }
        });

        restartjButton.setBackground(new java.awt.Color(255, 0, 0));
        restartjButton.setText("Quit Game");
        restartjButton.setDefaultCapable(false);
        restartjButton.setFocusable(false);
        restartjButton.setMultiClickThreshhold(1L);
        restartjButton.setRequestFocusEnabled(false);
        restartjButton.setVerifyInputWhenFocusTarget(false);
        restartjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartjButtonActionPerformed(evt);
            }
        });

        displayScoreJLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        displayScoreJLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        scoreJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        scoreJLabel.setText("Score:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        jComboBox1.setRequestFocusEnabled(false);
        jComboBox1.setVerifyInputWhenFocusTarget(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        playBoardJPanelPlayer1.setRequestFocusEnabled(false);
        playBoardJPanelPlayer1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout playBoardJPanelPlayer1Layout = new javax.swing.GroupLayout(playBoardJPanelPlayer1);
        playBoardJPanelPlayer1.setLayout(playBoardJPanelPlayer1Layout);
        playBoardJPanelPlayer1Layout.setHorizontalGroup(
            playBoardJPanelPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        playBoardJPanelPlayer1Layout.setVerticalGroup(
            playBoardJPanelPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        scoreJLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        scoreJLabel1.setText("Score:");

        displayScoreJLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        displayScoreJLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setText("PLAYER2");

        jLabel2.setText("Player 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playBoardJPanelPlayer2OrCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(displayScoreJLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playBoardJPanelPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(restartjButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(displayScoreJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(startJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(restartjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayScoreJLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scoreJLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayScoreJLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scoreJLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playBoardJPanelPlayer2OrCPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(playBoardJPanelPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startJButtonActionPerformed
                    SoundEffect.START.play(); //play a sound of start button
                    startJButton.setVisible(true);
                   // startJButton.setEnabled(false);
        //restartjButton.enableInputMethods(false);
                    //restartjButton.setVisible(true);
                    restartjButton.setEnabled(true);

                   
                   // restartjButton.setVisible(true);
                    jComboBox1.setEnabled(false);

                    //first player
                    playBoardForPlayer1.setRestartGame(false);
                    updateScreen(playBoardForPlayer1,"down",playBoardJPanelPlayer2OrCPU,displayScoreJLabel2);
                    this.thread1111=new AutomaticMovingDownThread(playBoardForPlayer1,"down",playBoardJPanelPlayer2OrCPU,displayScoreJLabel1,this.speed);
                    thread1111.start();
                    

                    //second player or cpu 
                    playBoardForPlayer2OrCPU.setRestartGame(false);
                    updateScreen(playBoardForPlayer2OrCPU,"down",playBoardJPanelPlayer1,displayScoreJLabel1);
                    this.thread2222=new AutomaticMovingDownThread(playBoardForPlayer2OrCPU,"down",playBoardJPanelPlayer1,displayScoreJLabel2,this.speed);
                    thread2222.start();
                    
                    
                    
    }//GEN-LAST:event_startJButtonActionPerformed

    private void restartjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartjButtonActionPerformed
                            SoundEffect.QUIT.play(); //play a sound of start button

                            startJButton.setVisible(true);
                           //startJButton.setEnabled(true);

                             //restartjButton.setVisible(false);
                             //restartjButton.setEnabled(false);
                            //restartjButton.enableInputMethods(false);

                             jComboBox1.setEnabled(true);
                        //automaticMovingDownThread.interrupt();
        thread1111.stop();
                thread2222.stop();

        try {
            Thread.sleep(1000);//this prevents to make duplicite threats by pushing startButton
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        playBoardForPlayer1.setPushNewShape(true); //new shape must come
        playBoardForPlayer1.setRestartGame(true); // main algoritmus knows that it is occation when it is not new game but only restart.

        playBoardForPlayer1.deleteScreenBorders();
        repaint();
        
        playBoardForPlayer2OrCPU.setPushNewShape(true); //new shape must come
        playBoardForPlayer2OrCPU.setRestartGame(true); // main algoritmus knows that it is occation when it is not new game but only restart.

        playBoardForPlayer2OrCPU.deleteScreenBorders();
        repaint();
        //startJButtonActionPerformed(evt);
    }//GEN-LAST:event_restartjButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
           this.speed=1000-100*Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()));//takes value of selected item and 
           this.speedLevel=Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()));;
           playBoardForPlayer1.setSpeedBonusFromSpeedLevel(speedLevel);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    
    /**
     *Gets level of shape´s moving speed.
     * @return Level of shape´s moving speed, which is used also for counting current bonus in game.
     */
    public int getSpeedLevel() {
        return speedLevel;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayBoardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayBoardJFrame().setVisible(true);
            }
        });
    }

    
    
    
    
    
    /**
 *
 * @author Jozef
 */
public class AutomaticMovingDownThread implements Runnable{

    private Thread mower;
    private final AtomicBoolean runningAtomicBoolean=new AtomicBoolean(false);
    private int interval;
    private PlayBoard playboardOfCurrentPlayer;
    private JPanel playBoardJPanelForCurrentPlayer;
    private JLabel displayScoreJLabelForCurrentPlayer;
    
    public AutomaticMovingDownThread(PlayBoard playboardOfCurrentPlayer,String direction,JPanel playBoardJPanelForCurrentPlayer,JLabel displayScoreJLabelForCurrentPlayer,int sleepInterval){
        this.interval=sleepInterval;
        this.playboardOfCurrentPlayer=playboardOfCurrentPlayer;
        this.playBoardJPanelForCurrentPlayer=playBoardJPanelForCurrentPlayer;
        this.displayScoreJLabelForCurrentPlayer=displayScoreJLabelForCurrentPlayer;
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
                //player1
                updateScreen(playboardOfCurrentPlayer,"down",playBoardJPanelForCurrentPlayer,displayScoreJLabelForCurrentPlayer);
                
                
        }
    }








}
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel displayScoreJLabel1;
    private javax.swing.JLabel displayScoreJLabel2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JPanel playBoardJPanelPlayer1;
    public javax.swing.JPanel playBoardJPanelPlayer2OrCPU;
    private javax.swing.JButton restartjButton;
    private javax.swing.JLabel scoreJLabel;
    private javax.swing.JLabel scoreJLabel1;
    private javax.swing.JButton startJButton;
    // End of variables declaration//GEN-END:variables



}
