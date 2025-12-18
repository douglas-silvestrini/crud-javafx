package br.com.douglasinformatica.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.douglasinformatica.models.Person;
import br.com.douglasinformatica.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
        String errors = "";
        // first name
        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errors += "O campo first name não pode ficar vazio.\n";
        }

        // last name
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errors += "O campo last name não pode ficar vazio.\n";
        }

        // city
        if (cityTextField.getText() == null || cityTextField.getText().length() == 0) {
            errors += "O campo city não pode ficar vazio.\n";
        }

        // street
        if (streetTextField.getText() == null || streetTextField.getText().length() == 0) {
            errors += "O campo street não pode ficar vazio.\n";
        }

        // postal code
        if (postalCodeTextField.getText() == null || postalCodeTextField.getText().length() == 0) {
            errors += "O campo postal code não pode ficar vazio.\n";
        } else {
            try {
                Integer.parseInt(postalCodeTextField.getText());
            } catch (NumberFormatException e) {
                errors += "O postal code não é válido.\n";
            }
        }

        // birthday
        if (birthdayTextField.getText() == null || birthdayTextField.getText().length() == 0) {
            errors += "O campo birthday não pode ficar vazio.\n";
        } else {
            // data válida
            if (!DateUtil.isValidDate(birthdayTextField.getText())) {
                errors += "O campo birthday não possui uma data válida.\n";
            }
        }

        // campos inválidos
        if (errors.length() != 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ops! Houve um erro");
            alert.setHeaderText("Por favor, corrija os campos inválidos");
            alert.setContentText(errors);
            alert.showAndWait();
            return false;
        }

        return true;
    }

}
