package PaooGame.Input;

import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*! \class public class MyMouse implements MouseListener, MouseMotionListener
    \brief Gestioneaza intrarea (input-ul) de mouse.
 */
public class MyMouse implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed; /*!< Flag pentru left click si right click la mouse.*/
    private int xMouse, yMouse; /*!< Retin pozitia mouse-ului.*/
    private UserInterfaceManager userInterfaceManager;
    /*! \fn public MyMouse()
        \brief Constructorul clasei.
     */
    public MyMouse() {

    }
    /*! \fn  public void setUserInterfaceManager(UserInterfaceManager userInterfaceManager)
        \brief Seteaza userInterfaceManager.
     */
    public void setUserInterfaceManager(UserInterfaceManager userInterfaceManager) {
        this.userInterfaceManager = userInterfaceManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /*! \fn public void mousePressed(MouseEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de mouse apasat este generat.

         \param e obiectul eveniment de mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }
    /*! \fn public void mouseReleased(MouseEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de mouse eliberat este generat.

        \param e obiectul eveniment de mouse.
    */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;
        if (userInterfaceManager != null)
            userInterfaceManager.onMouseRelease(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
    /*! \fn public void mouseMoved(MouseEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de mouse miscat este generat.
        \brief Ne ajuta sa determina pozitia mouse-ului in orice moment.

        \param e obiectul eveniment de mouse.
    */
    @Override
    public void mouseMoved(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
        if (userInterfaceManager != null)
            userInterfaceManager.onMouseMove(e);

    }

}
