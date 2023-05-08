package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import models.Projeto;

public class ProjetoDAO {
    
    public void save(Projeto projeto){
        
        String sql = "INSERT INTO projeto(nome, descricao, data_inicio, data_final, orcamento, status, ID_cliente) VALUES(?, ?, ?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
           
            pstm.setString(1, projeto.getNomeProjeto());
            pstm.setString(2, projeto.getDescricao());
            pstm.setDate(3, new Date(projeto.getDataInicio().getTime()));
            pstm.setDate(4, new Date(projeto.getDataFinal().getTime()));
            pstm.setFloat(5, projeto.getOrcamento());
            pstm.setString(6, projeto.getStatus());
            pstm.setInt(7, projeto.getIdCliente());

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

    public List<Projeto> getProjeto(){

        String sql = "SELECT * FROM projeto;";

        List<Projeto> projeto = new ArrayList<Projeto>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){
                Projeto novoProjeto = new Projeto();
                
                novoProjeto.setIdProjeto(rset.getInt("ID_projeto"));
                novoProjeto.setNomeProjeto(rset.getString("nome"));
                novoProjeto.setDescricao(rset.getString("descricao"));
                novoProjeto.setDataInicio(rset.getDate("data_inicio"));
                novoProjeto.setDataFinal(rset.getDate("data_final"));
                novoProjeto.setOrcamento(rset.getFloat("orcamento"));
                novoProjeto.setStatus(rset.getString("status"));
                novoProjeto.setIdCliente(rset.getInt("ID_cliente"));

                projeto.add(novoProjeto);
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
        return projeto;
        
    }
}
