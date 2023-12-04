package tn.esprit.codegame.GUI;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import tn.esprit.codegame.entities.Game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMultilist extends Form {

    EncodedImage enc;
    Image img = null;

    public GameMultilist(Resources theme) {
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.addMaterialCommandToSideMenu("accueil", FontImage.MATERIAL_ACCESS_ALARM, (e) -> new SignUp(theme).showBack());

        tb.addMaterialCommandToSideMenu("panier", FontImage.MATERIAL_SHOPPING_BASKET, (e) -> new Paniers(theme).showBack());

        //tb.addCommandToLeftBar("List view",null, (e) -> new GamesList(theme).showBack());
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        // Create your Game objects with resource paths
        Game game1 = new Game("Devil May Cry 5", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "http://localhost/gstore/dmc5.jpg", "/dmc5.jpg", "200");
        Game game2 = new Game("Resident Evill VIII", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "http://localhost/gstore/re8.jpg", "/re8.jpg", "200");
        Game game3 = new Game("Need For Speed Heat", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "http://localhost/gstore/nfs.jpg", "/nfs.jpg", "100");
        Game game4 = new Game("Red Dead Redemption II", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "http://localhost/gstore/rdr2.jpg", "/rdr2.jpg", "150");
        Game game5 = new Game("FIFA 22", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "http://localhost/gstore/fifa.jpg", "/fifa.jpg", "200");
        Game game6 = new Game("Minecraft", "Even with the best gameplay footage and screenshots to entice players to dive deeper on your game page, you can’t just stick a basic marketplace description and count on the visuals to pull more than their fair share of the weight to convince players to choose your game.", "v", "/minecraft.jpg", "100");
        // Add your Game objects to the data list
        data.add(createListEntry(game1));
        data.add(createListEntry(game2));
        data.add(createListEntry(game3));
        data.add(createListEntry(game4));
        data.add(createListEntry(game5));
        data.add(createListEntry(game6));

        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
        MultiList ml = new MultiList(model);

        // Add a selection listener to the MultiList
        ml.addSelectionListener(new SelectionListener() {
            @Override
            public void selectionChanged(int oldSelected, int newSelected) {
                // Get the selected game's details from the data
                System.out.println("selected index" + newSelected);
                Map<String, Object> selectedEntry = data.get(newSelected);
                Game selectedGame = (Game) selectedEntry.get("game"); // Assuming you store the Game object in the entry

                // Navigate to the GameDetails form with the selected game's details
                new GameDetails(theme, selectedGame).show();
            }
        });
        add(ml);

    }

    private Map<String, Object> createListEntry(Game game) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", game.getName());
        entry.put("Line2", game.getPrice() + " TND");
        // You can set the "icon" entry with the image associated with the game
        entry.put("icon", GetImage(game.getUrl())); // Assuming theme contains the images
        entry.put("game", game); // Store the Game object for later retrieval
        return entry;
    }

    private Image GetImage(String url) {
        System.out.println("url" + url);

        try {
            enc = EncodedImage.create("/load.png");

            img = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        } catch (IOException ex) {
        }

        return img;
    }

}
