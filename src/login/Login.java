package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import factory.Conexao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Login{

    @FXML
    private Button botaoLogar;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoSenha;

    @FXML
    private Button conectarServidor;

public void handleBotaoLogar(){
    String nomeUsuario = campoNome.getText();
    String senha = campoSenha.getText();

    if(nomeUsuario.equals("admin") && senha.equals("admin")){
        exibirTelaPrincipal();
    } else {
        exibirMensagemErro();
    }
}

public void handleConectarServidor() throws ClassNotFoundException, SQLException{
    try{
    Connection conn = Conexao.createConnectionToMySQL();

    if(conn!=null){
        exibirMensgemConexaoOK();
    }
}catch (SQLException e){
    exibirMensagemConexaoFalha();
}
}


private void exibirMensagemConexaoFalha() {
    Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Conexão com o Servidor");
        alert.setHeaderText(null);
        alert.setContentText("Falha ao conectar-se ao servidor. Por favor, verifique sua conexão e tente novamente.");
        alert.showAndWait();
}

private void exibirMensgemConexaoOK() {
    Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Conexão com o Servidor");
        alert.setHeaderText(null);
        alert.setContentText("Conexão estabelecida com sucesso!");
        alert.showAndWait();
}

private void exibirMensagemErro() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erro de Login");
    alert.setHeaderText("Login incorreto");
    alert.setContentText("Usuário ou Senha incorreto. Por favor, tente novamente.");
    alert.showAndWait();
}

private void exibirTelaPrincipal() {
    try {

        Parent root = FXMLLoader.load(getClass().getResource("/view/Layout.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("GPRO - Gerenciamento de Projetos");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage loginStage = (Stage) botaoLogar.getScene().getWindow();
        loginStage.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
}



}
