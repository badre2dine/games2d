package games2d;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Thread{
	final static public int RIGHT=1;
	final static public int LEFT=2;
	private int  x,y,width,hight;
	private Map<String,Animation> listAnimations;
	int currentNumberRep;
	private Animation currentAnimation;
	private int direction;
	boolean stop=true;
	public Player( Animation defaultAnimation,int x, int y, int width, int hight) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.hight = hight;
		this.direction=1;
		this.listAnimations=new HashMap<String,Animation>();
		this.listAnimations.put("idle",defaultAnimation);
		this.currentAnimation=defaultAnimation;
	}
	
	public void run()
	{
		while(true  )
		{
			
			try {
				Thread.sleep(1000/this.currentAnimation.getFps());
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			if(stop && this.currentAnimation.suivant() && this.currentNumberRep>0  )
			{
				if(--this.currentNumberRep<= 0)
				{
					stop=false;
				}
				
			}
			peraFrame();
		}
		
	}
	
	void setAnimation(String name,Animation animation)
	{
		this.listAnimations.put(name,animation);
	}
	void changeAnimation(String name)
	{
		
		
		
		if(this.currentAnimation!=this.listAnimations.get(name))
			{
			this.currentAnimation.reset();
			this.currentAnimation=this.listAnimations.get(name);
			
			}
		this.currentNumberRep=0;
		stop=true;
	}
	
	public void peraFrame()
	{
		
	}
	public void changeAnimation(String name,int numberrep)
	{
		
		if(this.currentAnimation!=this.listAnimations.get(name))
			{
			this.currentAnimation.reset();
			this.currentAnimation=this.listAnimations.get(name);
			
			}
		stop =true;
		this.currentNumberRep=numberrep;

		
	}
	void draw(Graphics g)
	{
		g.drawImage(	this.direction==RIGHT?this.currentAnimation.getCurrentimg():mirrorImageX(this.currentAnimation.getCurrentimg()),
				x, y, x+	this.width, y+	this.hight, 0, 0, 	this.currentAnimation
				.getCurrentimg()
				.getWidth(), 	
				this.currentAnimation.getCurrentimg().getHeight(), null);

	}
	void changeIdle()
	{
		
		
	}
	void changeDirection(int direction)
	{
		this.direction=direction;
	}
	private Image mirrorImageX(BufferedImage img) {
		
		BufferedImage mimg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		 for(int y = 0; y < img.getHeight(); y++){
		      for(int lx = img.getWidth()-1; lx >=0; lx--){
		        //lx starts from the left side of the image
		        //rx starts from the right side of the image
		        //get source pixel value
		        int p = img.getRGB(lx, y);
		        //set mirror image pixel value - both left and right
		        mimg.setRGB(img.getWidth()-1-lx, y, p);
		        
		      }
		    }
		
		return mimg;
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public int getHight() {
		return hight;
	}

	public Animation getCurrentAnimation() {
		return currentAnimation;
	}
	
}
