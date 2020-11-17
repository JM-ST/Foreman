package th.ac.ku.cs.sci.Foreman.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;

    @Enumerated(EnumType.STRING)
    private Status status ;

    @CreationTimestamp
    private Date createdAt ;

    public enum Status {
        CANCEL,PLANING,WORKING,FINISHED ;
    }


    public Site(String name,Status status) {
        this.name = name ;
        this.status = status ;
    }

}