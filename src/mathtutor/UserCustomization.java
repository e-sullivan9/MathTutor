/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
/**
 *
 * @author
 */
public class UserCustomization extends javax.swing.JPanel {

    /**
     * Creates new form UserCustomization
     */
    private final Account account;
    private ArrayList<Stickers> stickerList;
    private JLabel[] favorites;
    private OnAction onAction;
    public UserCustomization(Account account) {
        initComponents();
        this.account = account;
        onAction = new OnAction();
        initLabels();
        loadModuleIconsFromDB();
        loadFavoriteIcons();
        
    }
    private void loadFavoriteIcons()
    {
       DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
       ArrayList<String> aList = con.getFavoriteStickers(account);
       System.out.println(aList.size());
       con.closeDBConnection();
       
   for(int i = 0; i < favorites.length; ++i)
       {
           if(!aList.get(i).equalsIgnoreCase("lock"))
           {
               favorites[i].setIcon(new ImageIcon(aList.get(i)));
           }
       }
       
    }
    private void loadModuleIconsFromDB()
    {
       DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
       ArrayList<Stickers> aList = con.getStickersForUser(account);
       
       con.closeDBConnection();
       
    }
    //private static final int ICON_HEIGHT = 100;
    //private static final int ICON_WIDTH = 100;
    private void initLabels()
    {
        //Make sure to display correct user icon later
        //jlabelUserIcon.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlabelUserIcon.setIcon(account.getIcon());
        
        //jlTFOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlTFOne.setName("jlTFOne");
       // jlTFTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlTFThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlTFFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlOTOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlOTTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlOTThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlOTFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlPKOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlPKTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlPKThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        //jlPKFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        
        jLabel2.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jLabel3.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jLabel4.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jLabel5.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        favorites = new JLabel[4];
        favorites[0] = jLabel2;
        favorites[1] = jLabel3;
        favorites[2] = jLabel4;
        favorites[3] = jLabel5;
        
        jButton1.addActionListener(onAction);
        
    }
    private class OnAction implements ActionListener
    {
        private JLabel selected;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == jButton1)
            {
                
            }
            else
            {
                
                if(selected != null)
                {
                    selected.setIcon(((JLabel)e.getSource()).getIcon());
                    selected = null;
                }
                else
                {
                    for(int i = 0; i < favorites.length; ++i)
                    {
                        if(e.getSource() != favorites[i])
                        {
                            selected = null;
                            JOptionPane.showMessageDialog(null,"You can only select a favorite icon!");
                        }
                    }
                }
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

        jLabel1 = new javax.swing.JLabel();
        jlabelUserIcon = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlTFOne = new javax.swing.JLabel();
        jlTFTwo = new javax.swing.JLabel();
        jlTFThree = new javax.swing.JLabel();
        jlTFFour = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlOTOne = new javax.swing.JLabel();
        jlOTTwo = new javax.swing.JLabel();
        jlOTThree = new javax.swing.JLabel();
        jlOTFour = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jlPKOne = new javax.swing.JLabel();
        jlPKTwo = new javax.swing.JLabel();
        jlPKThree = new javax.swing.JLabel();
        jlPKFour = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Stencil Std", 1, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Profile Icon");

        jlabelUserIcon.setText(" ");

        jLabel10.setFont(new java.awt.Font("Stencil Std", 1, 38)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Grade 3-4 Awards");

        jlTFOne.setToolTipText("");

        jLabel22.setFont(new java.awt.Font("Stencil Std", 1, 38)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Grade 1-2 Awards");

        jLabel34.setFont(new java.awt.Font("Stencil Std", 1, 38)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Pre K - K Awards");

        jlPKOne.setToolTipText("");

        jlPKTwo.setToolTipText("");

        jlPKThree.setToolTipText("");

        jlPKFour.setToolTipText("");

        jButton1.setText("More");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlabelUserIcon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlTFOne)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlTFTwo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlTFThree)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlTFFour))
                            .addComponent(jLabel22)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlOTOne)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlOTTwo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlOTThree)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlOTFour))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlPKOne)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlPKTwo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlPKThree)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlPKFour)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jlabelUserIcon))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTFOne)
                    .addComponent(jlTFTwo)
                    .addComponent(jlTFThree)
                    .addComponent(jlTFFour))
                .addGap(0, 0, 0)
                .addComponent(jLabel22)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlOTOne)
                    .addComponent(jlOTTwo)
                    .addComponent(jlOTThree)
                    .addComponent(jlOTFour))
                .addGap(0, 0, 0)
                .addComponent(jLabel34)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPKOne)
                    .addComponent(jlPKTwo)
                    .addComponent(jlPKThree)
                    .addComponent(jlPKFour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jlabelUserIcon.getAccessibleContext().setAccessibleName("jlUserIcon");
        jlPKFour.getAccessibleContext().setAccessibleName("jlPKFive");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jlOTFour;
    private javax.swing.JLabel jlOTOne;
    private javax.swing.JLabel jlOTThree;
    private javax.swing.JLabel jlOTTwo;
    private javax.swing.JLabel jlPKFour;
    private javax.swing.JLabel jlPKOne;
    private javax.swing.JLabel jlPKThree;
    private javax.swing.JLabel jlPKTwo;
    private javax.swing.JLabel jlTFFour;
    private javax.swing.JLabel jlTFOne;
    private javax.swing.JLabel jlTFThree;
    private javax.swing.JLabel jlTFTwo;
    private javax.swing.JLabel jlabelUserIcon;
    // End of variables declaration//GEN-END:variables
}
