package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Service.UserService;

@Controller
@FxmlView
public class EditUserController {

    private final UserController controller;
    private User user ;

    @FXML
    private TextField email ;
    @FXML
    private TextField name ;
    @FXML
    private TextField tel;
    @FXML
    private ChoiceBox<User.Role> rolebox ;


    public void initialize() {
        email.setText(user.getEmail());
        email.setDisable(true);
        name.setText(user.getName());
        tel.setText(user.getTel());
        rolebox.getItems().setAll(User.Role.values());
        rolebox.setValue(user.getRole());
    }

    public EditUserController(UserController controller) {
        this.controller = controller;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void handleBtnSubmit(ActionEvent event) {
        user.setName(name.getText());
        user.setTel(tel.getText());
        user.setRole(rolebox.getValue());
        controller.updateUser(user);
    }
}