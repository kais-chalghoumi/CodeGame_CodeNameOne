package tn.esprit.codegame.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import tn.esprit.codegame.utils.Static;
import java.io.UnsupportedEncodingException;

public class Service {

    public static Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Service() {
        req = new ConnectionRequest();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public boolean Auth(String login, String password) {

        String url = Static.BASE_URL + "login.php";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("login", login);
        req.addArgument("password", password);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String ch = new String(req.getResponseData(), "utf-8");
                    if (ch.equalsIgnoreCase("ok")) {
                        resultOK = true;
                    } else {
                        resultOK = false;
                    }
                } catch (UnsupportedEncodingException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
