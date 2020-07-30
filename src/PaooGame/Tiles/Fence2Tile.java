package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class Fence2Tile extends Tile
    \brief Abstractizeaza notiunea de dala de tip gard cu flori.
 */
public class Fence2Tile extends Tile {
    /*! \fn public Fence2Tile(int id)
    \brief Constructorul de initializare al clasei

    \param id Id-ul dalei util in desenarea hartii.
 */
    public Fence2Tile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.fence2, id);
    }

    /*! \fn public boolean IsSolid()
//        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true;
    }
}
