package company;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class menu {
	
	JFrame window;
	JFrame frame;
	Container con;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel,imagePanel;
	JLabel titleNameLabel,imageLabel;
	Font titleFont = new Font("ArcadeClassic",Font.BOLD, 90);
	JButton startButton;
	Font secondFont = new Font("ArcadeClassic",Font.PLAIN, 30);
	JTextArea mainTextArea;
	
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	
	ImageIcon image;
	

	
	public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException{
		new menu();
		
	}
	
	
	public menu() {
		
		window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color customColor = new Color(25,25,112);
		Color customColor2 = new Color(128,0,0);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		con = window.getContentPane();
		
		//titleNamePanel = new JPanel();
	//	titleNamePanel.setBounds(100,100,600,130);
		//titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("ROCKET GO!");
		titleNameLabel.setBounds(175,100,600,130);
		titleNameLabel.setForeground(Color.yellow);
		titleNameLabel.setFont(titleFont);
		
		startButtonPanel = new JPanel();
		//startButtonPanel.setBounds(300, 420, 200, 100);
		//startButtonPanel.setBackground(Color.black);
		
	
		
		startButton = new JButton("START");
		startButton.setBounds(330,400,150,50);
		//startButton.setBackground(Color.white);
	   // startButton.setForeground(customColor);
		startButton.setFont(secondFont);
		startButton.addActionListener(tsHandler);
		
		//titleNamePanel.add(titleNameLabel);
     //   startButtonPanel.add(startButton); 
       
		con.add(startButton);
		con.add(titleNameLabel);
       // con.add(titleNamePanel);
		//con.add(startButtonPanel);
		
		imagePanel = new JPanel();
		imagePanel.setBounds(0,0,800,600);
		imagePanel.setBackground(Color.black);
	
		imageLabel = new JLabel(); 
		image = new ImageIcon(".//res//space_background.png");
		imageLabel.setIcon(image);
		imagePanel.add(imageLabel);
		
		con.add(imagePanel);
	
		
		window.setVisible(true);
		
	
		
	}
	

	 public class TitleScreenHandler implements ActionListener {

	        public void actionPerformed(ActionEvent event) {
	            //implement main class somehow??
	          //  window.dispose();
	            frame = new JFrame("Canvas");
	           if (event.getSource() == startButton){
	               try {

	                   game canvas = new game();
	                   game canvas2 = new KeyAnimation();

	                  
	                   frame.add(canvas);
	                   frame.add(canvas2);

	                   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	                   frame.setSize(2000, 1500);
	                   frame.setVisible(true);

	               } catch (UnsupportedAudioFileException e) {
	                   e.printStackTrace();
	               } catch (LineUnavailableException e) {
	                   e.printStackTrace();
	               }
	           }
	        }
	
	
	
	
}




   

    }




	
	
	
	
	
	
	
	
	
	
	
	
	
