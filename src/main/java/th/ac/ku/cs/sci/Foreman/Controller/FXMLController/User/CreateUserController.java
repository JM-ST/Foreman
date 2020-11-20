package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.User;

@Controller
@FxmlView
public class CreateUserController {

    private final UserController controller ;

    @FXML
    public TextField email, firstname, lastname, tel;
    @FXML
    public PasswordField password;


    public CreateUserController(UserController controller) {
        this.controller = controller;
    }

    public void initialize() {

    }

    public void handleBtnSubmit(ActionEvent event) {
        controller.createUser(new User(email.getText(),firstname.getText()+" "+lastname.getText(),password.getText(),tel.getText(), User.Role.USER));
    }
}