package th.ac.ku.cs.sci.Foreman.Service;

import org.springframework.stereotype.Service;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.data.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository ;

    public UserService(UserRepository repository) {
        this.repository = repository ;
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    public User findById(int id) {
        return repository.findById(id).get();
    }

    public List<User> getAllUser() {
        return repository.findAll();
    }

    public User findByEmail(String email){
        return repository.findUserByEmail(email);
    }

}