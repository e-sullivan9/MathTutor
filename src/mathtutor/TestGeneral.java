/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathtutor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Eric Sullivan
 */
public class TestGeneral extends JPanel implements PanelTemplate{
    CardLayout cl;
    ArrayList<GeneralTestPanel> test;
    int index;
    public TestGeneral(String fileName) 
    {
       cl = new CardLayout();
       index = 0;
       setLayout(cl);
       test = new ArrayList<>();
       //buildTest();
       try
       {
         parseFile(fileName);
       }
       catch(FileNotFoundException e)
       {
           
       }
    }
  /*  public void buildTest(){
        for(int i=0;i<6;i++){
            test.add(new GeneralTestPanel(""+i));
            add(test.get(i),""+i);
        }
    }
    */
    private void parseFile(String fileName)throws FileNotFoundException
    {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine())
        {
            String line = scan.nextLine();
            System.out.println(line);
            String delim[] = line.split(";");
            if(delim.length == 3)
            {
            System.out.println(delim[1]);
            String questions[] = delim[1].split(",");
            GeneralTestPanel gtp = new GeneralTestPanel(delim[0],questions,delim[2]);
            test.add(gtp);
            add(gtp);
            }
            
        }
        scan.close();
    }
    @Override
    public void help() {
        test.get(index).help();
    }

    @Override
    public void back() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void next(){
        cl.next(this);
        index=(index+1)%test.size();
    }
    
    
    public class GeneralTestPanel extends JLayeredPane implements PanelTemplate{
    JPanel top,bot,questionPane,correct,wrong,help1,help2;
    ArrayList<JPanel> answerP;
    JLabel questionLabel;
    ArrayList<JLabel> answerL;
    String correctAnswer;
    public GeneralTestPanel(String question, String[] answers, String correctAnswer) {
        setBackground(new Color(144,210,144));
        buildTop();
        buildBot();
        buildBack();
        
        questionLabel.setIcon(new ImageIcon(question));
        for(int i = 0; i < answers.length; ++i)
            answerL.get(i).setText(answers[i]);
        this.correctAnswer = correctAnswer;
        
        add(top,Integer.valueOf(0));
        add(bot,Integer.valueOf(0));
    }
    private void buildTop() {
        top = new JPanel();
        top.setBounds(0, 0, 610, 290);
        top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
        top.setBackground(new Color(144,210,144));
        top.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        questionPane = new JPanel();
        questionPane.setBorder(BorderFactory.createDashedBorder(Color.BLACK,10,5));
        questionPane.setBackground(Color.CYAN);
        
        top.add(Box.createRigidArea(new Dimension(10,0)));
        top.add(questionPane);
        top.add(Box.createRigidArea(new Dimension(10,0)));
        
        questionLabel = new JLabel();
        
        questionPane.add(questionLabel);
        
    }

    private void buildBot() {
        GridLayout gl = new GridLayout(2,2);
        bot = new JPanel(gl);
        bot.setBounds(0,290,610,290);
        bot.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bot.setBackground(new Color(144,210,144));
        gl.setHgap(10);
        gl.setVgap(10);
        answerP = new ArrayList<>();
        answerL = new ArrayList<>();
        for(int i =0; i<4;i++){
            answerP.add(new JPanel());
            Font myFont = new Font("Comic Sans MS", Font.BOLD,50);      
            answerL.add(new JLabel());
            answerL.get(i).setFont(myFont);
            answerP.get(i).add(answerL.get(i));
            
            
            
            answerP.get(i).setBackground(Color.GREEN);
            answerP.get(i).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            answerP.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            answerP.get(i).addMouseListener(new AnswerHandler());
            
            bot.add(answerP.get(i));
        }
        
        
    }
    public void buildBack(){
    correct = new JPanel();
    wrong = new JPanel();
    help1 = new JPanel();
    help2 = new JPanel();
    
    
    help1.setBackground(Color.BLUE);
    help1.setBounds(0,0,600,300);
    help1.addMouseListener(new AnswerHandler());
    help1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    help2.setBackground(Color.BLUE);
    help2.setBounds(0, 300, 600, 300);
    help2.addMouseListener(new AnswerHandler());
    help2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    correct.setBackground(Color.GREEN);
    correct.setBounds(155,70,300,150);
    correct.addMouseListener(new AnswerHandler());
    correct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    wrong.setBackground(Color.red);
    wrong.setBounds(155,70,300,150);
    wrong.addMouseListener(new AnswerHandler());
    wrong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    
    add(wrong, Integer.valueOf(-300));
    add(help1,Integer.valueOf(-300));
    add(correct, Integer.valueOf(-300));
    add(help2, Integer.valueOf(-300));
    
}
    @Override
    public void help() {
        setLayer(help1,300);
        setLayer(help2,300);
    }

    @Override
    public void back() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public class AnswerHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e)  
    {  
        if(e.getSource()==answerP.get(0)){
            if(answerL.get(0).getText().equals(correctAnswer)){
                setLayer(correct,300);
                repaint();
            }
            else{
                setLayer(wrong,300);
                repaint();
            }
        }
        else
        if(e.getSource()==answerP.get(1)){
            if(answerL.get(1).getText().equals(correctAnswer)){
                setLayer(correct,300);
                repaint();
            }
            else{
                setLayer(wrong,300);
                repaint();
            }
        }
        if(e.getSource()==answerP.get(2)){
            if(answerL.get(2).getText().equals(correctAnswer)){
                setLayer(correct,300);
                repaint();
            }
            else{
                setLayer(wrong,300);
                repaint();
            }
        }
        if(e.getSource()==answerP.get(3)){
            if(answerL.get(3).getText().equals(correctAnswer)){
                setLayer(correct,300);
                repaint();
            }
            else{
                setLayer(wrong,300);
                repaint();
            }
        }
        if(e.getSource()==help1){
            setLayer(help1,-300);
        }
        else
        if(e.getSource()==help2){
            setLayer(help2,-300);
        }
        else
            if(e.getSource()==wrong&&getLayer(wrong)==300){
                next();
            }
        else
            if(e.getSource()==correct&&getLayer(correct)==300){
                next();
            }
        }
        //else
           // JOptionPane.showMessageDialog(null, "Soon");

    }  
}

    


}
