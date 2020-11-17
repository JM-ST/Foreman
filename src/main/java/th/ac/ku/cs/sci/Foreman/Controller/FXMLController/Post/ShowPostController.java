package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Model.Post;


@Controller
@Setter
@FxmlView("showPost.fxml")
public class ShowPostController {

    private PostController controller ;
    private Post post ;

    @FXML
    private TextField topic ;

    @FXML
    private TextArea detail ;


    @Autowired
    public ShowPostController(PostController controller) {
        this.controller = controller;
    }

    public void setPost(Post post) {
        this.post = controller.getById(post.getId());
    }

    public void initialize() {
        topic.setText(post.getTopic());
        detail.setText(post.getDetail());
        topic.setDisable(true);
        detail.setDisable(true);
    }

}