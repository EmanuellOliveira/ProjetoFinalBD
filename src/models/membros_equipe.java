package models;

public class Membros_equipe {
    private int idMembros;
    private String nomeMembros;
    private String email;
    private String teleCel;
    private String cargo;
    private int idEquipe;
    
    public int getIdMembros() {
        return idMembros;
    }
    public void setIdMembros(int idMembros) {
        this.idMembros = idMembros;
    }
    public String getNomeMembros() {
        return nomeMembros;
    }
    public void setNomeMembros(String nomeMembros) {
        this.nomeMembros = nomeMembros;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTeleCel() {
        return teleCel;
    }
    public void setTeleCel(String teleCel) {
        this.teleCel = teleCel;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public int getIdEquipe() {
        return idEquipe;
    }
    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    
}
