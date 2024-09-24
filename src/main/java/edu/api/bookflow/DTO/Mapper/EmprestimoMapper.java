package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.AlunoFiltradoDTO;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.UsuarioFiltradoDTO;
import edu.api.bookflow.Model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public EmprestimoDTO convertToDto(Emprestimo model) {
        if (model == null) {
            return null;
        }

        AlunoFiltradoDTO alunoFiltradoDTO = new AlunoFiltradoDTO(
                model.getCodAluno().getCodAluno(),
                model.getCodAluno().getNomeCompleto(),
                model.getCodAluno().getRa(),
                model.getCodAluno().getCodCurso(),
                model.getCodAluno().getEmail(),
                model.getCodAluno().getTel());

        UsuarioFiltradoDTO respEmprestimo = new UsuarioFiltradoDTO(
                model.getRespEmprestimo().getCodUsuario(),
                model.getRespEmprestimo().getNome(),
                model.getRespEmprestimo().getSobrenome(),
                model.getRespEmprestimo().getPermissao());

        UsuarioFiltradoDTO respDevolucao = null;
        if (model.getRespDevolucao() != null) {
            respDevolucao = new UsuarioFiltradoDTO(
                    model.getRespDevolucao().getCodUsuario(),
                    model.getRespDevolucao().getNome(),
                    model.getRespDevolucao().getSobrenome(),
                    model.getRespDevolucao().getPermissao()
            );
        }

        return new EmprestimoDTO(
                model.getCodEmprestimo(),
                model.getCancelado(),
                model.getCodLivro(),
                alunoFiltradoDTO,
                respEmprestimo,
                respDevolucao,
                model.getDataEmprestimo(),
                model.getDataPrevistaDevolucao(),
                model.getDataDevolucao(),
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

        model.setCodLivro(dto.codLivro());
        model.setDataEmprestimo(dto.dataEmprestimo());
        model.setDataPrevistaDevolucao(dto.dataPrevDevolucao());
        model.setAtrasado(dto.isAtrasado());
        model.setFoiDevolvido(dto.foiDevolvido());
        model.setObservacao(dto.observacao());
        model.setCancelado(dto.isCancelado());

        return model;
    }
}
