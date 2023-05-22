package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import models.Etapa;

public class EtapaDAO {
    
    public void save(Etapa etapa){
        
        String sql = "INSERT INTO etapa(nome, descricao, ID_equipe)VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, etapa.getNomeEtapa());
            pstm.setString(2, etapa.getDescricao());
            pstm.setInt(3, etapa.getIdEquipe());

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
    
    public List<Etapa> getEtapa(){

        String sql = "SELECT * FROM etapa;";

        List<Etapa> etapa = new ArrayList<Etapa>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){

                Etapa novaEtapa = new Etapa();

                novaEtapa.setIdEtapa(rset.getInt("ID_etapa"));
                novaEtapa.setNomeEtapa(rset.getString("nome"));
                novaEtapa.setDescricao(rset.getString("descricao"));
                novaEtapa.setIdEquipe(rset.getInt("ID_equipe"));

                etapa.add(novaEtapa);
            }
        }catch (Exception e){
            e.printStackTrace();
            }finally{
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
        return etapa;
    }

    public void deleteByID(int id){
        
        String sql = "DELETE FROM etapa WHERE id = ?";

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

    public Etapa getByID(int id) {
        String sql = "SELECT * FROM etapa WHERE ID_etapa = ?";
    
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Etapa etapa = null;
    
        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
    
            if (rset.next()) {
                etapa = new Etapa();
                etapa.setIdEtapa(rset.getInt("ID_etapa"));
                etapa.setNomeEtapa(rset.getString("nome"));
                etapa.setDescricao(rset.getString("descricao"));
                etapa.setIdEquipe(rset.getInt("ID_equipe"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return etapa;
    }
    
}
