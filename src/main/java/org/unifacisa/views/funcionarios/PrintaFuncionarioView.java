package org.unifacisa.views.funcionarios;

import org.unifacisa.model.domain.entities.Funcionario;

import javax.swing.*;

public class PrintaFuncionarioView {

    private PrintaFuncionarioView() {
    }

    public static void exibeFuncionario(Funcionario funcionario) {
        JOptionPane.showMessageDialog(null, funcionario);
    }
}
