/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisSwingPackage;

import PlayBoardAndShapes.PlayBoard;
import PlayBoardAndShapes.ThreadMovingDown;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 *
 * @author Jozef
 */ 
public class PlayBoardJFrame extends javax.swing.JFrame {

    /**
     * Playboard
     */
    private PlayBoard playBoard=new PlayBoard();
           
    Action downArrow;
    Action rightArrow;
    Action leftArrow;
    Action rotateShape;
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
    
    
    
    //Vlakno threadForRepainting;
    /**
     * Creates new form MainJFrame
     */
    public PlayBoardJFrame() {
        initComponents();
         
        downArrow=new MoveDownBindingAction();
        rightArrow=new MoveRightBindingAction();
        leftArrow=new MoveLeftBindingAction();
        rotateShape=new RotateShapeBindingAction();
        
        /*jPanel1.getInputMap().put(KeyStroke.getKeyStroke("UP"), "downArrow");
        jPanel1.getActionMap().put("downArrow", downArrow);
        jPanel1.getInputMap().allKeys();
        jPanel1.getActionMap().allKeys();*/
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
        startJButton.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "spaceKeyToRotate");
        startJButton.getActionMap().put("spaceKeyToRotate", rotateShape);
        
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


        this.thread1111=new AutomaticMovingDownThread(this.speed);

        
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     *
     * @param direction
     */
    public void updateScreen(String direction){
            playBoard.inputShapeToPlayboard(direction);
            displayScoreJLabel.setText(Integer.toString(playBoard.getScore()));//shows actual score
            scoreJLabel.setText("Score");
            playBoardJPanel.repaint();
            

    }
    
    /**
     *
     */
    public class MoveDownBindingAction extends AbstractAction{

        /**
         *Moving down
         * @param e Action event of moving down
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("down");
        }
    }
    
    /**
     *
     */
    public class MoveRightBindingAction extends AbstractAction{

        /**
         * Moving right
         * @param e Action event of moving right
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("right");

        }
    }
        
    /**
     *
     */
    public class MoveLeftBindingAction extends AbstractAction{

        /**
         *Moving left
         * @param e Action event of moving right
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("left");

        }
    }

    /**
     *Class for rotate a shape.
     */
    public class RotateShapeBindingAction extends AbstractAction{

        /**
         *Rotates a shape.
         * @param e Action event for rotate a shape
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            playBoard.rotateAnyShape();
            playBoardJPanel.repaint();
        }
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
        playBoardJPanel = new TetrisSwingPackage.Background(playBoard);
        startJButton = new javax.swing.JButton();
        restartjButton = new javax.swing.JButton();
        displayScoreJLabel = new javax.swing.JLabel();
        scoreJLabel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(800, 50));
        setLocationByPlatform(true);

        playBoardJPanel.setRequestFocusEnabled(false);
        playBoardJPanel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout playBoardJPanelLayout = new javax.swing.GroupLayout(playBoardJPanel);
        playBoardJPanel.setLayout(playBoardJPanelLayout);
        playBoardJPanelLayout.setHorizontalGroup(
            playBoardJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        playBoardJPanelLayout.setVerticalGroup(
            playBoardJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
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

        displayScoreJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        displayScoreJLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playBoardJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restartjButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startJButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(scoreJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displayScoreJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayScoreJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scoreJLabel))
                        .addGap(18, 18, 18)
                        .addComponent(playBoardJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(startJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(restartjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(49, 49, 49))))
        );

        restartjButton.getAccessibleContext().setAccessibleParent(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startJButtonActionPerformed
                   startJButton.setVisible(true);
                   // startJButton.setEnabled(false);
        //restartjButton.enableInputMethods(false);
                    //restartjButton.setVisible(true);
                    restartjButton.setEnabled(true);

                   
                   // restartjButton.setVisible(true);
;
                    playBoard.setRestartGame(false);
                    updateScreen("down");

                    jComboBox1.setEnabled(false);

                    this.thread1111=new AutomaticMovingDownThread(this.speed);
                    thread1111.start();
                     
                     
    }//GEN-LAST:event_startJButtonActionPerformed

    private void restartjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartjButtonActionPerformed
                            startJButton.setVisible(true);
                           //startJButton.setEnabled(true);

                             //restartjButton.setVisible(false);
                             //restartjButton.setEnabled(false);
                            //restartjButton.enableInputMethods(false);

                             jComboBox1.setEnabled(true);
                        //automaticMovingDownThread.interrupt();
        thread1111.stop();
        try {
            Thread.sleep(1000);//this prevents to make duplicite threats by pushing startButton
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayBoardJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        playBoard.setPushNewShape(true); //new shape must come
        playBoard.setRestartGame(true); // main algoritmus knows that it is occation when it is not new game but only restart.

        playBoard.deleteScreenBorders();
        repaint();
        //startJButtonActionPerformed(evt);
    }//GEN-LAST:event_restartjButtonActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
           this.speed=1000-100*Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()));//takes value of selected item and 
           this.speedLevel=Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()));;
           playBoard.setSpeedBonusFromSpeedLevel(speedLevel);
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

                updateScreen("down");
                
        }
    }








}
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel displayScoreJLabel;
    private javax.swing.JComboBox<String> jComboBox1;
    public javax.swing.JPanel playBoardJPanel;
    private javax.swing.JButton restartjButton;
    private javax.swing.JLabel scoreJLabel;
    private javax.swing.JButton startJButton;
    // End of variables declaration//GEN-END:variables



}
