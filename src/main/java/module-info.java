module br.com.douglasinformatica {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens br.com.douglasinformatica to javafx.fxml;
    opens br.com.douglasinformatica.controllers;
    opens br.com.douglasinformatica.utils;
    opens br.com.douglasinformatica.models;

    exports br.com.douglasinformatica;
    exports br.com.douglasinformatica.controllers;
    exports br.com.douglasinformatica.utils;
    exports br.com.douglasinformatica.models;
}