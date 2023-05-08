package models;

public class Equipe {
    private int idEquipe;
    private String nomeEquipe;
    private String descricao;
    
    public int getIdEquipe() {
        return idEquipe;
    }
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
    public String getNomeEquipe() {
        return nomeEquipe;
    }
    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Override
    public String toString() {
        return "\n******Equipes****** \nIDEquipe: " + idEquipe + "\nNome da Equipe: " + nomeEquipe + "\nDescrição: " + descricao;
    }
}
