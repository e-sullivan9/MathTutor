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

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
    private JLabel[] favorites;
    private final OnAction onAction;
    private final OnMouse onMouse;
    private ArrayList<JLabel> pk;
    private ArrayList<JLabel> ot;
    private ArrayList<JLabel> tf;
    private ArrayList<Stickers> stickerList = null;
    private Login frame;

    public UserCustomization(Account account,Login frame) {
        initComponents();
        this.frame= frame;
        this.account = account;
        onAction = new OnAction();
        onMouse = new OnMouse();
        initLabels();
        loadModuleIconsFromDB();
        loadFavoriteIcons();

    }
    /*
    *Loads the user's favorite icons from the database
    *This method is called automatically from the constructor
    */
    private void loadFavoriteIcons() {
        DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
        ArrayList<String> aList = con.getFavoriteStickers(account);
        con.closeDBConnection();

        for (int i = 0; i < favorites.length; ++i) {
            favorites[i].setIcon(new ImageIcon(aList.get(i)));
        }

    }
    /*
    *Loads the users completed module stickers from the database
    *This method is called from the constructor
    *The user will also be able to set favorite icons from this list
    */
    private void loadModuleIconsFromDB() {
        DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
        stickerList = con.getStickersForUser(account);
        con.closeDBConnection();
        int i = 0;
        int j = 0;

        for (i = 0; i < stickerList.size(); ++i) {

            if (stickerList.get(i).getGrade().equals("PreK-K")) {
                if (j < 4) {
                    pk.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                    stickerList.get(i).setVisibility(true);
                    ++j;
                }
            }
        }
        j = 0;
        for (i = 0; i < stickerList.size(); ++i) {
            if (stickerList.get(i).getGrade().equalsIgnoreCase("Grade 1-2")) {
                if (j < 4) {
                    ot.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                    stickerList.get(i).setVisibility(true);
                    ++j;
                }
            }
        }
        j = 0;
        for (i = 0; i < stickerList.size(); ++i) {
            if (stickerList.get(i).getGrade().equalsIgnoreCase("Grade 3-4")) {
                if (j < 4) {
                    tf.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                    stickerList.get(i).setVisibility(true);
                    ++j;
                }
            }
        }
    }

    //private static final int ICON_HEIGHT = 100;
    //private static final int ICON_WIDTH = 100;
    /*
    *Initialize all the labels to a lock icon on start up.
    *This method is called from the constructor
    *These icons are subject to change depending on the user's completed modules
    */
    private void initLabels() {
        //Make sure to display correct user icon later
        //jlabelUserIcon.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlabelUserIcon.setIcon(account.getIcon());

        //jlTFOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));

        jlTFOne.addMouseListener(onMouse);
        // jlTFTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlTFTwo.addMouseListener(onMouse);
        //jlTFThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlTFThree.addMouseListener(onMouse);
        //jlTFFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlTFFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlTFFour.addMouseListener(onMouse);
        tf = new ArrayList<>();
        tf.add(jlTFOne);
        tf.add(jlTFTwo);
        tf.add(jlTFThree);
        tf.add(jlTFFour);
        //jlOTOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlOTOne.addMouseListener(onMouse);
        //jlOTTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlOTTwo.addMouseListener(onMouse);
        //jlOTThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlOTThree.addMouseListener(onMouse);
        //jlOTFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlOTFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlOTFour.addMouseListener(onMouse);
        ot = new ArrayList<>();
        ot.add(jlOTOne);
        ot.add(jlOTTwo);
        ot.add(jlOTThree);
        ot.add(jlOTFour);

        //jlPKOne.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKOne.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlPKOne.addMouseListener(onMouse);
        //jlPKTwo.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKTwo.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlPKTwo.addMouseListener(onMouse);
        //jlPKThree.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKThree.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlPKThree.addMouseListener(onMouse);
        //jlPKFour.setSize(ICON_HEIGHT,ICON_WIDTH);
        jlPKFour.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\lock.png"));
        jlPKFour.addMouseListener(onMouse);
        pk = new ArrayList<>();
        pk.add(jlPKOne);
        pk.add(jlPKTwo);
        pk.add(jlPKThree);
        pk.add(jlPKFour);
        jLabel2.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\bear.png"));
        jLabel3.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\elephant.png"));
        jLabel4.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\pig.png"));
        jLabel5.setIcon(new ImageIcon(".\\Icons\\Icons\\icons\\fox.png"));
        favorites = new JLabel[4];
        favorites[0] = jLabel2;
        favorites[1] = jLabel3;
        favorites[2] = jLabel4;
        favorites[3] = jLabel5;

        jButton1.addActionListener(onAction);
        for (int i = 0; i < favorites.length; ++i) {
            favorites[i].addMouseListener(onMouse);
        }
    }

    public String addExtraSlash(String path) {
        char curr[] = path.toCharArray();
        String temp = "";
        for (int i = 0; i < path.length(); ++i) {
            temp += curr[i];
            if (curr[i] == '\\') {
                temp += '\\';

            }
        }
        return temp;

    }
    
    private class OnMouse implements MouseListener {

        private JLabel selected;

        @Override
        public void mouseClicked(MouseEvent e) {
            {
                /*
                *A user can click on a current favorite Icon they'd like to switch
                *With one from their completed module stickers.
                *They can then click on the target icon they'd like to switch their 
                *current favorite to.
                *The 'selected' icon is the icon the user is willing to swap with their target.
                */
                if (selected != null) {
                    for (int i = 0; i < favorites.length; ++i) {
                        if (((JLabel) e.getSource()).getIcon().toString().equals(favorites[i].getIcon().toString())) {
                            selected = null;
                        }
                    }
                    if (selected == null) {
                        JOptionPane.showMessageDialog(null, "You already have this icon as your favorite!");
                    } else {
                        selected.setIcon(((JLabel) e.getSource()).getIcon());
                        DataBaseUserConnector con = new DataBaseUserConnector("MathTutorDB", "TutorAdmin", "Tut0r4dm1n");
                        ArrayList<String> aList = new ArrayList<>();
                        for (int i = 0; i < favorites.length; ++i) {
                            aList.add(addExtraSlash(favorites[i].getIcon().toString()));
                        }
                        con.updateFavoriteIcons(account, aList);
                        frame.getAccountPanel().update();
                        con.closeDBConnection();
                    }
                    selected = null;
                } else {
                    for (int i = 0; i < favorites.length; ++i) {
                        if (e.getSource() == favorites[i]) {
                            selected = favorites[i];
                        }
                    }
                    if (selected == null) {
                        JOptionPane.showMessageDialog(null, "You can only select a favorite icon!");
                    }

                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private class OnAction implements ActionListener {

        /*
        *The user can hit the "More" button to display the rest of their icons.
        *The button will toggle between icons currently visible and not visible
        *The icons will be displayed four at a time.
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            int j = 0;

            for (i = 0; i < stickerList.size(); ++i) {

                if (stickerList.get(i).getGrade().equals("PreK-K")) {
                    if (!stickerList.get(i).getVisibility()) {
                        if (j < 4) {
                            stickerList.get(i).setVisibility(true);
                            pk.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                            ++j;
                        }
                    } else {
                        stickerList.get(i).setVisibility(false);
                    }
                }
            }
            j = 0;
            for (i = 0; i < stickerList.size(); ++i) {
                if (stickerList.get(i).getGrade().equalsIgnoreCase("Grade 1-2")) {
                    if (stickerList.get(i).getVisibility()) {
                        if (j < 4) {
                            stickerList.get(i).setVisibility(true);
                            ot.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                            ++j;
                        }
                    } else {
                        stickerList.get(i).setVisibility(false);
                    }
                }
            }
            j = 0;
            for (i = 0; i < stickerList.size(); ++i) {
                if (stickerList.get(i).getGrade().equalsIgnoreCase("Grade 3-4")) {
                    if (!stickerList.get(i).getVisibility()) {
                        if (j < 4) {
                            stickerList.get(i).setVisibility(true);
                            tf.get(j).setIcon(new ImageIcon(stickerList.get(i).getReward()));
                            ++j;
                        }
                    } else {
                        stickerList.get(i).setVisibility(false);
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
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(144, 210, 144));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Profile Icon");

        jlabelUserIcon.setText(" ");
        jlabelUserIcon.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 38)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Grade 3-4 Awards");

        jlTFOne.setToolTipText("");
        jlTFOne.setPreferredSize(new java.awt.Dimension(75, 75));

        jlTFTwo.setPreferredSize(new java.awt.Dimension(75, 75));

        jlTFThree.setPreferredSize(new java.awt.Dimension(75, 75));

        jlTFFour.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 38)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Grade 1-2 Awards");

        jlOTOne.setPreferredSize(new java.awt.Dimension(75, 75));

        jlOTTwo.setPreferredSize(new java.awt.Dimension(75, 75));

        jlOTThree.setPreferredSize(new java.awt.Dimension(75, 75));

        jlOTFour.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel34.setFont(new java.awt.Font("Comic Sans MS", 1, 38)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Pre K - K Awards");

        jlPKOne.setToolTipText("");
        jlPKOne.setPreferredSize(new java.awt.Dimension(75, 75));

        jlPKTwo.setToolTipText("");
        jlPKTwo.setPreferredSize(new java.awt.Dimension(75, 75));

        jlPKThree.setToolTipText("");
        jlPKThree.setPreferredSize(new java.awt.Dimension(75, 75));

        jlPKFour.setToolTipText("");
        jlPKFour.setPreferredSize(new java.awt.Dimension(75, 75));

        jButton1.setText("More");

        jLabel2.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel3.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel4.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel5.setPreferredSize(new java.awt.Dimension(75, 75));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Hall of Fame");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlTFOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTFTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTFThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTFFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jlOTOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlOTTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlOTThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlOTFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jlabelUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlPKOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlPKTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlPKThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlPKFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlabelUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel10)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTFOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTFTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTFThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTFFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlOTFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlOTOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlOTTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlOTThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlPKOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPKTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPKThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPKFour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(57, 57, 57))
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
    private javax.swing.JLabel jLabel6;
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
