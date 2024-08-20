package org.unifacisa.model.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Hospede extends Pessoa {


    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoricoHospede> historicos = new HashSet<>();


}
