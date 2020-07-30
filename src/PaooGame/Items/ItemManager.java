package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
/*! \class public class ItemManager
    \brief Administreaza entitatile folosite in joc.
 */
public class ItemManager {

    private RefLinks refLink;
    private Hero hero;
    private Checkpoint checkpoint;
    private Villain villain;
    private ArrayList<Item> items;
//    private Comparator<Item> renderSorter = new Comparator<Item>() {
//        @Override
//        public int compare(Item o1, Item o2) {
//            if(o1.GetY()  < o2.GetY())
//                return -1;
//            return -1;
//        }
//    };
    /*! \fn public ItemManager(RefLinks refLink, Hero hero, Villain villain, Checkpoint checkpoint)
        \brief Constructorul aferent clasei.
     */
    public ItemManager(RefLinks refLink, Hero hero, Villain villain, Checkpoint checkpoint) {
        this.refLink = refLink;
        this.checkpoint = checkpoint;
        this.hero = hero;
        this.villain = villain;
        items = new ArrayList<Item>();
        addItem(hero);
        addItem(villain);
        addItem(checkpoint);
    }
    /*! \fn public ItemManager(RefLinks refLink, Villain villain)
        \brief Constructorul pe care il vom folosi pentru a dauga mai multi raufacatori.
     */
    public ItemManager(RefLinks refLink, Villain villain){
        this.refLink = refLink;
        this.villain = villain;
        items = new ArrayList<Item>();
        addItem(villain);
    }
    /*! \fn  public void addItem(Item i)
        \brief Adauga entitatile folosite pentru joc.
    */
    public void addItem(Item i) {
        items.add(i);
    }

    /*! \fn  public void removeItem(Item i)
        \brief Sterge entitatile folosite pentru joc.
    */
    public void removeItem(Item i){
        items.remove(i);
    }

    /*! \fn  public void Update()
        \brief Actualizeaza entitatile folosite pentru joc.
     */
    public void Update() {
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            it.Update();
        }
        //items.sort(renderSorter);

    }
    /*! \fn  public void Draw(Graphics g)
        \brief Deseneaza entitatile folosite pentru joc.
        \param g Contextul grafic in care trebuie efectuata desenarea entitatilor.
     */
    public void Draw(Graphics g) {
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            it.Draw(g);
        }
    }
    /*! \fn  public Hero getHero()
            \brief Returneaza eroul.
    */
    public Hero getHero() {
        return hero;
    }

    /*! \fn  public ArrayList<Item> getItems()
        \brief Returneaza entitatile.
    */
    public ArrayList<Item> getItems() {
        return items;
    }

    /*! \fn  public Hero getHero()
        \brief Seteaza eroul.
    */
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /*! \fn  public ArrayList<Item> setItems()
        \brief Seteaza entitatile.
    */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /*! \fn  public Villain getVillain()
        \brief Returneaza raufacatorul.
    */
    public Villain getVillain() {
        return villain;
    }

    /*! \fn  public Villain setVillain()
        \brief Seteaza raufacatorul.
    */
    public void setVillain(Villain villain) {
        this.villain = villain;
    }

    /*! \fn  public Checkpoint getCheckpoint()
        \brief Returneaza checkpoint.
    */
    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    /*! \fn  public Checkpoint setCheckpoint()
        \brief Seteazaa checkpoint.
    */
    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }
}
