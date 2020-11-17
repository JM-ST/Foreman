package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;


@Component
@Controller
@FxmlView("login.fxml")
public class LoginController {

    private final UserController controller ;

    @FXML
    private TextField email ;

    @FXML
    private PasswordField password ;

    @Autowired
    public LoginController (UserController controller) {
        this.controller = controller;
    }

    public void initialize() {

    }

    @FXML
    public void handleBtn_login(ActionEvent event) {
        try {
            controller.verifyUser(email.getText(),password.getText());
            if (UserSession.getUserInstance() == null){
                throw new Exception();
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Wrong username or password.");
            alert.showAndWait();
        }
    }







}