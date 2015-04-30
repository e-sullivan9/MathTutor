/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class allow the user to select a course/module to take
 * the Contains:
 * An Array list of clickable Panels
 * The ModuleSelectorLayer.
 * 2 Click able JButtons for cycling through all the modules
 * @author Eric Sullivan
 */
public class ModuleSelectorWindow extends javax.swing.JPanel {
    private ArrayList<Module> modules;
    private Login frame;
    private ModuleSelectorLayer layer;
    /**
     * Creates new form ModuleSelectorWindow
     */
    public ModuleSelectorWindow() {
        initComponents();
    }
    /**
     * 
     * @param grade
     * @param frame
     * @param parent 
     */
    public ModuleSelectorWindow(String grade, Login frame, ModuleSelectorLayer parent) {
        this.frame = frame;
        initComponents();
        layer = parent;
        modules = new ArrayList<>();
        next.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\forward.png"));
        prev.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\back.png"));
        next.addMouseListener(new NextPrev());
        prev.addMouseListener(new NextPrev());
        setPreferredSize(new Dimension(600, 600));
        if (grade.equals("Pre K - K")) {
            modules.add(new Module("PreK-K", "Coins"));
            modules.add(new Module("PreK-K", "Compare"));
            modules.add(new Module("PreK-K", "Counting"));
            modules.add(new Module("PreK-K", "Estimate"));
            modules.add(new Module("PreK-K", "Numbers"));
            modules.add(new Module("PreK-K", "Problems"));
            modules.add(new Module("PreK-K", "Sequences"));
            modules.add(new Module("PreK-K", "Wholes"));

            if (frame.getAccountPanel().getUserAccount().Complete("Prek K")) {
                JPanel pane = new JPanel(new GridLayout(1, 1, 10, 10));
                pane.setBackground(new Color(144, 210, 144));
                Module temp = new Module("PreK-K", "FinalTest");
                temp.addMouseListener(new Listener());
                pane.add(temp);
                jPanel1.add(pane);
            }
        }
        else if(grade.equals("Grade 1-2")) {
            modules.add(new Module("Grade 1-2", "Compare1"));
            modules.add(new Module("Grade 1-2", "Misc1"));
            modules.add(new Module("Grade 1-2", "Misc2"));
            modules.add(new Module("Grade 1-2", "Misc3"));
        }
        else if(grade.equals("Grade 3-4")){
            modules.add(new Module("Grade 3-4", "Fractions"));
            modules.add(new Module("Grade 3-4", "Misc1"));
            modules.add(new Module("Grade 3-4", "Misc2"));
            modules.add(new Module("Grade 3-4", "Misc3"));
        }
        JPanel pane = new JPanel(new GridLayout(2,2,10,10));
        pane.setBackground(new Color(144,210,144));
        for(int i=0;i<modules.size();i++){
            modules.get(i).addMouseListener(new Listener());
            pane.add(modules.get(i));
            if (i % 4 == 3 && i != 0) {
                    jPanel1.add(pane);
                    pane = new JPanel(new GridLayout(2,2,10,10));
                    pane.setBackground(new Color(144,210,144));
                }
        }
    }
    /**
     * 
     */
    public class Listener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if(((Module)e.getSource()).getjLabel1().getText().equals("FinalTest")){
                            layer.clipStop();
            frame.remove(layer);
            frame.getLastPane().push(layer);
            frame.setCurrentPane(new TestGeneral(((Module) e.getSource()).getjLabel1().getText(),((Module) e.getSource()).getGrade(), frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();
            }
            else{
            layer.clipStop();
            frame.remove(layer);
            frame.getLastPane().push(layer);
            frame.setCurrentPane(new ToTLayer(((Module) e.getSource()).getjLabel1().getText(),((Module) e.getSource()).getGrade(), frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();
        }
        }

        public void mouseEntered(MouseEvent e) {
            ((JPanel) e.getSource()).setBackground(new Color(255, 150, 15));
        }

        public void mouseExited(MouseEvent e) {
            ((JPanel) e.getSource()).setBackground(Color.GREEN);
        }

    }

    /**
     *
     */
    public class NextPrev extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == next) {
                ((CardLayout) jPanel1.getLayout()).next(jPanel1);
            }
            if (e.getSource() == prev) {
                ((CardLayout) jPanel1.getLayout()).previous(jPanel1);
            }

        }

        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == next) {
                jPanel2.setBackground(new Color(255, 150, 15));
            }
            if (e.getSource() == prev) {
                jPanel3.setBackground(new Color(255, 150, 15));
            }
        }

        public void mouseExited(MouseEvent e) {
            if (e.getSource() == next) {
                jPanel2.setBackground(new Color(144, 210, 144));
            }
            if (e.getSource() == prev) {
                jPanel3.setBackground(new Color(144, 210, 144));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        next = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        prev = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));

        jPanel1.setBackground(new java.awt.Color(144, 210, 144));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select Course");

        jPanel2.setBackground(new java.awt.Color(144, 210, 144));

        next.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(144, 210, 144));

        prev.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prev, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prev, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel next;
    private javax.swing.JLabel prev;
    // End of variables declaration//GEN-END:variables
}
