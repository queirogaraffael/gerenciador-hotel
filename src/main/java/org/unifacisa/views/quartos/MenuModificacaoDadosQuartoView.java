package org.unifacisa.views.quartos;

import javax.swing.*;

public class MenuModificacaoDadosQuartoView {

    private static final Object[] opcoesModificar = {"Tipo", "Preco", "Capacidade", "Voltar"};

    private MenuModificacaoDadosQuartoView() {
    }

    public static int exibeOpcoesModificarDadosView() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao: ",
                "Modificar dados do quarto",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesModificar,
                opcoesModificar[0]
        );

    }


}
