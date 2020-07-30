package PaooGame.UserInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class UserInterfaceImageButton
    \brief Implementeaza notiunea image button.

 */
public class UserInterfaceImageButton extends UserInterfaceObject {

    private final BufferedImage[] images;
    private final ClickListener clickListener;
    public boolean selected;

    /*! \fn  public UserInterfaceImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clickListener)
        \brief Constructorul aferent clasei.

        \param x, y pozitiile
        \param width, height dimensiunile
        \param images imaginea corespunzatoare butonului
    */
    public UserInterfaceImageButton(float x, float y, int width, int height, BufferedImage[] images, boolean selected, ClickListener clickListener) {
        ///Apel al constructorului clasei de baza.
        super(x, y, width, height);
        this.images = images;
        this.clickListener = clickListener;
        this.selected = selected;
    }

    /*! \fn public void Update()
        \brief Actualizeaza butonul/butoanele.
     */
    @Override
    public void Update() {

    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza in fereastra butonul/butoanele.

        \param g Contextul grafic in care sa se realizeze desenarea
     */
    @Override
    public void Draw(Graphics g) {
        if (hovering || selected)
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
    }

    /*! \fn public void Draw(Graphics g)
        \brief  Recunoaste cand utilizatorul apasa pe un obiect
     */
    @Override
    public void onClick() {
        clickListener.onClick();
    }
}
