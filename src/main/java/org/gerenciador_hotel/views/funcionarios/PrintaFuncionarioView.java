package org.gerenciador_hotel.views.funcionarios;

import org.gerenciador_hotel.model.domain.entities.Funcionario;

import javax.swing.*;

public class PrintaFuncionarioView {

    private PrintaFuncionarioView() {
    }

    public static void exibeFuncionario(Funcionario funcionario) {
        JOptionPane.showMessageDialog(null, funcionario);
    }
}
