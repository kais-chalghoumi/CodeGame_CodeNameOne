package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.codegame.entities.Game;
import java.io.IOException;
import java.util.ArrayList;

public class GameDetails extends Form {

    private Button acheterBtn;
    private SpanLabel description;
    private Label price;

    public GameDetails(Resources theme, Game game) {

        // Creation de Form
        //    Form form = new Form(game.getName(), new BorderLayout());
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.addCommandToLeftBar("back", null, (e) -> new GameMultilist(theme).showBack());

        // Create the ImageView component with the image
        ImageViewer imageView;
        try {
            imageView = new ImageViewer(Image.createImage(game.getImg()));
            // changer la taille de l'image

            add(imageView);

        } catch (IOException ex) {
            System.out.println("err" + ex.getMessage());
        }

        // Create a SpanButton
        description = new SpanLabel(game.getDescription());

        price = new Label(game.getPrice() + " TND");

        // Create the "Buy" button
        acheterBtn = new Button("Buy");
        acheterBtn.addActionListener(e -> {
            System.out.println(game.getImg());
            saveGameToLocalDatabase(game.getName(), game.getPrice(), game.getImg());
            Dialog.show("Succes", game.getName() + " added successfully", "OK", null);

        });
        add(description);
        add(price);
        add(acheterBtn);
    }

    public void saveGameToLocalDatabase(String name, String price, String image) {

        Database database = null;
        try {
            database = Database.openOrCreate("G-Store.db");

        } catch (IOException ex) {
            System.out.println("create " + ex);
        }

        try {

            database.execute("CREATE TABLE IF NOT EXISTS games (name varchar(100), price varchar(100), imageUrl varchar(100))");
        } catch (IOException ex) {
            System.out.println("create tab  " + ex);
        }
        try {

            database.execute("INSERT INTO games (name, price, imageUrl )"
                    + "VALUES ('" + name + "', '" + price + "', '" + image + "')");

        } catch (IOException ex) {
            System.out.println("insrt " + ex);
        }

    }

}
