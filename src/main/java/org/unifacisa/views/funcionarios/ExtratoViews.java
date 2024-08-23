package org.unifacisa.views.funcionarios;

import org.unifacisa.model.domain.entities.ExtratoFuncionario;

import javax.swing.*;

public class ExtratoViews {

    private static final  Object[] opcoes = {"Visualizar", "Adicionar", "Voltar"};

    private ExtratoViews() {
    }

    public static int exibeEEscolheOpcaoExtratoView() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao:",
                "Menu escolha",
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
