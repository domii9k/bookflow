package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.CategoriaDTO;
import edu.api.bookflow.DTO.Mapper.CategoriaMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Categoria;
import edu.api.bookflow.Repository.CategoriaRespository;
import edu.api.bookflow.Services.patchHttpRequest.GlobalPatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@Valid
public class CategoriaService {

    @Autowired
    private CategoriaRespository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    private GlobalPatch patcher;

    public PaginationDTO<CategoriaDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                               @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                               @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                               @RequestParam(value = "sortBy", defaultValue = "descricao") String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Categoria> page = categoriaRepository.findByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<CategoriaDTO> list = page.stream().map(categoriaMapper::convertToDto).toList();
        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public CategoriaDTO findById(@Positive Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public CategoriaDTO create(@Valid CategoriaDTO categoriaDTO) {
        return categoriaMapper.convertToDto(categoriaRepository.save(categoriaMapper.convertToEntity(categoriaDTO)));
    }

    public CategoriaDTO update(@Positive Long id, CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(categoriaDTO, registroEncontrado);
            return categoriaMapper.convertToDto(categoriaRepository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public CategoriaDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        GlobalPatch.globalPatch(fields, categoria);
        return categoriaMapper.convertToDto(categoriaRepository.save(categoria));
    }

    public void delete(@Positive Long id){
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> ativarCategoria(@Positive Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (!categoria.getStatus()) {
            categoria.setStatus(true);
            categoriaRepository.save(categoria);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Categoria ativada com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "");
        }
    }

    public ResponseEntity<Object> desativarCategoria(@Positive Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (categoria.getStatus()) {
            categoria.setStatus(false);
            categoriaRepository.save(categoria);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Categoria desativada com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "");
        }
    }
}
