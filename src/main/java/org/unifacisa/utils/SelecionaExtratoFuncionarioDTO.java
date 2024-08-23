package org.unifacisa.utils;

import org.unifacisa.dtos.ExtratoFuncionarioDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.views.commons.ExibirDTOsViews;

import java.util.List;

public class SelecionaExtratoFuncionarioDTO {

    private SelecionaExtratoFuncionarioDTO() {
    }

    public static Long selecionaExtratoFuncionario(List<ExtratoFuncionarioDTO> extratoFuncionarioDTOList) {

        Object[] opcoes = converterExtratosParaArray(extratoFuncionarioDTOList);
        String extratoSelecionado = ExibirDTOsViews.exibeESelecionaExtratoDTOView(opcoes);


        ExtratoFuncionarioDTO extratoFuncionarioDTO = buscaExtratoById(extratoFuncionarioDTOList, extratoSelecionado);

        if (extratoFuncionarioDTO != null) {
            return extratoFuncionarioDTO.getId();
        } else {
            GlobalExceptionHandler.handleNoResultException("Extrato nao encontrado.");
            return null;
        }
    }


    private static Object[] converterExtratosParaArray(List<ExtratoFuncionarioDTO> extratoFuncionarioDTOList) {
        return extratoFuncionarioDTOList.stream().map(ExtratoFuncionarioDTO::toString).toArray(Object[]::new);
    }


    public static ExtratoFuncionarioDTO buscaExtratoById(List<ExtratoFuncionarioDTO> extratoFuncionarioDTOList, String extratoSelecionado) {

        for (ExtratoFuncionarioDTO extrato : extratoFuncionarioDTOList) {
            if (extrato != null && extrato.getId() != null && extrato.getMesReferente().toString().equals(extratoSelecionado)) {
                return extrato;
            }
        }
        return null;
    }


}
