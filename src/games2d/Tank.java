package games2d;

public class Tank extends Player{


	private int SpeedAttak=200;
	private long lasthit;
	public int canonX,canonY;
	private int canonIx,canonIy;
	private int direction=0;
	private int ecart =32;
	private int speed=8 ;
	public Tank(Animation defaultAnimation, int x, int y, int width, int hight,int speed) {
		super(defaultAnimation, x, y, width, hight);
		// TODO Auto-generated constructor stub
		canonIy=-37;canonIx=-3;
		canonX=x+width/2+canonIx;canonY=y+hight/2+canonIy;
		lasthit=System.currentTimeMillis()-1000;
	}
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		canonX=x+getWidth()/2+canonIx;
		super.setX(x);
	}
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		canonY=y+getHight()/2+canonIy;
		super.setY(y);
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
		
		switch(direction)
		{
		case 0:	canonIy=-37;canonIx=-3;break;
		case 1: canonIy=6;canonIx=26;break;
		case 2:canonIy=46;canonIx=-3;break;
		case 3:canonIy=6;canonIx=-36;break;
		}
		canonY=getY()+getHight()/2+canonIy;
		canonX=getX()+getWidth()/2+canonIx;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean canAttak()
	{
		System.out.println(System.currentTimeMillis()+" "+lasthit);
		if(System.currentTimeMillis()-lasthit>=this.SpeedAttak)
		
			{
			lasthit=System.currentTimeMillis();
			return true;
			
			}
		return false;
	}
	public void move()
	{
		
		
	}
	
}
