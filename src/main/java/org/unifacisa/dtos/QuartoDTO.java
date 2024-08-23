package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unifacisa.enums.TipoQuarto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuartoDTO {

    private String numeroQuarto;
    private TipoQuarto tipoQuarto;

    @Override
    public String toString() {
        return numeroQuarto + " - " + tipoQuarto;
    }
}
