package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.EditoraDTO;
import edu.api.bookflow.DTO.LivroDTO;
import edu.api.bookflow.DTO.Mapper.LivroMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Editora;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Repository.LivroRepository;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Validated
@Service
public class LivroService {

    @Autowired
    private static LivroRepository livroRepository;
    @Autowired
    private static LivroMapper livroMapper;
    @Autowired
    private EditoraService editoraService;
    @Autowired
    private GlobalPatch patcher;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public PaginationDTO<LivroDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                           @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                           @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                           @RequestParam(value = "sortBy", defaultValue = "titulo") String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Livro> page = livroRepository.findByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<LivroDTO> list = page.stream().map(livroMapper::convertToDto).toList();
        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public LivroDTO findById(@Positive Long id) {
        return livroRepository.findById(id).map(livroMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public LivroDTO create(@Valid LivroDTO livroDTO) {
        validaPatrimonioEIsbn(livroMapper.convertToEntity(livroDTO));
        return livroMapper.convertToDto(livroRepository.save(livroMapper.convertToEntity(livroDTO)));
    }

    public LivroDTO update(@Positive Long id, @Valid LivroDTO livroDTO) {
        return livroRepository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(livroDTO, registroEncontrado);
            return livroMapper.convertToDto(livroRepository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public LivroDTO updatePatch(@Positive Long id, @Valid Map<String, Object> fields) {
        Livro livroExistente = livroRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        patcher.globalPatch(fields, livroExistente);
        //Editora editora = editoraService.findById();
        return livroMapper.convertToDto(livroRepository.save(livroExistente));
    }

    public void delete(@Positive Long id) {
        livroRepository.delete(livroRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> desativarLivro(@Positive Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (!livro.getSttsEmprestado()) { // só pode ser desativado se não estiver emprestado
            livro.setStatus(false); // desativa livro
            livroRepository.save(livro);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Livro desativado com sucesso!");
        } else {
            throw new IllegalStateException("Não é possível desativar um livro que está emprestado.");
        }
    }

    public ResponseEntity<Object> ativarLivro(@Positive Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (!livro.getStatus()) { // ativar livro somente se estiver desativado
            livro.setStatus(true); // ativa livro
            livroRepository.save(livro);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Livro ativado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "");
        }
    }

    private static void validaPatrimonioEIsbn(Livro livro) {
        String patrimonio = livro.getPatrimonio();
        String isbn = livro.getIsbn();
        List<Livro> livrosPatrimonio = livroRepository.findByPatrimonio(patrimonio);
        List<Livro> livrosIsbn = livroRepository.findByIsbn(isbn);

        if (!livrosPatrimonio.isEmpty()) {
            throw new IllegalStateException("Já existe um livro cadastrado com o patrimônio: " + patrimonio);
        }
        if (!livrosIsbn.isEmpty()) {
            throw new IllegalStateException("Já existe um livro cadastrado com o ISBN: " + isbn);
        }
    }

}
