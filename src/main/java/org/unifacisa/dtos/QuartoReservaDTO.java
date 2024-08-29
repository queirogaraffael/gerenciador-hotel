package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoReservaDTO {
    private Long idReserva;
    private int numeroQuarto;

    @Override
    public String toString() {
        return String.valueOf(getNumeroQuarto());
    }
}
