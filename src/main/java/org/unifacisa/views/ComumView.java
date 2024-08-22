package org.unifacisa.views;

import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.exceptions.GlobalExceptionHandler;

import javax.swing.*;

public class ComumView {

    private static final Object[] opcoes = {"Solteiro", "Casal", "Suite"};

    public static TipoQuarto exibeEEscolheViewTipoQuarto() {
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha tipo quarto:",
                "Menu escolha",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == 0) {
            return TipoQuarto.SOLTEIRO;
        } else if (escolha == 1) {
            return TipoQuarto.CASAL;
        } else {
            return TipoQuarto.SUITE;
        }

    }

    public static String exibeESelecionaExtratoDTOView(Object[] options) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um extrato: ",
                "Extratos",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        return opcaoSelecionada.toString();
    }


    public static String exibirFuncionariosDTOsView(Object[] opcoes) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um funcionario: ",
                "Menu escolha",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        return opcaoSelecionada.toString();
    }
}
