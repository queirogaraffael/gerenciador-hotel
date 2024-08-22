package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoFuncionarioDTO {

    private Long id;
    private YearMonth mesReferente;


    @Override
    public String toString() {
        return mesReferente.toString();
    }
}
