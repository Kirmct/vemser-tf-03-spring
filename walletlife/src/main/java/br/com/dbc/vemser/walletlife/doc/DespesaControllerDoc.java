package br.com.dbc.vemser.walletlife.doc;

import br.com.dbc.vemser.walletlife.dto.DespesaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.DespesaDTO;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface DespesaControllerDoc {

    @Operation(summary = "Lista todas as despesas", description = "Lista todas as despesas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de despesas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    public ResponseEntity<List<DespesaDTO>> listarTodos();

    @Operation(summary = "Buscar despesa no banco", description = "Busca no banco a despesa a partir de um ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma despesa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idDespesa}") //GET localhost:8080/despesa/1
    public ResponseEntity<DespesaDTO> buscarDespesas(@PathVariable("idDespesa") Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Listar despesas de um usuário", description = "Busca no banco as despesas de um usuário utilizando o ID do usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna as despesas de um usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/usuario/{idUsuario}")//GET localhost:8080/despesa/usuario/1
    public ResponseEntity<List<DespesaDTO>> listarDespesasPorUsuario(@PathVariable("idUsuario") Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Insere uma nova despesa", description = "Insere uma nova despesa no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a despesa criada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping //POST localhost:8080/despesa
    public ResponseEntity<DespesaDTO> adicionarDespesa(@Valid @RequestBody DespesaCreateDTO despesa) throws RegraDeNegocioException;

    @Operation(summary = "Atualiza uma despesa por ID", description = "Busca no banco a despesa a partir de um ID e a atualiza")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a despesa atualizada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idDespesa}") //PUT localhost:8080/despesa/1
    public ResponseEntity<DespesaDTO> editarDepesa(@PathVariable("idDespesa") Integer id,
                                                   @Valid @RequestBody DespesaDTO despesaAtualizar) throws RegraDeNegocioException;

    @Operation(summary = "Deleta uma despesa por ID", description = "Busca no banco a despesa a partir de um ID e a deleta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta uma despesa do banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idDespesa}") //DELETE localhost:8080/despesa/1
    public ResponseEntity<Void> removerDespesa(@PathVariable("idDespesa") Integer id) throws RegraDeNegocioException;

}
