package games2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class GA {

   static void  nextGeneration(ArrayList<Car> population)
    {
        ArrayList<Car>  crosover=new ArrayList<Car>();
        int size=population.size();
        Random r=new Random();
        float totalFitness=0;
        float maxfitnss=0;
       int sizecrossover= (int) (size * 0.8);
       int sizemutation=(int) (size * 0.2);
        Collections.sort(population);
        for(int i=population.size()-1;i>=0;i--)
        {
          test.print(population.get(i).numero+" "+population.get(i).maxscore+" "+population.get(i).best+"  "+population.get(i).brain.hidenOutput.data[0][0]);
        }

        for(int i=0;i<sizecrossover;i++) 
        {

          population.remove(population.size()-1);

        }
        for(int i=0;i<sizecrossover/2;i++)
        {

          population.add(population.get(i).Crosover(population.get(i+1)));
       
        }
        population.add(population.get(0).Crosover(population.get(2)));
        for(int i=0;i<sizemutation+sizecrossover/4;i++) 
        {
         
          population.get(i).mutate();

        }
        size=size-population.size();
        for(int i=0;i<size;i++) 
        {
         
          population.add(new Car(30, 200, 20, 20, population.get(0).race));

        }
        /*
       for(Car c :population)
       {
           if(c.score>maxfitnss)maxfitnss=c.score;
        totalFitness+=c.score*c.score;
       }
       for(Car c :population)
       {
           crosover.add(poolSelection(population, totalFitness));
       }
      
       /*
       for(Car c :population)
       {
           if(r.nextFloat()<=((float)c.score)/totalFitness)
           {
       
            
            crosover.add( poolSelection(population, totalFitness));
          }
           else{
           
           c= c.mutate();
       }

    }
       for(int i=0;i<crosover.size()-1;i++)
       {
        population.add(crosover.get(i).Crosover(crosover.get(i+1)));

       }
       size=population.size()-size;
       for(int i=0;i<size;i++)
       {
           int j=r.nextInt(crosover.size());
        population.remove(j);

       }
       */
      
      //population=crosover;
        System.out.println(population.size());
    }

  static  ArrayList<Car>  selection(ArrayList<Car> population,int size)
    {
        int i=0;
        ArrayList<Car>  newgeneration=new ArrayList<Car>();
        Random r=new Random();
        float totalFitness=0;
       for(Car c :population)
       {
           
        totalFitness+=c.score;
       }
       for(Car c :population)
       {
           if(r.nextFloat()<((c.score)/totalFitness))
           newgeneration.add(c);
           i++;
           if(i==size)break;
           
       }
        return newgeneration;
    }
    static  public Car poolSelection(ArrayList<Car> population, float totalFitness) {
        // Start at 0
        int index = 0;
        Random r = new Random();
        // Pick a random number between 0 and 1
        float rr = r.nextFloat();

        // Keep subtracting probabilities until you get less than zero
        // Higher probabilities will be more likely to be fixed since they will
        // subtract a larger number towards zero
        while (rr > 0) {
            rr -= ((float) population.get(index).score*population.get(index).score / totalFitness);
          // And move on to the next
        
          index += 1;
        }
      
        // Go back one
     
      
        // Make sure it's a copy!
        // (this includes mutation)
        return population.get(--index).mutate();
      }
}