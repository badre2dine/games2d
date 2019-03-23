package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Race extends JPanel {

    /**
     *
     */

    private static final String BADR_TXT = "badr.txt";
    int[] Walls = new int[3];
    int wallx[] = { 600, 900, 1200 };
    int btwnWall = 100;
    int wallWidth = 100;
    int wallComming = 0;

    int gen=0;
    
    ObjectOutputStream oos;
    int taillep = 1000;
    int numberLives = taillep;
    ArrayList<Car> cars = new ArrayList<Car>();
    ObjectInputStream ois;
    Random r = new Random();
    private static final long serialVersionUID = 1L;
    public int width, height;

    Race(int width, int height) {
        this.width = width;
        this.height = height;
Line.lines.add(new Line(0,height,width,height));
Line.lines.add(new Line(0,0,width,0));     

        try {
            ois = new ObjectInputStream(new FileInputStream(BADR_TXT));
            oos = new ObjectOutputStream(new FileOutputStream(BADR_TXT));
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (int i = 0; i < taillep; i++) {
            cars.add(new Car(30, 200, 20, 20, this));
           /* try {
                cars.get(i).brain = (NeuralNetwork) ois.readObject();
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }

        
        for (int i = 0; i < 3; i++) {
            Walls[i] = r.nextInt(height / 2) + height / 8;
            Line.lines.add(new Line(wallx[i],0,wallx[i],Walls[i]));
            Line.lines.add(new Line(wallx[i],Walls[i]+btwnWall,wallx[i],height));
            Line.lines.add(new Line(wallx[i],Walls[i],wallx[i]+wallWidth,Walls[i]));
            Line.lines.add(new Line(wallx[i],Walls[i]+btwnWall,wallx[i]+wallWidth,Walls[i]+btwnWall));
        }

    }

    void kill()
    {

       
        for (int i = 0; i < taillep; i++) {
            cars.get(i).dead = true;
            
        }
        numberLives =0;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width, height);
        test.text("Generation : "+gen,g, width-100, 30);
        for (int i = 0; i < 3; i++)
            if (wallx[i] + wallWidth <= -1) {
                Walls[i] = r.nextInt(height / 2) + height / 8;
                wallx[i] = width;
                wallComming = (i + 1) % 3;
                if( Line.lines.get(i*4+2).x1<=-1)
                {
                    Line.lines.get(i).x1=width;
                    Line.lines.get(i).x2=width;
                    Line.lines.get(i+1).x1=width;
                    Line.lines.get(i+1).x2=width;
                    Line.lines.get(i+2).x1=width;
                    Line.lines.get(i+2).x2=width+wallWidth;
                    Line.lines.get(i+3).x1=width;
                    Line.lines.get(i+3).x2=width+wallWidth;
                }
            }
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 3; i++) {
            g.fillRect(wallx[i], 0, wallWidth, Walls[i]);

            g.fillRect(wallx[i], btwnWall + Walls[i], wallWidth, height - btwnWall - Walls[i]);

            
            wallx[i]-=5;
        }
        for(int i=2;i<Line.lines.size();i++)
        {
            Line.lines.get(i).x1--; 
            Line.lines.get(i).x2--;
            Line.lines.get(i).update();
        }

        for (Car c : cars) {
            c.draw(g);
            if (c.dead) {
                numberLives--;
            }

        }

        if (numberLives == 0) {
            wallx[0] = 300;
            wallx[1] = 600;
            wallx[2] = 900;
            for (int i = 0; i < 3; i++){
                Line.lines.get(i).x1=300*(i+1);
                Line.lines.get(i).x2=300*(i+1);
                Line.lines.get(i+1).x1=300*(i+1);
                Line.lines.get(i+1).x2=300*(i+1);
                Line.lines.get(i+2).x1=300*(i+1);
                Line.lines.get(i+2).x2=300*(i+1)+wallWidth;
                Line.lines.get(i+3).x1=300*(i+1);
                Line.lines.get(i+3).x2=300*(i+1)+wallWidth;
                Line.lines.get(i).update(); 
                Line.lines.get(i+1).update();
                Line.lines.get(i+2).update();
                Line.lines.get(i+3).update();
        }
            GA.nextGeneration(cars);gen++;
            int max = 0;
            for (int i = 0; i < taillep; i++) {
                cars.get(i).dead = false;
                if (cars.get(max).maxscore < cars.get(i).maxscore)
                    max = i;
                cars.get(i).score = 0;
                cars.get(i).y = 200;
            }
            cars.get(max).best = true;
            try {
                oos.writeObject(cars.get(max).brain);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                System.out.println(cars.size());
                wallComming=0;
                
            }
            numberLives=cars.size();
    }

  
 


}