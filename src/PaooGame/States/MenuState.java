package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UserInterface.ClickListener;
import PaooGame.UserInterface.UserInterfaceImageButton;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State {
    private final UserInterfaceManager userInterfaceManager;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        userInterfaceManager = new UserInterfaceManager(refLink);
        refLink.GetMyMouse().setUserInterfaceManager(userInterfaceManager);
        isUserInterfaceActive = true;

        ///Play
        userInterfaceManager.addObj(new UserInterfaceImageButton(410, 160, 128, 64, Assets.button_play,false,
    new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;
                gameStarted = true;
                refLink.GetGame().playState.StartNewGame();
                State.SetState(refLink.GetGame().playState);
            }
        }));

        ///Settings
        userInterfaceManager.addObj(new UserInterfaceImageButton(370, 220, 200, 64, Assets.button_settings,false,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;
                refLink.GetGame().menuState.setUIManagerActive(true);
                State.SetState(refLink.GetGame().settingsState);
            }
        }));

        ///About
        userInterfaceManager.addObj(new UserInterfaceImageButton(850, 425, 100, 44, Assets.button_about,false,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;
                refLink.GetGame().menuState.setUIManagerActive(true);
                State.SetState(refLink.GetGame().aboutState);
            }
        }));


        ///Exit
        userInterfaceManager.addObj(new UserInterfaceImageButton(410, 280, 128, 64, Assets.button_exit,false,
                new ClickListener() {
            @Override
            public void onClick(){
                isUserInterfaceActive = false;
                System.exit(0);
            }
        }));
    }

    /*! \fn  public UserInterfaceManager getUiManager()
        \brief Returneaza userInterfaceManager.
     */
    public UserInterfaceManager getUiManager() {
        return userInterfaceManager;
    }


    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() {
        if(isUserInterfaceActive) {
            userInterfaceManager.Update();
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        BufferedImage bg2 = ImageLoader.LoadImage("/textures/BG2.png");
        g.drawImage(bg2, 0, 0, null);

        if (isUserInterfaceActive) {
            userInterfaceManager.Draw(g);
        }
    }
}
