package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.User;

import javax.xml.bind.ValidationException;


@Controller
@FxmlView("register.fxml")
public class RegisterController {

    @FXML
    private TextField email, tel, name ;

    @FXML
    private PasswordField password, verify ;

    private final UserController controller ;

    @Autowired
    public RegisterController(UserController controller) {
        this.controller = controller;
    }

    public void initialize() {  }

    public void handleSignupBtn(ActionEvent event) {
//        try{
//            if (password.getText().equals(verify.getText())) {
//                controller.CretedUser(new User(email.getText(),password.getText(),tel.getText()));
//            }else {
//                telhrow new Validation
//            }
//            if (email.getText().matches(
//                    "^[A-z]" +
//                            "([A-z0-9])+" +
//                            "@{1,1}" +
//                            "([A-z])+" +
//                            ".{1,1}"+
//                            "[A-z]")) {
//            }else {
//                throw new Exception() ;
//            }
//        }catch (Exception e){
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Sign-up Failed");
//            alert.setHeaderText(null);
//            alert.setContentText("Wrong E-mail pattern  or password not equals.");
//            alert.showAndWait();
//        }catch (ValidationException e) {
//
//        }
        controller.createUser(new User(email.getText(), name.getText(),password.getText(),tel.getText(), User.Role.USER));
    }

//    public void handleSignupBtn(javafx.event.ActionEvent event) {
//    }
}