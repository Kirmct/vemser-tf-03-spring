package br.com.dbc.vemser.walletlife.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDTO {
    @NotNull
    @Size(min = 5, max = 255)
    private String nomeCompleto;
    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @NotNull
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(min = 12)
    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String senha;
}
