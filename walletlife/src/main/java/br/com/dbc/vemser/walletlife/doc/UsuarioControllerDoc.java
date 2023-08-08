package br.com.dbc.vemser.walletlife.doc;

import br.com.dbc.vemser.walletlife.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.walletlife.dto.UsuarioDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface UsuarioControllerDoc {

    @Operation(summary = "Lista todos os Usuários", description = "Lista todas os usuários do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de usuários"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() throws BancoDeDadosException;

    @Operation(summary = "Listar Usuários por ID", description = "Busca no banco o usuário a partir de um ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPessoa}")
    public ResponseEntity<UsuarioDTO> listarPessoasPorId(@PathVariable("idPessoa") @Positive Integer idPessoa);


    @Operation(summary = "Insere um novo usuário", description = "Insere um novo usuário no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário criado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionarUsuario(@RequestBody @Valid UsuarioCreateDTO usuario);


    @Operation(summary = "Atualiza um Usuário por ID", description = "Busca no banco o usuário a partir de um ID e o atualiza")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário atualizado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> editarPessoa(@PathVariable @Positive Integer idUsuario,
                                                   @RequestBody @Valid UsuarioCreateDTO usuario);

    @Operation(summary = "Deleta um Usuário por ID", description = "Busca no banco o usuário a partir de um ID e o deleta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna um corpo vazio"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> remover(@PathVariable Integer idUsuario);
}
