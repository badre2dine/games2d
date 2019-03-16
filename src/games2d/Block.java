package games2d;

public class Block extends Player {

	int vie=3;
	Collision collision;
	public Block(Animation defaultAnimation, int x, int y, int width, int hight) {
		super(defaultAnimation, x, y, width, hight);
		// TODO Auto-generated constructor stub
		collision=new Collision(x,y,width,hight);
	}
	
	void hit()
	{
		 vie--;
		
	}
	boolean isDead()
	{
		return vie<=0;
	}
}
