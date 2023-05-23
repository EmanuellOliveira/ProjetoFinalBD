package view;

import java.time.LocalDate;
import java.util.List;

import DAO.ClienteDAO;
import DAO.EtapaDAO;
import DAO.Etapa_projetoDAO;
import DAO.ProjetoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cliente;
import models.Etapa;
import models.Etapa_projeto;
import models.Projeto;

public class LayoutController {

    private ClienteDAO clientes;
    private EtapaDAO etapas;
    private ProjetoDAO projetos;
    private Etapa_projetoDAO etapaProjetoDAO;
    private EtapaDAO etapasDAO = new EtapaDAO();

    public void initialize() {

        clientes = new ClienteDAO();
        etapas = new EtapaDAO();
        projetos = new ProjetoDAO();
        etapaProjetoDAO = new Etapa_projetoDAO();

        // COLUNA DE CLIENTES

        colunaIDCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaEmpresaCliente.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        colunaEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaContatoCliente.setCellValueFactory(new PropertyValueFactory<>("teleCel"));

        colunaIDProjeto.setCellValueFactory(new PropertyValueFactory<>("idProjeto"));
        colunaNomeProjeto.setCellValueFactory(new PropertyValueFactory<>("nomeProjeto"));
        colunaDescProjeto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaDInicialProjeto.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colunaDFinalProjeto.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        colunaOrcamentoProjeto.setCellValueFactory(new PropertyValueFactory<>("orcamento"));
        colunaStatusProjeto.setCellValueFactory(new PropertyValueFactory<>("status"));

        colunaClienteProjeto.setCellValueFactory(cellData -> {
            Projeto projeto = cellData.getValue();
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getByID(projeto.getIdCliente());
            String nomeCliente = cliente != null ? cliente.getNomeCliente() : "";
            return new SimpleStringProperty(projeto.getIdCliente() + " - " + nomeCliente);
        });

        // COLUNA ETAPA PROJETO
        colunaIDEtapa.setCellValueFactory(new PropertyValueFactory<>("idEtapaProjeto"));

        colunaEtapa.setCellValueFactory(cellData -> {
            Etapa_projeto etapaProjeto = cellData.getValue();
            EtapaDAO etapaDAO = new EtapaDAO();
            Etapa etapa = null;

            etapa = etapaDAO.getByID(etapaProjeto.getIdEtapa());

            String nomeEtapa = etapa != null ? etapa.getNomeEtapa() : "";
            return new SimpleStringProperty(etapaProjeto.getIdEtapa() + " - " + nomeEtapa);
        });

        colunaDIniEtapa.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colunaDFiniEtapa.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));

        colunaProjetoEtapa.setCellValueFactory(cellData -> {
            Etapa_projeto etapaProjeto = cellData.getValue();
            ProjetoDAO projetoDAO = new ProjetoDAO();
            Projeto projeto = null;

            try {
                projeto = projetoDAO.getByID(etapaProjeto.getIdProjeto());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String nomeProjeto = projeto != null ? projeto.getNomeProjeto() : "";
            return new SimpleStringProperty(etapaProjeto.getIdProjeto() + " - " + nomeProjeto);
        });

        colunaStatusEtapa.setCellValueFactory(new PropertyValueFactory<>("statusEtapa"));

        // SPLITS MENUS

        splitMenuStatusEtapa.getItems().clear();

        splitMenuStatusEtapa.getItems().add(new MenuItem("Aguardando"));
        splitMenuStatusEtapa.getItems().get(0).setOnAction(event -> splitMenuStatusEtapa.setText("Aguardando"));

        splitMenuStatusEtapa.getItems().add(new MenuItem("Em andamento"));
        splitMenuStatusEtapa.getItems().get(1).setOnAction(event -> splitMenuStatusEtapa.setText("Em andamento"));

        splitMenuStatusEtapa.getItems().add(new MenuItem("Concluído"));
        splitMenuStatusEtapa.getItems().get(2).setOnAction(event -> splitMenuStatusEtapa.setText("Concluído"));

        splitMenuStatusEtapa.getItems().add(new MenuItem("Cancelado"));
        splitMenuStatusEtapa.getItems().get(3).setOnAction(event -> splitMenuStatusEtapa.setText("Cancelado"));

        statusProjeto.getItems().clear();

        statusProjeto.getItems().add(new MenuItem("Aguardando"));
        statusProjeto.getItems().get(0).setOnAction(event -> statusProjeto.setText("Aguardando"));

        statusProjeto.getItems().add(new MenuItem("Em andamento"));
        statusProjeto.getItems().get(1).setOnAction(event -> statusProjeto.setText("Em andamento"));

        statusProjeto.getItems().add(new MenuItem("Concluído"));
        statusProjeto.getItems().get(2).setOnAction(event -> statusProjeto.setText("Concluído"));

        statusProjeto.getItems().add(new MenuItem("Cancelado"));
        statusProjeto.getItems().get(3).setOnAction(event -> statusProjeto.setText("Cancelado"));

        atualizarTabelaClientes();
        atualizarTabelaProjetos();
        atualizaTabelaEtapas();
        atualizarListaClientes();
        atualizarListaProjetos();
        atualizarListaEtapa();

    }

    private void atualizarListaProjetos() {
        List<Projeto> listaProjetos = projetos.getAll();
        ObservableList<MenuItem> items = splitMenuProjeto.getItems();
        items.clear();

        for (Projeto projeto : listaProjetos) {
            MenuItem menuItem = new MenuItem(projeto.getIdProjeto() + " - " + projeto.getNomeProjeto());
            menuItem.setOnAction(
                    event -> splitMenuProjeto.setText(projeto.getIdProjeto() + " - " + projeto.getNomeProjeto()));
            items.add(menuItem);
        }
    }

    private void atualizaTabelaEtapas() {
        List<Etapa_projeto> listaEtapas = etapaProjetoDAO.getAll();
        ObservableList<Etapa_projeto> observableListEtapas = FXCollections.observableArrayList(listaEtapas);
        tableViewEtapa.setItems(observableListEtapas);
    }

    private void atualizarListaEtapa() {
        List<Etapa> listaEtapas = etapasDAO.getAll();
        ObservableList<MenuItem> items = splitMenuEtapa.getItems();
        items.clear();

        for (Etapa etapa : listaEtapas) {
            MenuItem menuItem = new MenuItem(etapa.getIdEtapa() + " - " + etapa.getNomeEtapa());
            menuItem.setOnAction(event -> splitMenuEtapa.setText(etapa.getIdEtapa() + " - " + etapa.getNomeEtapa()));
            items.add(menuItem);
        }
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
    private TextField campoIDCliente;

    @FXML
    private TextField campoIDProjeto;

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
    private Button altEtapa;

    @FXML
    private TextField campoIDEtapaProjeto;

    @FXML
    private TableColumn<Etapa_projeto, LocalDate> colunaDFiniEtapa;

    @FXML
    private TableColumn<Etapa_projeto, LocalDate> colunaDIniEtapa;

    @FXML
    private TableColumn<Etapa_projeto, String> colunaEtapa;

    @FXML
    private TableColumn<Etapa_projeto, Integer> colunaIDEtapa;

    @FXML
    private TableColumn<Etapa_projeto, String> colunaProjetoEtapa;

    @FXML
    private TableColumn<Etapa_projeto, String> colunaStatusEtapa;

    @FXML
    private DatePicker dataFiniEtapa;

    @FXML
    private DatePicker dataIniEtapa;

    @FXML
    private Button limparCamposEtapa;

    @FXML
    private Button pesqEtapa;

    @FXML
    private Button selecionarEtapa;

    @FXML
    private SplitMenuButton splitMenuEtapa;

    @FXML
    private SplitMenuButton splitMenuProjeto;

    @FXML
    private SplitMenuButton splitMenuStatusEtapa;

    @FXML
    private TableView<Etapa_projeto> tableViewEtapa;

    @FXML
    void alterarEtapaProjeto(ActionEvent event) {
        try {
            String idEtapaProjetoText = campoIDEtapaProjeto.getText();
            int idEtapaProjeto = Integer.parseInt(idEtapaProjetoText);

            Etapa_projetoDAO etapaProjetoDAO = new Etapa_projetoDAO();
            Etapa_projeto etapaProjeto = etapaProjetoDAO.getByID(idEtapaProjeto);

            if (etapaProjeto != null) {
                LocalDate novaDataInicio = dataIniEtapa.getValue();
                LocalDate novaDataFinal = dataFiniEtapa.getValue();
                String novoStatusEtapa = splitMenuStatusEtapa.getText();
                String novoNomeProjeto = splitMenuProjeto.getText();
                String nomeNomeEtapa = splitMenuEtapa.getText();

                Projeto projeto = encontrarProjetoPorNome(novoNomeProjeto);
                Etapa etapa = encontrarEtapaPorNome(nomeNomeEtapa);

                if (projeto != null && etapa != null) {
                    int novoIdProjeto = projeto.getIdProjeto();
                    int novoIdEtapa = etapa.getIdEtapa();

                    etapaProjeto.setIdProjeto(novoIdProjeto);
                    etapaProjeto.setIdEtapa(novoIdEtapa);
                    etapaProjeto.setDataInicio(novaDataInicio);
                    etapaProjeto.setDataFinal(novaDataFinal);
                    etapaProjeto.setStatusEtapa(novoStatusEtapa);

                    etapaProjetoDAO.update(etapaProjeto);

                    atualizaTabelaEtapas();
                    limparCamposEtapa(event);
                    etapaProjetoAlterada();
                } else {
                    System.out.println("Nao deu");
                }
            } else {
                // Exibir mensagem de erro se a etapa não for encontrada
                etapaProjetoNaoEncontrada();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Exibe o rastro de pilha do erro
            erroDeInsercaoDeDados();
        }
    }

    @FXML
    void limparCamposEtapa(ActionEvent event) {

    }

    @FXML
    void pesquisarEtapa(ActionEvent event) throws ClassNotFoundException {
        try {
            String idEtapaProjetoText = campoIDEtapaProjeto.getText();
            int idEtapaProjeto = Integer.parseInt(idEtapaProjetoText);

            Etapa_projetoDAO etapaProjetoDAO = new Etapa_projetoDAO();
            Etapa_projeto etapaProjeto = etapaProjetoDAO.getByID(idEtapaProjeto);

            if (etapaProjeto != null) {
                Etapa etapa = encontrarEtapaPorID(etapaProjeto.getIdEtapa());
                Projeto projeto = encontrarProjetoPorID(etapaProjeto.getIdProjeto());

                if (etapa != null && projeto != null) {
                    splitMenuEtapa.setText(etapa.getIdEtapa() + " - " + etapa.getNomeEtapa());
                    splitMenuProjeto.setText(projeto.getIdProjeto() + " - " + projeto.getNomeProjeto());
                    dataIniEtapa.setValue(etapaProjeto.getDataInicio());
                    dataFiniEtapa.setValue(etapaProjeto.getDataFinal());
                    splitMenuStatusEtapa.setText(etapaProjeto.getStatusEtapa());

                    atualizaTabelaEtapas();
                    atualizarListaClientes();
                    atualizarListaEtapa();
                    atualizarTabelaClientes();

                } else {
                }
            } else {
                etapaProjetoNaoEncontrada();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            erroDeInsercaoDeDados();
        }
    }

    private Etapa encontrarEtapaPorID(int idEtapa) {
        EtapaDAO etapaDAO = new EtapaDAO();
        Etapa etapa = etapaDAO.getByID(idEtapa);
        return etapa;
    }

    private Projeto encontrarProjetoPorID(int idProjeto) throws ClassNotFoundException {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        Projeto projeto = projetoDAO.getByID(idProjeto);
        return projeto;
    }

    @FXML
    void selecionarEtapaProjeto(ActionEvent event) {
        try {
            String nomeEtapa = splitMenuEtapa.getText();
            String nomeProjeto = splitMenuProjeto.getText();

            Etapa etapa = encontrarEtapaPorNome(nomeEtapa);
            Projeto projeto = encontrarProjetoPorNome(nomeProjeto);

            LocalDate novaDataInicio = dataIniEtapa.getValue();
            LocalDate novaDataFinal = dataFiniEtapa.getValue();
            String novoStatusEtapa = splitMenuStatusEtapa.getText();

            boolean etapaProjetoJaExiste = verificarEtapaProjetoExistente(etapa.getIdEtapa(), projeto.getIdProjeto());
            if (etapaProjetoJaExiste) {
                erroEtapaProjetoDuplicado();
                return;
            } else {

                Etapa_projeto novaEtapaProjeto = new Etapa_projeto();
                novaEtapaProjeto.setIdProjeto(projeto.getIdProjeto());
                novaEtapaProjeto.setIdEtapa(etapa.getIdEtapa());
                novaEtapaProjeto.setDataInicio(novaDataInicio);
                novaEtapaProjeto.setDataFinal(novaDataFinal);
                novaEtapaProjeto.setStatusEtapa(novoStatusEtapa);

                etapaProjetoDAO.save(novaEtapaProjeto);

                atualizaTabelaEtapas();
                limparCamposEtapa(event);
                etapaProjetoAdicionada();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
        }

    }

    private boolean verificarEtapaProjetoExistente(int idEtapa, int idProjeto) {

        List<Etapa_projeto> listaEtapaProjeto = etapaProjetoDAO.getAll();
        for (Etapa_projeto etapaProjeto : listaEtapaProjeto) {
            if (etapaProjeto.getIdEtapa() == idEtapa && etapaProjeto.getIdProjeto() == idProjeto) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void adicionarCliente(ActionEvent event) {
        try {

            String nome = nomeCliente.getText();
            String empresa = empresaCliente.getText();
            String email = emailCliente.getText();
            String contato = contatoCliente.getText();

            if (nome.isEmpty() || empresa.isEmpty() || email.isEmpty() || contato.isEmpty()) {
                camposVazios();
                return;
            }

            boolean emailJaExiste = verificarEmailExistente(email);
            if (emailJaExiste) {
                erroEmailDuplicado();
                return;
            } else {

                Cliente cliente = new Cliente(nome, empresa, email, contato);
                clientes.save(cliente);

                atualizarTabelaClientes();
                atualizarListaClientes();

                limparCampos(event);
                clienteAdicionado();
            }

        } catch (Exception e) {

        }
    }

    private boolean verificarEmailExistente(String email) {
        List<Cliente> listaClientes = clientes.getAll();
        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void adicionarProjeto(ActionEvent event) {
        try {
            String nome = nomeProjeto.getText();
            String descricao = descProjeto.getText();
            LocalDate dataInicial = dInicialProjeto.getValue();
            LocalDate dataFinal = dFinalProjeto.getValue();
            float orcamenteo = Float.parseFloat(orcamentoProjeto.getText());
            String status = statusProjeto.getText();

            String clienteSelecionado = clienteProjeto.getText();
            Cliente cliente = encontrarClientePorNome(clienteSelecionado);

            if (nome.isEmpty() || descricao.isEmpty() || dataInicial == null || dataFinal == null
                    || orcamentoProjeto.getText().isEmpty() || status.isEmpty() || cliente == null) {
                camposVazios();
                return;
            }

            boolean projetoJaExiste = verificarProjetoExistente(nome);
            if (projetoJaExiste) {

                erroNomeProjetoDuplicado();
                return;
            } else {

                Projeto projeto = new Projeto(nome, descricao, dataInicial, dataFinal, orcamenteo, status,
                        cliente.getIdCliente());
                projetos.save(projeto);

                limparCamposP(event);
                atualizarTabelaProjetos();
                atualizaTabelaEtapas();
                atualizarListaClientes();
                projetoAdicionado();
                atualizarListaProjetos();
            }

        } catch (Exception e) {
            erroDeInsercaoDeDados();
        }
    }

    private boolean verificarProjetoExistente(String nome) {
        List<Projeto> listaProjetos = projetos.getAll();
        for (Projeto projeto : listaProjetos) {
            if (projeto.getNomeProjeto().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    void alterarCliente(ActionEvent event) {
        try {
            String idClienteText = campoIDCliente.getText();
            int idCliente = Integer.parseInt(idClienteText);

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getByID(idCliente);

            if (cliente != null) {

                String novoNome = nomeCliente.getText();
                String novaEmpresa = empresaCliente.getText();
                String novoEmail = emailCliente.getText();
                String novoContato = contatoCliente.getText();

                cliente.setNomeCliente(novoNome);
                cliente.setEmpresa(novaEmpresa);
                cliente.setEmail(novoEmail);
                cliente.setTeleCel(novoContato);

                clienteDAO.update(cliente);

                atualizarTabelaClientes();
                atualizaTabelaEtapas();
                atualizarListaClientes();
                limparCampos(event);
                clienteAlterado();
                atualizarListaProjetos();

            } else {
                clienteNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
        }
    }

    @FXML
    void alterarProjeto(ActionEvent event) throws ClassNotFoundException {
        try {
            String idProjetoText = campoIDProjeto.getText();
            int idProjeto = Integer.parseInt(idProjetoText);

            ProjetoDAO projetoDAO = new ProjetoDAO();
            Projeto projeto = projetoDAO.getByID(idProjeto);

            if (projeto != null) {
                String novoNome = nomeProjeto.getText();
                String novaDescricao = descProjeto.getText();
                LocalDate novaDataInicio = dInicialProjeto.getValue();
                LocalDate novaDataFinal = dFinalProjeto.getValue();
                float novoOrcamento = Float.parseFloat(orcamentoProjeto.getText());
                String novoStatus = statusProjeto.getText();
                String novoNomeCliente = clienteProjeto.getText();

                Cliente cliente = encontrarClientePorNome(novoNomeCliente);

                if (cliente != null) {
                    int novoIdCliente = cliente.getIdCliente();

                    projeto.setNomeProjeto(novoNome);
                    projeto.setDescricao(novaDescricao);
                    projeto.setDataInicio(novaDataInicio);
                    projeto.setDataFinal(novaDataFinal);
                    projeto.setOrcamento(novoOrcamento);
                    projeto.setStatus(novoStatus);
                    projeto.setIdCliente(novoIdCliente);

                    projetoDAO.update(projeto);

                    atualizarTabelaProjetos();
                    atualizaTabelaEtapas();
                    atualizarListaClientes();
                    atualizarListaProjetos();

                    limparCamposP(event);

                    projetoAlterado();

                }
            } else {
                projetoNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
            e.printStackTrace();
        }
    }

    @FXML
    void deletarCliente(ActionEvent event) {
        try {
            String idClienteText = campoIDCliente.getText();
            int idCliente = Integer.parseInt(idClienteText);

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getByID(idCliente);

            if (cliente != null) {
                clienteDAO.deleteByID(idCliente);
                atualizarTabelaClientes();
                atualizaTabelaEtapas();
                atualizarListaClientes();
                atualizarTabelaProjetos();
                limparCampos(event);
                clienteDeletado();

            } else {
                clienteNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
            e.printStackTrace();
        }

    }

    @FXML
    void deletarProjeto(ActionEvent event) throws ClassNotFoundException {
        try {
            String idProjetoText = campoIDProjeto.getText();
            int idProjeto = Integer.parseInt(idProjetoText);

            ProjetoDAO projetoDAO = new ProjetoDAO();
            Projeto projeto = projetoDAO.getByID(idProjeto);

            if (projeto != null) {
                projetoDAO.deleteByID(idProjeto);
                ;
                atualizarTabelaProjetos();
                atualizaTabelaEtapas();
                atualizarListaClientes();
                atualizarTabelaClientes();
                atualizarListaProjetos();
                limparCamposP(event);
                projetoDeletado();
            } else {
                projetoNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
            e.printStackTrace();
        }
    }

    @FXML
    void limparCampos(ActionEvent event) {
        nomeCliente.setText("");
        empresaCliente.setText("");
        emailCliente.setText("");
        contatoCliente.setText("");
        campoIDCliente.setText("");
    }

    @FXML
    void limparCamposP(ActionEvent event) {
        nomeProjeto.setText("");
        descProjeto.setText("");
        dInicialProjeto.setValue(null);
        dFinalProjeto.setValue(null);
        orcamentoProjeto.setText("");
        statusProjeto.setText("");
        clienteProjeto.setText("");
    }

    @FXML
    void pesquisarCliente(ActionEvent event) {
        try {
            String idClienteText = campoIDCliente.getText();
            int idCliente = Integer.parseInt(idClienteText);

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getByID(idCliente);

            if (cliente != null) {

                nomeCliente.setText(cliente.getNomeCliente());
                empresaCliente.setText(cliente.getEmpresa());
                emailCliente.setText(cliente.getEmail());
                contatoCliente.setText(cliente.getTeleCel());

                clienteProjeto.setText(cliente.getNomeCliente());
            } else {
                clienteNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
            e.printStackTrace();
        }
    }

    @FXML
    void pesquisarProjeto(ActionEvent event) throws ClassNotFoundException {
        try {
            String idProjetoText = campoIDProjeto.getText();
            int idProjeto = Integer.parseInt(idProjetoText);

            ProjetoDAO projetoDAO = new ProjetoDAO();
            Projeto projeto = projetoDAO.getByID(idProjeto);

            if (projeto != null) {

                nomeProjeto.setText(projeto.getNomeProjeto());
                descProjeto.setText(projeto.getDescricao());
                dInicialProjeto.setValue(projeto.getDataInicio());
                dFinalProjeto.setValue(projeto.getDataFinal());
                orcamentoProjeto.setText(String.valueOf(projeto.getOrcamento()));
                statusProjeto.setText(projeto.getStatus());

                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getByID(projeto.getIdCliente());
                clienteProjeto.setText(String.valueOf(projeto.getIdCliente()));

                if (cliente != null) {
                    clienteProjeto.setText(cliente.getIdCliente() + " - " + cliente.getNomeCliente());
                } else {
                    clienteProjeto.setText(String.valueOf(projeto.getIdCliente()));
                }
            } else {
                projetoNaoEncontrado();
            }
        } catch (Exception e) {
            erroDeInsercaoDeDados();
            e.printStackTrace();
        }
    }

    private void atualizarTabelaClientes() {
        List<Cliente> listaClientes = clientes.getAll();
        ObservableList<Cliente> observableListClientes = FXCollections.observableArrayList(listaClientes);
        tableViewCliente.setItems(observableListClientes);
    }

    private void atualizarTabelaProjetos() {
        List<Projeto> listaProjetos = projetos.getAll();
        ObservableList<Projeto> observableListProjetos = FXCollections.observableArrayList(listaProjetos);
        tableViewProjeto.setItems(observableListProjetos);
    }

    private void atualizarListaClientes() {
        List<Cliente> listaClientes = clientes.getAll();
        ObservableList<MenuItem> items = clienteProjeto.getItems();

        items.clear();

        for (Cliente cliente : listaClientes) {
            MenuItem menuItem = new MenuItem(cliente.getIdCliente() + " - " + cliente.getNomeCliente());
            menuItem.setOnAction(
                    event -> clienteProjeto.setText(cliente.getIdCliente() + " - " + cliente.getNomeCliente()));
            items.add(menuItem);
        }

    }

    private Cliente encontrarClientePorNome(String nome) {
        List<Cliente> listaClientes = clientes.getAll();
        for (Cliente cliente : listaClientes) {
            String nomeCompleto = cliente.getIdCliente() + " - " + cliente.getNomeCliente();
            if (nomeCompleto.equals(nome)) {
                return cliente;
            }
        }
        return null;
    }

    private Etapa encontrarEtapaPorNome(String nome) {
        List<Etapa> listaEtapas = etapas.getAll();

        for (Etapa etapa : listaEtapas) {
            String nomeEtapa = etapa.getIdEtapa() + " - " + etapa.getNomeEtapa();
            if (nomeEtapa.equals(nome)) {
                return etapa;
            }
        }
        return null;
    }

    private Projeto encontrarProjetoPorNome(String nome) {
        List<Projeto> listaProjetos = projetos.getAll();

        for (Projeto projeto : listaProjetos) {
            String nomeProjeto = projeto.getIdProjeto() + " - " + projeto.getNomeProjeto();
            if (nomeProjeto.equals(nome)) {
                return projeto;
            }
        }
        return null;
    }

    private void clienteDeletado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Deletar Cliente");
        alert.setHeaderText(null);
        alert.setContentText("Cliente deletado com sucesso!");
        alert.showAndWait();
    }

    private void projetoDeletado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Deletar Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Projeto deletado com sucesso!");
        alert.showAndWait();
    }

    private void clienteNaoEncontrado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Pesquisa de Cliente");
        alert.setHeaderText(null);
        alert.setContentText("Cliente não encontrado!");
        alert.showAndWait();
    }

    private void projetoNaoEncontrado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Pesquisa de Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Projeto não encontrado!");
        alert.showAndWait();
    }

    private void projetoAdicionado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Adicionar Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Projeto adicionado com sucesso!");
        alert.showAndWait();
    }

    private void clienteAdicionado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Adicionar Cliente");
        alert.setHeaderText(null);
        alert.setContentText("Cliente adicionado com sucesso!");
        alert.showAndWait();
    }

    private void clienteAlterado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alterar Cliente");
        alert.setHeaderText(null);
        alert.setContentText("Cliente alterado com sucesso!");
        alert.showAndWait();
    }

    private void projetoAlterado() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alterar Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Projeto alterado com sucesso!");
        alert.showAndWait();
    }

    private void erroDeInsercaoDeDados() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de dados");
        alert.setHeaderText(null);
        alert.setContentText("Algum dado não foi inserido corretamente!");
        alert.showAndWait();
    }

    private void etapaProjetoAlterada() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alterar Etapa do Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Etapa alterada com sucesso!");
        alert.showAndWait();
    }

    private void etapaProjetoNaoEncontrada() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Etapa não Encontrada!");
        alert.showAndWait();
    }

    private void etapaProjetoAdicionada() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Etapa do Projeto");
        alert.setHeaderText(null);
        alert.setContentText("Etapa adicionada com sucesso!");
        alert.showAndWait();
    }

    private void camposVazios() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Não pode haver campos vazios!");
        alert.showAndWait();
    }

    private void erroEmailDuplicado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de duplicação!");
        alert.setHeaderText(null);
        alert.setContentText("Não pode haver clientes com o mesmo e-mail!");
        alert.showAndWait();
    }

    private void erroNomeProjetoDuplicado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de duplicação!");
        alert.setHeaderText(null);
        alert.setContentText("Não pode haver projetos com o mesmo nome!");
        alert.showAndWait();
    }

    private void erroEtapaProjetoDuplicado() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de duplicação!");
        alert.setHeaderText(null);
        alert.setContentText("Não pode haver Projetos e Etapas duplicadas!");
        alert.showAndWait();
    }
}