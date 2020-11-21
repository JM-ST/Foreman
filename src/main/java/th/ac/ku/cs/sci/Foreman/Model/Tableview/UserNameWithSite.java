package th.ac.ku.cs.sci.Foreman.Model.Tableview;

import lombok.AllArgsConstructor;
import lombok.Data;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Model.User;

import java.sql.Date;

@Data
public class UserNameWithSite {

    private Site modelSite ;
    private int siteId ;
    private String userName ;
    private Site.Status status ;
    private String siteName ;
    private Date updateAt;

    public UserNameWithSite(Site site,String userName){
        modelSite = site;
        siteId = site.getId();
        this.userName = userName;
        siteName = site.getName();
        status = site.getStatus();
        updateAt = site.getUpdateAt();
    }


}