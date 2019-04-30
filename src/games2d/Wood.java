package games2d;

import java.awt.Color;
import java.awt.Graphics;

class Wood extends Objet{


    public static final float[] velocity = null;

	Wood(int x,int y,int width ,int height)
    {
      super(x,y,width,height);  
      this.setCollision(x, y, width, height);
      this.physique=new Physique2D(10f,new float[]{0f,0f});
      
    }

    void draw(Graphics g)
    {
       this.update(0, 0);
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, (int)width, (int)height);

    }
    
}