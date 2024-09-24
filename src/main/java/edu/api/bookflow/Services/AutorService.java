package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.AutorDTO;
import edu.api.bookflow.DTO.Mapper.AutorMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Autor;
import edu.api.bookflow.Repository.AutorRepository;
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
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    public PaginationDTO<AutorDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                           @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                           @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                           @RequestParam(value = "sortBy", defaultValue = "nome") String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Autor> page = autorRepository.findByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<AutorDTO> list = page.stream().map(autorMapper::convertToDto).toList();
        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public AutorDTO findById(@Positive Long id) {
        return autorRepository.findById(id).map(autorMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public AutorDTO create(@Valid AutorDTO autorDTO) {
        Autor autor = new Autor();
        return autorMapper.convertToDto(autorRepository.save(autorMapper.convertToEntity(autorDTO)));
    }

    public AutorDTO update(@Positive Long id, AutorDTO autorDTO) {
        return autorRepository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(autorDTO, registroEncontrado);
            return autorMapper.convertToDto(autorRepository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public AutorDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        GlobalPatch.globalPatch(fields, autor);
        return autorMapper.convertToDto(autorRepository.save(autor));
    }

    public void delete(@Positive Long id) {
        autorRepository.delete(autorRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> ativarAutor(@Positive Long id) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (!autor.getStatus()) {
            autor.setStatus(true);
            autorRepository.save(autor);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Autor ativado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "");
        }
    }

    public ResponseEntity<Object> desativarAutor(@Positive Long id) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (autor.getStatus()) {
            autor.setStatus(false);
            autorRepository.save(autor);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Autor desativado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "");
        }
    }
}
