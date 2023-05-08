package aplicacao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import DAO.ClienteDAO;
import DAO.ProjetoDAO;
import DAO.EquipeDAO;
import models.Cliente;
import models.Projeto;
import models.Equipe;

public class Main {
    
    public static void main(String[] args) throws ParseException {

        //CLIENTE

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente novoCliente = new Cliente();


        novoCliente.setNomeCliente("Antonio");
        novoCliente.setEmpresa("teste sa");
        novoCliente.setEmail("tes@tes");
        novoCliente.setTeleCel("234234234");

        clienteDAO.save(novoCliente);
        
        // PROJETO

        ProjetoDAO projetoDAO = new ProjetoDAO();
        Projeto novoProjeto = new Projeto();

        novoProjeto.setNomeProjeto("testenomeprojeto");
        novoProjeto.setDescricao("descricaoteste");
        novoProjeto.setDataInicio(new Date());
        novoProjeto.setDataFinal(new Date());
        novoProjeto.setOrcamento(456.76f);
        novoProjeto.setStatus("Iniciado");
        novoProjeto.setIdCliente(1);

        projetoDAO.save(novoProjeto);

        //Visualizar Lista de Projetos
        List<Projeto> projetos = projetoDAO.getProjeto();

        for (Projeto projeto : projetos){
            System.out.println(projeto);
        }

        //Visualizar Lisata de Cllientes

        List<Cliente> clientes = clienteDAO.getCliente();

        for (Cliente cliente : clientes){
            System.out.println(cliente);
        }

        //Visualizar Lisata de Equipe

        EquipeDAO equipeDAO = new EquipeDAO();

        List<Equipe> equipes = equipeDAO.getEquipe();

        for (Equipe equipe : equipes){
            System.out.println(equipe);
        }
    }
}
