package th.ac.ku.cs.sci.Foreman.Service;

import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Model.Comment;
import th.ac.ku.cs.sci.Foreman.data.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository repository ;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public void createComment(Comment comment) {
        repository.save(comment);
    }

    public void deleteComment(int id) {
        repository.deleteById(id);
    }
}