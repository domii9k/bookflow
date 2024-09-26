package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.EditoraDTO;
import edu.api.bookflow.DTO.Mapper.EditoraMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Editora;
import edu.api.bookflow.Repository.EditoraRepository;
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
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;
    @Autowired
    private EditoraMapper editoraMapper;
    @Autowired
    private GlobalPatch patcher;

    public PaginationDTO<EditoraDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                             @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                             @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                             @RequestParam(value = "sortBy", defaultValue = "nomeFantasia") String sortBy,
                                             @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Editora> page = editoraRepository.findByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<EditoraDTO> list = page.stream().map(editoraMapper::convertToDto).toList();
        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public EditoraDTO findById(@Positive Long id) {
        return editoraRepository.findById(id).map(editoraMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public EditoraDTO create(@Valid EditoraDTO editoraDTO) {
        return editoraMapper.convertToDto(editoraRepository.save(editoraMapper.convertToEntity(editoraDTO)));
    }

    public EditoraDTO update(@Positive Long id, EditoraDTO editoraDTO) {
        return editoraRepository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(editoraDTO, registroEncontrado);
            return editoraMapper.convertToDto(editoraRepository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public EditoraDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Editora editora = editoraRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        GlobalPatch.globalPatch(fields, editora);
        return editoraMapper.convertToDto(editoraRepository.save(editora));
    }

    public void delete(@Positive Long id){
        editoraRepository.delete(editoraRepository.findById(id).orElseThrow(()-> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> ativaEditora(@Positive Long id){
        Editora editora = editoraRepository.findById(id).orElseThrow(()->new NotFoundObject(id));

        if (!editora.getStatus()) { // ativar editora somente se estiver desativado
            editora.setStatus(true); // ativa editora
            editoraRepository.save(editora);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Editora ativada com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "");
        }
    }

    public ResponseEntity<Object> desativaEditora(@Positive Long id){
        Editora editora = editoraRepository.findById(id).orElseThrow(()->new NotFoundObject(id));

        if (editora.getStatus()) {
            editora.setStatus(false);
            editoraRepository.save(editora);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Editora desativada com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "");
        }
    }


}
