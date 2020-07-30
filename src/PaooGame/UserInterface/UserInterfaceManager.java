package PaooGame.UserInterface;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/*! \class UserInterfaceManager
    \brief Administreaza obiectele folosite pentru interfata cu utilizatorul.

 */
public class UserInterfaceManager {
    private RefLinks refLink;
    private final ArrayList<UserInterfaceObject> obj;

    /*! \fn public UserInterfaceManager(RefLinks refLinks)
        \brief Constructorul aferent clasei.
     */
    public UserInterfaceManager(RefLinks refLinks) {
        this.refLink = refLinks;
        obj = new ArrayList<UserInterfaceObject>();
    }

    /*! \fn public void Update()
        \brief Actualizeaza obiectele folosite pentru interfata cu utilizatorul.
     */
    public void Update() {
        for (UserInterfaceObject o : obj)
            o.Update();
    }

    /*! \fn  public void Draw(Graphics g)
        \brief Deseneaza obiectele folosite pentru interfata cu utilizatorul.
        \param g Contextul grafic in care trebuie efectuata desenarea obiectelor.
     */
    public void Draw(Graphics g) {
        for (UserInterfaceObject o : obj)
            o.Draw(g);
    }

    /*! \fn public void onMouseMove(MouseEvent e)
        \brief Vedem daca mouse-ul utilizatorului trece peste un obiect.

        \param e obiectul eveniment de mouse
     */
    public void onMouseMove(MouseEvent e) {
        for (UserInterfaceObject o : obj)
            o.onMouseMove(e);
    }

    /*! \fn public void onMouseRelease(MouseEvent e)
        \brief Vedem daca obiectul a fost apasat sau nu.

        \param e obiectul eveniment de mouse
     */
    public void onMouseRelease(MouseEvent e) {
        for (UserInterfaceObject o : obj)
            o.onMouseRelease(e);
    }

    /*! \fn  public void addObj(UserInterfaceObject o)
        \brief Adauga obiectele folosite pentru interfata cu utilizatorul intr-un array list.
     */
    public void addObj(UserInterfaceObject o) {
        obj.add(o);
    }

    /*! \fn public void removeObj(UserInterfaceObject o)
        \brief Sterge obiectele folosite pentru interfata cu utilizatorul.
     */
    public void removeObj(UserInterfaceObject o) {
        obj.remove(o);
    }

}
