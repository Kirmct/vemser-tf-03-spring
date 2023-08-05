package br.com.dbc.vemser.walletlife.modelos;

import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investimento extends AbstractMovimentoDinheiro<TipoDespesaEReceita> {

    @NotEmpty
    protected String corretora;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private int idFK;

    public Investimento(TipoDespesaEReceita tipo, Double valor, String descricao) {
    }
}
