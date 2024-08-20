package org.unifacisa.model.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExtratoFuncionario {

    public static final String FORMATO_DATA = "MM/yyyy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private LocalDate mesReferente;

    private int horasTrabalhadas;
    private double valorHora;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
        return "Pagamento: \n" +
                "Mes referente: \n" + mesReferente.format(formatter) +
                "Horas trabalhadas: \n" + horasTrabalhadas +
                "Valor hora: \n" + valorHora +
                "Salario: " + salario;
    }

}
