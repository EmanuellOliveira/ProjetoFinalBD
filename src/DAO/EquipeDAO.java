package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        }

    }
}
