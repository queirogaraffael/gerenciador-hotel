package org.unifacisa.utils;

import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.exceptions.GlobalExceptionHandler;
import org.unifacisa.views.commons.ExibirDTOsViews;

import java.util.List;

public class SelecionaReservaDTO {

    private SelecionaReservaDTO() {
    }

    public static Long selecionaReserva(List<ReservaDTO> reservaDTOList) {

        Object[] opcoes = converterReservasParaArray(reservaDTOList);

        String reservaSelecionada = ExibirDTOsViews.exibeESelecionaReservaDTOView(opcoes);


       ReservaDTO reservaDTO = buscaReservaById(reservaDTOList, reservaSelecionada);

        if (reservaDTO != null) {
            return reservaDTO.getId();
        } else {
            GlobalExceptionHandler.handleNoResultException("Extrato nao encontrado.");
            return null;
        }
    }


    private static Object[] converterReservasParaArray(List<ReservaDTO> reservaDTOList) {
        return reservaDTOList.stream().map(ReservaDTO::toString).toArray(Object[]::new);
    }


    public static ReservaDTO  buscaReservaById(List<ReservaDTO> reservaDTOList, String reservaSelecionada) {

        long id =  Long.parseLong(reservaSelecionada.split(" - ")[0].trim());

        for (ReservaDTO reservaDTO : reservaDTOList) {
            if (reservaDTO != null && reservaDTO.getId() != null && reservaDTO.getId() == id) {
                return reservaDTO;
            }
        }
        return null;
    }

}
