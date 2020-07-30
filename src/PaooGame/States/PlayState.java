package PaooGame.States;

import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.UserInterface.UserInterfaceManager;

import java.awt.*;
import java.sql.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    private Map map;   /*!< Referinta catre harta curenta.*/
    public String diff = "easy";

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza
        super(refLink);
        try {
            CreateDataBase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*! \fn public void StartNewGame()
        \brief Incarca un joc nou.
    */
    public void StartNewGame(){
        ///Construieste harta primului nivel
        map = new Map(refLink,"res/maps/"+ diff + "/map1.txt", "level_1");
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
    }

    /*! \fn public void StartNewGame()
        \brief Incarca nivelul urmator.
    */
    public void StartNextLevel(){
        ///Construieste harta celui de-al doilea nivel
        map = new Map(refLink,"res/maps/" + diff + "/map2.txt", "level_2");
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
    }
    /*! \fn  public UserInterfaceManager getUiManager()
        \brief Returneaza null.
    */
    @Override
    public UserInterfaceManager getUiManager() {
        return null;
    }

    /*! \fn  public void SetDifficulty(String row, int diff)
        \brief Seteaza dificultatea
    */
    public void SetDifficulty(String row, int diff){ ;
        try {
            refLink.GetGame().playState.setValue(row, diff);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /*! \fn   public void setDiff(String diff)
    */
    public void setDiff(String diff) {
        this.diff = diff;
        SetDifficulty(diff,1);
        if(diff.equals("easy")){
            SetDifficulty("hard",0);
        }else if(diff.equals("hard")){
            SetDifficulty("easy",0);
        }
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() {
        map.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        map.Draw(g);


    }
    /*! \fn public void CreateDataBase()
        \brief Creaza baza de date
    */
    public void CreateDataBase() throws ClassNotFoundException, SQLException {
        Connection c = this.connect();

        if(tableExists("Game",c)){
            System.out.println("Tabela exista");
        }else{
            Statement stmt = null;
            stmt = c.createStatement();
            String sql = "CREATE TABLE Game " +
                    "(Difficulty          TEXT   NOT NULL," +
                    "Active               INT    NOT NULL)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Game (Difficulty, Active) " +"VALUES ('easy', 1);";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Game (Difficulty, Active) " +"VALUES ('hard', 0);";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("\nTabel creat cu succes");
        }
        try {
            Statement stmt = null;
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Game;");
            System.out.println("Continutul bazei de date: ");
            while (rs.next()) {
                String dif = rs.getString("Difficulty");
                Integer value = rs.getInt("Active");

                System.out.println("Difficulty: " + dif + " set to: " + value);
            }

            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn  private Connection connect()
        \brief Conexiune baza de date
    */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:GAME.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /*! \fn  public Integer returnValue(String row)
        \brief Returneaza valoarea de la campul Active
    */
    public Integer returnValue(String row) throws ClassNotFoundException, SQLException {
        String sql = "SELECT Active FROM Game WHERE Difficulty=?;";

        try (Connection c= this.connect();
             PreparedStatement pstmt  = c.prepareStatement(sql)){

            pstmt.setString(1,row);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                int act = rs.getInt("Active");
                return act;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*! \fn  public void setValue(String row, int diff)
        \brief Seteaza/actualizeaza valoarea de la campul Active
    */
    public void setValue(String row, int diff) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Game SET Active=? WHERE Difficulty=?;";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, row);
            pstmt.setInt(1, diff);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*! \fn  public static boolean tableExists(String tableName, Connection c)
        \brief Verifica existenta tabelei din baza de date
    */
    public static boolean tableExists(String tableName, Connection c) throws SQLException, ClassNotFoundException {
        try{
            DatabaseMetaData md = c.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            if (rs.next())
                return true;
        }catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
}
