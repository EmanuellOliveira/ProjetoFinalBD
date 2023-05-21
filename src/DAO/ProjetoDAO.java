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
            pstm.setDate(3, Date.valueOf(projeto.getDataInicio()));
            pstm.setDate(4, Date.valueOf(projeto.getDataFinal()));
            pstm.setFloat(5, projeto.getOrcamento());
            pstm.setString(6, projeto.getStatus());
            pstm.setInt(7, projeto.getIdCliente());

            pstm.execute();
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

    public List<Projeto> getAll(){

        String sql = "SELECT * FROM projeto;";

        List<Projeto> projetos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){
                Projeto novoProjeto = new Projeto();

                novoProjeto.setIdProjeto(rset.getInt("ID_projeto"));
                novoProjeto.setNomeProjeto(rset.getString("nome"));
                novoProjeto.setDescricao(rset.getString("descricao"));
                novoProjeto.setDataInicio(rset.getDate("data_inicio").toLocalDate());
                novoProjeto.setDataFinal(rset.getDate("data_final").toLocalDate());
                novoProjeto.setOrcamento(rset.getFloat("orcamento"));
                novoProjeto.setStatus(rset.getString("status"));
                novoProjeto.setIdCliente(rset.getInt("ID_cliente"));

                projetos.add(novoProjeto);
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
        
        return projetos;
    }

    public void update(Projeto projeto){

        String sql = "UPDATE projeto SET nome = ?, descricao = ?, data_inicio = ?, data_final = ?, orcamento = ?, status = ? WHERE ID_projeto = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, projeto.getNomeProjeto());
            pstm.setString(2, projeto.getDescricao());
            pstm.setDate(3, Date.valueOf(projeto.getDataInicio()));
            pstm.setDate(4, Date.valueOf(projeto.getDataFinal()));
            pstm.setFloat(5, projeto.getOrcamento());
            pstm.setString(6, projeto.getStatus());
            pstm.setInt(7, projeto.getIdProjeto());

            pstm.execute();

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

    public void deleteByID(int id){
        
        String sql = "DELETE FROM projeto WHERE ID_projeto = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();

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
