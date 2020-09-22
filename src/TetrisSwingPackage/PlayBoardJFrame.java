/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisSwingPackage;

import PlayBoardAndShapes.PlayBoard;
import java.awt.event.ActionEvent;
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
        jButton1.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downArrow");
        jButton1.getActionMap().put("downArrow", downArrow);
        //move right
        jButton1.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightArrow");
        jButton1.getActionMap().put("rightArrow",rightArrow );
        //move left
        jButton1.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftArrow");
        jButton1.getActionMap().put("leftArrow", leftArrow);
        
        //rotate shape
        jButton1.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "spaceKeyToRotate");
        jButton1.getActionMap().put("spaceKeyToRotate", rotateShape);
        
        //threadForRepainting = new Vlakno("PrveVlakno");
        
        
        
        
        
        
    }

    
    public void updateScreen(String direction){
            playBoard.inputShapeToPlayboard(direction);
            jPanel1.repaint();
            

    }
    
    public class MoveDownBindingAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("down");
        }
    }
    
       
    public class MoveRightBindingAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("right");

        }
    }
        
    public class MoveLeftBindingAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            updateScreen("left");

        }
    }

    
    public class RotateShapeBindingAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            playBoard.rotateAnyShape();
            jPanel1.repaint();
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
        jPanel1 = new TetrisSwingPackage.Background(playBoard);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris");
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(800, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("restart");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(163, 163, 163))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
                     updateScreen("down");

           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                            updateScreen("right");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     
//add your elements

playBoard.deleteScreenBorders();
        repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}