package games2d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

public class Animation {
	private ReentrantLock lock = new ReentrantLock();
	public final static int DEFAULT=0;
	private BufferedImage images[] ;
	private int currentimg=0;
	private int fps;
	private int  size;
	public Animation(String name,String format,int size,int time,int fps)
	{
		int t2=((int)(time/1000)==0)?1:time/1000;
		this.size=size;
		if(fps==Animation.DEFAULT)this.fps=120;
		else if(time==Animation.DEFAULT)this.fps=fps;
		else this.fps=(size)/t2;
		this.currentimg=0;
		this.images=new BufferedImage[size];
		for(int i=0;i<size;i++)
			try {
				this.images[i] = ImageIO.read(new File(name+(i)+"."+format));
			} catch (IOException e) {
			}
		
	}
	
	
	

	public BufferedImage getCurrentimg() {
		
		return images[currentimg];
		 
	}

	public int getFps() {
		return fps;
	}

	public int getSize() {
		return size;
	}
	 public boolean suivant()
	 {
		 
		 return (this.currentimg=(this.currentimg+1)%size)==size-1;
		
	 }
	 public void reset()
	 {
		 this.currentimg=0;
	 }
}
