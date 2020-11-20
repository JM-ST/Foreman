package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Service.UserService;

@Controller
@FxmlView
public class EditUserController {

    private final UserService service ;


    public void initialize() {

    }

    public EditUserController(UserService service) {
        this.service = service;
    }

    public void setUser(User selectedItem) {

    }
}