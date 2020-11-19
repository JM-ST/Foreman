package th.ac.ku.cs.sci.Foreman.Service;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.CommentController;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;
import th.ac.ku.cs.sci.Foreman.data.CommentRepository;

import java.util.Collection;

@Service
public class CommentService {

    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public void createComment(Comment comment) {
        repository.save(comment);
    }

    public void updateComment(Comment comment) {
        repository.save(comment);
    }

    public void deleteComment(int id) {
        repository.deleteById(id);
    }

    public Collection<Comment> findCommentByPostId(int id) {
        return repository.findAllByPostid(id);
    }

}