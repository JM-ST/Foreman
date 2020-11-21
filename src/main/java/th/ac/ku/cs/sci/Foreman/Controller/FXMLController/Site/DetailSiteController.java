package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    private String upload = null;

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

    public void handleBtnUploadImage(ActionEvent event) throws IOException {
        Path from ;
        Path to = Paths.get("src/main/resources/public/siteImage/"+site.getId()+"/"+site.getId()+".jpg");
        String path = "src/main/resources/public/siteImage/"+site.getId();
        File files = new File(path);
        if (!files.exists()){
            files.mkdir();
        }
        FileChooser imageChoose = new FileChooser();
        imageChoose.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("JPG file (JPG)","*.jpg")
        );
        File image = imageChoose.showOpenDialog(null);
        from = Paths.get(image.toURI());
        Files.copy(
                from,
                to,
                StandardCopyOption.REPLACE_EXISTING
        );
        img.setImage(new Image("public/siteImage/"+site.getId()+"/"+site.getId()+".jpg"));
        upload = "public/siteImage/"+site.getId()+"/"+site.getId()+".jpg";
    }

    public void handleBtnSubmit(ActionEvent event) {
        site.setName(name.getText());
        site.setStatus((Site.Status) statusBox.getValue());
        site.setDetail(detail.getText());
        site.setImg(upload);
        controller.update(site.getId(),site);
        Stage stage = (Stage) ((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }

    private void Imageload() {
        if (site.getImg() != null) {
            img.setImage(new Image(site.getImg()));
        }else {
            img.setImage(new Image("public/image_placeholder.jpg"));
        }
    }
}