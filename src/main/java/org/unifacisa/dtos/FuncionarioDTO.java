package org.unifacisa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;
    private String cpf;
    private String nome;


    @Override
    public String toString() {
        return cpf + " - " + nome;
    }
}
