package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UserInterface.ClickListener;
import PaooGame.UserInterface.UserInterfaceImageButton;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;
import java.awt.image.BufferedImage;
    /*! \class public class NextLevelState extends State
        \brief Implementeaza notiunea de nivel urmator.
     */
public class NextLevelState extends State{
    private final UserInterfaceManager userInterfaceManager;
    /*! \fn public NextLevelState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public NextLevelState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        userInterfaceManager = new UserInterfaceManager(refLink);
        refLink.GetMyMouse().setUserInterfaceManager(userInterfaceManager);
        isUserInterfaceActive = true;

        ///Return to menu
        userInterfaceManager.addObj(new UserInterfaceImageButton(200, 280, 200, 100, Assets.button_returnmenu,false,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;
                refLink.GetGame().menuState.setUIManagerActive(true);
                State.SetState(refLink.GetGame().menuState);
            }
        }));

        ///Next level
        userInterfaceManager.addObj(new UserInterfaceImageButton(600, 250, 200, 100, Assets.button_nextlevel,false,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = true;
                refLink.GetGame().menuState.setUIManagerActive(false);
                refLink.GetGame().playState.StartNextLevel();
                State.SetState(refLink.GetGame().playState);
            }
        }));
    }

    /*! \fn  public UserInterfaceManager getUiManager()
        \brief Returneaza userInterfaceManager.
    */
    @Override
    protected UserInterfaceManager getUiManager() {
        return userInterfaceManager;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta.
    */
    @Override
    public void Update() {
        if (isUserInterfaceActive){
            userInterfaceManager.Update();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
    */
    @Override
    public void Draw(Graphics g) {
        BufferedImage bg3 = ImageLoader.LoadImage("/textures/NEXTLEVEL.png");
        g.drawImage(bg3, 0, 0, null);
        if (isUserInterfaceActive){
            userInterfaceManager.Draw(g);
        }
    }
}
