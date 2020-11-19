package th.ac.ku.cs.sci.Foreman.Controller.ModelController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Service.CommentService;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.util.Collection;

@Controller
@Component
public class CommentController {

    private final CommentService service ;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    public void createComment(Comment comment) {
        Comment record = new Comment(comment.getUserid(),comment.getPostid(),comment.getDetail());
        service.createComment(record);
    }

    public void updateComment(Comment comment){
        service.updateComment(comment);
    }

    public Collection<Comment> findAllByPost(int id) {
        return service.findCommentByPostId(id);
    }

}