package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Comment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.CommentController;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;


@Controller
@FxmlView("createComment.fxml")
public class CreateCommentController {

    private final CommentController controller ;
    private int postid ;

    @FXML
    private TextArea comment ;

    @Autowired
    public CreateCommentController(CommentController controller) {
        this.controller = controller;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public void initialize() {

    }

    public void handleBtnCreate(ActionEvent event) {
        Comment record = new Comment(UserSession.getUserInstance().getId(),postid,comment.getText());
        controller.createComment(record);
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.close();
    }

}