package th.ac.ku.cs.sci.Foreman;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ForemanApplication {

	public static void main(String[] args) {
		Application.launch(JavafxApplication.class, args);
	}

}
