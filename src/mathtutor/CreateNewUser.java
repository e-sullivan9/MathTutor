/*
*Learn-2-Math team:
 *  Eric Sullivan
 *  Tauseef Pirzada
 *  Leonid Melnikov
 *
 * Date Produced:
 *  4/2/15 - 4/30/15
 *
 *  Purpose of project:
 *The purpose of the project was to create a software that could be used by 
 *children aged 3-10 in Pre-k to Grade 4. The software is a Math tutoring software 
 *that will teach kids number sense and other important skills that they will use 
 *throughout life. Our goal was that the software be easy to learn because our clients 
 *would be mostly children, and that it helped teach the important concepts that they are
 *supposed to learn in each grade. This is meant to be a supplement to schoolwork and helps 
 *reinforce concepts that are learned in the class room. 
 */
package mathtutor;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This Class creates a new user by letting the user create a username and password. They can also select a avatar for their account.
 * Color scheme: blue, Orange and Green
 * 5 Click able JLabel help, back, next,prevPic, Picnext.
 * 7 JLabels that are used as titles
 * 2 textfields for First name and Last name
 * 2 Passwordfields for Password and Password confirmation
 * @author Eric Sullivan
 */
public class CreateNewUser extends javax.swing.JPanel {

    /**
     * Creates new form CreateNewUser
     */
    public CreateNewUser() {
        initComponents();
    }

    /**
     * Creates new form CreateNewUser and added components to the JPanel
     * Makes connection to the Database.
     * @param frame
     * @param layer 
     */
    public CreateNewUser(Login frame, CreateNewUserLayer layer) {

        con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
        this.layer = layer;
        this.frame = frame;
        avatarList = new ArrayList<>();
        setUpClip();
        initComponents();
        buildList();
        
        selectedIcon.setIcon(avatarList.get(0));
        back.addMouseListener(new Listener());
        back.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\back-large.png"));
        next.addMouseListener(new Listener());
        next.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\go.png"));
        help.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\help.png"));
        picNext.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\forward.png"));
        prevPic.setIcon(new ImageIcon(".\\Icons\\Icons\\Buttons\\back.png"));
        picNext.addMouseListener(new Listener());
        prevPic.addMouseListener(new Listener());
        help.addMouseListener(new Listener());
    }

    /**
     * Adds the selectable avatar images to a ArrayList.
     */
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

    /**
     * Fixes String escape character errors in a String
     *
     * @param path
     * @return
     */
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
    /**
     * This Listener control:
     * 5JPanels: back, picNext, prevPic, help, next.
     * 
     * back: stops and AudioClip on the parent class, removes the current Jpanels on the frame,
     * adds previous Jpanel back on the JFrame.
     * 
     * picNext: progresses forward through the ArrayList of avatar images and sets it to a JLabel
     * 
     * prevPic: progresses backward throught the ArrayList of avatar images and adds sets it to a JLabel
     * 
     * help: starts the audio clip for helping the user us the page.
     * 
     * next: checks the textfields for error and either shows a error popup or creates a new user and adds them to the database
     */
    public class Listener extends MouseAdapter {
        int place=0;
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == back) {
                layer.clipStop();
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
                layer.help();
            }
            if (e.getSource() == next) {
                String errors = "";
                // First Name
                if (firstNameTF.getText().isEmpty()) {
                    errors += "Please Enter your first name\n";
                }
                //Last Name
                if (lastNameTF.getText().isEmpty()) {
                    errors += "Please Enter your last name\n";
                }
                // Password
                if (jPasswordField1.getPassword().length==0) {
                    errors += "Please Enter a password\n";
                }
                //Retype
                if (jPasswordField2.getPassword().length==0) {
                    errors += "Please Retype the password\n";
                }
                //Matching
                if (!Arrays.equals(jPasswordField1.getPassword(),jPasswordField2.getPassword())) {
                    errors += "Passwords do not match\n";
                }//errors shown
                if (errors.isEmpty()) {
                    String table = "users";
                    String pid = firstNameTF.getText() + " " + lastNameTF.getText().charAt(0);
                    {
                        
                        int i = 0;
                        String temp = pid;
                        while(con.isPIDExistant(pid))
                        {
                            pid = temp;
                            pid+=Integer.toString(i);
                            ++i;
                        }
                    String pass = Xor.encrypt(new String(jPasswordField1.getPassword()));
                    String lock = "./Icons/Icons/icons/lock.png";
                    String entry = "'" + pid + "','" + firstNameTF.getText() + "','" + lastNameTF.getText() + "','" + pass + "','" + addExtraSlash(((ImageIcon) selectedIcon.getIcon()).toString()) + "','" + lock + "','" + lock + "','" + lock + "','" + lock + "'";

                    JOptionPane.showMessageDialog(null, "" + pid + " Created");
                    con.insertIntoDB(table, entry);
                    
                    con.closeDBConnection();
                    frame.getContentPane().removeAll();
                    BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS);
                    frame.setLayout(bl);
                    GradeChooserLayer chooser = new GradeChooserLayer(frame,0);
                    frame.setAccountPanel(new AccountPanel(new Account(pid, selectedIcon.getIcon()), frame));
                    frame.add(frame.getAccountPanel());
                    frame.add(chooser);
                    frame.repaint();
                    frame.pack();
                    layer.clipStop();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, errors);
                }

            }
        }
                    public void mouseEntered(MouseEvent e) {
            if (e.getSource() == back) {
                backPane.setBackground(new Color(255, 150, 15));
            }
            if (e.getSource() == next) {
                nextPane.setBackground(new Color(255, 150, 15));
            }
            if (e.getSource() == help) {
                helpPane.setBackground(new Color(255, 150, 15));
            }
        }
        //if exited they change to the normal color
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == back) {
                backPane.setBackground(new Color(144, 210, 144));
            }
            if (e.getSource() == next) {
                nextPane.setBackground(new Color(144, 210, 144));
            }
            if (e.getSource() == help) {
                helpPane.setBackground(new Color(144, 210, 144));
            }
        }
    }
        public synchronized void setUpClip() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    File yourFile = new File(".\\Help\\NewUser.wav");
                    AudioInputStream stream;
                    AudioFormat format;
                    DataLine.Info info;
                    //

                    stream = AudioSystem.getAudioInputStream(yourFile);
                    format = stream.getFormat();
                    info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    LineListener listener = new LineListener() {
                        public void update(LineEvent e) {
                            if (e.getType() == LineEvent.Type.STOP) {
                                clip.close();
                                setUpClip();
                            }
                        }
                    };
                    clip.addLineListener(listener);
                    clip.open(stream);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
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
        lastNameTF = new javax.swing.JTextField();
        retypeL = new javax.swing.JLabel();
        selectedIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        picNext = new javax.swing.JLabel();
        prevPic = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        backPane = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        helpPane = new javax.swing.JPanel();
        help = new javax.swing.JLabel();
        nextPane = new javax.swing.JPanel();
        next = new javax.swing.JLabel();

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

        lastNameTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        retypeL.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        retypeL.setForeground(java.awt.Color.white);
        retypeL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        retypeL.setText("Re-type Password");

        selectedIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Select Avatar");

        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPasswordField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        backPane.setBackground(new java.awt.Color(144, 210, 144));

        back.setBackground(new java.awt.Color(144, 210, 144));
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout backPaneLayout = new javax.swing.GroupLayout(backPane);
        backPane.setLayout(backPaneLayout);
        backPaneLayout.setHorizontalGroup(
            backPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backPaneLayout.setVerticalGroup(
            backPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backPaneLayout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        helpPane.setBackground(new java.awt.Color(144, 210, 144));

        help.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout helpPaneLayout = new javax.swing.GroupLayout(helpPane);
        helpPane.setLayout(helpPaneLayout);
        helpPaneLayout.setHorizontalGroup(
            helpPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        helpPaneLayout.setVerticalGroup(
            helpPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPaneLayout.createSequentialGroup()
                .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        nextPane.setBackground(new java.awt.Color(144, 210, 144));

        next.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout nextPaneLayout = new javax.swing.GroupLayout(nextPane);
        nextPane.setLayout(nextPaneLayout);
        nextPaneLayout.setHorizontalGroup(
            nextPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nextPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        nextPaneLayout.setVerticalGroup(
            nextPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nextPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(helpPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(prevPic, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectedIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(picNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(62, 62, 62))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lastNameL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(firstNameTF)
                                        .addComponent(firstNameL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passwordL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lastNameTF)
                                        .addComponent(retypeL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPasswordField1)
                                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(188, 188, 188)
                        .addComponent(nextPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firstNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(retypeL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(picNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(prevPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(selectedIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(helpPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nextPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );
    }// </editor-fold>//GEN-END:initComponents
    private Clip clip;
    private Login frame;
    private ArrayList<ImageIcon> avatarList;
    private CreateNewUserLayer layer;
    private DataBaseUserConnector con;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel backPane;
    private javax.swing.JLabel firstNameL;
    private javax.swing.JTextField firstNameTF;
    private javax.swing.JLabel help;
    private javax.swing.JPanel helpPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JLabel lastNameL;
    private javax.swing.JTextField lastNameTF;
    private javax.swing.JLabel next;
    private javax.swing.JPanel nextPane;
    private javax.swing.JLabel passwordL;
    private javax.swing.JLabel picNext;
    private javax.swing.JLabel prevPic;
    private javax.swing.JLabel retypeL;
    private javax.swing.JLabel selectedIcon;
    // End of variables declaration//GEN-END:variables
}
