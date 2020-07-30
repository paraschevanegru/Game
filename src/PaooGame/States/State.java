package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State {
    ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink;
    boolean isUserInterfaceActive = false;
    public static boolean gameStarted = false;

    public State(RefLinks refLink) {
        this.refLink = refLink;
    }

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state) {
        if (!(state instanceof PlayState)){
            state.setUIManagerActive(true);
        }
        state.SetUIManagerForMyMouse(state.getUiManager());
        previousState = currentState;
        currentState = state;
    }

    ///Metoda abstracta destinata returnarii userInterfaceManager
    protected abstract UserInterfaceManager getUiManager();

    /*! \fn public static State GetState()
        \brief Returneaza starea curenta a jocului.
     */
    public static State GetState() {
        return currentState;
    }

    ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update();

    ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g);

    ///Metoda abstracta
    public void setUIManagerActive(boolean UIManagerActive) {
       isUserInterfaceActive = UIManagerActive;
    }

    ///Metoda abstracta
    public void SetUIManagerForMyMouse(UserInterfaceManager userInterfaceManager){
        refLink.GetMyMouse().setUserInterfaceManager(userInterfaceManager);
    }
}
