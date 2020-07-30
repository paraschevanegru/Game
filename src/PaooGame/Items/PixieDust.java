package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class PixieDust
    \brief Implementeaza notiunea de pixieDust.
 */
public class PixieDust extends Item{
    private float x;
    private float y;
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    private int direction;
    RefLinks refLink;
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/

    /*! \fn public PixieDust(RefLinks refLink, double x, double y, int direction)
    \brief Constructorul clasei aferente.
    \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
    \param x Pozitia initiala pe axa X a pixieDust-ului.
    \param y Pozitia initiala pe axa Y a pixieDust-ului.\
    \param direction
*/
    public PixieDust(RefLinks refLink, float x, float y, int width, int height, int direction) {
        super(refLink,x,y,width,height);
        this.refLink = refLink;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        xMove = 0;
        yMove = 0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza directia pixieDust-ului.
    */
    public void Update() {
        checkEntityCollisions();
    }

    public void checkEntityCollisions(){

        for (Item e : refLink.GetMap().getItemManager().getItems()){

            if (getcol().intersects(e.getcol()) && e instanceof Villain) {
                System.out.println(e);
                diePixie();
            }
        }
    }

    public void diePixie(){
        refLink.GetMap().getController().removeBullet(this);
    }
    /*! \fn public void Draw(Graphics g)
    \brief Randeaza/deseneaza pixie dust.

    \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
 */
    public void Draw(Graphics g) {
        g.drawImage(Assets.bullet, (int) (x - refLink.GetGameCamera().getxOffset()), (int) (y - refLink.GetGameCamera().getyOffset()), width, height, null);
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}