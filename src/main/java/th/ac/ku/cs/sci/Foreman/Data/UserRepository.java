package th.ac.ku.cs.sci.Foreman.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.User;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Collection<User> findAllByRole(User.Role role);
    User findUserByEmail(String email);
    User findUserByName(String name);

}