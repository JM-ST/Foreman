package th.ac.ku.cs.sci.Foreman.Service;

import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.Data.CommentRepository;

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