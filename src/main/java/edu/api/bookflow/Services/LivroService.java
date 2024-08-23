package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.Mapper.LivroMapper;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }



    public void validaLivroOnCreateEmprestimo(EmprestimoDTO emprestimoDTO){
        Long codLivro = emprestimoDTO.codLivro().getCodLivro();
        Livro livro = livroRepository.findById(codLivro).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        if (livro.isSttsEmprestado()){
            throw new IllegalStateException("O livro de código "+codLivro+" já está associado a outro empréstimo!");
        }
        livro.setSttsEmprestado(true);
        livroRepository.save(livro);
    }
}
