/*Learn-2-Math team:
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

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This is the account panel on the left side of each frame
 * 
 */
public class AccountPanel extends JPanel{
    Font myFont;
    JLabel name, accountPic, section, back, help;
    ArrayList<JLabel> sticker;
    JPanel top, bot, buttons, account;
    private Account userAccount;
    private Login frame;
    
    /**
     * account panel constructor
     */
    public AccountPanel(){
        myFont = new Font("Comic Sans MS", Font.BOLD,50);      
       
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setSize(200, 600);
        buildTop();
        buildBot();
        buildButtons();
        add(top,BorderLayout.NORTH);
        add(bot,BorderLayout.CENTER);
	add(buttons,BorderLayout.SOUTH);
        
    }
    /**
     * account panel constructor
     * @param userAccount
     * @param frame 
     */
    public AccountPanel(Account userAccount,Login frame){
        this.frame = frame;
        this.userAccount = userAccount;
        if(userAccount.getUsername().length()<8){
            myFont = new Font("Comic Sans MS", Font.BOLD,50);
        }
        else if(userAccount.getUsername().length()>=8){
            myFont = new Font("Comic Sans MS", Font.BOLD,18);
        }
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setSize(200, 600);
        buildTop();
        buildBot();
        buildButtons();
        add(top,BorderLayout.NORTH);
        add(bot,BorderLayout.CENTER);
	add(buttons,BorderLayout.SOUTH);
    }
    /**
     * builds the top portion which is the name of the user
     */
    private void buildTop() {
        top = new JPanel(new BorderLayout());
        top.setBackground(new Color(144,210,144));
        
        name = new JLabel(userAccount.getUsername(),JLabel.CENTER);
        name.setForeground(Color.WHITE);
        name.setFont(myFont);
        name.addMouseListener(new ImageHandler());
        accountPic = new JLabel(userAccount.getIcon());
        
        section = new JLabel("Hall of Fame", JLabel.CENTER);
        myFont = new Font("Comic Sans MS", Font.BOLD,25);
        section.setForeground(Color.WHITE);
        section.setFont(myFont);
        
        top.add(name,BorderLayout.NORTH);
        top.add(accountPic,BorderLayout.CENTER);
        top.add(section,BorderLayout.SOUTH);
    } 
    /**
     * builds the bottom portion of the account panel which is the user favorites
     * in a grid
     */
    private void buildBot() {
        bot = new JPanel(new GridLayout(3,2,10,10));
        sticker = new ArrayList<>();
        bot.setBackground(new Color(144,210,144));
        ArrayList<String> favorites = userAccount.getFavorites();
        for(int i = 0;i<4;i++){
            sticker.add(new JLabel(new ImageIcon(favorites.get(i))));
            sticker.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            bot.add(sticker.get(i));
        }
        
    }
    /**
     * updates the favorite part of the frame if they user changed it from the hall of fame
     * 
     */
    public void update(){
        remove(bot);
        buildBot();
        add(bot,BorderLayout.CENTER);
        frame.repaint();
        frame.pack();
    }

    /**
     * builds the back and help buttons on the panel and adds listeners
     */
    private void buildButtons() {
        buttons = new JPanel(new GridLayout(1,2,25,10));
        buttons.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        buttons.setBackground(new Color(144,210,144));
        
        back = new JLabel(new ImageIcon(".\\Buttons\\Back-large.png"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addMouseListener(new ImageHandler());
        
        help = new JLabel(new ImageIcon(".\\Buttons\\Help.png"));
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.addMouseListener(new ImageHandler());
        buttons.add(back);
        buttons.add(help);
    }
    public class ImageHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e)  {
            if(e.getSource()==back&&!frame.getLastPane().isEmpty()){
                frame.getAccountPanel().update();
                ((HelpLayerAbstract)frame.getCurrentPane()).clipStop();
                frame.remove(frame.getCurrentPane());
                frame.setCurrentPane(frame.getLastPane().pop());
                frame.add(frame.getCurrentPane());
                frame.repaint();
                frame.pack();
            }
            if(e.getSource()==help){
                ((HelpLayerAbstract)frame.getCurrentPane()).help();
            }
            if(e.getSource()==name){
            frame.remove(frame.getCurrentPane());
            frame.getLastPane().push(frame.getCurrentPane());
            frame.setCurrentPane(new UserCustomizationLayer(userAccount,frame));
            frame.add(frame.getCurrentPane());
            frame.repaint();
            frame.pack();
            }
        }
}

    /**
     * gets the user account
     * @return 
     */
    public Account getUserAccount() {
        return userAccount;
    }
/**
 * sets the user account
 * @param userAccount 
 */
    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
    


}
