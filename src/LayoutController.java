
import java.sql.Date;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Cliente;
import models.Projeto;

public class LayoutController {

    @FXML
    private Button addCliente;

    @FXML
    private Button addProjeto;

    @FXML
    private Button altCliente;

    @FXML
    private Button altProjeto;

    @FXML
    private Button clearBottom;

    @FXML
    private SplitMenuButton clienteProjeto;

    @FXML
    private TableColumn<Projeto, String> colunaClienteProjeto;

    @FXML
    private TableColumn<Projeto, String> colunaContatoCliente;

    @FXML
    private TableColumn<Projeto, Date> colunaDFinalProjeto;

    @FXML
    private TableColumn<Projeto, Date> colunaDInicialProjeto;

    @FXML
    private TableColumn<Projeto, String> colunaDescProjeto;

    @FXML
    private TableColumn<Cliente, String> colunaEmailCliente;

    @FXML
    private TableColumn<Cliente, String> colunaEmpresaCliente;

    @FXML
    private TableColumn<Cliente, Integer> colunaIDCliente;

    @FXML
    private TableColumn<Projeto, Integer> colunaIDProjeto;

    @FXML
    private TableColumn<Cliente, String> colunaNomeCliente;

    @FXML
    private TableColumn<Projeto, String> colunaNomeProjeto;

    @FXML
    private TableColumn<Projeto, Float> colunaOrcamentoProjeto;

    @FXML
    private TableColumn<Projeto, String> colunaStatusProjeto;

    @FXML
    private TextField contatoClliente;

    @FXML
    private TextField dFinalProjeto;

    @FXML
    private TextField dInicialProjeto;

    @FXML
    private Button delCliente;

    @FXML
    private Button delProjeto;

    @FXML
    private TextArea descProjeto;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField empresaCliente;

    @FXML
    private TextField idCliente;

    @FXML
    private TextField idProjeto;

    @FXML
    private Button limpProjeto;

    @FXML
    private TextField nomeCliente;

    @FXML
    private TextField nomeProjeto;

    @FXML
    private TextField orcamentoProjeto;

    @FXML
    private Button pesqCliente;

    @FXML
    private Button pesqProjeto;

    @FXML
    private SplitMenuButton statusProjeto;

    @FXML
    void adicionarCliente(ActionEvent event) {

    }

    @FXML
    void adicionarProjeto(ActionEvent event) {

    }

    @FXML
    void alterarCliente(ActionEvent event) {

    }

    @FXML
    void alterarProjeto(ActionEvent event) {

    }

    @FXML
    void deletarCliente(ActionEvent event) {

    }

    @FXML
    void deletarProjeto(ActionEvent event) {

    }

    @FXML
    void limparCampos(ActionEvent event) {

    }

    @FXML
    void limparCamposP(ActionEvent event) {

    }

    @FXML
    void pesquisarCliente(ActionEvent event) {

    }

    @FXML
    void pesquisarProjeto(ActionEvent event) {

    }

}
