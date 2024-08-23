package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.Mapper.AlunoMapper;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Repository.AlunoRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Validated
@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoMapper alunoMapper, AlunoRepository alunoRepository) {
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO findById(@Positive @NotNull Long id){
        return alunoRepository.findById(id).map(alunoMapper::convertToDto).orElseThrow(()-> new NotFoundObject(id));
    }


}
