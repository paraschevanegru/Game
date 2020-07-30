package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class PumpkinTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip dovleac.
 */
public class PumpkinTile extends Tile {
    /*! \fn public PumpkinTile(int id)
    \brief Constructorul de initializare al clasei

    \param id Id-ul dalei util in desenarea hartii.
 */
    public PumpkinTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.pumpkin, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true;
    }
}
