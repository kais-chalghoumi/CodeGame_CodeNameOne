package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class ForgetPassword extends Form {

    //Déclaration des variables
    private TextField usernameField;
    private Button suivantButton;

    public ForgetPassword(Resources theme) {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        tb.addCommandToLeftBar("back", null, (e) -> new SignUp(theme).showBack());
        // Load an image from the resources
        Image image = theme.getImage("minecraft.jpg");

        // Create the ImageView component with the image
        ImageViewer imageView = new ImageViewer(image);
        add(imageView);

        // Create UI components
        usernameField = new TextField("", "Username", 20, TextField.ANY);

        //Button suivant
        suivantButton = new Button("Réinitialiser le mot de passe");

        add(usernameField);
        add(suivantButton);

    }

}
