package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.PostController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Model.Site;

@Controller
@FxmlView
public class ReportSiteController {

    private final PostController postController;
    private final UserController userController;
    private final ApplicationContext ac ;

    @FXML
    private TextArea reportArea ;

    private Site site;


    public ReportSiteController(PostController postController, UserController userController, ApplicationContext ac) {
        this.postController = postController;
        this.userController = userController;
        this.ac = ac;
    }

    public void initialize() {
        String postName = "";
        for (Post s: postController.getAllBySite(site.getId())) {
            postName += "\t"+s.getTopic()+" "+s.getCreatedAt()+"\n";
        }
        reportArea.setText(
                "Site:     "+site.getName()+"\n"+
                "Status:   "+site.getStatus()+"\n"+
                "Foreman:  "+userController.getNameById(site.getUserid()).getName()+"\n"+
                "Detail:   "+site.getDetail()+"\n"+
                "Created:  "+site.getCreatedAt()+"\n"+
                "Updated:  "+site.getUpdateAt()+"\n"+
                "Post:     "+postController.getAllBySite(site.getId()).size()+"\n"+
                postName
        );
        System.out.println(ac.getDisplayName());
    }



    public void setSite(Site site) {
        this.site = site ;
    }
}