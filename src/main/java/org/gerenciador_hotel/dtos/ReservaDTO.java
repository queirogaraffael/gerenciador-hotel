package org.gerenciador_hotel.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gerenciador_hotel.enums.TipoQuarto;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO implements Comparable<ReservaDTO> {

    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private TipoQuarto tipoQuarto;


    @Override
    public String toString() {
        return id + " - " + dataEntrada + " - " + dataSaida + " - " + tipoQuarto;
    }

    @Override
    public int compareTo(ReservaDTO other) {
        return other.getDataEntrada().compareTo(this.dataEntrada);
    }
}
