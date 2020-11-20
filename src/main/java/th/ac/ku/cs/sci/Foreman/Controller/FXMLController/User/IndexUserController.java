package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post.IndexPostController;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.SiteController;
import th.ac.ku.cs.sci.Foreman.Model.Site;
import th.ac.ku.cs.sci.Foreman.Model.User;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;


import java.io.IOException;
import java.sql.Date;


@Controller
@FxmlView
public class IndexUserController {

    private final SiteController siteController;
    private final Resource MANGERUSERFXML ;
    private final Resource SITEFXML;
    private final Resource POSTFXML;
    private final Resource LOGIN;
    private final ApplicationContext ac ;

    @FXML
    private TableView<Site> table ;

    @FXML
    private Button createSiteBtn, userManagerBtn;

    @FXML
    private ImageView profile ;

    @Autowired
    public IndexUserController(SiteController siteController,
                               @Value("classpath:templates/UserFXML/managerUser.fxml") Resource MANAGERUSERFXML,
                               @Value("classpath:templates/SiteFXML/createSite.fxml") Resource SITEFXML,
                               @Value("classpath:templates/PostFXML/index.fxml") Resource POSTFXML,
                               @Value("classpath:templates/UserFXML/login.fxml") Resource LOGIN,
                               ApplicationContext ac) {
        this.siteController = siteController;
        this.MANGERUSERFXML = MANAGERUSERFXML;
        this.SITEFXML = SITEFXML;
        this.POSTFXML = POSTFXML;
        this.LOGIN = LOGIN;
        this.ac = ac ;
    }

    public void initialize() throws IOException {
        profile.setImage(new Image("/public/placeholder.jpg"));
        loadSite();
    }

    public void handleBtnUserManager(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(MANGERUSERFXML,ac);
        call.getStage("ManagerUser").showAndWait();;
    }

    public void handleBtnShow(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(POSTFXML,ac);
        IndexPostController controller = call.getApplicationContext().getBean(IndexPostController.class);
        controller.setStie(table.getSelectionModel().getSelectedItem());
        call.getStage(table.getSelectionModel().getSelectedItem().getName()).showAndWait();
    }

    @FXML
    public void handleBtnCreateSite(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(SITEFXML.getURL());
            loader.setControllerFactory(ac::getBean);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene((Parent) loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableRefresh();
    }

    @FXML
    public void handleBtnExits(ActionEvent event) throws IOException {
        UserSession.clearUserSession();
        StageCaller call = new StageCaller(LOGIN,ac);
        call.changeScene((Stage) ((Node)event.getTarget()).getScene().getWindow(),"Login");
    }

    private void tableRefresh() {
        table.getItems().clear();
        table.getColumns().clear();
        loadSite();
    }

    private void loadSite() {

        TableColumn<Site,String> SITE = new TableColumn<>("Site");
        TableColumn<Site,String> STATUS = new TableColumn<>("Status");
        TableColumn<Site,Date> DATE = new TableColumn<>("lastUpDate");

        SITE.setCellValueFactory(new PropertyValueFactory<>("name"));
        STATUS.setCellValueFactory(new PropertyValueFactory<>("status"));
        DATE.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        table.getColumns().addAll(SITE,STATUS,DATE);
        table.getItems().addAll(siteController.getAll());

        if (UserSession.getUserInstance().getRole() != User.Role.ADMIN) {
            createSiteBtn.setVisible(false);
            userManagerBtn.setVisible(false);
        }

    }
}