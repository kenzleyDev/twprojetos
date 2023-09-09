package br.com.treinaweb.twprojetos.api.docs;

import br.com.treinaweb.twprojetos.api.annotations.ApiPageable;
import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.exceptions.ApiErro;
import br.com.treinaweb.twprojetos.api.exceptions.ValidationApiError;
import br.com.treinaweb.twprojetos.entities.Cargo;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Cargos")
public interface CargoControllerApiDoc {

    @ApiOperation(value = "Listar todos cargos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listagem dos cargos realizada com sucesso!")
    })

    @ApiPageable
    CollectionModel<EntityModel<Cargo>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar cargo por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cargo encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    public EntityModel<Cargo> buscarPorId(long id);

    @ApiOperation(value = "Cadastrar Cargo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cargo cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidationApiError.class)
    })
    public EntityModel<Cargo> cadastrar(CargoDTO cargoDTO);

    @ApiOperation(value = "Atualizar Cargo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cargo atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Houveram erros de validação", response = ValidationApiError.class),
            @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    public EntityModel<Cargo> atualizar(CargoDTO cargoDTO, Long id);

    @ApiOperation(value = "Excluir Cargo")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cargo excluído com sucesso"),
            @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiErro.class)
    })
    public ResponseEntity<?> excluirPorId(Long id);
}
