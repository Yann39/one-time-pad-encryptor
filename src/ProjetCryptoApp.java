/*
 * ProjetCryptoApp.java
 *
 * Created on 19 nov. 2008
 * By Yann39 <rockyracer@mailfence.com>
 */

package projetcrypto;

import java.awt.Image;
import java.awt.Toolkit;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ProjetCryptoApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new ProjetCryptoView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
        this.getMainFrame().setTitle("Chiffrement / déchiffrement de données");
        this.getMainFrame().setResizable(false);
        Image icone = Toolkit.getDefaultToolkit().getImage("./src/projetcrypto/resources/building_key.png");
        this.getMainFrame().setIconImage(icone);
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ProjetCryptoApp
     */
    public static ProjetCryptoApp getApplication() {
        return Application.getInstance(ProjetCryptoApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(ProjetCryptoApp.class, args);
    }
}
