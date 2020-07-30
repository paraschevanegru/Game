package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UserInterface.ClickListener;
import PaooGame.UserInterface.UserInterfaceImageButton;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State {
    private final UserInterfaceManager userInterfaceManager;
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        userInterfaceManager = new UserInterfaceManager(refLink);
        refLink.GetMyMouse().setUserInterfaceManager(userInterfaceManager);
        isUserInterfaceActive = true;
        ///Return to menu
        userInterfaceManager.addObj(new UserInterfaceImageButton(200, 280, 200, 100, Assets.button_returnmenu,false, new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;
                refLink.GetGame().menuState.setUIManagerActive(true);
                State.SetState(refLink.GetGame().menuState);
            }
        }));
    }

    /*! \fn  public UserInterfaceManager getUiManager()
        \brief Returneaza null.
     */
    public UserInterfaceManager getUiManager() {
        return userInterfaceManager;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update() {
        if(isUserInterfaceActive) {
            userInterfaceManager.Update();
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        BufferedImage bg5 = ImageLoader.LoadImage("/textures/ABOUT.png");
        g.drawImage(bg5, 0, 0, null);
        Font fnt1 = new Font("Comic Sans MS", Font.ITALIC, 15);
        g.setColor(Color.white);
        g.setFont(fnt1);
        g.drawString("After TinkerBell escaped from Captain Hook’s ship, she returned", 470, 180);
        g.drawString("home and to her surprise the Enchanted Forest was under siege by evil", 420, 200);
        g.drawString("witches. Her friends were captured and the magic of the forest was gone.", 420, 220);
        g.drawString("She was the only one who still had power. In order to save her friends ", 420, 240);
        g.drawString("and bring the magic back she must defeat the evil witches and solve", 420, 260);
        g.drawString("the forest maze.", 420, 280);
        if(isUserInterfaceActive) {
            userInterfaceManager.Draw(g);
        }

    }
}
