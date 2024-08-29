package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unifacisa.enums.TipoQuarto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {

    private int numeroQuarto;

    @Override
    public String toString() {
        return String.valueOf(getNumeroQuarto());
    }
}

