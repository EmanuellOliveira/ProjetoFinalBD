package models;

import java.util.Date;

public class etapa_projeto {
    private int idEtapaProjeto;
    private int idProjeto;
    private int idEtapa;
    private String statusEtapa;
    private Date dataInicio;
    private Date dataFinal;
    
    public int getIdEtapaProjeto() {
        return idEtapaProjeto;
    }
    public void setIdEtapaProjeto(int idEtapasProjeto) {
        this.idEtapaProjeto = idEtapasProjeto;
    }
    public int getIdProjeto() {
        return idProjeto;
    }
    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
    public int getIdEtapa() {
        return idEtapa;
    }
    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }
    public String getStatusEtapa() {
        return statusEtapa;
    }
    public void setStatusEtapa(String statusEtapa) {
        this.statusEtapa = statusEtapa;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    
    
}
