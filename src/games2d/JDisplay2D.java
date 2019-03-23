package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class JDisplay2D extends JFrame {

    private static final long serialVersionUID = 1L;
    private int fps = 60;

    JPanel root;

    JDisplay2D() {

        this.setSize(720, 480);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setName("JDispaly2D");
        this.root = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                loop(g);
            }

        };
            this.addKeyListener(new KeyListener(){
            
                @Override
                public void keyTyped(KeyEvent e) {
                    
                    JkeyTyped(e);
                    
                }
            
                @Override
                public void keyReleased(KeyEvent e) {
                    JkeyReleased(e);
                }
            
                @Override
                public void keyPressed(KeyEvent e) {
                    JkeyPressed(e);
                    
                }
            });
            
        {


        }
        this.setContentPane(root);
        init();
        this.setVisible(true);
        while (true) {

            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            root.repaint();
        }

    }

    abstract public void init();

    abstract public void loop(Graphics g);
    public void JkeyTyped(KeyEvent e)
    {}
    public void JkeyPressed(KeyEvent e)
    {}
    public void JkeyReleased(KeyEvent e)
    {}
    @Override
    public int getHeight() {
        return super.getHeight()-38;
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }
    
    
    


}