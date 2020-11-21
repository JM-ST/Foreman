package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Session.UserSession;

import java.io.IOException;


@Component
@FxmlView("login.fxml")
public class LoginController {

    private final UserController controller ;
    private final ApplicationContext ac ;
    private final Resource INDEXFXML ;

    @FXML
    private TextField email ;
    @FXML
    private Button signup ;
    @FXML
    private PasswordField password ;

    public LoginController(UserController controller,
                           ApplicationContext ac,
                           @Value("classpath:templates/UserFXML/index.fxml") Resource indexfxml)
    {
        this.controller = controller;
        this.ac = ac;
        INDEXFXML = indexfxml;
    }

    @FXML
    public void initialize() {
        signup.setVisible(false);
    }

    @FXML
    public void handleBtn_login(ActionEvent event) throws IOException{
        StageCaller call = new StageCaller(INDEXFXML,ac);
        if (controller.verifyUser(email.getText(),password.getText())){
            call.changeScene((Stage) ((Node)event.getTarget()).getScene().getWindow(),
                    "Welcome: "+UserSession.getUserInstance().getName()).show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Wrong username or password.");
            alert.showAndWait();
        }
    }

//    @FXML
//    public void handleBtnSignUp(ActionEvent event) {
//        try {
//        Stage stage = new Stage();
//        FXMLLoader fxmlLoader = new FXMLLoader(SIGNUPFXML.getURL());
//        fxmlLoader.setControllerFactory(ac::getBean);
//        Parent root = fxmlLoader.load();
//        Scene scene = new Scene(root,800,600);
//        stage.setScene(scene);
//        stage.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    }







}