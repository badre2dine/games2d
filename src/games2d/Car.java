package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLContext;
import javax.swing.JPanel;

class Car implements Comparable<Car> {

    static long reference = 0;
    boolean best = false;
    int numero;
    ReentrantLock lock = new ReentrantLock();
    float width, height, x, y;
    Race race;
    boolean dead = false;
    NeuralNetwork brain;
    long maxscore = 0;
    long score;
    Line lines[] = new Line[5];

    Car(int x, int y, int width, int height, JPanel race) {

     brain = new NeuralNetwork(3, 20, 2);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(race instanceof Race)this.race = (Race) race;
        score=0;
        numero= (int) reference++;
        lines[0]=new Line(x+width/2,y,x+width/2,-4000);//up line
        lines[1]=new Line(x+width/2,y+height,x+width/2,4000);//down line
        lines[2]=new Line(x+width,y+height/2,4000,y+height/2);//right line
        lines[3]=new Line(x+width,y,Float.POSITIVE_INFINITY,Float.NEGATIVE_INFINITY,-1,y);//right line
        lines[4]=new Line(x+width,y+height,Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY,1,y+height);//right line
        
    }

    

	
    void updateline(float x1,float y1)
    {

        for(int i=0;i<5;i++)
        lines[i].update(x1, y1);

    }
    public void draw2(Graphics g){
        g.setColor(new Color(255,0, 0, 50));
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.drawLine( (int)(lines[3].x1), (int)(lines[3].y1), 400, (int)(400*lines[3].a+lines[3].b));
        g.drawLine( (int)(lines[4].x1), (int)(lines[4].y1), 400, (int)(400*lines[4].a+lines[4].b));
        g.drawLine( (int)(lines[0].x1), (int)(lines[0].y1), (int)(lines[0].x2), (int)(lines[0].y2));
        g.drawLine( (int)(lines[1].x1), (int)(lines[1].y1), (int)(lines[1].x2), (int)(lines[1].y2));
        g.drawLine( (int)(lines[2].x1), (int)(lines[2].y1), (int)(lines[2].x2), (int)(lines[2].y2));

    }
	public void draw(Graphics g) {
        if(x+this.width>=race.wallx[race.wallComming] && (y<race.Walls[race.wallComming] || y+this.height>race.Walls[race.wallComming]+race.btwnWall  ) || y<0 || y>race.height)
        {
             
             
             dead=true; 
            
                         
         }
         else if(true)
         {
             Matrix m=brain.guess(new Matrix(new float[]{y,race.wallx[race.wallComming]-x,race.Walls[race.wallComming]}));
           
           
             if(m.data[0][0]<m.data[0][1])
             {
                for(int i=0;i<5;i++)
                {
                    lines[i].update(0,10);

                }
                 y-=10;
             }
             else 
             {
                for(int i=0;i<5;i++)
                {
                    lines[i].update(0,10);

                }
                y+=10;
             }
          
         }
      if(!dead)
    {        
        score++;
       if(best) g.setColor(new Color(0,255, 0));
       else g.setColor(new Color(255,0, 0, 50));
        g.fillRect((int)x, (int)y,(int) width,(int) height);
        // g.drawLine( (int)(lines[3].x1), (int)(lines[3].y1), 400, (int)(400*lines[3].a+lines[3].b));
        // g.drawLine( (int)(lines[4].x1), (int)(lines[4].y1), 400, (int)(400*lines[4].a+lines[4].b));
        // g.drawLine( (int)(lines[0].x1), (int)(lines[0].y1), (int)(lines[0].x2), (int)(lines[0].y2));
        // g.drawLine( (int)(lines[1].x1), (int)(lines[1].y1), (int)(lines[1].x2), (int)(lines[1].y2));
        // g.drawLine( (int)(lines[2].x1), (int)(lines[2].y1), (int)(lines[2].x2), (int)(lines[2].y2));
       
            
    }else{
        if(score>maxscore)
        maxscore = score;
        
    }
   
}
    public Car mutate()
   {

   this.y=200;
   lines[0]=new Line(x+width/2,y,x+width/2,-4000);//up line
   lines[1]=new Line(x+width/2,y+height,x+width/2,4000);//down line
   lines[2]=new Line(x+width,y+height/2,4000,y+height/2);//right line
   lines[3]=new Line(x+width,y,Float.POSITIVE_INFINITY,Float.NEGATIVE_INFINITY,-1,y);//right line
   lines[4]=new Line(x+width,y+height,Float.POSITIVE_INFINITY,Float.POSITIVE_INFINITY,1,y+height);//right line

    this.brain.mutate((float) 0.01);
    numero= (int) reference++;
    
  return this;
   }
   public Car Crosover(Car p)
   {
    Car c= new Car((int)30,(int)200,(int)this.width,(int)this.height,this.race);
    
        c.brain=this.brain.crossover(p.brain);
    return c;
   }
    /**
     * @return the width
     */
    public int getWidth() {
        return (int)width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return (int)height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the x
     */
    public int getX() {
        return (int)x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return (int)y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    
    public void update() {

      
           
           
            
        }

    

    @Override
    public int compareTo(Car o) {
        if(score==o.score)  
        return 0;  
        else if(score>o.score)  
        return -1;  
        else  
        return 1;  
    }


}