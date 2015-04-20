/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Eric Sullivan
 */
public class CreateNewUser extends javax.swing.JPanel {

    /**
     * Creates new form CreateNewUser
     */
    public CreateNewUser() {
        initComponents();
    }

    public CreateNewUser(Login frame, CreateNewUserLayer layer) {

        con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
        this.layer = layer;
        this.frame = frame;
        avatarList = new ArrayList<>();
        initComponents();
        buildList();
        selectedIcon.setIcon(avatarList.get(0));
        back.addMouseListener(new Listener());
        next.addMouseListener(new Listener());
        picNext.addMouseListener(new Listener());
        prevPic.addMouseListener(new Listener());
    }

    public void buildList() {
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\bear.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\duck.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\elephant.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\fox.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\lion.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\penguin.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\pig.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\rabbit.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\racoon.png"));
        avatarList.add(new ImageIcon(".\\Icons\\Icons\\icons\\turtle.png"));
    }

    public String addExtraSlash(String path) {
        char curr[] = path.toCharArray();
        String temp = "";
        int k = 0;
        for (int i = 0; i < path.length(); ++i) {
            temp += curr[i];
            if (curr[i] == '\\') {
                temp += '\\';

            }
        }
        return temp;

    }

    public class Listener extends MouseAdapter {
        int place=0;
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == back) {
                frame.getContentPane().removeAll();
                frame.setCurrentPane(frame.getLastPane().pop());
                frame.add(frame.getCurrentPane());
                frame.repaint();
                frame.pack();
            }
            if(e.getSource()==picNext){
                place = (place+1)%9;
                selectedIcon.setIcon(avatarList.get(place));
            }
            if(e.getSource()==prevPic){
                place = (place-1);
                if(place<0){
                    place =9;
                }
                selectedIcon.setIcon(avatarList.get(place));
            }
            if (e.getSource() == help) {

            }
            if (e.getSource() == next) {
                String errors = "";
                if (firstNameTF.getText().isEmpty()) {
                    errors += "Please Enter your first name\n";
                }
                if (lastNameTF.getText().isEmpty()) {
                    errors += "Please Enter your last name\n";
                }
                if (passwordTF.getText().isEmpty()) {
                    errors += "Please Enter a password\n";
                }
                if (retypeTF.getText().isEmpty()) {
                    errors += "Please retype a password\n";
                }
                if (!retypeTF.getText().equals(passwordTF.getText())) {
                    errors += "Passwords do not match\n";
                }
                if (errors.isEmpty()) {
                    String table = "users";
                    String pid = firstNameTF.getText() + " " + lastNameTF.getText().charAt(0);
                    String entry = "'" + pid + "','" + firstNameTF.getText() + "','" + lastNameTF.getText() + "','" + passwordTF.getText() + "','" + addExtraSlash(((ImageIcon) selectedIcon.getIcon()).toString()) + "'";
                    JOptionPane.showMessageDialog(null, "" + pid + " Created");
                    con.insertIntoDB(table, entry);
                    con.closeDBConnection();
                    frame.getContentPane().removeAll();
                    BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS);
                    frame.setLayout(bl);
                    GradeChooserLayer chooser = new GradeChooserLayer(frame);
                    frame.add(new AccountPanel(new Account(pid, selectedIcon.getIcon()), chooser));
                    frame.add(chooser);
                    frame.repaint();
                    frame.pack();
                } else {
                    JOptionPane.showMessageDialog(null, errors);
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
        lastNameL = new javax.swing.JLabel();
        firstNameTF = new javax.swing.JTextField();
        firstNameL = new javax.swing.JLabel();
        passwordL = new javax.swing.JLabel();
        passwordTF = new javax.swing.JTextField();
        lastNameTF = new javax.swing.JTextField();
        retypeTF = new javax.swing.JTextField();
        retypeL = new javax.swing.JLabel();
        next = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        help = new javax.swing.JLabel();
        selectedIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        picNext = new javax.swing.JLabel();
        prevPic = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 50)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("New User");

        lastNameL.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lastNameL.setForeground(java.awt.Color.white);
        lastNameL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameL.setText("Last Name");

        firstNameTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        firstNameL.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        firstNameL.setForeground(java.awt.Color.white);
        firstNameL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstNameL.setText("First Name");

        passwordL.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        passwordL.setForeground(java.awt.Color.white);
        passwordL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordL.setText("Password");

        passwordTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lastNameTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        retypeTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        retypeL.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        retypeL.setForeground(java.awt.Color.white);
        retypeL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        retypeL.setText("Re-type Password");

        next.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        next.setText("go");
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eric Sullivan\\Desktop\\MathTutor\\Back.png")); // NOI18N
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        help.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        help.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eric Sullivan\\Desktop\\MathTutor\\help.png")); // NOI18N
        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        selectedIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Select Avatar");

        picNext.setText("next");

        prevPic.setText("prev");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lastNameL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstNameTF)
                    .addComponent(firstNameL, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(passwordTF)
                    .addComponent(passwordL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastNameTF)
                    .addComponent(retypeL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(retypeTF))
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(prevPic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectedIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(picNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(firstNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lastNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(passwordL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(retypeL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(retypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(help, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectedIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(picNext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(prevPic, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private Login frame;
    private ArrayList<ImageIcon> avatarList;
    private CreateNewUserLayer layer;
    private DataBaseUserConnector con;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel firstNameL;
    private javax.swing.JTextField firstNameTF;
    private javax.swing.JLabel help;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lastNameL;
    private javax.swing.JTextField lastNameTF;
    private javax.swing.JLabel next;
    private javax.swing.JLabel passwordL;
    private javax.swing.JTextField passwordTF;
    private javax.swing.JLabel picNext;
    private javax.swing.JLabel prevPic;
    private javax.swing.JLabel retypeL;
    private javax.swing.JTextField retypeTF;
    private javax.swing.JLabel selectedIcon;
    // End of variables declaration//GEN-END:variables
}
