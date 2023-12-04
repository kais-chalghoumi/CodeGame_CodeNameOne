package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

public class SignIn extends Form {
    //Déclaration des variables

    private TextField usernameField;
    private TextField emailField;
    private TextField passwordField;

    private TextArea adresseField;
    private Button annulerButton;
    private Button inscritButton;

    private Picker dateDeNField;

    public SignIn(Resources theme) {
        // Creation de Form
        Form form = new Form("Inscription", new BorderLayout());

        // Load an image from the resources
        Image image = theme.getImage("minecraft.jpg");

        // Create the ImageView component with the image
        ImageViewer imageView = new ImageViewer(image);

        form.add(BorderLayout.CENTER, imageView);

        // Create UI components
        emailField = new TextField("", "Email", 20, TextField.ANY);
        passwordField = new TextField("", "mot de passe", 20, TextField.PASSWORD);
        usernameField = new TextField("", "Username", 20, TextField.ANY);

        dateDeNField = new Picker();
        dateDeNField.setType(Display.PICKER_TYPE_DATE);

        adresseField = new TextArea();
        adresseField.setRows(5);
        adresseField.setHint("Adresse de facturation");
        // Create a container for the buttons
        Container buttonContainer = new Container(new FlowLayout(CENTER));

        // Create a "Inscrit" button
        inscritButton = new Button("S'inscrire");
        // Add the "Inscrit" button to the button container
        buttonContainer.add(inscritButton);

        // Create a "Annuler" button
        annulerButton = new Button("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new SignUp(theme).show();
            }
        });
        // Add the "Annuler" button to the button container
        buttonContainer.add(annulerButton);

        // Add components to form
        Container centerContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        centerContainer.add(form);
        centerContainer.add(usernameField);
        centerContainer.add(emailField);
        centerContainer.add(passwordField);
        centerContainer.add(dateDeNField);
        centerContainer.add(adresseField);
        centerContainer.add(buttonContainer);
        add(centerContainer);

    }

}
