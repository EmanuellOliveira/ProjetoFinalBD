package models;

public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String email;
    private String empresa;
    private String teleCel;
    
    
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public void add(Cliente cliente) {
    }
    @Override
    
    public String toString() {
        return "\n******Clientes****** \nIDCliente: " + idCliente + "\nNome do cliente: " + nomeCliente + "\nE-mail: " + email + "\nEmpresa: "
                + empresa + "\nTelefone/celular: " + teleCel;
    }
    
}
