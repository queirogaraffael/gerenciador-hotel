package org.unifacisa.views.commons;

import javax.swing.*;

public class ExibirDTOsViews {

    private ExibirDTOsViews() {
    }

    public static String exibirQuartosDTOsView(Object[] opcoes) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um quarto: ",
                "Quartos",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        return opcaoSelecionada.toString();
    }

    public static String exibirFuncionariosDTOsView(Object[] opcoes) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um funcionario: ",
                "Funcionarios",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        return opcaoSelecionada.toString();
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


    public static String exibeESelecionaHospedeDTOView(Object[] options) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um hospede: ",
                "Hospedes",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        return opcaoSelecionada.toString();
    }

    public static String exibeESelecionaReservaDTOView(Object[] options) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha uma reserva: ",
                "Reservas",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        return opcaoSelecionada.toString();
    }

    public static String exibirQuartosReservasDTOsView(Object[] options) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha um quarto em manutencao: ",
                "Quartos em Manutencao",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        return opcaoSelecionada.toString();
    }
}
