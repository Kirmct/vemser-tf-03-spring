package br.com.dbc.vemser.walletlife.modelos;

import enumerators.TipoDespesaEReceita;

public class Receita extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    private String banco;

    private String empresa;

    private int idFK;

    public Receita() {
        super();
    }

    public Receita(double valor, String descricao, String banco, String empresa, int idFK) {
        this.setValor(valor);
        this.setDecricao(descricao);
        this.banco = banco;
        this.empresa = empresa;
        this.idFK = idFK;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
                Banco: %s
                Empresa: %s""", super.toString(), getBanco(), getEmpresa());
    }
}
