package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import static com.codename1.io.Util.cleanup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import tn.esprit.codegame.GUI.GameMultilist;
import tn.esprit.codegame.entities.Game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Paniers extends Form {

    ArrayList<Game> gamesList = new ArrayList<>();
    EncodedImage enc;
    Image img = null;
    Form f;
    // int counter = 0;
    private Resources theme;
    private static ArrayList<Game> basket = new ArrayList<>();

    public Paniers(Resources theme) {

        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.addCommandToLeftBar("Back", null, e -> new GameMultilist(theme).showBack());

        try {
            Database db = Database.openOrCreate("G-Store.db");
            Cursor cur = db.executeQuery("SELECT * FROM games");

            //  Cursor cursor = database.executeQuery("SELECT * FROM games");
            while (cur.next()) {
                String name = cur.getRow().getString(0);
                String price = cur.getRow().getString(1);
                String urlImg = cur.getRow().getString(2);

                Game game = new Game(name, price, urlImg);
                Collections.addAll(gamesList, game);

            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        f = new Form("G-Store Esprit", BoxLayout.y());
        for (Game game : gamesList) {
            addItem(game, theme);

        }
        add(f);
    }

    public void deleteItem(String itemName) {
        try {
            Database.openOrCreate("G-Store.db").execute("delete from games where name = '" + itemName + "'");
            Dialog.show("Success", "Item deleted", "OK", null);

            for (int i = 0; i < gamesList.size(); i++) {
                Game game = gamesList.get(i);
                if (game.getName().equals(itemName)) {
                    gamesList.remove(i);
                    break;
                }
            }

            refreshForm();

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void refreshForm() {
        f.removeAll();

        for (Game game : gamesList) {
            addItem(game, theme);

        }

        f.refreshTheme();
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
        name.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            String itemNameToDelete = name.getText();
            deleteItem(itemNameToDelete);

        });
        // ajouter le container c1 pour notre form f 
        f.add(c1);

        f.refreshTheme(true);

    }

}
