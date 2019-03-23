package games2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Display extends JFrame{

	int speed=60;
	public Display (String name)
	{
		
		super(name);
		int w=800,h=500;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(w, h);
	
		
		Race en = new Race(w, h);
		this.add(en);
		this.setVisible(true);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				

				switch(arg0.getKeyCode())
				{
				
				case KeyEvent.VK_UP:en.cars.get(0).y--;
				en.cars.get(0).updateline(0,-1);
				;break;
				case KeyEvent.VK_DOWN:en.cars.get(0).y++;	en.cars.get(0).updateline(0,1);
				break;
				case KeyEvent.VK_RIGHT:en.cars.get(0).x++;	en.cars.get(0).updateline(1,0);
				
				;break;
				case KeyEvent.VK_LEFT:en.cars.get(0).x--;	en.cars.get(0).updateline(-1,0);
				break;
				case KeyEvent.VK_SPACE:en.kill();
            }
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//person.changeAnimation("idle");
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });
		while(true )
		{
			try {
				Thread.sleep(1000/90);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			en.repaint();
			
		}
		
	}
}
