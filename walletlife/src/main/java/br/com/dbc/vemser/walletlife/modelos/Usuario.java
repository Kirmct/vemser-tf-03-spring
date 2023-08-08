package br.com.dbc.vemser.walletlife.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Integer id;

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String senha;

}
