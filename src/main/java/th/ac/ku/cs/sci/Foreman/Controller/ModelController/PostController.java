package th.ac.ku.cs.sci.Foreman.Controller.ModelController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Service.PostService;

import java.util.Collection;

@Controller
@Component
public class PostController {

    private final PostService service ;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    public void createPost(Post post) {
        service.createPost(post);
    }

    public Post getById(int id) {
        return service.findById(id);
    }

    public void updatePost(Post post) {
//        System.out.println(post);
        service.update(post);
    }

    public Collection<Post> getAllBySite(int id) {
        return service.findBySite(id);
    }

    public Collection<Post> getAllPosts() {
        return service.findAll();
    }

}