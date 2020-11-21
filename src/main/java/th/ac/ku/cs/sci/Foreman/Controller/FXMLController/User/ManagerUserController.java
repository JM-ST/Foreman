package th.ac.ku.cs.sci.Foreman.Controller.FXMLController.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import th.ac.ku.cs.sci.Foreman.Application.StageCaller;
import th.ac.ku.cs.sci.Foreman.Controller.ModelController.UserController;
import th.ac.ku.cs.sci.Foreman.Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@FxmlView
public class ManagerUserController {


    private final UserController controller ;
    private final Resource CREATEUSERFXML;
    private final Resource EDITUSERFXML;
    private final ApplicationContext ac ;

    @FXML
    private TextField search;

    @FXML
    private TableView<User> table ;

    public ManagerUserController(UserController controller,
                                 @Value("classpath:templates/UserFXML/create.fxml") Resource createuserfxml,
                                 @Value("classpath:templates/UserFXML/edit.fxml") Resource editUserfxml,
                                 ApplicationContext ac) {
        this.controller = controller;
        EDITUSERFXML = editUserfxml;
        CREATEUSERFXML = createuserfxml;
        this.ac = ac;
    }

    public void initialize() {
        loadInsertable();
        search.textProperty().addListener((observable,oldValue,newValue) ->
                table.setItems(filterList((List<User>) controller.getAll(),newValue)));
    }

    public void handleBtnCreate(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(CREATEUSERFXML,ac);
        call.getStage("Create User").showAndWait();
        refreshTable();
    }

    public void handleBtnEdit(ActionEvent event) throws IOException {
        StageCaller call = new StageCaller(EDITUSERFXML,ac);
        EditUserController controller = call.getApplicationContext().getBean(EditUserController.class);
        controller.setUser(table.getSelectionModel().getSelectedItem());
        call.getStage(table.getSelectionModel().getSelectedItem().getName()).showAndWait();;
        refreshTable();
    }

    private void loadInsertable() {
        TableColumn<User,User.Role> ROLE = new TableColumn<>("ROLE");
        TableColumn<User,String> NAME = new TableColumn<>("Name");
        TableColumn<User,String> MAIL = new TableColumn<>("E-Mail");
        TableColumn<User,String> TEL = new TableColumn<>("Tel.");

        ROLE.setCellValueFactory(new PropertyValueFactory<>("role"));
        NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
        MAIL.setCellValueFactory(new PropertyValueFactory<>("email"));
        TEL.setCellValueFactory(new PropertyValueFactory<>("tel"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(ROLE,NAME,MAIL,TEL);
        table.getItems().addAll(controller.getAll());

    }

    private void refreshTable() {
        table.getItems().clear();
        table.getColumns().clear();
        loadInsertable();
    }

    private boolean searchFindsUser(User user, String searchText){
        return (user.getName().toLowerCase().contains(searchText.toLowerCase())) ||
                (user.getEmail().toLowerCase().contains(searchText.toLowerCase()));
    }

    private ObservableList<User> filterList(List<User> userList, String searchText){
        List<User> filteredList = new ArrayList<>();
        for (User user : userList){
            if(searchFindsUser(user, searchText)) filteredList.add(user);
        }
        return FXCollections.observableList(filteredList);
    }
}