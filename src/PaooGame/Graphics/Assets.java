package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    private static final int width = 48, height = 48;
    // Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage soil;
    public static BufferedImage grass;
    public static BufferedImage checkpoint;
    public static BufferedImage villain_right, villain_left;
    public static BufferedImage water;
    public static BufferedImage tree;
    public static BufferedImage autumnTree;
    public static BufferedImage pumpkin;
    public static BufferedImage bush;
    public static BufferedImage fence1;
    public static BufferedImage fence2;
    public static BufferedImage bullet;
    public static BufferedImage[] hero_down, hero_up, hero_left, hero_right;
    public static BufferedImage[] button_play, button_settings, button_exit, button_returnmenu, button_nextlevel, button_playagain;
    public static BufferedImage[] button_about, button_hard, button_easy;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init() {

        /// Se creaza temporar obiecte SpriteSheet initializate prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/A6.png"));
        SpriteSheet fairy_sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/FairySheet.png"));
        SpriteSheet buttons_sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/buttons.png"));
        SpriteSheet buttons_sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/buttons2.png"));
        SpriteSheet buttons_sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/buttons3.png"));
        SpriteSheet buttons_sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/buttons4.png"));
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 0);
        soil = sheet.crop(1,0);
        bush = sheet.crop(2, 0);
        tree = sheet.crop(3, 0);
        fence1 = sheet.crop(0, 1);
        fence2 = sheet.crop(1, 1);
        water = sheet.crop(2, 1);
        autumnTree = sheet.crop(3, 1);
        villain_left = sheet.crop(0, 2);
        villain_right = sheet.crop(1, 2);
        checkpoint = sheet.crop(2, 2);
        pumpkin = sheet.crop(3,2);
        bullet = fairy_sheet.crop(3,0);

        /// Se obtin subimaginile corespunzatoare eroului.
        hero_down = new BufferedImage[3];
        hero_up = new BufferedImage[3];
        hero_left = new BufferedImage[3];
        hero_right = new BufferedImage[3];

        hero_down[0] = fairy_sheet.crop(0, 0);
        hero_down[1] = fairy_sheet.crop(1, 0);
        hero_down[2] = fairy_sheet.crop(2, 0);

        hero_up[0] = fairy_sheet.crop(0, 3);
        hero_up[1] = fairy_sheet.crop(1, 3);
        hero_up[2] = fairy_sheet.crop(2, 3);

        hero_left[0] = fairy_sheet.crop(0, 1);
        hero_left[1] = fairy_sheet.crop(1, 1);
        hero_left[2] = fairy_sheet.crop(2, 1);

        hero_right[0] = fairy_sheet.crop(0, 2);
        hero_right[1] = fairy_sheet.crop(1, 2);
        hero_right[2] = fairy_sheet.crop(2, 2);

        /// Se obtin subimaginile corespunzatoare butoanelor.
        button_play = new BufferedImage[2];
        button_settings = new BufferedImage[2];
        button_exit = new BufferedImage[2];
        button_returnmenu = new BufferedImage[2];
        button_nextlevel = new BufferedImage[2];
        button_playagain = new BufferedImage[2];
        button_about = new BufferedImage[2];
        button_easy = new BufferedImage[2];
        button_hard = new BufferedImage[2];

        button_play[0] = buttons_sheet.new_crop(0, 0, width * 2, height);
        button_play[1] = buttons_sheet.new_crop(0, height, width * 2, height);

        button_settings[0] = buttons_sheet.new_crop(0, height * 2, width * 3, height);
        button_settings[1] = buttons_sheet.new_crop(0, height * 3, width * 3, height);

        button_exit[0] = buttons_sheet.new_crop(width * 2, 0, width * 2, height);
        button_exit[1] = buttons_sheet.new_crop(width * 2, height, width * 2, height);

        button_returnmenu[0] = buttons_sheet2.new_crop(0,0, width*4, height);
        button_returnmenu[1] = buttons_sheet2.new_crop(0, height, width*4, height);

        button_nextlevel[0] = buttons_sheet2.new_crop(0,height*2,width*4, height);
        button_nextlevel[1] = buttons_sheet2.new_crop(0,height*3 ,width*4, height);

        button_playagain[0] = buttons_sheet3.new_crop(0,0,width*4, height);
        button_playagain[1] = buttons_sheet3.new_crop(0, height ,width*4, height);

        button_about[0] = buttons_sheet3.new_crop(0,height*3,width*3, height);
        button_about[1] = buttons_sheet3.new_crop(0, height*2 ,width*3, height);

        button_hard[0] = buttons_sheet4.new_crop(0,0,width*2, height);
        button_hard[1] = buttons_sheet4.new_crop(0, height ,width*2, height);

        button_easy[0] = buttons_sheet4.new_crop(width * 2, 0, width * 2, height);
        button_easy[1] = buttons_sheet4.new_crop(width * 2, height, width * 2, height);

    }
}
