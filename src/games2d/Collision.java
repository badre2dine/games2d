package games2d;

public class Collision {

	int x;
	int y;
	private int width;
	private int height;
	
	public Collision(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	boolean isHit(Collision c)
	{
		if(
				x<c.x+c.width && c.x+c.width<x+width && y <c.y+c.height && c.y+c.height<y+height
				|| x<c.x && c.x<x+width && y<c.y && c.y<y+height
				|| x<c.x && c.x<x+width && y<c.y+c.height && c.y+c.height<y+height
				|| x<c.x+c.width && c.x+c.width<x+width && y<c.y && c.y<y+height
		)
		return true;
		return false;
	}

}
