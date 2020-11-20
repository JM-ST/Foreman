package th.ac.ku.cs.sci.Foreman.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import th.ac.ku.cs.sci.Foreman.Controller.FXMLController.Post.EditPostController;

import java.io.IOException;

public class StageCaller {

    private final Resource resource ;
    private final ApplicationContext applicationContext ;

    public StageCaller(Resource resource, ApplicationContext ac) {
        this.resource = resource;
        applicationContext = ac;
    }

    public Stage getStage(String titleStage) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(resource.getURL());
        loader.setControllerFactory(applicationContext::getBean);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(titleStage);
        stage.setScene(new Scene((Parent) loader.load()));
        return stage;
    }

    public Stage changeScene(Stage stage,String titleStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(resource.getURL());
        loader.setControllerFactory(applicationContext::getBean);
        stage.setTitle(titleStage);
        stage.setScene(new Scene((Parent) loader.load()));
        return stage ;
    }

    public ApplicationContext getApplicationContext() {
        return  applicationContext;
    }

}