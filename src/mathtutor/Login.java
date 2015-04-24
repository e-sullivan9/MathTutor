/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.sql.*;
import java.util.Stack;

/**
 *
 * @author Eric Sullivan
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        setPreferredSize(new Dimension(800,620));
        initComponents();
        frame = this;
        lastPane = new Stack<>();
        currentPane = mainPane;
        loadAccounts();
        cl = new CardLayout();
        accountPane.setLayout(cl);
        jLabel3.addMouseListener(new NextPrev());
        jLabel4.addMouseListener(new NextPrev());
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    
    public JComponent getCurrentPane() {
        return currentPane;
    }

    public void setCurrentPane(JComponent currentPane) {
        this.currentPane = currentPane;
    }

    public Stack<JComponent> getLastPane() {
        return lastPane;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        accountPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPane.setBackground(new java.awt.Color(144, 210, 144));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Select User");

        accountPane.setBackground(java.awt.Color.white);
        accountPane.setPreferredSize(new java.awt.Dimension(600, 300));

        javax.swing.GroupLayout accountPaneLayout = new javax.swing.GroupLayout(accountPane);
        accountPane.setLayout(accountPaneLayout);
        accountPaneLayout.setHorizontalGroup(
            accountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        accountPaneLayout.setVerticalGroup(
            accountPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Exit");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Previos");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Next");
        jLabel4.setPreferredSize(new java.awt.Dimension(30, 15));

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPaneLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPaneLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(accountPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPaneLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // <editor-fold defaultstate="collapsed" desc="loadAccounts">        
    public void loadAccounts() {
        accountPanels = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MathTutorDB", "TutorAdmin", "Tut0r4dm1n"); //change password for it to work.
            String sql = "select * from Users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //set up for build of the mid or userSwitchPane
            String name;
            String icon;
            int page = 0;
            userSwitchPane usp = new userSwitchPane();

            while (rs.next()) {
                name = rs.getString("PID");
                icon = rs.getString("Icon");
                accountPanelForm apf = new accountPanelForm(name, icon);
                apf.addMouseListener(new AccountListener());
                accountPanels.add(apf);
            }

            accountPanels.add(new accountPanelForm("New Account", "sticker.png"));
            accountPanels.get(accountPanels.size() - 1).addMouseListener(new NewAccountListener());
            if (accountPanels.size() % 4 != 0) {
                int needed = 4 - accountPanels.size() % 4;
                for (int x = 0; x < needed; x++) {
                    JPanel temp = new JPanel();
                    temp.setBackground(new Color(144, 210, 144));
                    accountPanels.add(temp);
                    System.out.println(x);
                }
            }
            System.out.println(accountPanels.size());
            for (int i = 0; i < accountPanels.size(); i++) {
                System.out.println("here");
                usp.add(accountPanels.get(i));
                accountPane.add(usp);
                if (i % 4 == 3 && i != 0) {
                    System.out.println("added at " + i);
                    accountPane.add(usp);
                    usp = new userSwitchPane();
                }
            }

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="NewAccountListener">
    public class NewAccountListener extends MouseAdapter {
        
        public void mouseClicked(MouseEvent e){
            getContentPane().removeAll();
            setLayout(new GridLayout(1,1));
            lastPane.push(currentPane);
            currentPane = new CreateNewUserLayer(frame);
            getContentPane().add(currentPane);
            repaint();
            pack();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="AccountListener">
    public class AccountListener extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            ((accountPanelForm)e.getSource()).setBackground(new Color(255, 150, 15));
        }
        public void mouseExited(MouseEvent e) {
            ((accountPanelForm)e.getSource()).setBackground(Color.GREEN);
        }
        public void mouseClicked(MouseEvent e) {
            // System.out.println(((accountPanelForm)e.getSource()).getJLabelName());
            getContentPane().removeAll();
            getContentPane().setLayout(new GridLayout(1,1));
            lastPane.push(currentPane);
            currentPane = new PassEntryLayer(((accountPanelForm) e.getSource()).getJLabelName(), ((accountPanelForm) e.getSource()).getJLabelIcon(),frame);
            getContentPane().add(currentPane);
            repaint();
            pack();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="CardLayout Listener">
    public class NextPrev extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            if(e.getSource()==jLabel3){
                cl.previous(accountPane);
               // System.out.println("jLabel3 Clicked");
            }
            if(e.getSource()==jLabel4){
                cl.next(accountPane);
               // System.out.println("jLabel4 Clicked");
            }
        }
        
                
                
        
    }// </editor-fold>
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    
    
    
    CardLayout cl;
    Login frame;
    private ArrayList<JPanel> accountPanels;
    private JComponent currentPane;
    private Stack<JComponent> lastPane;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accountPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel mainPane;
    // End of variables declaration//GEN-END:variables
}
