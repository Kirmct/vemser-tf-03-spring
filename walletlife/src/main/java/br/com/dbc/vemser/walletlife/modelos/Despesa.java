package br.com.dbc.vemser.walletlife.modelos;

import enumerators.TipoDespesaEReceita;

import java.time.LocalDate;

public class Despesa extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private LocalDate dataPagamento;

    private int idFK;

    public Despesa(TipoDespesaEReceita tipoDespesa, double valor, String descricao, LocalDate dataPagamento, int idFK) {
        super(tipoDespesa, valor, descricao);
        this.dataPagamento = dataPagamento;
        this.idFK = idFK;

    }

    public Despesa() {

    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
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
                Data Pagamento: %s""", super.toString(), getDataPagamento());
    }
}
