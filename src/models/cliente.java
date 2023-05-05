package models;

public class cliente {
    private int idCliente;
    private String nomeCliente;
    private String empresa;
    private String  teleCel;
    
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int id_cliente) {
        this.idCliente = id_cliente;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nome) {
        this.nomeCliente = nome;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getTeleCel() {
        return teleCel;
    }
    public void setTeleCel(String tel_cel) {
        this.teleCel = tel_cel;
    }

    
}
