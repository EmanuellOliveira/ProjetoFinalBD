package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import factory.Conexao;
import models.Etapa_projeto;

public class Etapa_projetoDAO {
    
    public void save(Etapa_projeto etapaProjeto) {
        
        String sql = "INSERT INTO etapa_projeto(ID_projeto, ID_etapa, status_etapa, data_inicio, data_final) VALUES (?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, etapaProjeto.getIdProjeto());
            pstm.setInt(2, etapaProjeto.getIdEtapa());
            pstm.setString(3, etapaProjeto.getStatusEtapa());
            pstm.setDate(4, Date.valueOf(etapaProjeto.getDataInicio()));
            pstm.setDate(5, Date.valueOf(etapaProjeto.getDataFinal()));

            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Etapa_projeto> getAll(){

        String sql = "SELECT * FROM etapa_projeto;";

        List<Etapa_projeto> etapa_projeto = new ArrayList<Etapa_projeto>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){

                Etapa_projeto novaEtapaProjeto = new Etapa_projeto();

                novaEtapaProjeto.setIdEtapaProjeto(rset.getInt("ID_etapaprojeto"));
                novaEtapaProjeto.setIdProjeto(rset.getInt("ID_projeto"));
                novaEtapaProjeto.setIdEtapa(rset.getInt("ID_etapa"));
                novaEtapaProjeto.setStatusEtapa(rset.getString("status_etapa"));
                novaEtapaProjeto.setDataInicio(rset.getDate("data_inicio").toLocalDate());
                novaEtapaProjeto.setDataFinal(rset.getDate("data_final").toLocalDate());

                etapa_projeto.add(novaEtapaProjeto);

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
        return etapa_projeto;
    }
    
    public void deleteByID(int id){
        
        String sql = "DELETE FROM etapa_projeto WHERE id = ?";

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

    public Etapa_projeto getByID(int id) {
        String sql = "SELECT * FROM etapa_projeto WHERE ID_etapaprojeto = ?";
    
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Etapa_projeto etapaProjeto = null;
    
        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();
    
            if (rset.next()) {
                etapaProjeto = new Etapa_projeto();
                etapaProjeto.setIdEtapaProjeto(rset.getInt("ID_etapaprojeto"));
                etapaProjeto.setIdProjeto(rset.getInt("ID_projeto"));
                etapaProjeto.setIdEtapa(rset.getInt("ID_etapa"));
                etapaProjeto.setStatusEtapa(rset.getString("status_etapa"));
                etapaProjeto.setDataInicio(rset.getDate("data_inicio").toLocalDate());
                etapaProjeto.setDataFinal(rset.getDate("data_final").toLocalDate());
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
    
        return etapaProjeto;
    }

    public void update(Etapa_projeto etapaProjeto) {
        String sql = "UPDATE etapa_projeto SET ID_etapa = ?, status_etapa = ?, data_inicio = ?, data_final = ? WHERE ID_etapaprojeto = ?";
    
        Connection conn = null;
        PreparedStatement pstm = null;
    
        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, etapaProjeto.getIdEtapa());
            pstm.setString(2, etapaProjeto.getStatusEtapa());
            pstm.setDate(3, Date.valueOf(etapaProjeto.getDataInicio()));
            pstm.setDate(4, Date.valueOf(etapaProjeto.getDataFinal()));
            pstm.setInt(5, etapaProjeto.getIdEtapaProjeto());
    
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
    }
    
    

}