package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class test2 extends JDisplay2D {

    int x = 50;

    @Override
    public void init() {

        this.setSize(1000,200);
        setFps(10);
    }

    @Override
    public void loop(Graphics g) {


        g.setColor(Color.red);
        g.fillRect(x, 5, 20, 20);
x++;
    }

    public static void main(String[] args) {

        new test2();
    }

    @Override
    public void JkeyPressed(KeyEvent e) {
       
       if(e.getKeyCode()==KeyEvent.VK_RIGHT) x+=10;
    }


}