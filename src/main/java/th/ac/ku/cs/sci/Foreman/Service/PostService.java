package th.ac.ku.cs.sci.Foreman.Service;

import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Data.PostRepository;

import java.util.Collection;

@Service
public class PostService {

    private final PostRepository repository ;

    public PostService(PostRepository repository) {
        this.repository = repository ;
    }

    public void createPost(Post post) {
        repository.save(post);
    }

    public Post findById(int id){
        return repository.findById(id).get();
    }

    public Collection<Post> findBySite(int id) {return repository.findPostsBySiteid(id);}

    public Collection<Post> findAll() {
        return repository.findAll() ;
    }

    public Collection<Post> findByuserId(int user_id) {
        return repository.findPostsByUserid(user_id);
    }

    public void update(Post post) {
        repository.save(post);
    }

    public Post delete(int id) {
        Post delete = repository.findById(id).get();
        repository.deleteById(id);
        return delete;
    }

}