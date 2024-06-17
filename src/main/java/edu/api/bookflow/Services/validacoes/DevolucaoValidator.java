package edu.api.bookflow.Services.validacoes;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DevolucaoValidator {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private int prazoDevolucao;

    public DevolucaoValidator(LocalDate dataEmprestimo, LocalDate dataDevolucao, int prazoDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.prazoDevolucao = prazoDevolucao;
    }

    public boolean isAtrasada() {
        LocalDate dataLimiteDevolucao = dataEmprestimo.plusDays(prazoDevolucao);
        return dataDevolucao.isAfter(dataLimiteDevolucao);
    }

    public static void main(String[] args) {
        // Exemplo de uso
        LocalDate dataEmprestimo = LocalDate.of(2023, 6, 1);
        LocalDate dataDevolucao = LocalDate.of(2023, 6, 10);
        int prazoDevolucao = 7; // 7 dias de prazo

        DevolucaoValidator validator = new DevolucaoValidator(dataEmprestimo, dataDevolucao, prazoDevolucao);
        if (validator.isAtrasada()) {
            System.out.println("A devolução está atrasada.");
        } else {
            System.out.println("A devolução está dentro do prazo.");
        }
    }
}
