package th.ac.ku.cs.sci.Foreman;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavafxApplication extends Application {

    private ConfigurableApplicationContext context ;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = genericApplicationContext -> {
                genericApplicationContext.registerBean(Application.class, () -> JavafxApplication.this);
                genericApplicationContext.registerBean(Parameters.class, this::getParameters);
                genericApplicationContext.registerBean(HostServices.class, this::getHostServices);
        };

        this.context = new SpringApplicationBuilder()
                .sources(ForemanApplication.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

}

class StageReadyEvent extends ApplicationEvent {
    public Stage getStage() {
        return Stage.class.cast(getSource());
    }

    public StageReadyEvent(Stage source) {
        super(source);
    }
}