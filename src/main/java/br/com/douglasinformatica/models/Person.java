package br.com.douglasinformatica.models;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty city;
    private IntegerProperty postalCode;
    private ObjectProperty<LocalDate> birthday;

    /**
     * Construtor padrão.
     */
    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("some city");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty streetProperty() {
        return street;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getStreet() {
        return street.get();
    }

    public String getCity() {
        return city.get();
    }

    public Integer getPostalCode() {
        return postalCode.get();
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode.set(postalCode);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

}
