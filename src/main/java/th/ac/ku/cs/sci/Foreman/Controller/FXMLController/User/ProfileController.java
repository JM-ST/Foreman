package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.io.IOException;


@Controller
@FxmlView
public class ProfileController {


    private final UserController controller ;
    private final Resource INDEXUSERFXML;
    private final ApplicationContext ac ;

    @FXML
    private TextField name , mail , tel ;

    @FXML
    private ImageView profile ;

    @Autowired
    public ProfileController(UserController controller,
                             @Value("classpath:templates/UserFXML/index.fxml") Resource indexuserfxml,
                             ApplicationContext ac) {
        INDEXUSERFXML = indexuserfxml;
        this.controller = controller ;
        this.ac = ac ;
    }

    public void initialize() {
        name.setText(UserSession.getUserInstance().getName());
        mail.setText(UserSession.getUserInstance().getEmail());
        tel.setText(UserSession.getUserInstance().getTel());
        profile.setImage(new Image("public/user_placeholder.jpg"));
    }

    public void handleBtnBack(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(INDEXUSERFXML,ac);
        call.changeScene((Stage) ( (Node)event.getTarget() ).getScene().getWindow(),
                "Welcome : "+UserSession.getUserInstance().getName()).show();
    }

    public void handleBtnSave() throws Exception {
        UserSession.getUserInstance().setName(name.getText());
        UserSession.getUserInstance().setTel(tel.getText());
        controller.updateUser(UserSession.getUserInstance());
    }

}