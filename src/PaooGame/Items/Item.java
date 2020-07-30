package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;


/*! \class Item
    \brief. Implementeaza notiunea abstracta de entitate activa din joc, "element cu care se poate interactiona: monstru, turn etc.".
 */
public abstract class Item {
    ///asa cum s-a mai precizat, coordonatele x si y sunt de tip float pentru a se elimina erorile de rotunjire
    ///ce pot sa apara in urma calculelor, urmand a se converti la intreg doar in momentul desenarii.
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected Rectangle normalBounds;   /*!< Dreptunghiul de coliziune aferent starii obisnuite(spatiul ocupat de entitate in mod normal), poate fi mai mic sau mai mare decat dimensiunea imaginii sale.*/
    protected Rectangle attackBounds;   /*!< Dreptunghiul de coliziune aferent starii de atac.*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    protected boolean active = true;
    /*! \fn public Item(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei

        \param  reflink Referinte "shortcut" catre alte referinte
        \param  x   Pozitia pe axa X a "tablei" de joc a imaginii entitatii.
        \param  y   Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.
        \param  width   Latimea imaginii entitatii.
        \param  height  Inaltimea imaginii entitatii.
     */
    public Item(RefLinks refLink, float x, float y, int width, int height) {
        this.x = x;             /*!< Retine coordonata pe axa X.*/
        this.y = y;             /*!< Retine coordonata pe axa X.*/
        this.width = width;     /*!< Retine latimea imaginii.*/
        this.height = height;   /*!< Retine inaltimea imaginii.*/
        this.refLink = refLink; /*!< Retine the "shortcut".*/

        ///Creaza dreptunghi de coliziune pentru modul normal, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        normalBounds = new Rectangle(0, 0, width, height);
        ///Creaza dreptunghi de coliziune pentru modul de atack, aici a fost stabilit la dimensiunea imaginii dar poate fi orice alta dimensiune
        attackBounds = new Rectangle(0, 0, width, height);
        ///Dreptunghiul de coliziune implicit este setat ca fiind cel normal
        bounds = normalBounds;
    }
    /*! \fn public boolean isActive()
    */
    public boolean isActive() {
        return active;
    }

    /*! \fn public void setActive(boolean active)
    */
    public void setActive(boolean active) {
        this.active = active;
    }

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    /*! \fn public boolean ItemCollision(float xOffset, float yOffset)
        \brief Testeaza fiecare entitate din joc si returneaza true daca exita o coliziune cu aceasta, false in caz contrar.
    */
    public boolean ItemCollision(float xOffset, float yOffset) {
        for (Item i : refLink.GetMap().getItemManager().getItems()) {

            if (i.equals(this))
                continue;
            if (i.CollisionBounds(0f, 0f).intersects(CollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }
    public Rectangle getcol(){
        return new Rectangle((int)(normalBounds.x + x),(int)(normalBounds.y + y), normalBounds.width, normalBounds.height);
    }
    /*! \fn public Rectangle CollisionBounds(float xOffset, float yOffset)
        \brief Returneaza dreptunghiul de coliziune al entitatii.
    */
    public Rectangle CollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }
    /*! \fn public Rectangle AttackBounds(float xOffset, float yOffset)
        \brief Returneaza dreptunghiul de coliziune pentru atac.
    */
    public Rectangle AttackBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + attackBounds.x + xOffset), (int) (y + attackBounds.y + yOffset), attackBounds.width, attackBounds.height);
    }

    /*! \fn public float GetX()
        \brief Returneaza coordonata pe axa X.
     */
    public float GetX() {
        return x;
    }

    /*! \fn public float GetY()
        \brief Returneaza coordonata pe axa Y.
     */
    public float GetY() {
        return y;
    }

    /*! \fn public float GetWidth()
        \brief Returneaza latimea entitatii.
     */
    public int GetWidth() {
        return width;
    }

    /*! \fn public float GetHeight()
        \brief Returneaza inaltimea entitatii.
     */
    public int GetHeight() {
        return height;
    }

    /*! \fn public float SetX()
        \brief Seteaza coordonata pe axa X.
     */
    public void SetX(float x) {
        this.x = x;
    }

    /*! \fn public float SetY()
        \brief Seteaza coordonata pe axa Y.
     */
    public void SetY(float y) {
        this.y = y;
    }

    /*! \fn public float SetWidth()
        \brief Seteaza latimea imaginii entitatii.
     */
    public void SetWidth(int width) {
        this.width = width;
    }

    /*! \fn public float SetHeight()
        \brief Seteaza inaltimea imaginii entitatii.
     */
    public void SetHeight(int height) {
        this.height = height;
    }

    /*! \fn public void SetNormalMode()
        \brief Seteaza modul normal de interactiune
     */
    public void SetNormalMode() {
        bounds = normalBounds;
    }

    /*! \fn public void SetAttackMode()
        \brief Seteaza modul de atac de interactiune
     */
    public void SetAttackMode() {
        bounds = attackBounds;
    }
}
