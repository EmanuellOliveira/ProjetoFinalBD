package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import models.Equipe;

public class EquipeDAO {
    
    public void save(Equipe equipe) {

        String sql = "INSERT INTO equipe(nome, descricao) VALUES (?, ?);";
        
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, equipe.getNomeEquipe());
            pstm.setString(2, equipe.getDescricao());

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

    public List<Equipe> getEquipe(){
        
        String sql = "SELECT * FROM equipe;";

        List<Equipe> equipe = new ArrayList<Equipe>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){

                Equipe novaEquipe = new Equipe();

                novaEquipe.setIdEquipe(rset.getInt("ID_equipe"));
                novaEquipe.setNomeEquipe(rset.getString("nome"));
                novaEquipe.setDescricao(rset.getString("descricao"));

                equipe.add(novaEquipe);
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
        return equipe;
        
    }

    public void deleteByID(int id){
        
        String sql = "DELETE FROM equipe WHERE id = ?";

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
