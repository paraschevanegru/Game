package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UserInterface.ClickListener;
import PaooGame.UserInterface.UserInterfaceImageButton;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State {
    private final UserInterfaceManager userInterfaceManager;
    boolean difficultyEasy, difficultyHard;
    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        userInterfaceManager = new UserInterfaceManager(refLink);
        refLink.GetMyMouse().setUserInterfaceManager(userInterfaceManager);
        isUserInterfaceActive = true;
        difficultyEasy = GetDifficulty("easy");
        difficultyHard = GetDifficulty("hard");

        ///EASY
        userInterfaceManager.addObj(new UserInterfaceImageButton(620, 220, 120, 70, Assets.button_easy,difficultyEasy,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = true;
                refLink.GetGame().playState.setDiff("easy");
                State.SetState(refLink.GetGame().menuState);
            }
        }));

        ///HARD
        userInterfaceManager.addObj(new UserInterfaceImageButton(620, 300, 120, 70, Assets.button_hard,difficultyHard,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = true;
                refLink.GetGame().playState.setDiff("hard");
                State.SetState(refLink.GetGame().menuState);
            }
        }));

        ///Return to menu
        userInterfaceManager.addObj(new UserInterfaceImageButton(200, 280, 200, 100, Assets.button_returnmenu,false,
                new ClickListener() {
            @Override
            public void onClick() {
                isUserInterfaceActive = false;

                State.SetState(refLink.GetGame().menuState);
            }
        }));
    }

    /*! \fn  public boolean GetDifficulty(String diff)
    */
    public boolean GetDifficulty(String diff){ ;
        boolean boolValue;
        try {
            Integer easy = refLink.GetGame().playState.returnValue(diff);
            if (easy >= 1) {
                boolValue = true;
            }
            else {
                boolValue = false;
            }
            return boolValue;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /*! \fn  public UserInterfaceManager getUiManager()
        \brief Returneaza userInterfaceManager.
    */
    public UserInterfaceManager getUiManager() {
        return userInterfaceManager;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() {
        if(isUserInterfaceActive) {
            userInterfaceManager.Update();
        }
        difficultyEasy = GetDifficulty("easy");
        difficultyHard = GetDifficulty("hard");
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        BufferedImage bg4 = ImageLoader.LoadImage("/textures/SETTINGS.png");
        g.drawImage(bg4, 0, 0, null);
        if(isUserInterfaceActive) {
            userInterfaceManager.Draw(g);
        }
    }
}
