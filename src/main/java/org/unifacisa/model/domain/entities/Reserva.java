package org.unifacisa.model.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.unifacisa.enums.StatusReserva;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    private int numeroHospedes;

    private StatusReserva statusReserva;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;


    @Override
    public String toString() {
        return "Reserva: \n\n" +
                "Data entrada: " + getDataEntrada().toString() + "\n" +
                "Data saida: " +getDataSaida().toString() + "\n" +
                "Status da Reserva: \n\n" + getStatusReserva() + "\n";

    }


}
