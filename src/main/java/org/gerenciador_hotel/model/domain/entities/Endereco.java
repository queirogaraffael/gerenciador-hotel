package org.gerenciador_hotel.model.domain.entities;


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
        return "Rua: " + (rua != null ? rua : "Não informado") + " \n" +
                "Numero: " + (numero != null ? numero : "Não informado") + "\n" +
                "Cidade: " + (cidade != null ? cidade : "Não informado") + " \n" +
                "Bairro: " + (bairro != null ? bairro : "Não informado") + " \n" +
                "Estado: " + (estado != null ? estado : "Não informado");
    }
}
