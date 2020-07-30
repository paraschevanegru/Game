package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class BushTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip tufis.
 */
public class BushTile extends Tile {
    /*! \fn public BushTile(int id)
      \brief Constructorul de initializare al clasei

      \param id Id-ul dalei util in desenarea hartii.
   */
    public BushTile(int id) {

        /// Apel al constructorului clasei de baza
        super(Assets.bush, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true;
    }
}
