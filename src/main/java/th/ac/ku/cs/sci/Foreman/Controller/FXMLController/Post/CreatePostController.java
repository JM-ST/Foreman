package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Service.SiteService;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


@Component
@Controller
@FxmlView("createPost.fxml")
public class CreatePostController {

    private final PostController controller;

    @FXML
    private TextField topic ;
    @FXML
    private TextArea detail ;

    private Post post ;

    private int siteId ;

    public CreatePostController(PostController controller) {
        this.controller = controller;
    }

    public void setSiteId(int id) {
        siteId = id;
    }

    public void initialize() {

    }

    public void handleBtnCreate(ActionEvent event) {
        Post post = new Post(UserSession.getUserInstance().getId(),siteId,topic.getText(),detail.getText(),"");
        controller.createPost(post);
    }



    public void handleBtnUpload(ActionEvent event) throws IOException {
        FileChooser imagefile = new FileChooser();
        imagefile.showOpenDialog(new Stage());
        CreateStorageDirectoryPost();
    }

    public void CreateStorageDirectoryPost() {
        String path = "src/main/resource/public/10"+siteId;
        File siteDir = new File(path);
        if (!siteDir.exists()){
            siteDir.mkdirs();
        }
//        File postDir = new File();
    }





}