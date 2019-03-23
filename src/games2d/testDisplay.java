package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class testDisplay extends JDisplay2D {

    private static final long serialVersionUID = 1L;
    Color color;

    @Override
    public void init() {

        color = Color.red;
    }

    @Override
    public void loop(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, this.root.getWidth(), this.getHeight());
    }

    public void JkeyPressed(KeyEvent e) {
        // presse up key to chnage color of display ( easy code ;) )
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            color = color == Color.green ? Color.red : Color.green;
        }
       
    }

    

    public static void main(String[] args) {

        new testDisplay();
    }

   
    


    
}