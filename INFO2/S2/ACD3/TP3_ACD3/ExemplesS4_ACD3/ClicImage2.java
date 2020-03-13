import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class ClicImage2 {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		FrameClicImage2  f = new FrameClicImage2 ("ClicImage ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(432,324);
		f.setVisible(true);  
	}
 }); 
}
}

class FrameClicImage2 extends JFrame{
		private Pan panneau;
		private Image img, img1,img2;
		private int i;
		
		public FrameClicImage2(String titre){
			super(titre);
			i=0;
			panneau=new Pan();
			panneau.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					i=i+1;
					if(i%2==0) img=img1;
					else img=img2;
					panneau.repaint();
				}
			});

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
	
}