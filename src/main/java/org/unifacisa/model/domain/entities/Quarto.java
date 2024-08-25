package org.unifacisa.model.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.unifacisa.enums.TipoQuarto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true)
    private String numeroQuarto;

    private TipoQuarto tipoQuarto;
    private int capacidade;
    private double precoDiaria;


    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas = new HashSet<>();


    @Override
    public String toString() {
        return "Quarto " + getNumeroQuarto() + "\n" +
                "Tipo Quarto: " + getTipoQuarto() + "\n" +
                "Capacidade: " + getCapacidade() + "\n" +
                "Diaria : " + getPrecoDiaria();
    }
}
