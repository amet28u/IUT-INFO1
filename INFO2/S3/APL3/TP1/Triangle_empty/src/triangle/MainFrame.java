package triangle;

import java.awt.*;
import java.util.*;
import javax.swing.*;


 
public class MainFrame extends JPanel
{
	// set window size here
	public static final int WINDOWSIZE = 600;
	
	public static Sierpinski triangle;
	 
	public void paint(Graphics g)
	{	
		System.out.println("Paint  = "+ MainFrame.triangle.polyList.length );
		
		for(int i = 0; i < MainFrame.triangle.polyList.length; i++)
		{
			g.fillPolygon(MainFrame.triangle.polyList[i]);
		}
	}
	
	
	public static void main (String[] args)
	{
		// make the base triangle based on the window size
		//point 1 -- top
		int p1x = WINDOWSIZE/2;
		int p1y = WINDOWSIZE/10;
		//point 2 -- bottom right
		int p2x = WINDOWSIZE-p1y;
		int p2y = WINDOWSIZE-p1y;
		//point 3 -- bottom left
		int p3x = WINDOWSIZE/10;
		int p3y = WINDOWSIZE-p1y;
		 
		// ask user how many splits
		//TODO
		int splits = 0;

				
		
		// new window
		JFrame f = new JFrame("Sierpinski's Triangle!");
		f.setSize(WINDOWSIZE,WINDOWSIZE);
		f.setBackground(Color.white);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MainFrame s = new MainFrame();
		f.add(s);
		// Build a new fractal 
		//TODO:
			MainFrame.triangle = new Sierpinski(splits);
			triangle.computeTriangles(splits,p1x,p1y,p2x,p2y,p3x,p3y);
		//drawing the triangles
		f.setVisible(true);
	}
	 
}
