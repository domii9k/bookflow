package edu.api.bookflow.DTO.Pagination;


import java.util.List;

/*
 * Classe genérica para paginação de todas as tabelas do banco
 * */
public record PaginationDTO<T>(List<T> lista, Long totalRegistros, int totalPaginas) {
}
