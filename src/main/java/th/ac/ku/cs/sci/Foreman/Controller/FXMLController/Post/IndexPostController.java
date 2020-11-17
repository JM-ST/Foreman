package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Model.Post;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Service.PostService;
import th.ac.ku.cs.sci.Foreman.Service.SiteService;

import java.io.IOException;
import java.sql.Date;

@Controller
@FxmlView("index.fxml")
public class IndexPostController {

    private final Resource CREATEPOSTFXML;
    private final Resource EDITPOSTFXML;
    private final Resource SHOWPOSTFXML;
    private final ApplicationContext ac ;
    private final SiteService siteService ;
    private final PostService postService ;
    private Site site ;

    @FXML
    private Label siteName , status ;

    @FXML
    private TableView<Post> table ;


    @Autowired
    public IndexPostController(@Value("classpath:templates/PostFXML/createPost.fxml") Resource CREATEPOSTFXML,
                               @Value("classpath:templates/PostFXML/editPost.fxml") Resource EDITPOSTFXML,
                               @Value("classpath:templates/PostFXML/showPost.fxml") Resource SHOWPOSTFXML,
                               ApplicationContext ac,
                               SiteService siteService, PostService postService) {
        this.CREATEPOSTFXML = CREATEPOSTFXML;
        this.EDITPOSTFXML = EDITPOSTFXML;
        this.SHOWPOSTFXML = SHOWPOSTFXML;
        this.ac = ac ;
        this.siteService = siteService;
        this.postService = postService;
    }

    public void setStie(Site site) {
        this.site = siteService.findByName(site.getName());
    }

    public void initialize() {
        siteName.setText("SITE: "+site.getName());
        status.setText("STATUS: "+ site.getStatus().toString());
        loadPost();
    }

    public void handleBtnShow() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(SHOWPOSTFXML.getURL());
            loader.setControllerFactory(ac::getBean);
            ShowPostController controller = ac.getBean(ShowPostController.class);
            controller.setPost(table.getSelectionModel().getSelectedItem());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SHOW");
            stage.setScene(new Scene((Parent) loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableRefresh();
    }

    public void handleBtnCreate() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(CREATEPOSTFXML.getURL());
            loader.setControllerFactory(ac::getBean);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("CREATE");
            stage.setScene(new Scene((Parent) loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableRefresh();
    }

    public void handleBtnEdit() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(EDITPOSTFXML.getURL());
            loader.setControllerFactory(ac::getBean);
            EditPostController controller = ac.getBean(EditPostController.class);
            controller.setPost(table.getSelectionModel().getSelectedItem());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("EDIT");
            stage.setScene(new Scene((Parent) loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableRefresh();
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

        table.getColumns().addAll(TOPIC, DETAIL, CDATE, LUDATE);
        table.getItems().addAll(postService.findBySite(site.getId()));
    }

    private void tableRefresh() {
        table.getItems().clear();
        table.getColumns().clear();
        loadPost();
    }

}