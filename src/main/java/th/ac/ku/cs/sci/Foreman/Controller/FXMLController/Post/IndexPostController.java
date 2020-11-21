package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Site.DetailSiteController;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Service.PostService;
import th.ac.ku.cs.sci.Foreman.Service.SiteService;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.io.IOException;
import java.sql.Date;

@Controller
@FxmlView("index.fxml")
public class IndexPostController {

    private final Resource SITEDETAILFXML ;
    private final Resource CREATEPOSTFXML;
    private final Resource EDITPOSTFXML;
    private final Resource SHOWPOSTFXML;
    private final ApplicationContext ac ;
    private final SiteService siteService ;
    private final PostService postService ;
    private Site site ;


    @FXML
    private Button editBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Label siteName , status ;
    @FXML
    private TableView<Post> table ;


    @Autowired
    public IndexPostController(@Value("classpath:templates/SiteFXML/detailSite.fxml") Resource SITEDETAILFXML,
                               @Value("classpath:templates/PostFXML/createPost.fxml") Resource CREATEPOSTFXML,
                               @Value("classpath:templates/PostFXML/editPost.fxml") Resource EDITPOSTFXML,
                               @Value("classpath:templates/PostFXML/showPost.fxml") Resource SHOWPOSTFXML,
                               ApplicationContext ac,
                               SiteService siteService, PostService postService) {
        this.SITEDETAILFXML = SITEDETAILFXML;
        this.CREATEPOSTFXML = CREATEPOSTFXML;
        this.EDITPOSTFXML = EDITPOSTFXML;
        this.SHOWPOSTFXML = SHOWPOSTFXML;
        this.siteService = siteService;
        this.postService = postService;
        this.ac = ac ;
    }

    public void setStie(Site site) {
        this.site = siteService.findByName(site.getName());
    }

    public void initialize() {
        siteName.setText("SITE: "+site.getName());
        status.setText("STATUS: "+ site.getStatus().toString());

        if (UserSession.getUserInstance().getRole() == User.Role.VIEWER){
            createBtn.setVisible(false);
            editBtn.setVisible(false);
        }
        loadPost();
    }

    public void handleBtnDetail(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(SITEDETAILFXML,ac);
        DetailSiteController controller = call.getApplicationContext().getBean(DetailSiteController.class);
        controller.setSite(site);
        call.getStage("Detail : "+site.getName()).showAndWait();

        tableRefresh();
    }

    public void handleBtnShow() throws IOException {
        StageCaller call = new StageCaller(SHOWPOSTFXML,ac);
        ShowPostController controller = call.getApplicationContext().getBean(ShowPostController.class);
        controller.setPost(table.getSelectionModel().getSelectedItem());
        call.getStage("SHOW : "+table.getSelectionModel().getSelectedItem().getTopic()).showAndWait();

        tableRefresh();
    }

    public void handleBtnCreate() throws IOException {
        StageCaller call = new StageCaller(CREATEPOSTFXML,ac);
        CreatePostController controller = call.getApplicationContext().getBean(CreatePostController.class);
        controller.setSiteId(site.getId());
        call.getStage("CREATE").showAndWait();

        tableRefresh();
    }

    public void handleBtnEdit() throws IOException {
        StageCaller call = new StageCaller(EDITPOSTFXML,ac);
        EditPostController controller = call.getApplicationContext().getBean(EditPostController.class);
        controller.setPost(table.getSelectionModel().getSelectedItem());
        call.getStage("EDIT : "+table.getSelectionModel().getSelectedItem().getTopic()).showAndWait();

        tableRefresh();;
    }

    private void loadPost() {

        TableColumn<Post,String> TOPIC = new TableColumn<>("Topic");
        TableColumn<Post,String> DETAIL = new TableColumn<>("Detail");
        TableColumn<Post, Date> CDATE = new TableColumn<>("Created");
        TableColumn<Post, Date> LUDATE = new TableColumn<>("Updated");

        TOPIC.setCellValueFactory(new PropertyValueFactory<>("topic"));
        DETAIL.setCellValueFactory(new PropertyValueFactory<>("detail"));
        CDATE.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        LUDATE.setCellValueFactory(new PropertyValueFactory<>("updateAt"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(TOPIC, DETAIL, CDATE, LUDATE);
        table.getItems().addAll(postService.findBySite(site.getId()));
    }

    private void tableRefresh() {
        table.getItems().clear();
        table.getColumns().clear();
        loadPost();
    }
}