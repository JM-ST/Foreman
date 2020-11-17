package th.ac.ku.cs.sci.Foreman.Controller.ModelController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Service.UserService;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

@Controller
@Component
public class UserController {

    private final UserService service ;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    public void CreatedUser(User newuser){
        newuser.setPassword(new BCryptPasswordEncoder().encode(newuser.getPassword()));
        service.createUser(newuser);
    }

    public void verifyUser(String email, String password){
        User user = service.findByEmail(email);
        if (new BCryptPasswordEncoder().matches(password,user.getPassword())) {
            UserSession.getInstance(user);
        }
    }


}