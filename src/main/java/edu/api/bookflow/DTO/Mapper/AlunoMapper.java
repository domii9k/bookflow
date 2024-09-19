package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.Model.Aluno;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public AlunoDTO convertToDto(Aluno aluno) {
        if (aluno == null) {
            return null;
        }
        return new AlunoDTO(
                aluno.getCodAluno(),
                aluno.getNomeCompleto(),
                aluno.getRa(),
                aluno.getCpf(),
                aluno.getCodCurso(),
                aluno.getEmail(),
                aluno.getTel(),
                aluno.getTel2(),
                aluno.getStatus(),
                aluno.getCep(),
                aluno.getEndereco()
        );
    }

    public Aluno convertToEntity(AlunoDTO dto) {
        if (dto == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        if (dto.codAluno() != null) {
            aluno.setCodAluno(dto.codAluno());
        }
        BeanUtils.copyProperties(dto, aluno);
        return aluno;
    }
}
