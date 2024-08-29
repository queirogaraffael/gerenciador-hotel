package org.unifacisa.dtos.utils;

import org.unifacisa.dtos.QuartoReservaDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.views.commons.ExibirDTOsViews;

import java.util.List;

public class SelecionaQuartoReservaDTO {

    private SelecionaQuartoReservaDTO() {
    }

    public static Long selecionaIdReservaQuartoReserva(List<QuartoReservaDTO> quartosReservasDTO) {

        Object[] opcoes = converterQuartosReservasParaArray(quartosReservasDTO);

        String quartoReservaSelecionado = ExibirDTOsViews.exibirQuartosReservasDTOsView(opcoes);

        QuartoReservaDTO quartoReserva = buscaQuartoReservaByNumeroQuarto(quartosReservasDTO, quartoReservaSelecionado);

        if (quartoReserva != null) {
            return quartoReserva.getIdReserva();
        } else {
            GlobalExceptionHandler.handleNoResultException("Reserva nao encontrada.");
            return null;
        }
    }


    private static Object[] converterQuartosReservasParaArray(List<QuartoReservaDTO> quartosReservasDTO) {
        return quartosReservasDTO.stream()
                .map(QuartoReservaDTO::toString)
                .toArray(Object[]::new);
    }


    public static QuartoReservaDTO buscaQuartoReservaByNumeroQuarto(List<QuartoReservaDTO> quartosReservasDTO, String quartoReservaSelecionado) {

        if (quartoReservaSelecionado == null) {
            return null;
        }

        int numeroQuarto = Integer.parseInt(quartoReservaSelecionado);

        for (QuartoReservaDTO quartoreservaDTO : quartosReservasDTO) {
            if (quartoreservaDTO != null && quartoreservaDTO.getNumeroQuarto() == numeroQuarto) {
                return quartoreservaDTO;
            }
        }
        return null;
    }
}
