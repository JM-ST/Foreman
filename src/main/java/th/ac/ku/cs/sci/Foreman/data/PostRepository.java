package th.ac.ku.cs.sci.Foreman.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.Post;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostsByUserid(int user_id) ;
    Collection<Post> findPostsBySiteid(int id) ;

}
