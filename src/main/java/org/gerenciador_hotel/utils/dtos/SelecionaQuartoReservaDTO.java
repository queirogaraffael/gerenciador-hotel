package org.gerenciador_hotel.utils.dtos;

import org.gerenciador_hotel.dtos.QuartoReservaDTO;
import org.gerenciador_hotel.exceptions.GlobalExceptionHandler;
import org.gerenciador_hotel.views.commons.ExibirDTOsViews;

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
