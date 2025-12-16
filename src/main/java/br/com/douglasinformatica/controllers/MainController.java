package br.com.douglasinformatica.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import br.com.douglasinformatica.models.Estabelecimento;
import br.com.douglasinformatica.models.Produto;
import br.com.douglasinformatica.services.ProdutoService;
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

    private static ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    private ProdutoService service = new ProdutoService();

    public static ObservableList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public static void setListaProdutos(ArrayList<Produto> listaProdutos) {
        MainController.listaProdutos.clear();
        MainController.listaProdutos.addAll(listaProdutos);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        montarTabela();
        btnPesquisar.setOnAction(event -> {
            pesquisar();
        });
    }

    public void pesquisar() {
        String termo = txtPesquisa.getText();
        if (termo != "") {
            service.all(termo, cbGtin.isSelected());
        }
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
                    String nome = estabelecimento.getNm_fan() + " - " + estabelecimento.getNm_emp();
                    return new SimpleStringProperty(nome);
                });
        tblProdutos.setItems(listaProdutos);
    }

}
