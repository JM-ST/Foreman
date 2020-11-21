package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

@Controller
@FxmlView
public class DetailSiteController {

    private final SiteController controller ;
    private Site site ;

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox statusBox;
    @FXML
    private Button submitBtn;
    @FXML
    private Button uploadImageBtn;
    @FXML
    private ImageView img;
    @FXML
    private TextArea detail;

    public DetailSiteController(SiteController controller) {
        this.controller = controller;
    }

    public void setSite(Site site) {
        this.site = site ;
    }

    public void initialize() {
        name.setText(site.getName());
        statusBox.getItems().addAll(Site.Status.values());
        statusBox.setValue(site.getStatus());
        detail.setText(site.getDetail());
        Imageload();
        if (UserSession.getUserInstance().getRole() != User.Role.ADMIN){
            statusBox.setDisable(true);
            uploadImageBtn.setVisible(false);
            submitBtn.setVisible(false);
            detail.setDisable(true);
        }
    }

    public void handleBtnUploadImage(ActionEvent event) {
    }

    public void handleBtnSubmit(ActionEvent event) {
        site.setName(name.getText());
        site.setStatus((Site.Status) statusBox.getValue());
        site.setDetail(detail.getText());
        controller.update(site.getId(),site);
        Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }

    private void Imageload() {
    }
}