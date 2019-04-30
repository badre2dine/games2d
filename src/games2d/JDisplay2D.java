package games2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
                g.clearRect(0,0,getWidth(),getHeight());
                loop(g);
            }

        };
            this.addMouseListener(new MouseListener(){
            
                @Override
                public void mouseReleased(MouseEvent e) {
                    JMouseReleased(e);
                }
            
                @Override
                public void mousePressed(MouseEvent e) {
                    JMousePressed(e);
                }
            
                @Override
                public void mouseExited(MouseEvent e) {
                    
                }
            
                @Override
                public void mouseEntered(MouseEvent e) {
                    
                }
            
                @Override
                public void mouseClicked(MouseEvent e) {
                    JMouseClicked(e);
                }
            });
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
    public void JMouseClicked(MouseEvent e)
    {}
    public void JMousePressed(MouseEvent e)
    {}
    public void JMouseReleased(MouseEvent e)
    {}
    @Override
    public int getHeight() {
        return super.getHeight()-38;
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    /**
     * @return the fps
     */
    public int getFps() {
        return fps;
    }

    /**
     * @param fps the fps to set
     */
    public void setFps(int fps) {
        this.fps = fps;
    }

    /**
     * @return the root
     */
    public JPanel getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(JPanel root) {
        this.root = root;
    }
    
    
    


}