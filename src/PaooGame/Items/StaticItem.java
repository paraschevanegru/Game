package PaooGame.Items;

import PaooGame.RefLinks;
/*! \class public abstract class StaticItem extends Item
    \brief Implementeaza notiunea abstracta de obiect static(checkpoint).
 */
public abstract class StaticItem extends Item {
    /*! \fn public StaticItem(RefLinks refLink, float x, float y, int width, int height)
    \brief Constructorul de initializare al clasei Hero.

    \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
    \param x Pozitia initiala pe axa X a obiectului static.
    \param y Pozitia initiala pe axa Y a obiectului static.
    \param width Latimea obiectului static.
    \param height Inaltimea obiectului static.
 */
    public StaticItem(RefLinks refLink, float x, float y, int width, int height) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, width, height);
    }
}
