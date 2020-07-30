package PaooGame.UserInterface;

import java.awt.*;
import java.awt.event.MouseEvent;
/*! \class UserInterfaceObject
    \brief Implementeaza notiunea abstracta de interactiune cu utilizatorul a jocului/programului(butoane).

 */
public abstract class UserInterfaceObject {

    protected float x, y;
    protected int width, height;
    protected boolean hovering = false;
    protected Rectangle bounds;

    /*! \fn  public UserInterfaceObject(float x, float y, int width, int height)
    \brief Constructorul aferent clasei.

    \param x, y pozitiile
    \param width, height dimensiunile
 */
    public UserInterfaceObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    //Metoda abstracta destinata actualizarii interactii cu utilizatorul
    public abstract void Update();

    ///Metoda abstracta destinata desenarii interactii cu utilizatorul
    public abstract void Draw(Graphics g);

    ///Metoda abstracta care recunoaste cand utilizatorul apasa pe un obiect
    public abstract void onClick();

    /*! \fn  public void onMouseMove(MouseEvent e)
        \brief Vedem daca mouse-ul utilizatorului trece peste un buton.

        \param e obiectul eveniment de mouse
    */
    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY())){
            hovering = true;
        }
        else{
            hovering = false;
        }
    }
    /*! \fn   public void onMouseRelease(MouseEvent e)
        \brief Vedem daca obiectul user interface a fost apasat sau nu.

        \param e obiectul eveniment de mouse
    */
    public void onMouseRelease(MouseEvent e) {
        if (hovering)
            onClick();

    }
    /*! \fn    public void onMouseSelectedButton(MouseEvent e)
        \brief

        \param e obiectul eveniment de mouse
    */
    public void onMouseSelectedButton(MouseEvent e){
        hovering = true;
    }

    /*! \fn public float getX()
        \brief Returneaza pozitia unui obiect user interface pe axa X.
    */
    public float getX() {
        return x;
    }

    /*! \fn public float getY()
        \brief Returneaza pozitia unui obiect user interface pe axa Y.
    */
    public float getY() {
        return y;
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea unui obiect user interface.
    */
    public int getHeight() {
        return height;
    }

    /*! \fn public int getWidth()
        \brief Returneaza latimea unui obiect user interface.
    */
    public int getWidth() {
        return width;
    }

    public boolean isHovering() {
        return hovering;
    }

    /*! \fn public float setX()
        \brief Seteaza pozitia unui obiect user interface pe axa X.
    */
    public void setX(float x) {
        this.x = x;
    }

    /*! \fn public float setY()
        \brief Seteaza pozitia unui obiect user interface pe axa X.
    */
    public void setY(float y) {
        this.y = y;
    }

    /*! \fn public int setWidth()
        \brief Seteaza latimea unui obiect user interface.
    */
    public void setWidth(int width) {
        this.width = width;
    }

    /*! \fn public int setHeight()
        \brief Seteaza inaltimea unui obiect user interface.
    */
    public void setHeight(int height) {
        this.height = height;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
