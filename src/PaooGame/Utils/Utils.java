package PaooGame.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*! \class public class Utils
    \brief Implementeaza functii care vor fi de folos in joc(incarca un fisier,converterste uns tring la int).
 */
public class Utils {

    /*! \fn  public static String loadFileAsString(String path)
        \brief Incarca un fisier.

        \param file_path reprezinta locatia fisierului.
    */
    public static String loadFileAsString(String file_path) {
        ///Putem adauga caractere unui string cu usurinta folosind StringBuilder
        StringBuilder b = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String line;
            while ((line = br.readLine()) != null)
                b.append(line + "\n");

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b.toString();
    }

    /*! \fn  public static int convertt(String nr)
        \brief Converteste un string la int.

        \param nr reprezinta sirul(ex: "5").
    */
    public static int convert(String nr) {
        try {
            return Integer.parseInt(nr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
