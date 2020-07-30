package PaooGame.Maps;

import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.Utils.Utils;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private final RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;                /*!< Latimea hartii in numar de dale.*/
    private int height;               /*!< Inaltimea hartii in numar de dale.*/
    private int[][] tiles;            /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    public String lvl;
    public String path;
    private  ItemManager itemManager;
    private  ItemManager itemManager1;
    private  ItemManager itemManager2;
    private  ItemManager itemManager3;
    public Controller c;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, String path, String lvl) {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        /// Retine referinta catre path.
        this.path = path;
        /// Retine referinta catre nivel.
        this.lvl = lvl;
        /// Genereaza pozitii random pentru raufacator
        int xVillain = (int) (Math.random() * 760) + 150;
        int yVillain = (int) (Math.random() * 250) + 50;
        /// Initializeaza pozitiile caracterelor pentru fiecare nivel
        initItems(this.lvl);

        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(this.path);
        c = new Controller(refLink);
    }
    /*! \fn public Controller getController()
        \brief Seteaza nivelul.

       */
    public Controller getController() {
        return c;
    }
    /*! \fn public void setLevel
        \brief Seteaza nivelul.

        \param lvl String care face referire catre nivel.
    */
    public void setLevel(String lvl){
        this.lvl = lvl;
    }

    /*! \fn public void setPath
        \brief Seteaza path-ul.

        \param path String care face referire catre path.
    */
    public void setPath(String path){
        this.path = path;
    }

    /*! \fn public void initItems(String lvl)
        \brief Initializeaza pentru fiecare nivel in parte pozitia eroului si a raufacatorilor.

        \param lvl String care primeste nivelul.
    */
    public void initItems(String lvl){
        if (lvl.equals("level_1")) {
            itemManager = new ItemManager(refLink, new Hero(refLink, 100, 100), new Villain(refLink, 680, 40), new Checkpoint(refLink, 879, 863));
            Villain itemManager1 = new Villain(refLink, 300, 280);
            Villain itemManager2 = new Villain(refLink, 580, 470);
            Villain itemManager3 = new Villain(refLink, 720, 760);
            itemManager.addItem(itemManager1);
            itemManager.addItem(itemManager2);
            itemManager.addItem(itemManager3);
        }else{
            itemManager = new ItemManager(refLink, new Hero(refLink, 50, 850), new Villain(refLink, 680, 40), new Checkpoint(refLink, 200, 150));
            Villain itemManager1 = new Villain(refLink, 380, 140);
            Villain itemManager2 = new Villain(refLink, 680, 470);
            Villain itemManager3 = new Villain(refLink, 640, 710);
            itemManager.addItem(itemManager1);
            itemManager.addItem(itemManager2);
            itemManager.addItem(itemManager3);
        }
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente
     */
    public void Update() {

        itemManager.Update();
        c.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        int xStart = (int) Math.max(0, refLink.GetGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (refLink.GetGameCamera().getxOffset() + refLink.GetWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, refLink.GetGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (refLink.GetGameCamera().getyOffset() + refLink.GetHeight()) / Tile.TILE_HEIGHT + 1);
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                GetTile(x, y).Draw(g, (int) (x * Tile.TILE_HEIGHT - refLink.GetGameCamera().getxOffset()),
                                (int) (y * Tile.TILE_WIDTH - refLink.GetGameCamera().getyOffset()));
            }
        }
        itemManager.Draw(g);
        c.Draw(g);
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.waterTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta.
     */
    private void LoadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        /// Imparte fiecare numar din fisier intr-un string separat si le pune intr-un string array
        String[] a = file.split("\\s+");
        width = Utils.convert(a[0]);
        height = Utils.convert(a[1]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.convert(a[(x + y * width) + 2]);
            }
        }
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea hartii.
    */
    public int GetWidth() {
        return width;
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea hartii.
    */
    public int GetHeight() {
        return height;
    }

    /*! \fn public ItemManager getItemManager()
        \brief Returneaza o referinta catre itemManager-ul curent.
    */
    public ItemManager getItemManager() {
        return itemManager;
    }
}
