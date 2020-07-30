package PaooGame.Items;

import PaooGame.Graphics.Animations;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        deplasarea
        atacul
        dreptunghiul de coliziune
 */
public class Hero extends Character {

    public  PixieDust pixieDust;
    ///Animatiile eroului
    private final Animations aDown;
    private final Animations aUp;
    private final Animations aLeft;
    private final Animations aRight;
    /// Eroul se uita initial in jos
    public static int direction = 3;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */

    public Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

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

        ///Initializare animatii
        aDown = new Animations(500, Assets.hero_down);
        aUp = new Animations(500, Assets.hero_up);
        aLeft = new Animations(500, Assets.hero_left);
        aRight = new Animations(500, Assets.hero_right);

    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si animatiile eroului.
     */
    @Override
    public void Update() {
        //Actualizare animatii
        aDown.Update();
        aUp.Update();
        aLeft.Update();
        aRight.Update();
        pixieDust = new PixieDust(refLink, GetX(), GetY(), 48, 48, direction);
        ///Verifica daca a fost apasata o tasta
        Movement();

        ///Actualizeaza pozitia
        Move();

        ///Verifica daca eroul a ajuns la pozitia care indica castigarea jocului sau daca trece la urmatorul nivel
        checkWinPosition();

        ///Centreaza game camera pe erou
        refLink.GetGameCamera().centerOnEntity(this);
    }

    /*! \fn protected boolean collisionTile(int x, int y)
        \brief Returneaza true atunci cand gaseste o dala de tip solid, iar false in caz contrar.
    */
    protected boolean collisionTile(int x, int y) {

        return refLink.GetMap().GetTile(x, y).IsSolid();
    }

    /*! \fn public boolean checkWinPosition()
        \brief Verifica daca eroul a ajuns la pozitia care indica castigarea jocului sau daca trece la urmatorul nivel.
    */
    public boolean checkWinPosition(){
        Checkpoint str = refLink.GetMap().getItemManager().getCheckpoint();
        if(ItemCollision(xMove, 0f) && (str instanceof Checkpoint) && refLink.GetMap().lvl.equals("level_1")){

             State.SetState(refLink.GetGame().nextLevelState);
            return true;

        }else if(ItemCollision(xMove, 0f) && (str instanceof Checkpoint) && refLink.GetMap().lvl.equals("level_2")){
            refLink.GetMyMouse().setUserInterfaceManager(null);
            State.SetState(refLink.GetGame().winState);
            return true;
        }
        else{
            return false;
        }

    }
    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X si stabileste coliziunile.
    */
    public void MoveX() {
        ///Determinarea mai usor a colturilor care trebuie pt coliziuni
        if (xMove > 0) {
            int xTemp = (int) (x + xMove + normalBounds.x + normalBounds.width) / Tile.TILE_WIDTH;

            if (!collisionTile(xTemp, (int) (y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTile(xTemp, (int) (y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = xTemp * Tile.TILE_WIDTH - normalBounds.x - normalBounds.width - 1;
            }
        } else if (xMove < 0) {
            int xTemp = (int) (x + xMove + normalBounds.x) / Tile.TILE_WIDTH;

            if (!collisionTile(xTemp, (int) (y + normalBounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionTile(xTemp, (int) (y + normalBounds.y + normalBounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = xTemp * Tile.TILE_WIDTH + Tile.TILE_WIDTH - normalBounds.x;
            }
        }


    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y si stabileste coliziunile.
    */
    public void MoveY() {
        ///Determinarea mai usor a colturilor care trebuie pt coliziuni
        if (yMove < 0) {
            int yTemp = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (!collisionTile((int) (x + bounds.x) / Tile.TILE_WIDTH, yTemp) &&
                    !collisionTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, yTemp)) {
                y += yMove;
            } else {
                y = yTemp * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - normalBounds.y;
            }
        } else if (yMove > 0) {
            int yTemp = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (!collisionTile((int) (x + bounds.x) / Tile.TILE_WIDTH, yTemp) &&
                    !collisionTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, yTemp)) {
                y += yMove;
            } else {
                y = yTemp * Tile.TILE_HEIGHT - normalBounds.y - normalBounds.height - 1;
            }
        }

    }

    /*! \fn private void Movement()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void Movement() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up) {
            yMove = -speed;
            direction = 4;
        }
        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down) {
            yMove = speed;
            direction = 3;
        }
        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
            direction = 2;
        }
        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            xMove = speed;
            direction = 1;
        }
        if(refLink.GetKeyManager().space && refLink.GetMap().lvl.equals("level_2")){
            pixieDust.active = true;
            refLink.GetMap().getController().addBullet(pixieDust);
        }
        if(!pixieDust.active){
            refLink.GetMap().getController().removeBullet(pixieDust);
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(getAnimationFrame(), (int) (x - refLink.GetGameCamera().getxOffset()), (int) (y - refLink.GetGameCamera().getyOffset()), width, height, null);
        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
//        g.setColor(Color.blue);
//        g.fillRect((int)(x + normalBounds.x - refLink.GetGameCamera().getxOffset()), (int)(y + normalBounds.y - refLink.GetGameCamera().getyOffset()), normalBounds.width, normalBounds.height);
    }

    /*! \fn private BufferedImage getAnimationFrame()
        \brief Returneaza frame-ul respectiv pozitie in care eroul se deplaseaza.
    */
    private BufferedImage getAnimationFrame() {
        if (xMove < 0) {
            return aLeft.getFrames();
        } else if (xMove > 0) {
            return aRight.getFrames();
        } else if (yMove < 0) {
            return aUp.getFrames();
        } else {
            return aDown.getFrames();
        }
    }
}
