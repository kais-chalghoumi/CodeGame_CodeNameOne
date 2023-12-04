package tn.esprit.codegame.GUI;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import tn.esprit.codegame.services.Service;

public class SignUp extends Form {

    private TextField emailField;
    private TextField passwordField;
    private Button loginButton;
    private Button InscritButton;

    public SignUp(Resources theme) {
        // Create UI components
        emailField = new TextField("", "Email", 20, TextField.ANY);
        passwordField = new TextField("", "Mot de passe", 20, TextField.PASSWORD);

        //Button Login
        loginButton = new Button("S'authentifier");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (Service.getInstance().Auth(emailField.getText(), passwordField.getText())) {
                    new GameMultilist(theme).show();
                } else {
                    Dialog.show("error", "verifier login password", "ok", null);
                }
            }
        });
        //Button Inscrit
        InscritButton = new Button("Créer un compte");
        InscritButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new GamesList(theme).show();
            }
        });
        InscritButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new SignIn(theme).show();
            }
        });
        //Creation de Form
        Form form = new Form("S'authentifier", new BorderLayout());
        // Load an image from the resources
        Image image = theme.getImage("minecraft.jpg");

        // Create the ImageView component with the image
        ImageViewer imageView = new ImageViewer(image);

        //changer la taille de l'image
        imageView.setPreferredH(600);
        imageView.setPreferredW(200);

        form.add(BorderLayout.CENTER, imageView);

        //create a SpanButton
        SpanButton spanButton = new SpanButton("Oublier mot de passe");
        spanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ForgetPassword(theme).show();
            }
        });
        // Add components to form
        Container centerContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        centerContainer.add(form);
        centerContainer.add(emailField);
        centerContainer.add(passwordField);
        centerContainer.add(loginButton);
        centerContainer.add(InscritButton);
        centerContainer.add(spanButton);
        add(centerContainer);
    }

}
