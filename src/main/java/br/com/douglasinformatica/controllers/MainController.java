package br.com.douglasinformatica.controllers;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.douglasinformatica.models.Estabelecimento;
import br.com.douglasinformatica.models.Produto;
import br.com.douglasinformatica.utils.Response;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController implements Initializable {

    @FXML
    private TableView<Produto> tblProdutos;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableColumn<Produto, String> colDescricao;

    @FXML
    private TableColumn<Produto, String> colGtin;

    @FXML
    private TableColumn<Produto, String> colValor;

    @FXML
    private TableColumn<Produto, String> colValorTabela;

    @FXML
    private CheckBox cbGtin;

    @FXML
    private TableColumn<Produto, String> colValorDesconto;

    @FXML
    private TableColumn<Produto, String> colTempo;

    @FXML
    private TableColumn<Produto, String> colEstabelecimento;

    ObservableList<Produto> listaProdutos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbGtin.setSelected(false);
        listaProdutos = FXCollections.observableArrayList();
        montarTabela();
        btnPesquisar.setOnAction(event -> {
            pesquisar();
        });
    }

    public void pesquisar() {
        String termo = txtPesquisa.getText();
        if (termo != "") {
            System.out.println("Pesquisando por: " + termo);
            request(termo);
        }
    }

    public void request(String termo) {
        String pesquisa = !cbGtin.isSelected() ? "&termo=" + URLEncoder.encode(termo, StandardCharsets.UTF_8)
                : "&gtin=" + termo;
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://menorpreco.notaparana.pr.gov.br/api/v1/produtos?local=6gukbgyzh" + pesquisa
                        + "&offset=0&raio=2&data=-1&ordem=0"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:144.0) Gecko/20100101 Firefox/144.0")
                .timeout(Duration.ofSeconds(10))
                .build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
        response.thenAccept(data -> {
            try {
                traduzir(data);
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    public void traduzir(HttpResponse<String> data) throws JsonMappingException, JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Response json = obj.readValue(data.body(), Response.class);
        listaProdutos.clear();
        listaProdutos.addAll(json.getProdutos());
        System.out.println("Produtos carregados");
    }

    public void montarTabela() {
        colDescricao.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDesc()));
        colGtin.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getGtin()));
        colTempo.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTempo()));
        colValor.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValor()));
        colValorDesconto.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValor_desconto()));
        colValorTabela.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValor_tabela()));
        colEstabelecimento.setCellValueFactory(
                param -> {
                    Estabelecimento estabelecimento = param.getValue().getEstabelecimento();
                    String nome = estabelecimento.getNm_fan() == "" ? estabelecimento.getNm_fan()
                            : estabelecimento.getNm_emp();
                    return new SimpleStringProperty(nome);
                });
        tblProdutos.setItems(listaProdutos);
    }

}
