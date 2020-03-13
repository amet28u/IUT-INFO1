
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Gribouillage1  {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		FrameGribouillage1  f = new FrameGribouillage1 ("Gribouillage - bonne version");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(520,360);
		f.setVisible(true);  
	}
 }); 
}

}

class FrameGribouillage1 extends JFrame{
	Pan panneau;
		
	public FrameGribouillage1(String titre){
		super(titre);
		panneau=new Pan();
		panneau.addMouseMotionListener(panneau);
		setContentPane(panneau);
	}
	
	public class Pan extends JPanel implements MouseMotionListener {
	int currentX,currentY,oldX,oldY;	
			
			public Pan(){
				super();
				currentX=currentY=0;
				oldY=oldX=-1;
			}
			
			public void mouseDragged (MouseEvent e) {
				oldX=currentX; oldY=currentY;
		   		currentX=e.getX(); currentY=e.getY();
				repaint();	
			}		
			
			public void mouseMoved (MouseEvent e) {
				currentX=e.getX(); currentY=e.getY();
			}
	              
			public void paintComponent(Graphics g) {
				g.setColor( Color.black);
				if(oldX!=-1) 
					g.drawLine(oldX,oldY,currentX,currentY);	
			}
	}	
	
}