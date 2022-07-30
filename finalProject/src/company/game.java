package company;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class game extends JComponent {
  public static int xCoordinate = 5;
  public static int yCoordinate = 450;
  public static int x = 360;
  public static boolean fuelOver = false;
  public static boolean play = false;
  static boolean collision = false;
  protected BufferedImage image;
  protected boolean collided = false;
  protected BufferedImage image2;
  private BufferedImage image3;
  private BufferedImage image4;
  private BufferedImage image5;
  private Clip clip;
  private File file;
  int score = 0;
  JButton startButton;
  Random random = new Random();
  int xrange;
  double x2range;
  double y2range;
  double yrange;
  boolean nextLevel;
  double[] line1x;
  double[] line1y;
  static Image img;
  Image img2;
  BufferedImage img3;
  static Color borderColor;
	protected boolean collect1 = false;
	protected boolean collect2 = false;
	protected boolean collect3 = false;
	protected static int lives = 3;

  public game() throws UnsupportedAudioFileException, LineUnavailableException {
    this.xrange = this.random.nextInt(2) + 2;
    this.x2range = (double)this.random.nextInt(2) + 1.5D;
    this.y2range = (double)(this.random.nextInt(3) + 3);
    this.yrange = (double)(this.random.nextInt(2) + 2);
    this.nextLevel = false;

    try {
    	img = ImageIO.read(new File("bgImg.png"));
		img2 = ImageIO.read(new File("bgImg2.png"));

		file = new File("coinSound.WAV");
		AudioInputStream sound = AudioSystem.getAudioInputStream(file);
		image = ImageIO.read(getClass().getResourceAsStream("/rocket.png"));
		image2 = ImageIO.read(getClass().getResourceAsStream("/cloud.png"));
		//image3 = ImageIO.read(getClass().getResourceAsStream("/cloud2.png"));
		image4 = ImageIO.read(getClass().getResourceAsStream("/bird.png"));
		img3 = ImageIO.read(getClass().getResourceAsStream("/rockstar.png"));
		image5 = ImageIO.read(getClass().getResourceAsStream("/spaceship.png"));
		clip = AudioSystem.getClip();
		clip.open(sound);
    } catch (IOException var2) {
      var2.printStackTrace();
    }

  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    Ellipse2D border = new Double((double)(xCoordinate + 22), (double)(yCoordinate - 10), (double)(this.image.getWidth() / 14), (double)(this.image.getHeight() / 6));
    boolean collision = false;
    int w = this.getWidth();
    int h = this.getHeight();
    Rectangle landingSpot = new Rectangle(w - w / 3, h - h / 6, w / 3, h / 50);
    Color landingColor = new Color(3, 135, 87);
    if (border.getBounds2D().intersects(landingSpot.getBounds2D()) && KeyAnimation.theta == 0.0) {
    	if (!nextLevel) {
    	KeyAnimation.theta = 0.0D;
    	 KeyAnimation.count = 0;
    	 KeyAnimation.rocketSpeed = 0;
        xCoordinate = this.getWidth() - this.getWidth();
        yCoordinate = this.getHeight() - this.getHeight() / 3;
		KeyAnimation.timer.stop();
		lives = 3;
		x = 360;
       this.nextLevel = true;
    	} else {
    		KeyAnimation.timer.stop();
    		KeyAnimation.createNewWin();
    	}
    }

    if (!this.nextLevel) {
      Level1(border, g, h, w);
    } else {
      Level2(border, g, h, w);
    }
  
    java.lang.Double[] x2points = new java.lang.Double[]{0.0D, (double)w / 7.0D, (double)w / 7.0D, (double)w / 3.0D, (double)w / 3.0D, (double)w - (double)w / 2.0D, (double)w - (double)w / 2.0D, (double)w - (double)w / 2.5D, (double)w - (double)w / 2.5D, (double)w - (double)w / 3.0D, (double)w - (double)w / 3.0D, (double)w / 1.0D, (double)w / 1.0D, (double)w / 1.0D};
    java.lang.Double[] y2points = new java.lang.Double[]{(double)h - (double)h / 7.0D, (double)h - (double)h / 7.0D, (double)h - (double)h / 4.0D, (double)h - (double)h / 4.0D, (double)h - (double)h / 3.0D, (double)h - (double)h / 3.0D, (double)h - (double)h / 7.0D, (double)h - (double)h / 7.0D, (double)(h - h / 3) - 50.0D, (double)(h - h / 3) - 70.0D, (double)h - (double)h / 7.0D, (double)h - (double)h / 7.0D, (double)h - (double)h / 7.0D, (double)h - (double)h / 7.0D};
    g2.setStroke(new BasicStroke(5.0F));
    g2.setColor(landingColor);
    g2.fill(landingSpot);
    g2.draw(landingSpot);
    new java.awt.geom.Line2D.Double();

    for(int i = 0; i < x2points.length - 1; ++i) {
      g.setColor(borderColor);
      Line2D line1 = new java.awt.geom.Line2D.Double(this.line1x[i], this.line1y[i], this.line1x[i + 1], this.line1y[i + 1]);
      Line2D line2 = new java.awt.geom.Line2D.Double(x2points[i], y2points[i], x2points[i + 1], y2points[i + 1]);
      ((Graphics2D)g).draw(line1);
      ((Graphics2D)g).draw(line2);
      
      if (border.getBounds2D().intersectsLine(line1) || border.getBounds2D().intersectsLine(line2) ) {
    	  collision = true;
    	  
      }
      else if (xCoordinate > w || xCoordinate < 0) {
        	collision = true;
        }
        }
        

    if (collision) {
    	lives--;
    	KeyAnimation.timer.stop();
    	if (lives == 0) {
    		KeyAnimation.createNewWindow();
    	} else {
      KeyAnimation.theta = 0.0D;
      xCoordinate = this.getWidth() - this.getWidth();
      yCoordinate = this.getHeight() - this.getHeight() / 3;
      KeyAnimation.count = 0;
      KeyAnimation.rocketSpeed = 0.0F;
      x = 360;
    	}
    }

    drawHeart(g);
    g.setColor(Color.BLACK);
	Font myFont = new Font("Courier New", 1, 20);
	g.setFont(myFont);
	g.drawString("Score: " + score, 0, getHeight() / 4 - 150);
    g2.setStroke(new BasicStroke(0.5F));
    g.setColor(Color.green);
    g.fillArc(w - 75, 25, 50, 50, 90, x);
    g2.rotate(Math.toRadians(KeyAnimation.theta), (double)(xCoordinate + this.image.getWidth() / 12), (double)(yCoordinate + this.image.getHeight() / 12));
    g.drawImage(this.image, xCoordinate, yCoordinate - 10, this.image.getWidth() / 6, this.image.getHeight() / 6, (ImageObserver)null);
    //g2.draw(border);
  }

  public void Level1(Ellipse2D border, Graphics g, int h, int w) {
		Ellipse2D coin2 = new Ellipse2D.Double(w / 3 + 100, h / 2 + 50, w / 60, h / 40);
		Ellipse2D coin3 = new Ellipse2D.Double(w - w / 2 + 260, h / 3 - 80, w / 60, h / 40);
		Ellipse2D coin = new Ellipse2D.Double(w / 3 - 70, h / 3 + 30, w / 60.0, h / 40.0);
		double[] xpoints = { 0.0, w / 10.0, w / 10.0, w / 5.2, w / 5.2, w / 3.5, w / 3.5, w / 2.0, w / 2.0, w - w / 2.5,
				w - w / 2.5, w - w / 3.0, w - w / 3.0, w / 1.0 };

		double[] ypoints = { h / 5.0, h / 5.0, h / 3.0, h / 3.0, h / 5.0, h / 5.0, h / 10.0, h / 10.0, h / 5.0, h / 5.0,
				h / 3.0, h / 3.0, h / 10.0, h / 10.0 };
		g.drawImage(img, 0, h / 10, getWidth(), h - h / 4, null);
		borderColor = new Color(5, 88, 141);
		line1x = xpoints;
		line1y = ypoints;
	//	g.drawImage(image3, w - w / 2 + 30, h - h / 3, w / 14, h / 16, null);
		g.drawImage(image4, w / 4 , (int) (h/1.7) , image4.getWidth() / 8, image4.getHeight() / 10, null);

		g.drawImage(image2, w / 3, h / 4, image2.getWidth() / 4, image2.getHeight() / 6, null);
		if (border.getBounds2D().intersects(w / 3, h / 4, image2.getWidth() / 4, image2.getHeight() / 6)) {
		
			yCoordinate = yCoordinate + 30;

		}else if (border.getBounds2D().intersects( w / 4 ,h/1.7 , image4.getWidth() / 8, image4.getHeight() / 10)) {
			xCoordinate = xCoordinate -10;
		}
			
		coins(border, h, w, g, coin, coin2, coin3);
	}

	public void Level2(Ellipse2D border, Graphics g, int h, int w) {
		Ellipse2D coin2 = new Ellipse2D.Double(w-w / 3.5 , h / 2 , w / 60, h / 40);
		Ellipse2D coin3 = new Ellipse2D.Double(w/3.5 , h / 1.7 , w / 60, h / 40);
		Ellipse2D coin = new Ellipse2D.Double(w / 2.5 , h / 3.5 , w / 60.0, h / 40.0);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img2, 0, h / 10, getWidth(), h - h / 4, null);
		borderColor = new Color(250, 128, 241);
		double[] xpoints2 = { 0.0, w / 8.0, w / 8.0, w / 5.2, w / 5.2, w / 3.5, w / 3.5, w / 2.0, w / 2.0, w - w / 2.5,
				w - w / 2.5, w - w / 3.0, w - w / 3.0, w / 1.0 };

		double[] ypoints2 = { h / 4, h / 4, h / 2.5, h / 2.2, h / 4.0, h / 4.0, h / 10.0, h / 10.0, h / 5.0, h / 5.0,
				h / 2.5, h / 2.5, h / 6.0, h / 6.0 };

		g2.setStroke(new BasicStroke(7));
		g.drawImage(img3,  (int) ((int) w/2.5), (int) (h / 1.8), img3.getWidth() / 20, img3.getHeight() / 20, null);
		g.drawImage(image5, w/4 , h / 3 , img3.getWidth() / 20, image5.getHeight() / 7, null);
		line1x = xpoints2;
		line1y = ypoints2;
		if (border.getBounds2D().intersects(w/2.5, (h / 1.8), img3.getWidth() / 20, img3.getHeight() / 20)) {
			yCoordinate = yCoordinate- 10;
			

		}else if (border.getBounds2D().intersects(w/4 , h / 3 , img3.getWidth() / 20, image5.getHeight() / 7)) {
			yCoordinate = yCoordinate + 30;
		}
		coins(border, h, w, g, coin, coin2, coin3);
	}

	public void coins(Ellipse2D border, int h, int w, Graphics g, Ellipse2D coin, Ellipse2D coin2, Ellipse2D coin3) {

		Graphics2D g2 = (Graphics2D) g;
		Color coinColor = new Color(241, 177, 71);
		Color coinBorderColor = new Color(241, 215, 71);

		g.setColor(coinColor);
		g2.setStroke(new BasicStroke(15));
		if (!collect1 && coin.getBounds().intersects(border.getBounds())) {
			collect1 = true;
			clip.setFramePosition(0);
			score += 10;

		}
		if (!collect2 && coin2.getBounds().intersects(border.getBounds())) {
			collect2 = true;
			clip.setFramePosition(0);
			score += 10;

		}
		if (!collect3 && coin3.getBounds().intersects(border.getBounds())) {
			collect3 = true;
			clip.setFramePosition(0);
			score += 10;
		}
		if (collect1) {
			coin = new Ellipse2D.Double(0, 0, 0, 0);

			clip.start();
		}
		if (collect2) {
			clip.start();
			coin2 = new Ellipse2D.Double(0, 0, 0, 0);
		}
		if (collect3) {
			clip.start();
			coin3 = new Ellipse2D.Double(0, 0, 0, 0);
		}
		g.setColor(coinBorderColor);
		g2.draw(coin);
		g2.draw(coin2);
		g2.draw(coin3);

	}

	public void drawHeart(Graphics g) {

		int a[] = { 15, 20, 20, 30, 30, 35, 35, 45, 45, 50, 50, 45, 45, 40, 40, 35, 35, 30, 30, 25, 25, 20, 20, 15 };
		int b[] = { 20, 20, 15, 15, 20, 20, 15, 15, 20, 20, 30, 30, 35, 35, 40, 40, 45, 45, 40, 40, 35, 35, 30, 30 };
		int numberOfPoints = 24;

		if (lives > 0) {Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(6));
		g2.setColor(Color.black);
		g2.drawPolygon(a, b, numberOfPoints);
		g2.setColor(Color.red);
		g2.fillPolygon(a, b, numberOfPoints);}
		int x = 45;
		int c[] = { 15 + x, 20 + x, 20 + x, 30 + x, 30 + x, 35 + x, 35 + x, 45 + x, 45 + x, 50 + x, 50 + x, 45 + x,
				45 + x, 40 + x, 40 + x, 35 + x, 35 + x, 30 + x, 30 + x, 25 + x, 25 + x, 20 + x, 20 + x, 15 + x };
		int d[] = { 20, 20, 15, 15, 20, 20, 15, 15, 20, 20, 30, 30, 35, 35, 40, 40, 45, 45, 40, 40, 35, 35, 30, 30 };
		
		if (lives >= 2) {Graphics2D g3 = (Graphics2D) g;
		g3.setStroke(new BasicStroke(6));
		g3.setColor(Color.black);
		g3.drawPolygon(c, d, numberOfPoints);
		g3.setColor(Color.red);
		g3.fillPolygon(c, d, numberOfPoints);}

		int xx = 90;
		int e[] = { 15 + xx, 20 + xx, 20 + xx, 30 + xx, 30 + xx, 35 + xx, 35 + xx, 45 + xx, 45 + xx, 50 + xx, 50 + xx,
				45 + xx, 45 + xx, 40 + xx, 40 + xx, 35 + xx, 35 + xx, 30 + xx, 30 + xx, 25 + xx, 25 + xx, 20 + xx,
				20 + xx, 15 + xx };
		int f[] = { 20, 20, 15, 15, 20, 20, 15, 15, 20, 20, 30, 30, 35, 35, 40, 40, 45, 45, 40, 40, 35, 35, 30, 30 };


		if (lives >= 3) {Graphics2D g4 = (Graphics2D) g;
		g4.setStroke(new BasicStroke(6));
		g4.setColor(Color.black);
		g4.drawPolygon(e, f, numberOfPoints);
		g4.setColor(Color.red);
		g4.fillPolygon(e, f, numberOfPoints);}
	}
  public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
    JFrame frame = new JFrame("Canvas");
    game canvas = new game();
    game canvas2 = new KeyAnimation();
    frame.add(canvas);
    frame.add(canvas2);
    frame.setDefaultCloseOperation(3);
    frame.setSize(700, 700);
    frame.setVisible(true);
  }

}
