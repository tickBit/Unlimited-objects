import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class Unlimited {

	/**
	 *  "Unlimited objects" in Java
	 */
	private static final long serialVersionUID = 1L;
	
	int imgX1;
	int imgY1;


	double r1 = 1;
	double r2 = 250;
	double angle = 0;
	
	int counter = 0;

	Graphics bufferGraphics; 
	Image offscreen;
	Image drawArea[] = new Image[16];

	BufferedImage img = null;
	
	JFrame frame;
	
    /**
	 * 
	 */
	public static void main(String[] args) {

		new Unlimited().go();

	}

	private void go() {
			
		DrawPanel drawPanel;
	
		frame = new JFrame("Unlimited objects");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();

		frame.getContentPane().add(drawPanel);

		frame.setResizable(false);
		frame.setSize(600, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		initialize(drawPanel);
		
		moveIt();
    }

	private void moveIt() {
		while (true) {
		
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}

			frame.repaint();
		}
	}
	
	public void initialize(Component c) {
	    for (int i = 0; i < 16; i++) {
            drawArea[i] = c.createImage(600,600);
        }

        offscreen = c.createImage(600,600);
        bufferGraphics = offscreen.getGraphics();

        if (img == null) {
            try {
                img = ImageIO.read(new File("images//disc.jpg"));
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	class DrawPanel extends JPanel  {

		private static final long serialVersionUID = 1L;
	        
		public void paintComponent(Graphics g) {
			    
			imgX1 = (int)(Math.cos(angle) * r1 + 300 - img.getWidth() / 2);
		    imgY1 = (int)(Math.sin(angle) * r1 + 300 - img.getHeight() / 2);

		    bufferGraphics.clearRect(0,0,600,600);
		    drawArea[counter].getGraphics().drawImage(img, imgX1, imgY1, img.getWidth(), img.getHeight(), null);

		    bufferGraphics.drawImage(drawArea[counter],0,0,600,600,null);
		    g.drawImage(offscreen,0,0,this); 

		    counter++;
		    if (counter == 16) counter = 0;


		    r1+=0.075;
		    r2-=0.075;

		    angle+=Math.PI / 180;
		}

	}

}