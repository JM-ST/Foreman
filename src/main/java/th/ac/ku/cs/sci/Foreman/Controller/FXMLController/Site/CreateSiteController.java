package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;


@Controller
@FxmlView("createSite.fxml")
public class CreateSiteController {

    private SiteController controller ;

    @FXML
    private TextField name ;

    @Autowired
    private CreateSiteController(SiteController controller) {
        this.controller = controller;
    }

    public void initialize() {

    }

    @FXML
    public void handleBtnCreateSite(ActionEvent actionEvent) {
        controller.createSite(name.getText());
    }

}