package company;


import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class KeyAnimation extends game implements KeyListener{
    public static double theta = 0;
    public static int count = 0;
    public static float rocketSpeed = 0;
    public static Timer timer;
    public static boolean rotated = false;
    public static int speed = 0;
    public static float acceleration = 0;
    private BufferedImage image;
    public static int velX = 1;
    public static boolean disposed = false;

    public static JFrame window;
    public static Container con;
    public static JPanel titleNamePanel, startButtonPanel, mainTextPanel;
    public static JLabel titleNameLabel;
    public static Font titleFont = new Font("ArcadeClassic",Font.BOLD, 90);
    public static JButton startButton;
    public static Font secondFont = new Font("ArcadeClassic",Font.PLAIN, 30);
    public static JTextArea mainTextArea;
    
  
	
	ImageIcon image2;
	
	


    public KeyAnimation() throws UnsupportedAudioFileException, LineUnavailableException{
        super();
        addKeyListener(this);
        setFocusable(true);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/rocket.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case 37: //left
                rotated = true;
                theta -= 3;
                repaint();
                //System.out.println(theta);
                break;

            case 39: //right
                rotated = true;
                theta += 3;
                repaint();
                //System.out.println(theta);
                break;

            case 38: //up
                //rotated = true;
                rocketSpeed += 0.05;
                if (count == 0) {
                    timer = new Timer(85, new TimerCallback());
                    timer.start();
                }
                count++;
                repaint();

                break;

            case 40: //down
                rocketSpeed += 0.05;
                xCoordinate = (int) (xCoordinate - rocketSpeed *  (Math.cos(Math.toRadians(90 - theta))));
                yCoordinate= (int) (yCoordinate + rocketSpeed * (Math.sin(Math.toRadians(90 - theta))));
                //System.out.println(xCoordinate + "," + yCoordinate);
                break;

        }
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case 37: //left
                //speed -= 1;
                rotated = false;
                repaint();
                break;

            case 39: //right
                //speed -= 1;
                rotated = false;
                repaint();
                break;

            case 40: //down

                break;

            case 38: //up
                rotated = false;
                acceleration -= 0.1;
                repaint();
                break;
            default:
                break;
        }
        //repaint();
    }

    public static void createNewWin(){
    	window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color newColor = new Color(25,25,112);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		con = window.getContentPane();
		
		//titleNamePanel = new JPanel();
	//	titleNamePanel.setBounds(100,200,600,130);
		//titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("YOU  WIN !");
		titleNameLabel.setBounds(210,200,650,250);
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
	//	startButtonPanel = new JPanel();
	//	startButtonPanel.setBounds(300, 375, 200, 100);
	//	startButtonPanel.setBackground(Color.black);
		
		
	//	titleNamePanel.add(titleNameLabel);
     //   startButtonPanel.add(startButton); 
       
        con.add(titleNameLabel);
		//con.add(startButtonPanel);
       
		
		JPanel imagePanel2 = new JPanel();
		imagePanel2.setBounds(0,80,800,600);
		imagePanel2.setBackground(Color.black);
	
		JLabel imageLabel2 = new JLabel(); 
		ImageIcon image2 = new ImageIcon(".//res//images-removebg-preview.png");
		imageLabel2.setIcon(image2);
		imagePanel2.add(imageLabel2);
		
		con.add(imagePanel2);
		
		window.setVisible(true);
    }
    
    public static void createNewWindow(){
    	window = new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color newColor = new Color(25,25,112);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		con = window.getContentPane();
		
		//titleNamePanel = new JPanel();
		//titleNamePanel.setBounds(100,200,600,130);
		//titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("GAME OVER");
		titleNameLabel.setBounds(180,220,600,130);
		titleNameLabel.setForeground(Color.red);
		titleNameLabel.setFont(titleFont);
		
		
       
      //  con.add(titleNamePanel);
        con.add(titleNameLabel);
		
		
		JPanel imagePanel3 = new JPanel();
		imagePanel3.setBounds(0,30,800,600);
		imagePanel3.setBackground(Color.black);
	
		JLabel imageLabel3 = new JLabel(); 
		ImageIcon image3 = new ImageIcon(".//res//52c306f0214cb6d-removebg-preview.png");
		
		imageLabel3.setIcon(image3);
		imagePanel3.add(imageLabel3);
		
		
		con.add(imagePanel3);
	
		
		
		window.setVisible(true);
//    }
    }
   protected class TimerCallback implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (x > 0) {
                x = x - velX;
            } else {
                fuelOver = true;
                x = 360;
            }
            if (fuelOver) {
                timer.stop();
               // menu.frame.dispose();
                createNewWindow();
            } else {
                if (theta != 0) {
                    rocketSpeed += 0.05;
                    repaint();
                    if (!rotated && theta < 180) {
                        theta += 3;
                        repaint();
                    }
                    xCoordinate = (int) (xCoordinate + (rocketSpeed + acceleration) * (Math.cos(Math.toRadians(90 - theta))));
                    yCoordinate = (int) (yCoordinate - (rocketSpeed + acceleration) * (Math.sin(Math.toRadians(90 - theta))));
                    repaint();
                } else if (count < 1) {
                    xCoordinate = (int) (xCoordinate + (rocketSpeed + acceleration) * (Math.cos(Math.toRadians(90 - theta))));
                    yCoordinate = (int) (yCoordinate - (rocketSpeed + acceleration) * (Math.sin(Math.toRadians(90 - theta))));
                } else if (xCoordinate < getWidth() / 2) {
                    yCoordinate -= 5;
                } else {
                    //timer.start();
                    yCoordinate += 5;
                }
                repaint();
            }
        }
        



    }

}