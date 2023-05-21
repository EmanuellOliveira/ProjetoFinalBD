package view;

import java.time.LocalDate;
import java.util.List;

import DAO.ClienteDAO;
import DAO.ProjetoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cliente;
import models.Projeto;

public class LayoutController {

    private ClienteDAO clientes;
    private ProjetoDAO projetos;

    public void initialize(){

        clientes = new ClienteDAO();
        projetos = new ProjetoDAO();

        colunaIDCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaEmpresaCliente.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colunaEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaContatoCliente.setCellValueFactory(new PropertyValueFactory<>("teleCel"));

        colunaIDProjeto.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeProjeto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDescProjeto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaDInicialProjeto.setCellValueFactory(new PropertyValueFactory<>("dataInicial"));
        colunaDFinalProjeto.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        colunaOrcamentoProjeto.setCellValueFactory(new PropertyValueFactory<>("orcamento"));
        colunaStatusProjeto.setCellValueFactory(new PropertyValueFactory<>("status"));
        colunaClienteProjeto.setCellValueFactory(new PropertyValueFactory<>("cliente"));

        atualizarTabelaClientes();
        atualizarTabelaProjetos();
        atualizarListaClientes();
        
    }

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
    private TableColumn<Projeto, LocalDate> colunaDFinalProjeto;

    @FXML
    private TableColumn<Projeto, LocalDate> colunaDInicialProjeto;

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
    private TextField contatoCliente;

    @FXML
    private DatePicker dFinalProjeto;

    @FXML
    private DatePicker dInicialProjeto;

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
    private TableView<Cliente> tableViewCliente;

    @FXML
    private TableView<Projeto> tableViewProjeto;

    @FXML
    void adicionarCliente(ActionEvent event) {

        String nome = nomeCliente.getText();
        String empresa = empresaCliente.getText();
        String email = emailCliente.getText();
        String contato = contatoCliente.getText();

        Cliente cliente = new Cliente(nome, empresa, email, contato);
        clientes.save(cliente);

        limparCamposCliente();

        atualizarTabelaClientes();
        atualizarListaClientes();
    }

    private void limparCamposCliente() {
        nomeCliente.setText("");
        empresaCliente.setText("");
        emailCliente.setText("");
        contatoCliente.setText("");
    }

    @FXML
    void adicionarProjeto(ActionEvent event) {

        String nome = nomeProjeto.getText();
        String descricao = descProjeto.getText();
        LocalDate dataInicial = dInicialProjeto.getValue();
        LocalDate dataFinal = dFinalProjeto.getValue();
        float orcamenteo = Float.parseFloat(orcamentoProjeto.getText());
        String status = statusProjeto.getText();

        String clienteSelecionado = clienteProjeto.getText();
        Cliente cliente = encontrarClientePorNome(clienteSelecionado);

        if (cliente != null) {
            Projeto projeto = new Projeto(nome, descricao, dataInicial, dataFinal, orcamenteo, status, cliente.getIdCliente());
            projetos.save(projeto);
            
            limparCamposProjeto();
            atualizarTabelaProjetos();
        } else {
            // Cliente não encontrado, trate o erro adequadamente
            System.out.println("Erro: Cliente não encontrado.");
        }
    }

    private void limparCamposProjeto() {
        nomeProjeto.setText("");
        descProjeto.setText("");
        dInicialProjeto.setValue(null);
        dFinalProjeto.setValue(null);
        orcamentoProjeto.setText("");
        statusProjeto.setText("");
        clienteProjeto.setText("");
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

    private void atualizarTabelaClientes() {
        List<Cliente> listaClientes = clientes.getAll(); // Obtenha a lista de clientes do banco de dados
        ObservableList<Cliente> observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewCliente.setItems(observableListClientes);
    }
    
    private void atualizarTabelaProjetos() {
        List<Projeto> listaProjetos = projetos.getAll(); // Obtenha a lista de projetos do banco de dados
        ObservableList<Projeto> observableListProjetos = FXCollections.observableArrayList(listaProjetos);
        tableViewProjeto.setItems(observableListProjetos);
    }

    private void atualizarListaClientes() {
        List<Cliente> listaClientes = clientes.getAll();
        ObservableList<MenuItem> items = clienteProjeto.getItems();
    
        // Limpa os itens existentes
        items.clear();
    
        // Adiciona os clientes como itens no SplitMenuButton
        for (Cliente cliente : listaClientes) {
            MenuItem menuItem = new MenuItem(cliente.getNomeCliente());
            menuItem.setOnAction(event -> clienteProjeto.setText(cliente.getNomeCliente()));
            items.add(menuItem);
        }

        
    }

    private Cliente encontrarClientePorNome(String nome) {
        List<Cliente> listaClientes = clientes.getAll();
        for (Cliente cliente : listaClientes) {
            if (cliente.getNomeCliente().equals(nome)) {
                return cliente;
            }
        }
        return null; // Retornar null se o cliente não for encontrado
    }

}
