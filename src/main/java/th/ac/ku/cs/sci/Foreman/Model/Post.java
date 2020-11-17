package th.ac.ku.cs.sci.Foreman.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

//   @ManyToOne
//   @JoinColumn(nullable = false)
//   @OnDelete(action = OnDeleteAction.CASCADE)
//   private User userid ;
    private int userid;

//   @ManyToOne(optional = false)
//   @JoinColumn(nullable = false)
//   @OnDelete(action = OnDeleteAction.CASCADE)
//   private Site siteid ;
    private int siteid;

   private String topic , detail ;

   private String image ;

   @CreationTimestamp
   private Date createdAt ;

   @UpdateTimestamp
   private Date updateAt ;

    public Post(int userid,int siteid, String topic, String detail, String image) {
        this.userid = userid;
        this.siteid = siteid;
        this.topic = topic;
        this.detail = detail;
        this.image = image;
    }
}