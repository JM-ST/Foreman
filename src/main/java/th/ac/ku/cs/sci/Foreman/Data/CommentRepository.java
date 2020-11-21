package th.ac.ku.cs.sci.Foreman.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.Comment;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Collection<Comment> findAllByPostid(int id) ;

}
