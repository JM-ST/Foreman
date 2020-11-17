package th.ac.ku.cs.sci.Foreman.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.ac.ku.cs.sci.Foreman.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

}