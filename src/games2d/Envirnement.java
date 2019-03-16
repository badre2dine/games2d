package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Envirnement extends JPanel {
	private int width,height;
	BufferedImage arrier;
	private Player person;
	private Tank Tank;
	
	private Random r=new Random();
	private ArrayList<Projectil> projectils=new ArrayList<Projectil>() ;
	private ArrayList<Block> blocks=new  ArrayList<Block>();
	Block block;
	int x,y;
	public Envirnement(int width, int height) {
		super();
		x=0;y=0;
		this.width = width;
		this.height = height;
		Tank=new Tank(new Animation("tank/right_","png",1,Animation.DEFAULT,Animation.DEFAULT),120,255,200,200,8 );
		Tank.start();
		
		blocks.add(new Block(new Animation("block","png",1,Animation.DEFAULT,Animation.DEFAULT),255,255,32,32 ));
		Tank.setAnimation("upright", new Animation("tank/right_","png",10,Animation.DEFAULT,60) );
		Tank.setAnimation("rightdown", new Animation("tank/rightdown_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("downleft", new Animation("tank/downleft_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("leftup", new Animation("tank/leftup_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("upleft", new Animation("tank/upleft_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("leftdown", new Animation("tank/leftdown_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("downright", new Animation("tank/downright_","png",10,Animation.DEFAULT,Animation.DEFAULT) );
		Tank.setAnimation("rightup", new Animation("tank/rightup_","png",10,Animation.DEFAULT,Animation.DEFAULT) );

		/*	person=new Player( new Animation("dino/Idle","png",10,3000,10),120,255,150,150);
		person.setAnimation("run", new Animation("dino/Walk","png",10,3000,10));
		person.setAnimation("bas", new Animation("dino/Jump","png",10,3000,10));
		person.start();*/
		
		try {
			arrier = ImageIO.read(new File("tets.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);

				switch(arg0.getKeyCode())
				{
				
				case KeyEvent.VK_RIGHT:if(Tank.getDirection()==0){Tank.changeAnimation("upright",1);Tank.setDirection(1);}
										else if(Tank.getDirection()==2){Tank.changeAnimation("downright",1);Tank.setDirection(1);}
										else if(Tank.getDirection()==1)Tank.setX(Tank.getX()+Tank.getSpeed());
										else Tank.setX(Tank.getX()+Tank.getSpeed());break;
				case KeyEvent.VK_DOWN:if(Tank.getDirection()==1){Tank.changeAnimation("rightdown",1);Tank.setDirection(2);}
										else if(Tank.getDirection()==3){Tank.changeAnimation("leftdown",1);Tank.setDirection(2);}
										else if(Tank.getDirection()==2)Tank.setY(Tank.getY()+Tank.getSpeed());
										else Tank.setY(Tank.getY()+Tank.getSpeed());break;
				case KeyEvent.VK_LEFT:if(Tank.getDirection()==2){Tank.changeAnimation("downleft",1);Tank.setDirection(3);}
										else if(Tank.getDirection()==0){Tank.changeAnimation("upleft",1);Tank.setDirection(3);}
										else if(Tank.getDirection()==3)Tank.setX(Tank.getX()-Tank.getSpeed());
										else Tank.setX(Tank.getX()-Tank.getSpeed()); break;
				case KeyEvent.VK_UP:if(Tank.getDirection()==3){Tank.changeAnimation("leftup",1);Tank.setDirection(0);}
										else if(Tank.getDirection()==1){Tank.changeAnimation("rightup",1);Tank.setDirection(0);}
										else if(Tank.getDirection()==0)Tank.setY(Tank.getY()-Tank.getSpeed());
										else Tank.setY(Tank.getY()-Tank.getSpeed()); break;
				case KeyEvent.VK_SPACE:if(Tank.canAttak())projectils.add(new Projectil(new Animation("projectil","png",1,Animation.DEFAULT,60),Tank.canonX,Tank.canonY,6,12,Tank,Tank.getDirection(),6 ))  ;break;							
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
		
	}

	public void paintComponent(Graphics g)
	{
		int t=2;
		g.clearRect(0, 0, this.width, this.height);
		
		
		//g.drawImage(arrier, 0, 0, this.width, this.height, 0, 0, arrier.getWidth(), arrier.getHeight(), null);
		if(blocks.size()<2)
		{
			blocks.add(new Block(new Animation("block","png",1,Animation.DEFAULT,Animation.DEFAULT),r.nextInt(600),r.nextInt(400),32,32 ));

			
		}
		int i=0;
		for(Projectil p : projectils)
		{
			
			
		} 
		 i=0;
		for(Block p : blocks)
		{
			
			
			 
			
		}
		Tank.draw(g);
		//g.fillOval(Tank.canonX, Tank.canonY, 10, 10);
		//person.draw(g);
			//g.drawImage(this.person[2].getCurrentimg(),100, 100,null);
			//g.drawImage(person[3].getCurrentimg(), x, y, x+person[3].getCurrentimg().getWidth()/t, y+person[3].getCurrentimg().getHeight()/t, 0, 0, person[3].getCurrentimg().getWidth(), person[3].getCurrentimg().getHeight(), null);
			
			// TODO Auto-generated catch block
		
			   for(Projectil p : projectils)
				{
				   p.draw(g);
					for(Block b : blocks)
					{
						
						if(b.collision.isHit(p.collision)) {b.hit();projectils.remove(p); }

						
					}
					if(p!=null && p .time<0)
					{
				projectils.remove(0);
				}
			   
		   }
			   for(Block p : blocks)
				{
					
				   p.draw(g);
					if(p.isDead()) {blocks.remove(p); }
					
				}
		   
		
		
	}
	
}
