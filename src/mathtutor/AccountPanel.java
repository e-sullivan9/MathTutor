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

/**
 *
 * @author Eric Sullivan
 */
public class AccountPanel extends JPanel{
    Font myFont;
    JLabel name, accountPic, section, back, help;
    ArrayList<JLabel> sticker;
    JPanel top, bot, buttons, account;
    private Account userAccount;
    private Login frame;
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
    public void update(){
        remove(bot);
        buildBot();
        add(bot,BorderLayout.CENTER);
        frame.repaint();
        frame.pack();
    }

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

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
    


}
