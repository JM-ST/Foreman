package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

@Controller
@FxmlView
public class EditPostController {

    private final PostController controller ;

    @FXML
    private TextField topic ;

    @FXML
    private TextArea detail ;

    private Post post;

    @Autowired
    public EditPostController(PostController controller) {
        this.controller = controller;
    }

    public void setPost(Post post) {
        this.post = controller.getById(post.getId());
    }

    public void initialize() {
        topic.setText(post.getTopic());
        detail.setText(post.getDetail());
    }


    public void handleBtnUpdate(ActionEvent event) {
        Post record = controller.getById(post.getId());
        record.setDetail(detail.getText());
        controller.updatePost(record);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.close();
    }
}