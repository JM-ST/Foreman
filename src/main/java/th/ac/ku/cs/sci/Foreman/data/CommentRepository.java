package th.ac.ku.cs.sci.Foreman.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
