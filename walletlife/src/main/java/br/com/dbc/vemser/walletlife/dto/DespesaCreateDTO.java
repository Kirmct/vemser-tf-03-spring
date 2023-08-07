package br.com.dbc.vemser.walletlife.dto;

import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaCreateDTO {

    protected TipoDespesaEReceita tipo;

    @NotNull
    private Double valor;

    @NotNull
    @Size(min = 5, max = 30)
    private String descricao;

    @NotEmpty
    private LocalDate dataPagamento;

    private int idFK;
}
