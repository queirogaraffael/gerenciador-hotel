package org.unifacisa.dtos.utils;

import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.views.commons.ExibirDTOsViews;

import java.util.List;

public class SelecionaQuartoDTO {

    private SelecionaQuartoDTO() {
    }

    public static int selecionaNumeroQuarto(List<QuartoDTO> quartosDTO) {

        Object[] opcoes = converterQuartosParaArray(quartosDTO);

        String quartoSelecionado = ExibirDTOsViews.exibirQuartosDTOsView(opcoes);

        QuartoDTO quarto = buscaQuartoByNumero(quartosDTO, quartoSelecionado);

        if (quarto != null) {
            return quarto.getNumeroQuarto();
        } else {
            GlobalExceptionHandler.handleNoResultException("Quarto nao encontrado.");
            return 0;
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

        int numeroQuarto = Integer.parseInt(quartoSelecionado);

        for (QuartoDTO quartoDTO : quartosDTO) {
            if (quartoDTO != null && quartoDTO.getNumeroQuarto() == numeroQuarto) {
                return quartoDTO;
            }
        }
        return null;
    }
}
