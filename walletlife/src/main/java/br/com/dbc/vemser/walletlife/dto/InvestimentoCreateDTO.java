package br.com.dbc.vemser.walletlife.dto;

import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.vemser.walletlife.modelos.AbstractMovimentoDinheiro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestimentoCreateDTO{
    // Atributos herdados de AbstractMovimentoDinheiro
    @NotNull
    protected TipoDespesaEReceita tipo;
    // Atributos herdados de AbstractMovimentoDinheiro
    @NotNull
    private Double valor;
    // Atributos herdados de AbstractMovimentoDinheiro
    @NotNull
    @Size(min = 5, max = 30)
    private String descricao;

    @NotEmpty
    protected String corretora;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private int idFK;
}
