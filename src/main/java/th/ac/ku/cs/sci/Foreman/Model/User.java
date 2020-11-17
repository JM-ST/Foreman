package th.ac.ku.cs.sci.Foreman.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(unique = true)
    private String email ;

    private String name, tels , password ;

    @Enumerated(EnumType.STRING)
    private Role role ;

    public enum Role {
        USER,ADMIN ;
    }

    public User(String email,String password, String tels,Role role) {
        this.email = email;
        this.password = password;
        this.tels = tels;
        this.role = role;
    }

}

