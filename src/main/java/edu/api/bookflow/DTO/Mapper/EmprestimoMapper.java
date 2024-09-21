package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.AlunoFiltradoDTO;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.UsuarioFiltradoDTO;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public EmprestimoDTO convertToDto(Emprestimo model) {
        if (model == null) {
            return null;
        }
        AlunoFiltradoDTO alunoFiltradoDTO = new AlunoFiltradoDTO(model.getCodAluno().getCodAluno(), model.getCodAluno().getNomeCompleto(), model.getCodAluno().getRa(), model.getCodAluno().getCodCurso(), model.getCodAluno().getEmail(), model.getCodAluno().getTel());
        UsuarioFiltradoDTO usuarioFiltradoDTO = new UsuarioFiltradoDTO(model.getRespEmprestimo().getCodUsuario(), model.getRespEmprestimo().getNome(), model.getRespEmprestimo().getSobrenome(), model.getRespEmprestimo().getPermissao());
        return new EmprestimoDTO(
                model.getCodEmprestimo(),
                model.getCancelado(),
                model.getCodLivro(),
                alunoFiltradoDTO,
                usuarioFiltradoDTO,
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


        Aluno aluno = new Aluno();
        Usuario respEmprestimo = new Usuario();
        Emprestimo model = new Emprestimo();
        if (dto.codEmprestimo() != null) {
            model.setCodEmprestimo(dto.codEmprestimo());
        }
        model.setCancelado(dto.isCancelado());
        model.setCodLivro(dto.codLivro());
        model.setCodAluno(aluno);
        model.setRespEmprestimo(respEmprestimo);
        model.setDataEmprestimo(dto.dataEmprestimo());
        model.setDataPrevistaDevolucao(dto.dataPrevDevolucao());
        model.setAtrasado(dto.isAtrasado());
        model.setFoiDevolvido(dto.foiDevolvido());
        model.setObservacao(dto.observacao());

        return model;
    }
}
