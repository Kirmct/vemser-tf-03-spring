package br.com.dbc.vemser.walletlife.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractMovimentoDinheiro<T> {

    protected T tipo;
    private int id;

    @NotNull
    private Double valor;

    @NotNull
    @Size(min = 5, max = 30)
    private String descricao;

    public AbstractMovimentoDinheiro(T tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
    }
}
