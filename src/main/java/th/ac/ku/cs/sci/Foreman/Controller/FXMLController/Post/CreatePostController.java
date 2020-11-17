package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Service.SiteService;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;



@Component
@Controller
@FxmlView("createPost.fxml")
public class CreatePostController {

    private final PostController controller;
    private final SiteService siteService;

    @FXML
    private TextField topic ;
    @FXML
    private TextArea detail ;

    @FXML
    private ChoiceBox<String> siteOption ;

    @Autowired
    public CreatePostController(PostController controller,SiteService siteService) {
        this.controller = controller;
        this.siteService = siteService ;
    }

    public void initialize() {
        siteOption.getItems().addAll(siteService.getAllSiteName());;

    }

    public void handleBtnCreate(ActionEvent event) {
        Post post = new Post(UserSession.getUserInstance().getId(),siteService.findByName(siteOption.getValue()).getId(),topic.getText(),detail.getText(),"");
        controller.createPost(post);
    }

}