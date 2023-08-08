package br.com.dbc.vemser.walletlife.doc;

import br.com.dbc.vemser.walletlife.dto.ReceitaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.ReceitaDTO;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ReceitaControllerDoc {
    @Operation(summary = "Lista todos as receitas", description = "Lista todas as receitas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de receitas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarTodos();

    @Operation(summary = "Buscar receita no banco", description = "Busca no banco a receita a partir de um ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma receita"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idReceita}")
    public ResponseEntity<ReceitaDTO> buscarReceita(@PathVariable("idReceita") @Positive Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Listar receitas de um usuário", description = "Busca no banco as receitas de um usuário utilizando o ID do usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as receitas de um usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReceitaDTO>> listarReceitasPorUsuario(@PathVariable("idUsuario") Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Insere uma nova receita", description = "Insere uma nova receita no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a receita criada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ReceitaDTO> adicionarReceita(@Valid @RequestBody ReceitaCreateDTO receita) throws RegraDeNegocioException;


    @Operation(summary = "Atualiza uma receita por ID", description = "Busca no banco a receita a partir de um ID e a atualiza")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a receita atualizada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idReceita}")
    public ResponseEntity<ReceitaDTO> editarReceita(@PathVariable("idReceita") Integer id,
                                                    @Valid @RequestBody ReceitaDTO receitaAtualizar) throws RegraDeNegocioException;

    @Operation(summary = "Deleta uma receita por ID", description = "Busca no banco a receita a partir de um ID e a deleta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta uma receita do banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idReceita}")
    public ResponseEntity<Void> removerReceita(@PathVariable("idReceita") Integer id) throws RegraDeNegocioException;
}
