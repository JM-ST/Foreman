package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
@FxmlView("createSite.fxml")
public class CreateSiteController {

    private final SiteController siteController ;
    private final UserController userController ;

    @FXML
    public ChoiceBox<String> userBox;

    @FXML
    private TextField name ;

    @Autowired
    private CreateSiteController(SiteController siteController, UserController userController) {
        this.siteController = siteController;
        this.userController = userController;
    }

    public void initialize() {
        Collection<String> userName = new ArrayList<>();
        for (User user:userController.getAllByRole(User.Role.USER)) {
            userName.add(user.getName());
        }
        userBox.getItems().addAll(userName);
    }

    @FXML
    public void handleBtnCreateSite(ActionEvent actionEvent) {
        siteController.createSite(name.getText(),userController.getByName(userBox.getValue()).getId());
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        stage.close();
    }

}