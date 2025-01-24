package org.gerenciador_hotel.dtos.utils;

import org.gerenciador_hotel.dtos.FuncionarioDTO;
import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.views.commons.ExibirDTOsViews;

import java.util.List;

public class SelecionaFuncionarioDTO {

    public static String selecionaCPFFuncionario(List<FuncionarioDTO> funcionarios) {
        Object[] opcoes = converterFuncionarioParaArray(funcionarios);
        String funcionarioSelecionado = ExibirDTOsViews.exibirFuncionariosDTOsView(opcoes);


        FuncionarioDTO funcionario = buscaFuncionarioByCPF(funcionarios, funcionarioSelecionado);

        if (funcionario != null) {
            return funcionario.getCpf();
        } else {
            GlobalExceptionHandler.handleNoResultException("Funcionario não encontrado.");
            return null;
        }
    }


    private static Object[] converterFuncionarioParaArray(List<FuncionarioDTO> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioDTO::toString)
                .toArray(Object[]::new);
    }


    public static FuncionarioDTO buscaFuncionarioByCPF(List<FuncionarioDTO> funcionarios, String toString) {
        if (toString == null) {
            return null;
        }

        String cpf = toString.split(" - ")[0].trim();


        for (FuncionarioDTO funcionarioDTO : funcionarios) {
            if (funcionarioDTO != null && funcionarioDTO.getCpf() != null && funcionarioDTO.getCpf().equals(cpf)) {
                return funcionarioDTO;
            }
        }
        return null;
    }

}
