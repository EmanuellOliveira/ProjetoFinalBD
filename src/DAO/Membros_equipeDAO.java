package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import models.Membros_equipe;

public class Membros_equipeDAO {
    
    public void save(Membros_equipe membrosEquipe){
        
        String sql = "INSERT INTO membros_equipe(nome, email, telefone_celular, cargo, ID_equipe) VALUES(?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, membrosEquipe.getNomeMembros());
            pstm.setString(2, membrosEquipe.getEmail());
            pstm.setString(3, membrosEquipe.getTeleCel());
            pstm.setString(4, membrosEquipe.getCargo());
            pstm.setInt(5, membrosEquipe.getIdEquipe());

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

    public List<Membros_equipe> getMembros_equipe(){
        
        String sql = "SELECT * FROM membros_de_equipe;";

        List<Membros_equipe> membros_equipe = new ArrayList<Membros_equipe>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        
        try{
            conn = Conexao.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){

                Membros_equipe novoMembros_equipe = new Membros_equipe();

                novoMembros_equipe.setIdMembros(rset.getInt("ID_membros"));
                novoMembros_equipe.setNomeMembros(rset.getString("nome"));
                novoMembros_equipe.setEmail(rset.getString("email"));
                novoMembros_equipe.setTeleCel(rset.getString("telefone_celular"));
                novoMembros_equipe.setCargo(rset.getString("cargo"));
                novoMembros_equipe.setIdEquipe(rset.getInt("ID_equipe"));

                membros_equipe.add(novoMembros_equipe);
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
        return membros_equipe;
    }
}
