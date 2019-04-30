package games2d;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Objet /* extends Thread */ {

	protected float x, y, width, height;
	Collision collision;
	Physique2D physique;

	public Objet(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	void setCollision(int x, int y, int width, int height) {

		this.collision = new Collision(x, y, width, height, this);

	}

	void update(float dx, float dy) {
		ArrayList<Objet> objts;
		if ((objts = this.collision.hitBy()).size()!=0) {
			for(Objet o :objts)
			{this.physique.elastic(o.physique);
			test.print("not fall");}
		} else {

			this.physique.falling();

		}
		if (physique != null) {
			dx +=  physique.velocity[0];
			dy +=  physique.velocity[1];
		}
		this.x += dx/10000;
		this.y += dy/10000;
		this.collision.update(dx/10000,dy/10000);
	}
/*
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			update(0,0);
		}
	}
	*/
}
