package models;

import java.time.LocalDate;

public class Etapa_projeto {
    private int idEtapaProjeto;
    private int idProjeto;
    private int idEtapa;
    private String statusEtapa;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    
    public Etapa_projeto() {
    }

    

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
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate date) {
        this.dataInicio = date;
    }
    public LocalDate getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(LocalDate date) {
        this.dataFinal = date;
    }

    
    
}
