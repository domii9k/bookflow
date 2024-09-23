package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Livro;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    Page<Livro> findByStatus(@Param("status") Boolean status, Pageable pageable);
    List<Livro> findByIsbn(String isbn);
    List<Livro> findByPatrimonio(String patrimonio);
}
