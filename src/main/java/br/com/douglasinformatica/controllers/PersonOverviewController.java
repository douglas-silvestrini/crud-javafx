package br.com.douglasinformatica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.douglasinformatica.App;
import br.com.douglasinformatica.models.Person;
import br.com.douglasinformatica.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController implements Initializable {
    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label streetLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Label birthdayLabel;

    private App app;

    public PersonOverviewController() {
    }

    public void setApp(App mainApp) {
        app = mainApp;
        personTable.setItems(app.getPersonData());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void showPersonDetails(Person person) {
        if (person == null) {
            firstNameLabel.setText(null);
            lastNameLabel.setText(null);
            streetLabel.setText(null);
            cityLabel.setText(null);
            postalCodeLabel.setText(null);
            birthdayLabel.setText(null);
        } else {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(person.getPostalCode().toString());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        }
    }

    @FXML
    public void handleDeletePerson() {
        int index = personTable.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            personTable.getItems().remove(index);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Ops! Houve um erro");
            alert.setHeaderText("Nenhuma pessoa selecionada");
            alert.setContentText("Por favor, selecione uma pessoa na tabela");
            alert.showAndWait();
        }
    }
}
