package org.unifacisa.model.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String estado;


    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private Pessoa pessoa;


    @Override
    public String toString() {
        return  "Rua: " + getRua() + " \n" +
                "Numero: " + getNumero() + "\n" +
                "Cidade: " + getCidade() + " \n" +
                "Bairro: " + getBairro() + " \n" +
                "Estado: " + getEstado();
    }
}
