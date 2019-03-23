package games2d;

import java.awt.Graphics;

import javax.swing.JPanel;

class EnTest extends JPanel {

    int width, height;

    Line line;
    public EnTest(int width, int height)  {

        line =new Line(50,80,50,200);
        Line.lines.add(new Line(10,height-60,width,height-60));
        Line.lines.add(new Line(0,60,width,60));
        this.width = width;
        this.height = height;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        line.draw(g);        
        test.text("dadadsda", g, 10, 10);
        Line.lines.get(0).draw(g);
        Line.lines.get(1).draw(g);

    test.print(line.Detection());


    }

    
}