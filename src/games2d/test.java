package games2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.Random;

public class test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Display("game");


		// long time;
		// Random r=new Random();
		// Matrix n=new Matrix(1,72,Matrix.MATRIX_RANDOM);
		// NeuralNetwork nn=new NeuralNetwork(72, 36, 5);
		// time=System.currentTimeMillis();
		// print("start");
		// for(int i=0;i<1000000;i++)nn.guess(n);
		// print("ende "+ (System.currentTimeMillis()-time));
		
		print(Float.POSITIVE_INFINITY>4100000);
		
	}

	static void text(String text,Graphics g,int x,int y)
	{

		if(g instanceof Graphics2D)
		{
		  Graphics2D g2 = (Graphics2D)g;
		  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		  RenderingHints.VALUE_ANTIALIAS_ON);
  
		  g2.drawString(text,x,y); 
		 }
	}
	static void print(Object o)
	{
		System.out.println(o);
	}
}
