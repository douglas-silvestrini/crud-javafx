package br.com.douglasinformatica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.douglasinformatica.models.Person;
import br.com.douglasinformatica.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController implements Initializable {

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

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField birthdayTextField;

    private Stage dialogStage;
    private Person person;
    private Boolean okClicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameTextField.setText(person.getFirstName());
        lastNameTextField.setText(person.getLastName());
        cityTextField.setText(person.getCity());
        streetTextField.setText(person.getStreet());
        postalCodeTextField.setText(person.getPostalCode().toString());
        birthdayTextField.setText(DateUtil.format(person.getBirthday()));
        birthdayTextField.setPromptText("dd.MM.yyyy");
    }

    public Boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void handleOkButton() {
        if (isValidInputs()) {
            person.setFirstName(firstNameTextField.getText());
            person.setLastName(lastNameTextField.getText());
            person.setStreet(streetTextField.getText());
            person.setCity(cityTextField.getText());
            person.setBirthday(DateUtil.parse(birthdayTextField.getText()));
            person.setPostalCode(Integer.parseInt(postalCodeTextField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancelButton() {
        dialogStage.close();
    }

    public Boolean isValidInputs() {
        return true;
    }

}
