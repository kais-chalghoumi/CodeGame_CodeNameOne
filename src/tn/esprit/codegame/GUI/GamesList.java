package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.codegame.entities.Game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class GamesList extends Form {

    Form f;

    public GamesList(Resources theme) {
        super(new BorderLayout());

        //  ArrayList<Map<String, Object>> data = new ArrayList<>();
        // 2eme créer notre list des jeux
        ArrayList<Game> games = new ArrayList<>();
        // 3éme créer des instance pour notre jeux
        //    Game g =new Game
        Game game1 = new Game("Devil May Cry 5", "200", "/dmc5.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        Game game2 = new Game("Resident Evill VIII", "200", "/re8.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        Game game3 = new Game("Need For Speed Heat", "100", "/nfs.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        Game game4 = new Game("Red Dead Redemption II", "150", "/rdr2.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        Game game5 = new Game("FIFA 22", "100", "/fifa.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        Game game6 = new Game("Minecraft", "200", "/minecraft.jpg", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.");
        // 4eme add all the games to the array list of games
        //      Collections.addAll(games, elements)
        Collections.addAll(games, game1, game2, game3, game4, game5, game6);
        //5eme instance notre form avec box layout pour metre tous les element sur laxe de Y 
        f = new Form("G-Store Esprit", BoxLayout.y());
        for (Game game : games) {
            addItem(game, theme);

        }
        add(BorderLayout.CENTER, f);

    }

    public void addItem(Game game, Resources theme) {
        ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        try {
            img = new ImageViewer(Image.createImage(game.getImg()));

        } catch (IOException ex) {
            System.out.println("err" + ex.getMessage());
        }
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label name = new Label(game.getName());

        Label price = new Label(game.getPrice() + " TND");

        //ajouter les label pour note contaier sur laxe de y
        c2.add(name);
        c2.add(price);
        // ajouter notre image et lautre container sur laxe de x
        c1.add(img);
        c1.add(c2);
        
        // ajouter le container c1 pour notre form f 
        f.add(c1);

        f.refreshTheme(true);

    }

}
