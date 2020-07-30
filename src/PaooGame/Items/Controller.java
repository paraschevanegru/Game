package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.LinkedList;
/*! \class public class Controller
    \brief Controleaza pixieDust-ul.
 */
public class Controller {
    private LinkedList<PixieDust> b =new LinkedList<PixieDust>();
    PixieDust tempPixieDust;
    RefLinks refLink;
    /*! \fn public PixieDust(RefLinks refLink, double x, double y, int direction)
        \brief Constructorul aferent clasei.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
    */
    public Controller(RefLinks refLink){

        this.refLink = refLink;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pixieDust-ului.
    */
    public void Update() {
        if (!b.isEmpty()) {
            for (int i = 0; i < b.size(); i++)
                tempPixieDust = b.get(i);
            if(tempPixieDust.GetY() < 0)
                removeBullet(tempPixieDust);
            tempPixieDust.Update();
        }
    }

    /*! \fn  public void Draw(Graphics g)
        \brief Deseneaza pixieDust-ul.
        \param g Contextul grafic in care trebuie efectuata desenarea entitatilor.
    */
    public void Draw(Graphics g){
        if (!b.isEmpty()) {
            for (int i = 0; i < b.size(); i++)
                tempPixieDust = b.get(i);
            tempPixieDust.Draw(g);
        }
    }
    /*! \fn  public void addBullet(PixieDust block)
        \brief Adauga pixieDust-ul.
     */
    public void addBullet(PixieDust block){
        b.add(block);
    }
    /*! \fn  public void addBullet(PixieDust block)
        \brief Sterge pixieDust-ul.
     */
    public void removeBullet(PixieDust block){
        b.remove(block);
    }
    public LinkedList<PixieDust> getBullets() {
        return b;
    }
}
