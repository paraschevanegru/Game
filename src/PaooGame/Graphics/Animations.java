package PaooGame.Graphics;

import java.awt.image.BufferedImage;
/*! \class public class Animations
    \brief Implementeaza animatiile pentru eroul jocului.
 */
public class Animations {
    private final int speed;
    private int index;
    private long old_time, timer;
    private final BufferedImage[] frames;
    /*! \fn public Animations(int speed, BufferedImage[] frames)
        \brief Constructor aferent clasei.

        \param speed viteza animatiei .
        \param frmaes un array care contine toate frame-urile animatiei .
     */
    public Animations(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        old_time = System.currentTimeMillis();
        timer = 0;
    }
    /*! \fn public void Update()
    */
    public void Update() {
        timer += System.currentTimeMillis() - old_time;
        old_time = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length)
                index = 0;
        }
    }
    /*! \fn public BufferedImage getFrames()
        \brief Returneaza frame-ul curent al animatie.
     */
    public BufferedImage getFrames() {
        return frames[index];
    }
}
