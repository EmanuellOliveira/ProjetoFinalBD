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
        
        String sql = "INSERT INTO etapas_projeto(ID_projeto, ID_etapa, status_etapa, data_inicio, data_final) VALUES (?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, etapaProjeto.getIdProjeto());
            pstm.setInt(2, etapaProjeto.getIdEtapa());
            pstm.setString(3, etapaProjeto.getStatusEtapa());
            pstm.setDate(4, new Date(etapaProjeto.getDataInicio().getTime()));
            pstm.setDate(5, new Date(etapaProjeto.getDataFinal().getTime()));

            pstm.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Etapa_projeto> getEtapa_projeto(){

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
                novaEtapaProjeto.setDataInicio(rset.getDate("data_inicio"));
                novaEtapaProjeto.setDataFinal(rset.getDate("data_final"));

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
}