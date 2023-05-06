package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
