package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Emprestimo;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

    //metodo personalizado para buscar emprestimos de um aluno
    List<Emprestimo> findByCodAluno_CodAluno(Long codAluno);

    // Método personalizado para buscar empréstimos ativos de um livro
    List<Emprestimo> findByCodLivro_CodLivro(Long codLivro);

    @Query("SELECT e FROM Emprestimo e WHERE e.cancelado = ?1 AND e.foiDevolvido = ?2 AND e.atrasado = ?3")
    Page<Emprestimo> findAllByCanceladoAndAtrasado(@Param("cancelado") Boolean cancelado, @Param("foiDevolvido") Boolean foiDevolvido, @Param("atrasado") Boolean atrasado ,Pageable pageable);

}
