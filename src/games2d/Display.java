package games2d;

import javax.swing.JFrame;

public class Display extends JFrame{

	public Display (String name)
	{
		
		super(name);
		int w=1200,h=700;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(w, h);
	
		
		Envirnement en=new Envirnement(w,h);
		this.add(en);
		this.setVisible(true);
		while(true )
		{
			try {
				Thread.sleep(1000/120);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			en.repaint();
			
		}
		
	}
}
