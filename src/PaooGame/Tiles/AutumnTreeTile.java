package PaooGame.Tiles;

import PaooGame.Graphics.Assets;
/*! \class public class AutumnTreeTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip autumnTree.
 */
public class AutumnTreeTile extends Tile {
    /*! \fn public AutumnTreeTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public AutumnTreeTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.autumnTree, id);
    }

    /*! \fn public boolean IsSolid()
        \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
     */
    @Override
    public boolean IsSolid() {
        return true;
    }
}