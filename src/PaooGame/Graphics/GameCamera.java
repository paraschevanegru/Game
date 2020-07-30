package PaooGame.Graphics;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \class public class GameCamera
    \brief Implementeaza notiunea de game camera.
 */
public class GameCamera {
    private final RefLinks refLinks;
    private float xOffset, yOffset;
    /*! \fn public GameCamera(RefLinks refLinks, float xOffset, float yOffset)
        \brief Constructor aferent clasei.

        \param refLinks Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param xOffset  .
        \param yOffset  .
     */
    public GameCamera(RefLinks refLinks, float xOffset, float yOffset) {
        this.refLinks = refLinks;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /*! \fn  public void BlankSpace()
        \brief Elimina spatiile albe folosindu-se de latimea si inaltimea hartii.
    */
    public void BlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;

        } else if (xOffset > refLinks.GetMap().GetWidth() * Tile.TILE_WIDTH - refLinks.GetWidth()) {
            xOffset = refLinks.GetMap().GetWidth() * Tile.TILE_WIDTH - refLinks.GetWidth();
        }
        if (yOffset < 0) {
            yOffset = 0;

        } else if (yOffset > refLinks.GetMap().GetHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight()) {
            yOffset = refLinks.GetMap().GetHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight();
        }
    }

    /*! \fn  public void centerOnEntity(Item e)
        \brief Centreaza game camera pe o entitate.

        \param e Referinta catre o entitate(eroul jocului).
    */
    public void centerOnEntity(Item e) {
        xOffset = e.GetX() - refLinks.GetWidth() / 2 + e.GetWidth() / 2;
        yOffset = e.GetY() - refLinks.GetHeight() / 2 + e.GetHeight() / 2;
        BlankSpace();
    }

    /*! \fn public float getxOffset()
        \brief Returneaza xOffset.
    */
    public float getxOffset() {
        return xOffset;
    }

    /*! \fn public voi setxOffset()
        \brief Seteaza xOffset.
    */
    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    /*! \fn public float getyOffset()
        \brief Returneaza yOffset.
    */
    public float getyOffset() {
        return yOffset;
    }

    /*! \fn public voi setyOffset()
        \brief Seteaza yOffset.
    */
    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
