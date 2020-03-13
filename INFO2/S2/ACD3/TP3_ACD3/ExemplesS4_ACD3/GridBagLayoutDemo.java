
import java.awt.*;
import javax.swing.*;


public class GridBagLayoutDemo {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
	public void run() { 
		FrameGridBagLayoutDemo  f = new FrameGridBagLayoutDemo ("GridBagLayoutDemo ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(350,160);
		f.setVisible(true);  
	}
 }); 
}
}

class FrameGridBagLayoutDemo extends JFrame {
    
	private JPanel p;
	private GridBagConstraints c ;
	
    public FrameGridBagLayoutDemo(String t) {
        super(t);
        p=new JPanel();
        p.setLayout(new GridBagLayout());
		
        c = new GridBagConstraints();
        
	    
     
	  JButton button;
        button = new JButton("Button 1");
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        p.add(button, c);

        button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 2.0;
        c.gridx = 1;
        c.gridy = 0;
        p.add(button, c);

        button = new JButton("Button 3");
	    c.weightx = 2.0;
        c.gridx = 2;
        c.gridy = 0;
        p.add(button, c);

        button = new JButton("Long-Named Button 4");
        c.ipady = 40;      //make this component tall
        c.weightx = 2.0;
		c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        p.add(button, c);

        button = new JButton("5");
        c.ipady = 0;       //reset to default
        c.weighty = 2.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(20,0,0,10);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = GridBagConstraints.REMAINDER;   //2 columns wide
        c.gridy = 2;       //third row
        p.add(button, c);
		add(p);
    }
}