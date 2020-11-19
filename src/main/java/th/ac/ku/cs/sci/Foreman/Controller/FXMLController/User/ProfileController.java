package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;




@Controller
@FxmlView("profile.fxml")
public class ProfileController {


    private final UserController controller ;

    @FXML
    private TextField name , mail , tel ;

    @FXML
    private ImageView profile ;

    @Autowired
    public ProfileController(UserController controller) {
        this.controller = controller ;
    }

    public void initialize() {
        controller.verifyUser("admin@example.com","password");
        name.setText(UserSession.getUserInstance().getName());
        mail.setText(UserSession.getUserInstance().getEmail());
        tel.setText(UserSession.getUserInstance().getTels());
    }

    public void handleBtnBack(){
//        StageListener stageListener = new StageListener()
    }

    public void handleBtnSave() throws Exception {

    }

}