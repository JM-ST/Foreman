package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Comment.CreateCommentController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.CommentController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.Model.Post;

import java.io.IOException;


@Controller
@Setter
@FxmlView("showPost.fxml")
public class ShowPostController {

    private final Resource SHOWIMAGEFXML ;
    private final Resource COMMENTFXML ;
    private final ApplicationContext ac;
    private PostController postController ;
    private CommentController commentController;
    private Post post ;

    @FXML
    private TextField topic ;

    @FXML
    private TextArea detail ;

    @FXML
    private TableView<Comment> commentList ;


    @Autowired
    public ShowPostController(@Value("classpath:templates/PostFXML/showImage.fxml") Resource showimagefxml,
                              @Value("classpath:templates/CommentFXML/createComment.fxml") Resource commentfxml,
                              ApplicationContext ac,
                              PostController postController,
                              CommentController commentController) {
        SHOWIMAGEFXML = showimagefxml;
        COMMENTFXML = commentfxml;
        this.ac = ac ;
        this.postController = postController;
        this.commentController = commentController;
    }

    public void setPost(Post post) {
        this.post = postController.getById(post.getId());
    }

    public void initialize() {
        topic.setText(post.getTopic());
        detail.setText(post.getDetail());
        topic.setDisable(true);
        detail.setDisable(true);
        loadComment();
    }

    public void loadComment() {
        TableColumn<Comment,String> COMMENT = new TableColumn<>("Comment");
        COMMENT.setCellValueFactory(new PropertyValueFactory<>("detail"));
        commentList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        commentList.getColumns().addAll(COMMENT);
        commentList.getItems().addAll(commentController.findAllByPost(post.getId()));
    }

    public void handleBtnImage() {

    }

    public void handleBtnComment() throws IOException {
        StageCaller call = new StageCaller(COMMENTFXML,ac);
        CreateCommentController controller = call.getApplicationContext().getBean(CreateCommentController.class);
        controller.setPostid(post.getId());
        Stage stage = call.getStage("Comment on "+post.getTopic());
        stage.showAndWait();

        refreshComment();
    }

    private void refreshComment() {
        commentList.getItems().clear();
        commentList.getColumns().clear();
        loadComment();
    }

}