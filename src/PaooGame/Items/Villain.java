package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Villain extends Character
    \brief Implementeaza notiunea de raufacator.

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul
        dreptunghiul de coliziune
 */
public class Villain extends Character {

    private BufferedImage image;
    private final int DIRECTION_UP = 0;
    private final int DIRECTION_DOWN = 1;
    private final int DIRECTION_LEFT = 2;
    private final int DIRECTION_RIGHT = 3;
    private final int[] dir = {DIRECTION_UP, DIRECTION_DOWN, DIRECTION_LEFT, DIRECTION_RIGHT};//Determine Direction
    private int DIRECTION = dir[(int) (4 * Math.random())];

    /*! \fn  public Villain(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Villain.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a raufacatorului.
        \param y Pozitia initiala pe axa Y a raufacatorului.
     */
    public Villain(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a raufacatorului
        image = Assets.villain_left;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    /*! \fn protected boolean collisionTile(int x, int y)
        \brief Returneaza true atunci cand gaseste o dala de tip solid, iar false in caz contrar.
    */
    protected boolean collisionTile(int x, int y) {
        return refLink.GetMap().GetTile(x, y).IsSolid();
    }

    /*! \fn public boolean VillainCollision(float xOffset, float yOffset)
        \brief Testeaza fiecare entitate din joc si determina cand eroul s-a atins cu raufacatorul(atac).
    */
    public boolean VillainCollision(float xOffset, float yOffset) {
        for (Item i : refLink.GetMap().getItemManager().getItems()) {

            if (i.equals(this))
                continue;
            if (i.CollisionBounds(0f, 0f).intersects(CollisionBounds(xOffset, yOffset))) {
                return true;
            }
            /// Este de ajuns sa verificam daca s-a intalnit cu eroul in AtttackBounds
            if (i.AttackBounds(0f, 0f).intersects(AttackBounds(xOffset, yOffset)) && (i instanceof Hero)) {
                refLink.GetMyMouse().setUserInterfaceManager(null);
                State.SetState(refLink.GetGame().gameOverState);
                return true;
            }

        }

        return false;
    }

    /*! \fn public void PixieDustCollision()
        \brief Verifica coliziunea cu bomba(pixie dust)
     */
    public void PixieDustCollision(){

        for (PixieDust b : refLink.GetMap().getController().getBullets()){
            if (getcol().intersects(b.getcol())) {
                dieV();
            }
        }
}

    /*! \fn public void dieV()
        \brief Sterge reufacatorul at cand atinge o bomba(pixie dust)
     */
    public void dieV(){
        refLink.GetMap().getItemManager().getItems().remove(this);
    }
    ///
    public Rectangle AttackBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + attackBounds.x + xOffset), (int) (y + attackBounds.y + yOffset), attackBounds.width, attackBounds.height);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
    */
    @Override
    public void Update() {
        MovementV();
        MoveV();
        PixieDustCollision();
    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia raufacatorului pe axa X si stabileste coliziunile.
    */
    public void MoveVX() {
        if (xMove > 0) {
            int xTemp = (int) (x + xMove + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH;

            if (!collisionTile(xTemp, (int) (y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTile(xTemp, (int) (y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = xTemp * Tile.TILE_WIDTH - normalBounds.x - normalBounds.width - 1;
                DIRECTION = DIRECTION_LEFT;
            }
        } else if (xMove < 0) {
            int xTemp = (int) (x + xMove + normalBounds.x) / Tile.TILE_WIDTH;

            if (!collisionTile(xTemp, (int) (y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTile(xTemp, (int) (y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = xTemp * Tile.TILE_WIDTH + Tile.TILE_WIDTH - normalBounds.x;
                DIRECTION = DIRECTION_RIGHT;
            }
        }
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia raufacatorului pe axa Y si stabileste coliziunile.
    */
    public void MoveVY() {
        if (yMove < 0) {
            int yTemp = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (!collisionTile((int) (x + bounds.x) / Tile.TILE_WIDTH, yTemp) &&
                    !collisionTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, yTemp)) {
                y += yMove;
            } else {
                y = yTemp * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - normalBounds.y;
                DIRECTION = DIRECTION_DOWN;
            }
        } else if (yMove > 0) {
            int yTemp = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (!collisionTile((int) (x + bounds.x) / Tile.TILE_WIDTH, yTemp) &&
                    !collisionTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, yTemp)) {
                y += yMove;
            } else {
                y = yTemp * Tile.TILE_HEIGHT - normalBounds.y - normalBounds.height - 1;
                DIRECTION = DIRECTION_UP;
            }
        }
    }


    /*! \fn public void Move()
        \brief Modifica pozitia raufacatorului si verifica coliziunile cu obiecte.
     */
    public void MoveV() {
        if (!VillainCollision(xMove, 0f))
            ///Modifica pozitia raufacatorului pe axa X.
            MoveVX();
        if (!VillainCollision(0f, yMove))
            ///Modifica pozitia raufacatorului pe axa Y.
            MoveVY();
    }


    /*! \fn private void MovementV()
        \brief Determina directia raufacatorului.
     */
    private void MovementV() {
        xMove = 0;
        yMove = 0;
        ///Verificare apasare tasta "sus"
        if (DIRECTION == DIRECTION_UP) {
            yMove = -2;
        }
        ///Verificare apasare tasta "jos"
        if (DIRECTION == DIRECTION_DOWN) {
            yMove = 2;
        }
        ///Verificare apasare tasta "left"
        if (DIRECTION == DIRECTION_LEFT) {
            xMove = -2;
            image = Assets.villain_left;
        }
        ///Verificare apasare tasta "dreapta"
        if (DIRECTION == DIRECTION_RIGHT) {
            xMove = 2;
            image = Assets.villain_right;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza raufacatorul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.GetGameCamera().getxOffset()), (int) (y - refLink.GetGameCamera().getyOffset()), width, height, null);

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        // g.fillRect((int) (x + normalBounds.x - refLink.GetGameCamera().getxOffset()), (int) (y + normalBounds.y - refLink.GetGameCamera().getyOffset()), normalBounds.width, normalBounds.height);
    }
}
