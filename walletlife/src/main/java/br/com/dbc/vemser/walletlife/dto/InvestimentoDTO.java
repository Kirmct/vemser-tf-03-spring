package br.com.dbc.vemser.walletlife.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestimentoDTO extends InvestimentoCreateDTO {
    private Integer id_investimento;
}
