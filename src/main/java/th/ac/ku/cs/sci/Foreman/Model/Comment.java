package th.ac.ku.cs.sci.Foreman.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User userid ;
    private int userid ;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Post postid ;
    private int postid ;

    private String detail ;


}