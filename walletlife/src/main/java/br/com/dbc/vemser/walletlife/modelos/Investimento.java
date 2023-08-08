package br.com.dbc.vemser.walletlife.modelos;

import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Nome da corretora do investimento", required = true)
    protected String corretora;

    @NotNull
    @Schema(description = "Data de início do investimento", required = true)
    private LocalDate dataInicio;

    @NotNull
    @Schema(description = "ID de referência associado ao investimento", required = true)
    private int idFK;

    public Investimento(TipoDespesaEReceita tipo, Double valor, String descricao) {
    }
}
