package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
/*! \class public class Checkpoint extends StaticItem
    \brief Implementeaza notiunea checkpoint.
 */
public class Checkpoint extends StaticItem {
    /*! \fn public StaticItem(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a obiectului static.
        \param y Pozitia initiala pe axa Y a obiectului static.
    */
    public Checkpoint(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        bounds.x = 10;
        bounds.y = (int) (height / 1.9f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia checkponit-ului.
     */
    @Override
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza checkpoint-ul.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.checkpoint, (int) (x - refLink.GetGameCamera().getxOffset()), (int) (y - refLink.GetGameCamera().getyOffset()), width, height, null);
    }
}
