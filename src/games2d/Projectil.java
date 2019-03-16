package games2d;

public class Projectil extends Player {

	int speed;
	int direction;
	int time;
	Collision collision;
	Tank tank;
	public Projectil(Animation defaultAnimation, int x, int y, int width, int hight,Tank tank,int direction,int speed) {
		super(defaultAnimation, x, y, width, hight);
		// TODO Auto-generated constructor stub
		this.tank=tank;
		this.direction=direction;
		this.speed=speed;
		time=120;
		this.start();
		collision=new Collision(x,y,width,hight);
	}
	@Override
	public void peraFrame() {
		// TODO Auto-generated method stub
		switch(direction)
		{	
			case 0:this.setY(this.getY()-speed);break;
			case 1:this.setX(this.getX()+speed);break;
			case 2:this.setY(this.getY()+speed);break;
			case 3:this.setX(this.getX()-speed);break;
		}
		time--;
	}
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		collision.x+=x-getX();
		super.setX(x);
	}
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		collision.y+=y-getY();
		super.setY(y);
	}
	
	

}
