package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unifacisa.enums.TipoQuarto;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private TipoQuarto tipoQuarto;


    @Override
    public String toString() {
        return id + " - " + dataEntrada + " - " + dataSaida + " - " + tipoQuarto;
    }
}
