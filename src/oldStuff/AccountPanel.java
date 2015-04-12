/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oldStuff;

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
    private void buildTop() {
        top = new JPanel(new BorderLayout());
        top.setBackground(new Color(144,210,144));
        
        name = new JLabel("John!",JLabel.CENTER);
        name.setForeground(Color.WHITE);
        name.setFont(myFont);
        
        accountPic = new JLabel(new ImageIcon("pirate-clipart.png"));
        
        section = new JLabel("COUNTING", JLabel.CENTER);
        myFont = new Font("Comic Sans MS", Font.BOLD,25);
        section.setFont(myFont);
        
        top.add(name,BorderLayout.NORTH);
        top.add(accountPic,BorderLayout.CENTER);
        top.add(section,BorderLayout.SOUTH);
    } 
    private void buildBot() {
        bot = new JPanel(new GridLayout(3,2,10,10));
        sticker = new ArrayList<>();
        bot.setBackground(new Color(144,210,144));
        for(int i = 0;i<4;i++){
            sticker.add(new JLabel(new ImageIcon("sticker.png")));
            sticker.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            bot.add(sticker.get(i));
        }
        
    }

    private void buildButtons() {
        buttons = new JPanel(new GridLayout(1,2,25,10));
        buttons.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        buttons.setBackground(new Color(144,210,144));
        
        back = new JLabel(new ImageIcon("Back.png"));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addMouseListener(new ImageHandler());
        
        help = new JLabel(new ImageIcon("Help.png"));
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.addMouseListener(new ImageHandler());
        buttons.add(back);
        buttons.add(help);
    }
    public class ImageHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e)  
    {  
        if(e.getSource()==help){
            Frame.currentPanel.help();
        }
        else
       JOptionPane.showMessageDialog(null, "Soon");

    }  

    }


}
