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
                model.getRespEmprestimo(),
                model.getDataEmprestimo(),
                model.getDataPrevistaDevolucao(),
                model.getAtrasado(),
                model.getFoiDevolvido(),
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
        model.setCancelado(dto.isCancelado());
        model.setCodLivro(dto.codLivro());
        model.setCodAluno(dto.codAluno());
        model.setRespEmprestimo(dto.respEmprestimo());
        model.setDataEmprestimo(dto.dataEmprestimo());
        model.setDataPrevistaDevolucao(dto.dataPrevDevolucao());
        model.setAtrasado(dto.isAtrasado());
        model.setFoiDevolvido(dto.foiDevolvido());
        model.setObservacao(dto.observacao());

        return model;
    }
}
