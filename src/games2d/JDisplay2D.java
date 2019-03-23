package games2d;

import javax.swing.JFrame;

abstract class JDisplay2D extends JFrame {

    private static final long serialVersionUID = 1L;
    private int fps;

    JDisplay2D() {

        this.setSize(720, 480);
        init();

        while (true) {

            try {
                Thread.sleep(1000 / fps);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.loop();
        }

    }

    abstract public void init();
    
    abstract public void loop();


}