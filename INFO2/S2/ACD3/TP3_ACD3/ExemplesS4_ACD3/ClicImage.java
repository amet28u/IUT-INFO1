import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;


public class ClicImage {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		FrameClicImage  f = new FrameClicImage ("ClicImage ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(432,324);
		f.setVisible(true);  
	}
 }); 
}

}


class FrameClicImage extends JFrame{
		private Pan panneau;
		private Image img, img1,img2;
		private int i;
		
		public FrameClicImage(String titre) {
			super(titre);
			i=0;
			panneau=new Pan();
			panneau.addMouseListener(panneau);
			try{
			img=img1= ImageIO.read(new File("jour.jpg"));
			img2=ImageIO.read(new File("nuit.jpg"));
			}catch(Exception e){};
			add(panneau);
		}
	
		public class Pan extends JPanel implements MouseListener {
	 
			public Pan(){
				super();
			}
			      
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage( img, 0,0, this );		
			}
			
			public void mouseClicked(MouseEvent e) {
				i=i+1;
				if(i%2==0) img=img1;
				else img=img2;
				repaint();
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			} 
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}

		}	
	
}