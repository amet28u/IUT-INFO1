import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class ClicImage3 {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		FrameClicImage3  f = new FrameClicImage3 ("ClicImage ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(432,324);
		f.setVisible(true);  
	}
 }); 
}
}

class FrameClicImage3 extends JFrame{
		private Pan panneau;
		private Image img, img1,img2;
		private int i;
		
		public FrameClicImage3(String titre){
			super(titre);
			i=0;
			panneau=new Pan();
			panneau.addMouseListener(new MonMouseListener());

			img=img1= Toolkit.getDefaultToolkit().getImage("jour.jpg");
			img2=Toolkit.getDefaultToolkit().getImage("nuit.jpg");
			setContentPane(panneau);
		}
	
	class Pan extends JPanel{
	 
			public Pan(){
				super();
			}
			      
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage( img, 0,0, this );		
			}
			
	}	
	class MonMouseListener extends MouseAdapter{
				public void mouseClicked(MouseEvent e) {
					i=i+1;
					if(i%2==0) img=img1;
					else img=img2;
					panneau.repaint();
				}
	}
}