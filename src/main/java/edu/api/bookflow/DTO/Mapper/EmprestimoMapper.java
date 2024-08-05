package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.Model.Emprestimo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public EmprestimoDTO convertToDto(Emprestimo model) {
        if (model == null) {
            return null;
        }
        return new EmprestimoDTO(
                model.getCodEmprestimo(),
                model.getCancelado(),
                model.getCodLivro(),
                model.getCodAluno(),
                model.getCodCurso(),
                model.getCodRespEmprestimo(),
                model.getCodRespDevolucao(),
                model.getDataEmprestimo(),
                model.getDataDevolucao(),
                model.getAtrasado(),
                model.getObservacao()
        );
    }

    public Emprestimo convertToEntity(EmprestimoDTO dto) {
        if (dto == null) {
            return null;
        }
        Emprestimo model = new Emprestimo();
        if (dto.codEmprestimo() != null) {
            model.setCodEmprestimo(dto.codEmprestimo());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
