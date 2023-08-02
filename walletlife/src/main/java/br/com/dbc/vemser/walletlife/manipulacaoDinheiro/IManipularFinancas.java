package br.com.dbc.vemser.walletlife.manipulacaoDinheiro;

import java.util.HashMap;

public interface IManipularFinancas {

    double calcularTotal(HashMap<?, ?> lista);

    double calcularReceitaTotal();

    double calcularInvestimentos();

    double calcularDespesa();

}
