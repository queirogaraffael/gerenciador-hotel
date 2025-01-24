package org.gerenciador_hotel.views.funcionarios;

import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;

import javax.swing.*;

public class ExtratoViews {

    private static final String MENU_TITLE = "Extratos";
    private static final String MENU_MESSAGE = "Escolha uma opcao:";

    private static final  Object[] opcoes = {"Visualizar", "Adicionar", "Voltar"};

    private ExtratoViews() {
    }

    public static int exibeEEscolheOpcaoExtratoView() {
        return JOptionPane.showOptionDialog(
                null,
                MENU_MESSAGE,
                MENU_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );


    }

    public static void exibeExtratoFuncionario(ExtratoFuncionario extratoFuncionario) {
        JOptionPane.showMessageDialog(null, extratoFuncionario);
    }

}
