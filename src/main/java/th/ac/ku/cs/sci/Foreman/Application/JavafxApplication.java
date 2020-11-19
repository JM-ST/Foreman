package th.ac.ku.cs.sci.Foreman.Application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import th.ac.ku.cs.sci.Foreman.ForemanApplication;


public class JavafxApplication extends Application {

    private ConfigurableApplicationContext context ;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                genericApplicationContext -> {
                genericApplicationContext.registerBean(Application.class, () -> JavafxApplication.this);
                genericApplicationContext.registerBean(Parameters.class,this::getParameters);
                genericApplicationContext.registerBean(HostServices.class,this::getHostServices);
            };
        this.context = new SpringApplicationBuilder()
                .sources(ForemanApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

}
