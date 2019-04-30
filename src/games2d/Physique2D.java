package games2d;

class Physique2D {


    float masse;
    float velocity[];
    
    long currentTime;
    static float gravity=20.8f;
    boolean fixe=true;
    private float vi=0;
    Physique2D(float masse ,float[] velocityI){
        currentTime=0;

        this.masse=masse;
        this.velocity=velocityI;

    }
     void elastic(Physique2D o)
    {
        if(!fixe){
        currentTime=0;
        if(o.fixe){

            float c=(masse*gravity/1000)+velocity[1]/8;
            c=c<velocity[1]?c:velocity[1];
            velocity[0]=-velocity[0];
            velocity[1]=-velocity[1]+c;
            test.print(velocity[1]);
            }
        else{
        velocity[0]=((this.masse-o.masse)*this.velocity[0]+2*o.masse*o.velocity[0])/(this.masse+o.masse);
        velocity[1]=((this.masse-o.masse)*this.velocity[1]+2*o.masse*o.velocity[1])/(this.masse+o.masse);
        test.print(velocity[1]);
            }

            vi=velocity[1];
        }
        else {

            velocity[0]=0;
            velocity[1]=0;
    }
    }

    void falling()
    {currentTime++;
        if(!fixe)
        {
          
       
       
       
        velocity[1]=vi+ gravity*((float)(currentTime));
       
        test.print(velocity[1]);
        }
    }
    
}