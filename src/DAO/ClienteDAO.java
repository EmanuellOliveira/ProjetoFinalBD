package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import factory.Conexao;
import models.Cliente;

public class ClienteDAO {
    
    public void save(Cliente cliente){

        String sql = "INSERT INTO cliente(nome, empresa, email, telefone_celular) VALUES (?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cliente.getNomeCliente());
            pstm.setString(2, cliente.getEmail());
            pstm.setString(3, cliente.getEmpresa());
            pstm.setString(4, cliente.getTeleCel());

            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }

    public List<Cliente> getCliente(){


        
        String sql = "SELECT * FROM cliente;";

        List<Cliente> cliente = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                
                Cliente novoCliente = new Cliente();

                novoCliente.setIdCliente(rset.getInt("ID_cliente"));
                novoCliente.setNomeCliente(rset.getString("nome"));
                novoCliente.setEmpresa(rset.getString("empresa"));
                novoCliente.setEmail(rset.getString("email"));
                novoCliente.setTeleCel(rset.getString("telefone_celular"));

                cliente.add(novoCliente);

            }
        }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if(rset!=null){
                    rset.close();
                }

                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }   
        }
        return cliente;   
    }

    public void deleteByID(int id){
        
        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
