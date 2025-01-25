package org.gerenciador_hotel.ui.funcionarios;

import org.gerenciador_hotel.dtos.funcionario.FuncionarioCreateDTO;
import org.gerenciador_hotel.model.domain.entities.Funcionario;

import javax.swing.*;

public class PrintaFuncionarioView {

    private PrintaFuncionarioView() {
    }

    public static void exibeFuncionario(Funcionario funcionario) {
        JOptionPane.showMessageDialog(null, funcionario);
    }

    public static void exibeFuncionario(FuncionarioCreateDTO funcionario) {
        StringBuilder mensagem = new StringBuilder();

        mensagem.append("CPF: ").append(funcionario.cpf()).append("\n")
                .append("Nome: ").append(funcionario.nome()).append("\n")
                .append("Data de Nascimento: ").append(funcionario.dataNascimento()).append("\n")
                .append("Número de Telefone: ").append(funcionario.numeroTelefone()).append("\n")
                .append("Cargo: ").append(funcionario.cargo()).append("\n")
                .append("Turno: ").append(funcionario.turno()).append("\n")
                .append("Salário: R$ ").append(String.format("%.2f", funcionario.salario())).append("\n");

        mensagem.append("\nEndereço:\n").append(funcionario.endereco().toString());

        JOptionPane.showMessageDialog(null, mensagem.toString(), "Detalhes do Funcionário", JOptionPane.INFORMATION_MESSAGE);
    }

}
