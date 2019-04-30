package games2d;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Collision {

	float x;
	float y;
	private float width;
	private float height;
	Objet objet;
	private static ArrayList<Collision> Allcollisions = new ArrayList<Collision>();

	public Collision(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Collision(int x, int y, int width, int height, Objet o) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.objet = o;
		Allcollisions.add(this);
	}

	void update(float dx, float dy) {

		this.x += dx;
		this.y += dy;

	}

	boolean isHit() {

		for (Collision c : Allcollisions)
			if (x <= c.x + c.width && c.x + c.width <= x + width && y <= c.y + c.height && c.y + c.height <= y + height
					|| x <= c.x && c.x <= x + width && y <= c.y && c.y <= y + height
					|| x <= c.x && c.x <= x + width && y <= c.y + c.height && c.y + c.height <= y + height
					|| x <= c.x + c.width && c.x + c.width <= x + width && y <= c.y && c.y <= y + height)
				return true;
		return false;
	}

	ArrayList<Objet> hitBy()
	{
		ArrayList<Objet> objets = new ArrayList<Objet>();
		for( Collision c : Allcollisions)
		{
			if(
				c!=this &&(
				x<=c.x+c.width && c.x+c.width<=x+width && y <=c.y+c.height && c.y+c.height<=y+height
				|| x<=c.x && c.x<=x+width && y<=c.y && c.y<=y+height
				|| x<=c.x && c.x<=x+width && y<=c.y+c.height && c.y+c.height<=y+height
				|| x<=c.x+c.width && c.x+c.width<=x+width && y<=c.y && c.y<=y+height ||
				c.x<=this.x+this.width && this.x+this.width<=c.x+c.width && c.y <=y+this.height && y+height<=c.y+c.height
				|| c.x<=x && x<=c.x+c.width && c.y<=y && y<=c.y+c.height
				|| c.x<=x && x<=c.x+c.width && c.y<=y+height && y+height<=c.y+c.height
				|| c.x<=x+width && x+width<=c.x+c.width && c.y<=y && y<=c.y+c.height
		))
		objets.add(c.objet);
		}
		return objets;

	}

}
