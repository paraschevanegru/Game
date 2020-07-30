package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class FenceTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip gard cu brad.
 */
public class FenceTile extends Tile {
    /*! \fn public FenceTile(int id)
     \brief Constructorul de initializare al clasei

     \param id Id-ul dalei util in desenarea hartii.
  */
    public FenceTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.fence1, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true;
    }
}
