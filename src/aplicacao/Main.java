package aplicacao;

import DAO.ClienteDAO;
import DAO.EquipeDAO;
import models.Cliente;
import models.Equipe;

public class Main {
    
    public static void main(String[] args) {
        
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("dddddd");
        cliente.setEmpresa("Seila");
        cliente.setEmail("123123@rfasdr");
        cliente.setTeleCel("324234524524");

        clienteDAO.save(cliente);

        EquipeDAO equipeDAO = new EquipeDAO();

        Equipe equipe = new Equipe();
        equipe.setNomeEquipe("Teste1nome");
        equipe.setDescricao("textotextotexto");

        equipeDAO.save(equipe);

    }
}
