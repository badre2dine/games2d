package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class testDisplay extends JDisplay2D {

    private static final long serialVersionUID = 1L;
    Color color;

    Wood ground;
    Wood piece;
    Wood piece2;

    ArrayList<Wood> pieces;
    @Override
    public void init() {
        this.setSize(1200,800);
        pieces=new ArrayList<Wood>();
        setFps(1000);
        piece2 = new Wood(100, 10, 20, 20);
        piece2.physique.fixe = false;
        piece2.physique.masse = 30F;
        piece = new Wood(50, this.getHeight() - 140, 20, 20);
        piece.physique.fixe = false;
        ground = new Wood(0, this.getHeight() - 100, this.getWidth(), 100);
        color = Color.red;
    }

    @Override
    public void loop(Graphics g) {

        ground.draw(g);
        piece.draw(g);
        piece2.draw(g);
        for(Wood w:pieces)w.draw(g);
    }

    public void JkeyPressed(KeyEvent e) {
        // presse up key to chnage color of display ( easy code ;) )
        if (e.getKeyCode() == KeyEvent.VK_UP) {

            piece2.physique.velocity[0] = 500;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            piece2.physique.velocity[0] = -500;

        }

    }

    public static void main(String[] args) {

        new Display("test");
    }

    @Override
    public void JkeyReleased(KeyEvent e) {
        piece2.physique.fixe = false;
        piece2.physique.velocity[1] = 0;
    }

    @Override
    public void JMousePressed(MouseEvent e) {
        pieces.add(new Wood(e.getX(),e.getY(), 20, 20));
        
        pieces.get(pieces.size()-1).physique.fixe=false;
        pieces.get(pieces.size()-1).physique.masse=1500;
        pieces.get(pieces.size()-1).velocity[0]=(float)8000;
    }

   
    


    
}