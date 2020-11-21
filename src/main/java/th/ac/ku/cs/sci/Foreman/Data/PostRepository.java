package th.ac.ku.cs.sci.Foreman.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.Post;

import java.util.Collection;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostsByUserid(int user_id) ;
    Collection<Post> findPostsBySiteid(int id) ;

}
