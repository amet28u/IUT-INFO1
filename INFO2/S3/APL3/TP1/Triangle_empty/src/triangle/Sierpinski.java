package triangle;

import java.awt.Polygon;

public class Sierpinski {

	public Polygon[] polyList;
	public int count = 0;

	public Sierpinski( int splits )
	{
		//TODO
		this.polyList = new Polygon[1];
	}

	public void computeTriangles(int splits, int ax, int ay, int bx, int by, int cx, int cy)
	{
		//TODO
		//create basic triangle
		int[]xs={ax,bx,cx};
		int[]ys={ay,by,cy};
		Polygon p = new Polygon(xs,ys,xs.length);

		polyList[count] = p;
		count++;
	}

	// returns the midpoint as an array [x,y] of any line given the coordinates
	public static int[] getMidpoint(int ax, int ay, int bx, int by)
	{
		//TODO
		return null;
	}
}
