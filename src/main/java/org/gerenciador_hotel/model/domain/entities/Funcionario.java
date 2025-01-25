package org.gerenciador_hotel.model.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.gerenciador_hotel.enums.Turno;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario extends Pessoa {

    @Column(unique = true)
    private String cpf;

    private String cargo;
    private Turno turno;

    private Double salario;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExtratoFuncionario> extratoFuncionario = new HashSet<>();

    @Override
    public String toString() {
        return "Funcionario: \n\n" +
                "Nome: " + getNome() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "Data nascimento: " + getDataNascimento().toString() + "\n" +
                "Cargo : " + getCargo() + "\n" +
                "Turno : " + getTurno() + "\n" +
                "Telefone: " + getNumeroTelefone() + "\n\n" +
                "Endereco : \n\n" + getEndereco().toString();
    }

}
