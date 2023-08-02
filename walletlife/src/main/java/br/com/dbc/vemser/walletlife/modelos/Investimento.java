package br.com.dbc.vemser.walletlife.modelos;

import java.time.LocalDate;

public class Investimento extends AbstractMovimentoDinheiro<String> {

    protected String corretora;

    private LocalDate dataInicio;

    private int idFK;

    public Investimento() {
    }

    public Investimento(double valor, String descricao, String corretora, LocalDate dataInicio, int idFK) {
        super("Investimento", valor, descricao);
        this.corretora = corretora;
        this.dataInicio = dataInicio;
        this.idFK = idFK;
    }

    public String getCorretora() {
        return corretora;
    }

    public void setCorretora(String corretora) {
        this.corretora = corretora;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getIdFK() {
        return idFK;
    }

    public void setIdFK(int idFK) {
        this.idFK = idFK;
    }

    @Override
    public String toString() {
        return String.format("""
                %s
                Corretora: %s
                Data início: %s""", super.toString(), getCorretora(), getDataInicio());
    }
}
