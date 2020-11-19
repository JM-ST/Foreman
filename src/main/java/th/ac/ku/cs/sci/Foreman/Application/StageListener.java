package th.ac.ku.cs.sci.Foreman.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

    private final String applictionTitle ;
    private final Resource fxml ;
    private final ApplicationContext context;


    StageListener(@Value("Foreman") String applictionTitle,
                  @Value("classpath:templates/UserFXML/login.fxml") Resource fxml,
                  ApplicationContext context) {
        this.applictionTitle = applictionTitle;
        this.fxml = fxml;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(context::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,800,600);
            stage.setScene(scene);
            stage.setTitle(this.applictionTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}