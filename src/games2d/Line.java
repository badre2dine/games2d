package games2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

class Line {


static ArrayList<Line> lines =new ArrayList<Line>(); 

    float x1,x2,y1,y2;
    float a,b,c;
    float x=Float.POSITIVE_INFINITY;

    
    void draw(Graphics g)
    {

        Graphics2D g2 = (Graphics2D) g;

        g2.drawLine((int)x1,(int) y1, (int)x2, (int)y2);
        
   }
    Line(float x1,float y1,float x2, float y2,float a, float b)
    {
        
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
       if(x1==x2)
       {
        this.a=Float.POSITIVE_INFINITY;
      
            x=x1;        
       }else 
       if(this.x2==Float.POSITIVE_INFINITY)
       {
           this.b=this.y1;
       }
       else{
          this.a=a;
      this.b=b;
       
        x=Float.POSITIVE_INFINITY;
       }
      
       
    }
    Line(float x1,float y1,float x2, float y2)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
       if(x1==x2)
       {
        this.a=Float.POSITIVE_INFINITY;
            x=x1;        
       }else 
       if(this.x2==Float.POSITIVE_INFINITY)
       {
           this.b=this.y1;
       }
       else{ a=(y2-y1)/(x2-x1);
        b=y1-x1*a;x=Float.POSITIVE_INFINITY;
       }
       
       
    }
    
    float Detection()
    {


        float p[];
        float min=1500,dist=0;
       
        for(Line l :  lines)
        {

           
            if((p=this.intersection(l) )!=null )
            {
                dist= (float) Math.sqrt((this.x1 - p[0]) * (this.x1 - p[0]) + (this.y1 - p[1]) * (this.y1 - p[1]));
                if(dist<min)min=dist;
            }
          
        }

   
        return min;
        
    }
    void update()
    {
        if(this.x1==this.x2)
        {
 
             this.x=this.x1;        
        }
        else 
        if(this.x2==Float.POSITIVE_INFINITY)
        {
            this.b=this.y1;
        }
        else{ a=(this.y2-this.y1)/(this.x2-this.x1);
         b=this.y1-this.x1*a;this.x=Float.POSITIVE_INFINITY;
        }

    }
    void update(float x1,float y1)
    {

        this.x1+=x1;
       this.y1+=y1;
       this.x2+=x1;
       this.y2+=y1;
        if(this.x1==this.x2)
       {

            this.x=this.x1;        
       }
       else 
       if(this.x2==Float.POSITIVE_INFINITY)
       {
           this.b=this.y1;
       }
       else{ a=(this.y2-this.y1)/(this.x2-this.x1);
        b=this.y1-this.x1*a;this.x=Float.POSITIVE_INFINITY;
       }
       
    }
    float[] intersection(Line line)
    {
        
        
        float Ip[]=  new float[2];
        
        if(this.x==Float.POSITIVE_INFINITY && line.x==Float.POSITIVE_INFINITY)
        {
         
            if(line.a==this.a)return null;

        Ip[0] = (this.b - line.b) / (line.a - this.a);
        Ip[1] = Ip[0]*line.a+line.b;
       
        }
        else if (this.x==Float.POSITIVE_INFINITY )   
        {
           
            Ip[0]=line.x1; 
            Ip[1] = Ip[0]*this.a+this.b;
            
        }
        else 
        {
           
            Ip[0]=this.x1; 
            Ip[1] = Ip[0]*line.a+line.b;
          
        }
       
         if(
            min(line.x1,line.x2)<=Ip[0] && Ip[0]<=max(line.x1,line.x2) &&  min(line.y1,line.y2)<=Ip[1] && Ip[1]<=max(line.y1,line.y2)
          && min(this.x1,this.x2)<=Ip[0] && Ip[0]<=max(this.x1,this.x2) &&  min(this.y1,this.y2)<=Ip[1] && Ip[1]<=max(this.y1,this.y2) )
        {
            
           
            
            return Ip;
        }
        
      
        

        return null;
    }

    float min(float a,float b)
    {
        return a<b?a:b;

    }
    float max(float a,float b)
    {
        return a>b?a:b;

    }
    static void add(Line l)
    {
        lines.add(l);

    }
}