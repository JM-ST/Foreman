package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import th.ac.ku.cs.sci.Foreman.Application.StageReadyEvent;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;


@Component
@FxmlView("login.fxml")
public class LoginController {

    private final UserController controller ;
    private final ApplicationContext ac ;
    private final Resource INDEXFXML ;

    @FXML
    private TextField email ;

    @FXML
    private PasswordField password ;


    public LoginController(UserController controller, ApplicationContext ac,
                           @Value("classpath:templates/UserFXML/index.fxml") Resource indexfxml) {
        this.controller = controller;
        this.ac = ac;
        INDEXFXML = indexfxml;
    }


    @FXML
    public void initialize() {

    }

    @FXML
    public void handleBtn_login(ActionEvent event) {
        try {
            controller.verifyUser(email.getText(),password.getText());
            if (UserSession.getUserInstance() == null){
                throw new Exception();
            }
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(INDEXFXML.getURL());
            fxmlLoader.setControllerFactory(ac::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,800,600);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.showAndWait();
//            StageReadyEvent stage = new StageReadyEvent(stageReadyEvent.getStage());
//            ac.publishEvent(stageReadyEvent);

//            stage.setScene(scene);
//            stage.show();
//            stageReadyEvent.getStage().show();
//            Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing);

        }catch (Exception e) {
            e.getMessage();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Login Failed");
//            alert.setHeaderText(null);
//            alert.setContentText("Wrong username or password.");
//            alert.showAndWait();
        }
    }







}