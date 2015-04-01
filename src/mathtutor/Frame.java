/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Eric Sullivan
 */
public class Frame extends JFrame{
    AccountPanel p;
    static TestGeneral currentPanel;
    public Frame(){
        super();
        p = new AccountPanel();
        currentPanel  = new TestGeneral("Counting-Questions.txt");
        setLayout(new BorderLayout());
        add(p,BorderLayout.WEST);
        add(currentPanel,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        pack();
        setVisible(true);
        setResizable(false);
    
}
public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
          new Frame();
      }
    });
}
}