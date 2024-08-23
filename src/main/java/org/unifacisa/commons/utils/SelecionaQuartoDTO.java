package org.unifacisa.commons.utils;

import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.views.common.ExibirDTOsViews;

import java.util.List;

public class SelecionaQuartoDTO {

    public static String selecionaNumeroQuarto(List<QuartoDTO> quartosDTO) {


        Object[] opcoes = converterQuartosParaArray(quartosDTO);

        String quartoSelecionado = ExibirDTOsViews.exibirQuartosDTOsView(opcoes);

        QuartoDTO quarto = buscaQuartoByNumero(quartosDTO, quartoSelecionado);

        if (quarto != null) {
            return quarto.getNumeroQuarto();
        } else {
            GlobalExceptionHandler.handleNoResultException("Quarto nao encontrado.");
            return null;
        }
    }


    private static Object[] converterQuartosParaArray(List<QuartoDTO> quartosDTO) {
        return quartosDTO.stream()
                .map(QuartoDTO::toString)
                .toArray(Object[]::new);
    }


    public static QuartoDTO buscaQuartoByNumero(List<QuartoDTO> quartosDTO, String quartoSelecionado) {

        if (quartoSelecionado == null) {
            return null;
        }

        String numeroQuarto = quartoSelecionado.split(" - ")[0].trim();


        for (QuartoDTO quartoDTO : quartosDTO) {
            if (quartoDTO != null && quartoDTO.getNumeroQuarto().equals(numeroQuarto)) {
                return quartoDTO;
            }
        }
        return null;
    }
}
